package dbs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/databases/Search", loadOnStartup = 1)
public class Search extends HttpServlet {
	 private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Connection c = null;
	    List<Upload> files = new ArrayList<Upload>();
	    
	    String searchName = request.getParameter("searchName");
	    
	    try
	    {
	        String url = "jdbc:mysql://cs3.calstatela.edu:3306/cs3220stu12?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	        String username = "cs3220stu12";
	        String password = "EOZ6mrUV";
	
	        String sql = "SELECT * FROM uploads WHERE fileName = ?";
	
	        c = DriverManager.getConnection(url, username, password);
	        PreparedStatement pstmt = c.prepareStatement(sql);
	        pstmt.setString(1, searchName);
	        ResultSet rs = pstmt.executeQuery();
	
	        while(rs.next()) {
                Upload file = new Upload( Integer.parseInt(rs.getString("id")), rs.getString("fileName"), rs.getString("filePath"));
                files.add(file);
                System.out.println(file.getFileName());
	        }
	    }
	    catch(SQLException e)
	    {
	        throw new ServletException(e);
	    }
	    finally
	    {
	        try
	        {
	            if(c != null) c.close();
	        }
	        catch(SQLException e)
	        {
	            throw new ServletException(e);
	        }
	    }
	    
	    System.out.println(files.toString());
	    
	    request.setAttribute("files", files);
	    request.getRequestDispatcher("/WEB-INF/Search.jsp").forward(request, response );
	
	}

}
