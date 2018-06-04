package todo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.beans.Todo;
import todo.utils.DBUtils;

@WebServlet("/update.html")
public class UpdateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		try {
			con = DBUtils.getConnection();

			// GETパラメータを取得
			String id = req.getParameter("id");

			// SQL
			sql = "SELECT id, title, detail, priority, limit_day FROM todolist WHERE id = ?";
			// SELECT命令の準備
			ps = con.prepareStatement(sql);
			//SELECT文の？に値をセット
			ps.setString(1, id);

			// 実行
			rs = ps.executeQuery();

			rs.next();
			Todo todo = new Todo(
					rs.getInt("id"),
					rs.getString("title"),
					rs.getString("detail"),
					rs.getInt("priority"),
					rs.getDate("limit_day"));

			req.setAttribute("todo", todo);

			getServletContext().getRequestDispatcher("/WEB-INF/update.jsp").forward(req, resp);

		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				DBUtils.close(con);
			} catch (Exception e) {

			}
		}
	}


	//エラーチェック
	private List<String> validate(String id, String title, String detail, String priority, String limitDay){
		List<String> errors = new ArrayList<String>();

		// idのエラーチェック（不正防止
		// ここいまStringしてるからな！！気をつけろよ！！
		if(id == null || id.equals("")) {
			errors.add("存在しないIDです。");
		}

		// 題名のエラーチェック
		if(title.equals("")) {
			errors.add("題名は必須入力です。");
		} else if(title.length() > 100) {
			errors.add("題名は100文字以内にして下さい。");
		}

		// 詳細のエラーチェック、確認必須ではないが、NOTNULLのため
		if(detail.equals("")) {
			errors.add("詳細は必須入力です。");
		}

		// 重要度のエラーチェック（不正防止
		if(id == null || !priority.equals("1") && !priority.equals("2") && !priority.equals("3")) {
			errors.add("不正なアクセスです。");
		}

		// 日付一致チェック
		if(!limitDay.equals("")) {
			try {
				LocalDate.parse(limitDay, DateTimeFormatter.ofPattern("uuuu/MM/dd")
						.withResolverStyle(ResolverStyle.STRICT));
			} catch (Exception p) {
				errors.add("期限は「YYYY/MM/DD」形式で入力して下さい。");
			}
		}

		return errors;
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		// バリデーションチェック
		UpdateServlet error = new UpdateServlet();
		List<String> errors = error.validate(
				req.getParameter("id"),
				req.getParameter("title"),
				req.getParameter("detail"),
				req.getParameter("priority"),
				req.getParameter("limitDay"));

		if(errors.size() > 0) {
			//errorリストに1つ以上、エラーが含まれていた場合
			req.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/update.jsp").forward(req, resp);
		} else {
			// エラーがなかった場合
			Connection con = null;
			PreparedStatement ps = null;
			String sql = null;
			try{
				con = DBUtils.getConnection();

				sql = "UPDATE todolist SET title = ?, detail = ?, priority = ?, limit_day = ? WHERE id = ?";

				// INSERT命令の準備
				ps = con.prepareStatement(sql);
				// ポストデータの内容をセット
				ps.setString(1, req.getParameter("title"));
				ps.setString(2, req.getParameter("detail"));
				ps.setString(3, req.getParameter("priority"));
				if(req.getParameter("limitDay").equals("")) {
					ps.setString(4, null);
				} else {
					ps.setString(4, req.getParameter("limitDay"));
				}
				ps.setString(5, req.getParameter("id"));

				// 命令を実行
				ps.executeUpdate();

			} catch(Exception e){

				throw new ServletException(e);

			} finally {

				try{
					DBUtils.close(con);
					if(ps != null){
						ps.close();
					}
				} catch(Exception e){

				}
			}

			resp.sendRedirect("index.html");
		}
	}
}
