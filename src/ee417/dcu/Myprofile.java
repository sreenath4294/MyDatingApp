package ee417.dcu;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


  @WebServlet("/Myprofile")
  public class Myprofile extends HttpServlet {
	  private static final long serialVersionUID = 1L;
		Connection con = null;
	    Statement stmt = null;
	    ResultSet rs = null;
		String JDBCUrl = "jdbc:oracle:thin:@ee417.c7clh2c6565n.eu-west-1.rds.amazonaws.com:1521:EE417";
	    String uname = "ee_user";
	    String pword = "ee_pass";

	public void doGet(HttpServletRequest req, HttpServletResponse res)
     throws ServletException, IOException {
         res.setContentType("text/html");
         PrintWriter out = res.getWriter();      
         out.println("<html><head><title>My Profile</title><style type=\"text/css\">");
	 	 out.println("body { } .header { float:left; width:100%; height: 100px; background-color:maroon } </style></head>");
	     out.println("<body bgcolor=\"wheat\"><h1 align=\"center\"><div class=\"header\"><font face=\"arial\" size=\"18\" color=\"white\">My Dating System!!!</font></div></h1><br/>");
        
         
         try {
      		Class.forName("oracle.jdbc.driver.OracleDriver");
      		con = DriverManager.getConnection(JDBCUrl, uname, pword);
         }
         catch (Exception e) {
      		out.println("An error has occurred during the connection phase! Did you upload your Oracle Drivers?"); 
         }   
         
         try {
        	 stmt = con.createStatement();
        	 rs = stmt.executeQuery("select * from sreenathdatbl1");
             
        	 while (rs.next()) {
         		out.println("<b>My profile:</b><table>");
         		out.println("<tr><td>Firstname: </td><td>"+rs.getString("firstname")+"</td>");
         		out.println("<tr><td>Lastname: </td><td>"+rs.getString("lastname")+"</td>");
         		out.println("<tr><td>Age: </td><td>"+rs.getInt("age")+"</td>");
         		out.println("<tr><td>Gender: </td><td>"+rs.getString("gender")+"</td>");
         		out.println("<tr><td>Nationality: </td><td>"+rs.getString("nationality")+"</td>");
         		out.println("<tr><td>Email: </td><td>"+rs.getString("email")+"</td></table>");
        	 }
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
         out.println("</body></html>");
         out.close();
       }
  }
  
  