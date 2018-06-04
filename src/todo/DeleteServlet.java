package todo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import todo.utils.DBUtils;

@WebServlet("/delete.html")
public class DeleteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

		DeleteServlet error = new DeleteServlet();
		List<String> errors = error.validate(req.getParameter("id"));

		if(errors.size() > 0) {
			//errorリストに1つ以上、エラーが含まれていた場合
			session.setAttribute("errors", errors);
			resp.sendRedirect("index.html");
		} else {

			Connection con = null;
			PreparedStatement ps = null;
			String sql = null;

			try {
				con = DBUtils.getConnection();

				sql = "DELETE FROM todolist WHERE id = ?";

				ps = con.prepareStatement(sql);
				ps.setString(1, req.getParameter("id"));

				ps.executeUpdate();

				List<String> success = new ArrayList<>();
				success.add("削除しました。");
				session.setAttribute("success", success);

			} catch(Exception e) {
				throw new ServletException(e);
			}finally {
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


	private List<String> validate(String id){
		List<String> errors = new ArrayList<String>();

		// idのエラーチェック（不正防止
		// ここいまStringしてるからな！！気をつけろよ！！
		if(id == null || id.equals("")) {
			errors.add("存在しないIDです。");
		}

		return errors;
	}

}
