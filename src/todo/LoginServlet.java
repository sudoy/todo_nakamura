package todo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import todo.beans.User;
import todo.utils.DBUtils;

@WebServlet("/login.html")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

		String email = req.getParameter("email");
		String pass = req.getParameter("pass");


		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		//バリデーションチェック
		LoginServlet error = new LoginServlet();
		List<String> errors = error.validate(email, pass);

		if(errors.size() == 0){

			try {
				con = DBUtils.getConnection();

				sql = "SELECT id, name, email, password FROM users WHERE email = ? AND password = MD5(?)";
				ps = con.prepareStatement(sql);

				ps.setString(1, email);
				ps.setString(2, pass);

				rs = ps.executeQuery();

				if(rs.next()) {
						User user = new User(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("email"),
								rs.getString("password"));
					// ログインセッションの保存
					session.setAttribute("user", user);
					resp.sendRedirect("index.html");
				}else {
				errors.add("メールアドレス、またはパスワードが間違っています。");
				session.setAttribute("errors", errors);
				getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
				}

			} catch(Exception e) {
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
		} else {
		session.setAttribute("errors", errors);
		getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
		}

	}

	//追加）バリデーションチェック
	private List<String> validate(String email, String pass) {
		List<String> errors = new ArrayList<String>();

		if(email.equals("")) {
			errors.add("メールアドレスは必須入力です。");
		}

		if(pass.equals("")) {
			errors.add("パスワードは必須入力です。");
		}

		return errors;
	}
}
