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

import todo.beans.Todo;
import todo.utils.DBUtils;


@WebServlet("/index.html")
public class IndexServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		try {
			con = DBUtils.getConnection();
			// SQL
			sql = "SELECT id, title, detail, priority, limit_day FROM todolist ORDER BY id";
			// SELECT命令の準備
			ps = con.prepareStatement(sql);
			// 実行
			rs = ps.executeQuery();

			List<Todo> list = new ArrayList<>();
			while(rs.next()) {
				Todo a = new Todo(
						rs.getInt("id"),
						rs.getString("title"),
						rs.getString("detail"),
						rs.getInt("priority"),
						rs.getDate("limit_day"));

				list.add(a);
			}
			// JavaBeansをJSPに渡す
			req.setAttribute("list", list);

			getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);

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

	//まだここは残しておく、全工程終了次第残すかは決める
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.sendRedirect("index.html");
	}

}
