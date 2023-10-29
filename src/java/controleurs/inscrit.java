
package controleurs;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
 // import javax.servelet.*;
 // import javax.servelet.http.*;

import modele.*;

/**
 *
 * @author Riadh
 */
public class inscrit extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        }

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
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter(); 
        
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Inscription</title>");            
            out.println("</head>");
            out.println("<body>");
            String url="jdbc:mysql://localhost:3306/exam";
            String login="root";
            String pass="manager";
            //Instanciation d'un service de manipulation d'une base de données
            Service s = new Service(url,login,pass);
            //Récupération des valeurs du formulaire
            int id = Integer.parseInt(request.getParameter("id"));
            String nom=request.getParameter("nom");
            String prenom=request.getParameter("prenom");
            //creation d'une instance de l'Etudiant
            Etudiant e = new Etudiant(id,nom,prenom);
            if(s.connexion()){
                if(!s.isExistant(e)){
                    if (s.insertion(e)==1)
                        out.println("<h1>Inscription avec succès!<h1>");
                    else
                        out.println("<h1>Echec d'ajout!<h1>");
                }else{
                  out.println("<h1>Inscription impossible, étudiant déjà existant!<h1>");  
                }
            }else
                 out.println("<h1>Echec de connexion avec la base!<h1>");
            s.deconnexion();
            
            out.println("</body>");
            out.println("</html>");
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
