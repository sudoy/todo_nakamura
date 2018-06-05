package todo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import todo.utils.DBUtils;

@WebServlet("/logout.html")
public class LogoutServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if(!DBUtils.checked(req, resp)) {
			return;
		}

		HttpSession session = req.getSession();
		session.setAttribute("user", null);
		session.setAttribute("error2", "ログアウトしました。");
		resp.sendRedirect("login.html");
	}
}
