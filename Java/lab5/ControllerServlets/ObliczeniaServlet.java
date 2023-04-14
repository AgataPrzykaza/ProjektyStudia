/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pl.polsl.zadanie4.ControllerServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import static java.util.Collections.sort;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;
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
@WebServlet(name = "ObliczeniaServlet", urlPatterns = {"/ObliczeniaServlet"})
public class ObliczeniaServlet extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            Model m;
            HttpSession session = request.getSession();
            Object obj = session.getAttribute("wynik");
            if (obj == null) {
            m = new Model();
            createTableHistory();
          
            } else {
            m = (Model)obj;
            }
           
            session.setAttribute("wynik", m);
            if(validacja(request.getParameter("opcjaGry"))==false)
            {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Wynik</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Źle wybrana opcja </h1>");
            out.println("</body>");
            out.println("</html>");
            }
            else{
            
            int opcja=Integer.parseInt(request.getParameter("opcjaGry"));
            m.setNrOpcji(opcja);
            String s="";
            if(m.getNrOpcji()==1)
            {
                m.losowanieLotto();
                
                List<Integer> lista=m.getWynikLotto();
                sortList(lista);
                 for(int i=0;i<lista.size();i++)
                { 
                    s+=lista.get(i).toString()+", ";
                }
            }
           else if(m.getNrOpcji()==2)
            {
                m.losowanieMultiLotto();
                
                List<Integer> lista=m.getWynikMultiLotto();
                 sortList(lista);
                 for(int i=0;i<lista.size();i++)
                { 
                    s+=lista.get(i).toString()+", ";
                }
            }
           else if(m.getNrOpcji()==3)
            {
                m.losowanieMiniLotto();
                
                List<Integer> lista=m.getWynikMiniLotto();
                 sortList(lista);
                 for(int i=0;i<lista.size();i++)
                { 
                    s+=lista.get(i).toString()+", ";
                }
            }
            
           
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Wynik</title>");            
            out.println("</head>");
            out.println("<body>");
            String nazwa="";
            try{
            out.println("<h1>Wynik dla "+m.getNazwaZPozycji(opcja) +"</h1>");
            nazwa=m.getNazwaZPozycji(opcja);
            }
            catch(Exception e){
                s=e.getMessage();
            };
            out.println("<p>"+ s +"</p>");
            out.println("</body>");
            out.println("</html>");
            
             Cookie cookie1=new Cookie("cook","");
          

            String historyCookie= cookie1.getValue();
            historyCookie+="<p>"+nazwa+": "+s+"</p>";
            cookie1.setValue(historyCookie);
            insertData(opcja,s);
            response.addCookie(cookie1);
            }
        }
    }
      /**
 *
 * Sorts the list
 * 
 */
    void sortList(List<Integer> wynik)
    {  
        sort(wynik);
        wynik=wynik.stream()
                .sorted((a, b) -> a.compareTo(b))
                .collect(Collectors.toList());
        
    }
          /**
 *
 * Checks if good option
 * @return boolean
 */
    boolean validacja(String ...opcja)
    {
        if(opcja.length==1)
        {
        if(checkCzyInt(opcja[0])==false)
        {
            return false;
        }
        else
        {
             Integer o=Integer.parseInt(opcja[0]);
       
            if(o<=3&&o>=1)
            {   
                return true;
                  
            }
            else
            {
                return false;
            }
        }
        }
        else
        {
            return false;
        }
    }
          /**
 *
 * Checks if int
 * @return boolean
 */
    public boolean checkCzyInt(String sc)
    {
        
         try {
        Integer.parseInt(sc);
        return true;
    } catch (final NumberFormatException e) {
        return false;
    }
    }
    /**
     * Create table history
     */
         public void createTableHistory() {

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
            statement.executeUpdate("CREATE TABLE Historia "
                    + "( idGry INTEGER, "
                    + "numery VARCHAR(100) )");
            System.out.println("Table created");
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
        
        
    }
      /**
       * Inster data into table history
       * @param id
       * @param numery 
       */
        public void insertData(int id,String numery ) {
        
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
            statement.executeUpdate("INSERT INTO HISTORIA VALUES  (" + id + ",'" + numery + "')");
           
         
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }
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
