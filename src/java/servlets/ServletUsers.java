/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.Collection;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import outils.Paginateur;
import utilisateurs.gestionnaires.GestionnaireUtilisateurs;
import utilisateurs.modeles.Utilisateur;

/**
 *
 * @author TOSHIBA PC
 */
@WebServlet(name = "ServletUsers", urlPatterns = {"/ServletUsers"})
public class ServletUsers extends HttpServlet {

    @EJB
    private GestionnaireUtilisateurs gestionnaireUtilisateurs;
    private Paginateur paginateur = new Paginateur();

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
        String forwardTo = "";
        String message = "";
        int begin = 0;
        int end = 10;
        String pagination = "";
        Collection<Utilisateur> liste = null;
        if (action != null) {
            if (action.equals("listerLesUtilisateurs")) {

                Long total = gestionnaireUtilisateurs.countNombresElements();
                System.out.println("Nombre d'utilisateurs dans la base : " + total);

                pagination = paginateur.pagine(total, gestionnaireUtilisateurs, request);
                liste = paginateur.getListe();
                request.setAttribute("pagination", pagination);
                request.setAttribute("total", total);
                request.setAttribute("listeDesUsers", liste);
                forwardTo = "index.jsp?action=listerLesUtilisateurs&begin=" + begin + "&end=" + end + "";
                message = "Liste des utilisateurs";

            } else if (action.equals("creerUtilisateursDeTest")) {

                gestionnaireUtilisateurs.creerUtilisateursDeTest();
                Long total = gestionnaireUtilisateurs.countNombresElements();
                pagination = paginateur.pagine(total, gestionnaireUtilisateurs, request);
                liste = paginateur.getListe();
                request.setAttribute("pagination", pagination);
                request.setAttribute("total", total);
                request.setAttribute("listeDesUsers", liste);
                forwardTo = "index.jsp?action=listerLesUtilisateurs&begin=" + begin + "&end=" + end + "";
                message = "Liste des utilisateurs";
            } else if (action.equals("creer50UtilisateursDeTest")) {

                gestionnaireUtilisateurs.creer30UtilisateursDeTest();
                Long total = gestionnaireUtilisateurs.countNombresElements();
                pagination = paginateur.pagine(total, gestionnaireUtilisateurs, request);
                liste = paginateur.getListe();
                request.setAttribute("pagination", pagination);
                request.setAttribute("total", total);
                request.setAttribute("listeDesUsers", liste);
                forwardTo = "index.jsp?action=listerLesUtilisateurs&begin=" + begin + "&end=" + end + "";
                message = "Liste des utilisateurs";
            } else if (action.equals("creerUnUtilisateur")) {
                gestionnaireUtilisateurs.creeUtilisateur(request.getParameter("nom").toString(), request.getParameter("prenom").toString(), request.getParameter("login").toString(), request.getParameter("motdepasse").toString());
                Long total = gestionnaireUtilisateurs.countNombresElements();
                pagination = paginateur.pagine(total, gestionnaireUtilisateurs, request);
                liste = paginateur.getListe();
                request.setAttribute("pagination", pagination);
                request.setAttribute("total", total);
                request.setAttribute("listeDesUsers", liste);
                forwardTo = "index.jsp?action=listerLesUtilisateurs&begin=" + begin + "&end=" + end + "";
                message = "Liste des utilisateurs";
            } else if (action.equals("chercherParLogin")) {
                Utilisateur u = gestionnaireUtilisateurs.trouverUtilisateur(request.getParameter("login").toString());
                request.setAttribute("utilisateur", u);
                forwardTo = "index.jsp?action=chercherParLogin";
                message = "Résultat de la recherche";
            } else if (action.equals("updateUtilisateur")) {
                Utilisateur u = gestionnaireUtilisateurs.modifierUtilisateur(request.getParameter("nom").toString(), request.getParameter("prenom").toString(), request.getParameter("login").toString());
                request.setAttribute("utilisateur", u);
                forwardTo = "index.jsp?action=updateUtilisateur";
                message = "Résultat de la modification:";

            } else if (action.equals("deleteUtilisateur")) {
                boolean test = gestionnaireUtilisateurs.supprimerUtilisateur(request.getParameter("login").toString());
                request.setAttribute("test", test);
                forwardTo = "index.jsp?action=deleteUtilisateur";
                message = "Résultat de la suppression:";

            }
        } else {
            forwardTo = "index.jsp?action=todo";
            message = "La fonctionnalité pour le paramètre " + action + " est à implémenter !";
        }

        RequestDispatcher dp = request.getRequestDispatcher(forwardTo + "&message=" + message);

        dp.forward(request, response);
        // Après un forward, plus rien ne peut être exécuté après !  
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
