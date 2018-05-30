package todo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/index.html")
public class IndexServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		try {
			Context initContext = new InitialContext();
		    Context envContext  = (Context)initContext.lookup("java:/comp/env");
		    DataSource ds = (DataSource)envContext.lookup("todolist_nakamura");
		    con = ds.getConnection();
			// SQL
			sql = "SELECT id, title, priority, limit_day FROM todolist ORDER BY id";
			// SELECT命令の準備
			ps = con.prepareStatement(sql);
			// 実行
			rs = ps.executeQuery();

			// ここをやりたくないからJavaBeansに置き換える
			req.setAttribute("rs", rs);

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
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {

			}
		}

	}

}
