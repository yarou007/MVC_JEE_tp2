/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import modele.Etudiant;

/**
 *
 * @author Riadh
 */
public class listetud extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter(); 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Liste des étudiants</title>");            
            out.println("</head>");
            out.println("<body>");
            
            String url="jdbc:mysql://localhost:3306/exam";
            String login="root";
            String pass="manager";
            
            //Instanciation d'un service de manipulation d'une base de données et pour la connexion
            Service s = new Service(url,login,pass);
            if(s.connexion()){
                List<Etudiant> l = s.ListeDesEtudiant();
                if(l==null){
                    out.println("<h3><font color='red'>Aucun étudiant dans la base</font></h3>");                
                }else{
                    out.println("<table border='1'>");
                        out.println("<tr><th>ID</th><th>NOM</th><th>PRENOM</th></tr>");
                        for(Etudiant e : l){
                            out.println("<tr><td>" + e.getId() + "</th><td>" + e.getNom() + 
                                 "</td><td>" + e.getPrenom() + "</td></tr>");   
                        }
                    out.println("</table>");
                }
            }else{
               out.println("<h1>Echec de connexion avec la base!<h1>");  
            }
            s.deconnexion();
            
            out.println("</body>");
            out.println("</html>");
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
