package todo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		HttpSession session = req.getSession(true);

		String email = req.getParameter("email");
		String pass = req.getParameter("pass");

		boolean check = authUser(email, pass);
		if(check) {
			resp.sendRedirect("index.html");
		} else{
			session.setAttribute("errors2", "メールアドレス、またはパスワードが間違っています。");
			getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
		}

	}

	protected boolean authUser(String email, String pass) {

		if(email == null || email.equals("") || email.length() == 0 ||
				pass == null || pass.equals("")|| pass.length() == 0) {
			return false;
		}

		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;


		try {
			con = DBUtils.getConnection();

			sql = "SELECT id, name, email, password FROM users WHERE email = ? && password = ?";
			ps = con.prepareStatement(sql);

			ps.setString(1, email);
			ps.setString(2, pass);

			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				return true;
			}else {
				return false;
			}

		} catch(Exception e) {
			return false;
		} finally {

			try{
				DBUtils.close(con);
				if(ps != null){
					ps.close();
				}
			} catch(Exception e){

			}
		}
	}
}
