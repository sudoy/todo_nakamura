package todo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javax.servlet.http.HttpSession;

import todo.utils.DBUtils;

@WebServlet("/entry.html")
public class EntryServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		getServletContext().getRequestDispatcher("/WEB-INF/entry.jsp").forward(req, resp);
	}


	//エラーチェック
	private List<String> validate(String title, String detail, String priority, String limitDay){
		List<String> errors = new ArrayList<String>();

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

		// 重要度のエラーチェック、先にint型にキャストする
		if(!priority.equals("1") && !priority.equals("2") && !priority.equals("3")) {
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
		HttpSession session = req.getSession();

		// バリデーションチェック
		EntryServlet error = new EntryServlet();
		List<String> errors = error.validate(req.getParameter("title"),
				req.getParameter("detail"),
				req.getParameter("priority"),
				req.getParameter("limitDay"));

		if(errors.size() > 0) {
			//errorリストに1つ以上、errorが含まれていた場合
			session.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/entry.jsp").forward(req, resp);
		} else {

			Connection con = null;
			PreparedStatement ps = null;
			String sql = null;
			try{
				// データベースの接続を確立
				con = DBUtils.getConnection();
				sql = "INSERT INTO todolist(title, detail, priority, limit_day) VALUES (?, ?, ?, ?)";

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
				// 命令を実行
				ps.executeUpdate();

				List<String> success = new ArrayList<>();
				success.add("追加しました。");
				session.setAttribute("success", success);

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
