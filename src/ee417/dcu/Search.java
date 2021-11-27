package ee417.dcu;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;
import oracle.jdbc.driver.*;

@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
	String JDBCUrl = "jdbc:oracle:thin:@ee417.c7clh2c6565n.eu-west-1.rds.amazonaws.com:1521:EE417";
    String username = "ee_user";
    String password = "ee_pass";
  		
 	public void doPost(HttpServletRequest request, HttpServletResponse response)
     	throws ServletException, IOException {
        	response.setContentType("text/html");
    		PrintWriter out = response.getWriter();
    		out.println("<html><head><title>Search result</title><style type=\"text/css\">");
    		out.println("body { } .header { float:left; width:100%; height: 100px; background-color:maroon } </style></head>");
            out.println("<body bgcolor=\"wheat\"><h1 align=\"center\"><div class=\"header\"><font face=\"arial\" size=\"18\" color=\"white\">My Dating System!!!</font></div></h1><br/><table>");
           
		// Now we add our database code!
		try {
             		Class.forName("oracle.jdbc.driver.OracleDriver");
             		con = DriverManager.getConnection(JDBCUrl, username, password);
         	}
         	catch (Exception e) {
             		out.println("An error has occurred during the connection phase! Did you upload your Oracle Drivers?"); 
         	}   
		String search = request.getParameter("search");
		String category = request.getParameter("category");
 	 	try {
      	     		stmt = con.createStatement();
	     		rs = stmt.executeQuery("SELECT * FROM sreenathdatbl1 where "+category+" = '"+search+"'");
	     		out.println("<tr><td><b>NAME</b></td><td><b>AGE</b></td><td><b>NATIONALITY</b></td></tr>");
	     		while (rs.next())
	     			out.println("<tr><td>" + rs.getString("FIRSTNAME") + " " + rs.getString("LASTNAME")+"</td><td>"+ rs.getString("AGE") +"</td><td>"+ rs.getString("NATIONALITY") +"</td></tr>");

 	 	}
        catch (Exception e) {
	     		out.println("<BR>An error has occurred during the Statement/ResultSet phase.  Please check the syntax and study the Exception details!");
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
		
		out.println("</table></BODY></HTML>");
        out.close();
 	}
}