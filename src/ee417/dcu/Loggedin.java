package ee417.dcu;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


  @WebServlet("/Loggedin")
  public class Loggedin extends HttpServlet {
	  private static final long serialVersionUID = 1L;
		Connection con = null;
	    Statement stmt = null;
	    ResultSet rs = null;
		String JDBCUrl = "jdbc:oracle:thin:@ee417.c7clh2c6565n.eu-west-1.rds.amazonaws.com:1521:EE417";
	    String uname = "ee_user";
	    String pword = "ee_pass";

	public void doPost(HttpServletRequest req, HttpServletResponse res)
     throws ServletException, IOException {
         res.setContentType("text/html");
         PrintWriter out = res.getWriter();      
         out.println("<html><head><title>Home Page</title><style type=\"text/css\">");
 		 out.println("body { } .header { float:left; width:100%; height: 100px; background-color:maroon } </style></head>");
         out.println("<body bgcolor=\"wheat\"><h1 align=\"center\"><div class=\"header\"><font face=\"arial\" size=\"18\" color=\"white\">My Dating System!!!</font></div></h1><br/>");
        
         
         try {
      		System.out.println("\nConnecting to the SSD Database......");
      		Class.forName("oracle.jdbc.driver.OracleDriver");
      		con = DriverManager.getConnection(JDBCUrl, uname, pword);
         }
         catch (Exception e) {
      		out.println("An error has occurred during the connection phase! Did you upload your Oracle Drivers?"); 
         }   
         String username = req.getParameter("username");
         String password = req.getParameter("password");
         
         try {
        	 stmt = con.createStatement();
        	 rs = stmt.executeQuery("select * from sreenathdatbl1 where username = '"+username+"' and password = '"+password+"'");
             
        	 if (rs.next()) {
         		out.println("<b>Welcome <i>" + rs.getString("FIRSTNAME") + " " + rs.getString("LASTNAME")+"!!!</i></b>");
        	 }else {
        		 res.sendRedirect("Loginfailed.html");
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
         out.println("<p>Go to:<form method=\"get\" action=\"/DatingSystem_17212398/Myprofile\" name=\"myprofile\"><input type=\"submit\" value=\"My profile\"/></form></p>");
         out.println("<p>Go to:<form method=\"get\" action=\"/DatingSystem_17212398/Userlist\" name=\"myprofile\"><input type=\"submit\" value=\"Userlist\"/></form></p>");
         out.println("<p><a href=\"Loggedout.html\">Logout</a></p>");
         out.println("</body></html>");
         out.close();
       }
  }
  
  