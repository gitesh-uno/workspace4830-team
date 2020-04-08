
/**
 * @file InsertTeam.java
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InsertTeam")
public class InsertTeam extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public InsertTeam() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String userName = request.getParameter("userName");
      String password = request.getParameter("password");
      

      Connection connection = null;
      String insertSql = " INSERT INTO MyTableTeam (id, MYUSER, PASSWORD) values (default, ?, ?)";
 //    String insertSql = " INSERT INTO MyTableYadav0204 (id, MYUSER, EMAIL, PHONE, ADDRESS) values (default, ?, ?, ?, ?)";

      try {
         DBConnectionTeam.getDBConnection(getServletContext());
         connection = DBConnectionTeam.connection;
         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
         preparedStmt.setString(1, userName);
         preparedStmt.setString(2, password);
         
         
         preparedStmt.execute();
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Insert Data to DB table";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h2 align=\"center\">" + title + "</h2>\n" + //
            "<ul>\n" + //

            "  <li><b>User Name</b>: " + userName + "\n" + //
            "  <li><b>Password</b>: " + password + "\n" + //
            

            "</ul>\n");

      out.println("<a href=/webproject/SearchTeam.html>Search Data</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
