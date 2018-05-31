package todo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.utils.DBUtils;

@WebServlet("/entry.html")
public class EntryServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		getServletContext().getRequestDispatcher("/WEB-INF/entry.jsp").forward(req, resp);
	}


	//エラーチェック
	private List<String> validate(String a, String b, String c){
		List<String> errors = new ArrayList<String>();

		// 題名のエラーチェック
		if(a == null || a.trim().isEmpty()) {
			errors.add("");
		} else if(a.length() > 100) {
			errors.add("");
		}

		// 詳細のエラーチェック
		if(b == null || b.trim().isEmpty()) {
			errors.add("");
		}

		// 問題児 日付一致チェック
		if(c != null) {
			try {
				DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
				df.setLenient(false);
				String s1 = c;
				String s2 = df.format(df.parse(s1));

//			    ここなくても正常に動作する気がする
//			    if (s1.equals(s2)) {
//
//			    } else {
//			    	System.out.println("D");
//			    	errors.add("期限は「YYYY/MM/DD」形式で入力して下さい");
//			    }
			} catch (Exception p) {
				p.printStackTrace();
				errors.add("");
			}
		}

		return errors;
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		EntryServlet error = new EntryServlet();
		List<String> errors = error.validate(req.getParameter("title"),
				req.getParameter("detail"),
				req.getParameter("limitDay"));

		if(errors.isEmpty()) {
			//errorリストにひとつもerrorがなかった場合、insert処理させる

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
				ps.setString(4, req.getParameter("limitDay"));
				// 命令を実行

				System.out.println(ps);
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

		} else {
			//errorリストに1つ以上、errorが含まれていた場合
			resp.sendRedirect("entry.html");
		}
	}
}
