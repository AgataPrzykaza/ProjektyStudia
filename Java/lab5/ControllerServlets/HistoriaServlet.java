/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pl.polsl.zadanie4.ControllerServlets;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pl.polsl.zadanie4.model.Model;

/**
 *
 * @author SuperStudent.PL
 */
@WebServlet(name = "HistoriaServlet", urlPatterns = {"/HistoriaServlet"})
public class HistoriaServlet extends HttpServlet {
        boolean isTabelCreated=false;
        Vector<String> dowypisania=new Vector();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            Model m;
            HttpSession session = request.getSession();
            Object obj = session.getAttribute("wynik");
          
            if(isTabelCreated==false)
            {
                createTableNameGame();
                isTabelCreated=true;
            }
            
            
          
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Historia obliczeń</title>");            
            out.println("</head>");
            out.println("<body>");
          
            
            if (obj == null) {
            
            out.println("<h1>Brak historii</h1>");
            } else {
            m = (Model)obj;
            out.println("<h1>Historia</h1>");
            

            out.println("<p ><strong> Ostatni los z cookies:</strong></p>");
            Cookie[] cookies=request.getCookies();
            if(cookies!=null)
            {
                for(Cookie c : cookies)
                {
                    if(c.getName().equals("cook"))
                    {
                        out.println("<p >"+c.getValue()+"</p>");
                        
                        out.println("<hr>");
                        break;
                    }
                }
            }
            
             out.println("<p ><strong> Historia losow z bazy danych:</strong></p>");
            selectData();
            for(String s:dowypisania)
            {
                out.println("<p>"+s+"</p>");
            }
            }        
           
            
            out.println("</body>");
            out.println("</html>");
            
            
            
           
           
            
          
     }
    }
/**
 * Creating table with game names
 */
 public void createTableNameGame() {

        try {
            // loading the JDBC driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe.getMessage());
            return;
        }

        // make a connection to DB
        try (
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/baza", "baza", "baza")) {
            Statement statement = con.createStatement();
            statement.executeUpdate("CREATE TABLE NazwyGier "
                    + "(id INTEGER,"
                    +"nazwaGry VARCHAR(50)) ");
            statement.executeUpdate("INSERT INTO NazwyGier VALUES (1, 'Lotto')");
            statement.executeUpdate("INSERT INTO NazwyGier VALUES (2, 'MultiLotto')");
            statement.executeUpdate("INSERT INTO NazwyGier VALUES (3, 'MiniLotto')");
            System.out.println("Table created");
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
        
        
    }
       /**
        * Select history with games names
        */
      public void selectData() {

        try {
            // loading the JDBC driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe.getMessage());
            return;
        }

        // make a connection to DB
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/baza", "baza", "baza")) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM HISTORIA JOIN NAZWYGIER ON Historia.idGry=NazwyGier.id");
            dowypisania.clear();
            while (rs.next()) {
              
               
               String tmp=rs.getString("nazwaGry")+": "+rs.getString("numery");
               dowypisania.add(tmp);
            }
          
            rs.close();
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }
//     /**
//      * 
//      * @param id
//      * @param nazwa
//      * @param numery 
//      */
//     public void insertData(int id, String nazwa,String numery ) {
//        
//        try {
//            // loading the JDBC driver
//            Class.forName("org.apache.derby.jdbc.ClientDriver");
//        } catch (ClassNotFoundException cnfe) {
//            System.err.println(cnfe.getMessage());
//            return;
//        }
//
//        // make a connection to DB
//        try (
//            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/loteria", "app", "app")) {
//            Statement statement = con.createStatement();
//            statement.executeUpdate("INSERT INTO HISTORIA VALUES (id,nazwa,numery)");
//           
//         
//        } catch (SQLException sqle) {
//            System.err.println(sqle.getMessage());
//        }
//    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
