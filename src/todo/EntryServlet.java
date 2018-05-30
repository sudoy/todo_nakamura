package todo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

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

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

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
