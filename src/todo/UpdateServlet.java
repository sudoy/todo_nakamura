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

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.sendRedirect("index.html");
	}
}
