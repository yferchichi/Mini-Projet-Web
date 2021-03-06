/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utilisateurs.gestionnaires.GestionnaireUtilisateurs;
import utilisateurs.modeles.Utilisateur;

/**
 *
 * @author Youssef
 */
@WebServlet(name = "ServletLogin", urlPatterns = {"/ServletLogin"})
public class ServletLogin extends HttpServlet {

    @EJB
    private GestionnaireUtilisateurs gestionnaireUtilisateurs;

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
        String action = request.getParameter("action");
        String forwardTo = "login.jsp?action=connectUser";
        String message = "";
        String isNull = null;
        String resultat = null;
        HttpSession session = request.getSession();
        if (action != null) {
            if (!request.getParameter("userName").equals(isNull) && !request.getParameter("motDePasse").equals(isNull)) {

                String username = request.getParameter("userName");
                String password = request.getParameter("motDePasse");

                Utilisateur utilisateur = gestionnaireUtilisateurs.trouverUtilisateur(username);
                if (utilisateur.getLogin() != null) {

                    if (password.equals(utilisateur.getPassword())) {
                        session.setAttribute("utilisateur", username);
                        getServletContext().getRequestDispatcher("/index.jsp").forward(
                                request, response);
                    } else {
                        resultat = "Mot de passe incorrect!";
                        session.setAttribute("resultat", resultat);
                        getServletContext().getRequestDispatcher("/login.jsp").forward(
                                request, response);
                    }
                } else {
                    resultat = "Ce login n'existe pas!";
                    session.setAttribute("resultat", resultat);
                    getServletContext().getRequestDispatcher("/login.jsp").forward(
                            request, response);

                }
            } else {
                resultat = "Veuillez saisir vos informations de connexion!!";
                session.setAttribute("resultat", resultat);
                getServletContext().getRequestDispatcher("/login.jsp").forward(
                        request, response);
            }
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
