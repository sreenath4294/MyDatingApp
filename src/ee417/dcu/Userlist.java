package ee417.dcu;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;
import oracle.jdbc.driver.*;

@WebServlet("/Userlist")
public class Userlist extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
	String JDBCUrl = "jdbc:oracle:thin:@ee417.c7clh2c6565n.eu-west-1.rds.amazonaws.com:1521:EE417";
    String username = "ee_user";
    String password = "ee_pass";
  		
 	public void doGet(HttpServletRequest request, HttpServletResponse response)
     	throws ServletException, IOException {
        	response.setContentType("text/html");
    		PrintWriter out = response.getWriter();
    		out.println("<html><head><title>User list</title><style type=\"text/css\">");
    		out.println("body { } .header { float:left; width:100%; height: 100px; background-color:maroon } </style></head>");
            out.println("<body bgcolor=\"wheat\"><h1 align=\"center\"><div class=\"header\"><font face=\"arial\" size=\"18\" color=\"white\">My Dating System!!!</font></div></h1><br/><ul>");
            
		// Now we add our database code!
		try {
             		Class.forName("oracle.jdbc.driver.OracleDriver");
             		con = DriverManager.getConnection(JDBCUrl, username, password);
         	}
         	catch (Exception e) {
             		out.println("An error has occurred during the connection phase! Did you upload your Oracle Drivers?"); 
         	}   
			out.println("<li><a href=\"Categories.html\">Category search</a></li></ul>");
 	 	try {
      	     		stmt = con.createStatement();
	     		rs = stmt.executeQuery("SELECT * FROM SREENATHDATBL1");

	     		while (rs.next())
	     			out.println("<li>" + rs.getString("FIRSTNAME") + " " + rs.getString("LASTNAME")+"</li><br/>");
	 	}
        catch (Exception e) {
	     		out.println("An error has occurred during the Statement/ResultSet phase.  Please check the syntax and study the Exception details!");
	    }   
         	
		finally {
	     try {    
	        if (rs != null) rs.close();
		 	if (stmt != null) stmt.close();
		 	if (con != null) con.close();
	     }
	     catch (Exception ex) {
	      	out.println("<BR>An error occurred while closing down connection/statement"); 
         }
        }
		
		out.println("</BODY></HTML>");
        out.close();
 	}
}