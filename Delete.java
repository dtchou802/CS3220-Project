package dbs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/databases/Delete", loadOnStartup = 1)
public class Delete extends HttpServlet {
	 private static final long serialVersionUID = 1L;

	 private Upload getFile(Integer id) throws ServletException {
		 
	    Upload file = null;
	    Connection c = null;
	    
	    try
	    {
	        String url = "jdbc:mysql://cs3.calstatela.edu:3306/cs3220stu12?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	        String username = "cs3220stu12";
	        String password = "EOZ6mrUV";
	
	        String sql = "select * from uploads where id = ?";
	
	        c = DriverManager.getConnection(url, username, password);
	        PreparedStatement pstmt = c.prepareStatement(sql);
	        pstmt.setInt(1, id);
	        ResultSet rs = pstmt.executeQuery();
	
	        if(rs.next())
	            file = new Upload(rs.getInt("id"), rs.getString("fileName"), rs.getString("filePath"));
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
	
	    return file;
	}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        Upload file = getFile(id);
        Connection c = null;
        
        try
        {
	        String url = "jdbc:mysql://cs3.calstatela.edu:3306/cs3220stu12?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	        String username = "cs3220stu12";
	        String password = "EOZ6mrUV";

            String sql = "delete from uploads where id =" + id;

            c = DriverManager.getConnection(url, username, password);
            
            PreparedStatement pstmt = c.prepareStatement(sql);
            
            pstmt.executeUpdate();
            
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

        response.sendRedirect("FileManager");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
    }

}

