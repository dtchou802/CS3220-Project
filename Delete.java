package GroupProject;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Upload;

@WebServlet(urlPatterns = "/databases/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.valueOf(request.getParameter("id"));
		String path = null;
		Connection c = null;

		try {
			String url = "jdbc:mysql://cs3.calstatela.edu:3306/cs3220stu06?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String username = "cs3220stu06";
			String password = "****";

			String sql = "delete from uploads where id =" + id;

			c = DriverManager.getConnection(url, username, password);



			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM uploads where id =" + id);
			
			while (rs.next()) {
				path = rs.getString("filePath");
				System.out.println(path);
			}
			
			File file = new File(path);
			if (file.delete()) {
				System.out.println(path + "deleted");
			} else
				System.out.println(path + "doesn't exists");
			
			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.executeUpdate();
			

		} catch (SQLException e) {
			throw new ServletException(e);
		} finally {
			try {
				if (c != null)
					c.close();
			} catch (SQLException e) {
				throw new ServletException(e);
			}
		}

		response.sendRedirect("FileManager");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
