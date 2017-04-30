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
        String pagenum = "1";
        String pagination = "";
        Collection<Utilisateur> liste = null;
        if (action != null) {
            if (action.equals("listerLesUtilisateurs")) {

                begin = Integer.parseInt(request.getParameter("begin"));
                end = Integer.parseInt(request.getParameter("end"));

                int total = gestionnaireUtilisateurs.getAllUsers(0, 0).size();
                if (total < 10) {
                    liste = gestionnaireUtilisateurs.getAllUsers(0, 0);

                    pagination = "<a href=\"ServletUsers?action=listerLesUtilisateurs&begin=1&end=" + total + "&pagenum=1\">&laquo;</a>\n";
                    pagination = pagination + "<a class=\"active\" href=\"ServletUsers?action=listerLesUtilisateurs&begin=0&end=" + total + "&pagenum=1\">1</a>\n";
                    pagination = pagination + " <a href=\"ServletUsers?action=listerLesUtilisateurs&begin=0&end=" + total + "&pagenum=1\">&raquo;</a>\n";

                } else {
                    liste = gestionnaireUtilisateurs.getAllUsers(begin, end);

                    pagination = "<a href=\"ServletUsers?action=listerLesUtilisateurs&begin=1&end=9&pagenum=1\">&laquo;</a>\n";
                    int i = 0;
                    int count = 0;
                    int start = 1;
                    int fin = 10;
                    Integer page = 1;
                    int finalStart = 0;
                    int lastpage = 1;
                    pagenum = request.getParameter("pagenum");
                    while (i <= total) {
                        count = count + 1;

                        if (count == 10 || i == total - 1) {
                            if (page.toString().equals(pagenum)) {
                                pagination = pagination + "<a class=\"active\" href=\"ServletUsers?action=listerLesUtilisateurs&begin=" + start + "&end=" + (start + count - 1) + "&pagenum=" + page + "\">" + page + "</a>\n";

                            } else {
                                pagination = pagination + "<a href=\"ServletUsers?action=listerLesUtilisateurs&begin=" + start + "&end=" + (start + count - 1) + "&pagenum=" + page + "\">" + page + "</a>\n";
                            }
                            fin = start + count - 1;
                            finalStart = start;
                            lastpage = page;
                            page++;
                            start = start + 10;
                            count = 0;
                        }
                        i++;
                    }
                    pagination = pagination + " <a href=\"ServletUsers?action=listerLesUtilisateurs&begin=" + finalStart + "&end=" + fin + "&pagenum=" + lastpage + "\">&raquo;</a>\n";
                }
                request.setAttribute("pagination", pagination);
                request.setAttribute("total", total);
                request.setAttribute("listeDesUsers", liste);
                forwardTo = "index.jsp?action=listerLesUtilisateurs&begin=" + begin + "&end=" + end + "";
                message = "Liste des utilisateurs";

            } else if (action.equals("creerUtilisateursDeTest")) {
                begin = Integer.parseInt(request.getParameter("begin"));
                end = Integer.parseInt(request.getParameter("end"));
                gestionnaireUtilisateurs.creerUtilisateursDeTest();
                int total = gestionnaireUtilisateurs.getAllUsers(0, 0).size();
                if (total < 10) {
                    liste = gestionnaireUtilisateurs.getAllUsers(0, 0);

                    pagination = "<a href=\"ServletUsers?action=listerLesUtilisateurs&begin=1&end=" + total + "&pagenum=1\">&laquo;</a>\n";
                    pagination = pagination + "<a class=\"active\" href=\"ServletUsers?action=listerLesUtilisateurs&begin=0&end=" + total + "&pagenum=1\">1</a>\n";
                    pagination = pagination + " <a href=\"ServletUsers?action=listerLesUtilisateurs&begin=0&end=" + total + "&pagenum=1\">&raquo;</a>\n";

                } else {
                    liste = gestionnaireUtilisateurs.getAllUsers(begin, end);

                    pagination = "<a href=\"ServletUsers?action=listerLesUtilisateurs&begin=1&end=9&pagenum=1\">&laquo;</a>\n";
                    int i = 0;
                    int count = 0;
                    int start = 1;
                    int fin = 10;
                    Integer page = 1;
                    int finalStart = 0;
                    int lastpage = 1;
                    pagenum = request.getParameter("pagenum");
                    while (i <= total) {
                        count = count + 1;

                        if (count == 10 || i == total - 1) {
                            if (page.toString().equals(pagenum)) {

                                pagination = pagination + "<a class=\"active\" href=\"ServletUsers?action=listerLesUtilisateurs&begin=" + start + "&end=" + (start + count - 1) + "&pagenum=" + page + "\">" + page + "</a>\n";

                            } else {
                                pagination = pagination + "<a href=\"ServletUsers?action=listerLesUtilisateurs&begin=" + start + "&end=" + (start + count - 1) + "&pagenum=" + page + "\">" + page + "</a>\n";
                            }
                            fin = start + count - 1;
                            finalStart = start;
                            lastpage = page;
                            page++;
                            start = start + 10;
                            count = 0;
                        }
                        i++;
                    }
                    pagination = pagination + " <a href=\"ServletUsers?action=listerLesUtilisateurs&begin=" + finalStart + "&end=" + fin + "&pagenum=" + lastpage + "\">&raquo;</a>\n";
                }
                request.setAttribute("pagination", pagination);
                request.setAttribute("total", total);
                request.setAttribute("listeDesUsers", liste);
                forwardTo = "index.jsp?action=listerLesUtilisateurs&begin=" + begin + "&end=" + end + "";
                message = "Liste des utilisateurs";
            } else if (action.equals("creer30UtilisateursDeTest")) {
                begin = Integer.parseInt(request.getParameter("begin"));
                end = Integer.parseInt(request.getParameter("end"));
                gestionnaireUtilisateurs.creer30UtilisateursDeTest();
                int total = gestionnaireUtilisateurs.getAllUsers(0, 0).size();
                if (total < 10) {
                    liste = gestionnaireUtilisateurs.getAllUsers(0, 0);

                    pagination = "<a href=\"ServletUsers?action=listerLesUtilisateurs&begin=1&end=" + total + "&pagenum=1\">&laquo;</a>\n";
                    pagination = pagination + "<a class=\"active\" href=\"ServletUsers?action=listerLesUtilisateurs&begin=0&end=" + total + "&pagenum=1\">1</a>\n";
                    pagination = pagination + " <a href=\"ServletUsers?action=listerLesUtilisateurs&begin=0&end=" + total + "&pagenum=1\">&raquo;</a>\n";

                } else {
                    liste = gestionnaireUtilisateurs.getAllUsers(begin, end);

                    pagination = "<a href=\"ServletUsers?action=listerLesUtilisateurs&begin=1&end=9&pagenum=1\">&laquo;</a>\n";
                    int i = 0;
                    int count = 0;
                    int start = 1;
                    int fin = 10;
                    Integer page = 1;
                    int finalStart = 0;
                    int lastpage = 1;
                    pagenum = request.getParameter("pagenum");
                    while (i <= total) {
                        count = count + 1;

                        if (count == 10 || i == total - 1) {
                            if (page.toString().equals(pagenum)) {

                                pagination = pagination + "<a class=\"active\" href=\"ServletUsers?action=listerLesUtilisateurs&begin=" + start + "&end=" + (start + count - 1) + "&pagenum=" + page + "\">" + page + "</a>\n";

                            } else {
                                pagination = pagination + "<a href=\"ServletUsers?action=listerLesUtilisateurs&begin=" + start + "&end=" + (start + count - 1) + "&pagenum=" + page + "\">" + page + "</a>\n";
                            }
                            fin = start + count - 1;
                            finalStart = start;
                            lastpage = page;
                            page++;
                            start = start + 10;
                            count = 0;
                        }
                        i++;
                    }
                    pagination = pagination + " <a href=\"ServletUsers?action=listerLesUtilisateurs&begin=" + finalStart + "&end=" + fin + "&pagenum=" + lastpage + "\">&raquo;</a>\n";
                }
                request.setAttribute("pagination", pagination);
                request.setAttribute("total", total);
                request.setAttribute("listeDesUsers", liste);
                forwardTo = "index.jsp?action=listerLesUtilisateurs&begin=" + begin + "&end=" + end + "";
                message = "Liste des utilisateurs";
            } else if (action.equals("creerUnUtilisateur")) {
                begin = Integer.parseInt(request.getParameter("begin"));
                end = Integer.parseInt(request.getParameter("end"));
                gestionnaireUtilisateurs.creeUtilisateur(request.getParameter("nom").toString(), request.getParameter("prenom").toString(), request.getParameter("login").toString(), request.getParameter("motdepasse").toString());
                int total = gestionnaireUtilisateurs.getAllUsers(0, 0).size();
                if (total < 10) {
                    liste = gestionnaireUtilisateurs.getAllUsers(0, 0);

                    pagination = "<a href=\"ServletUsers?action=listerLesUtilisateurs&begin=1&end=" + total + "&pagenum=1\">&laquo;</a>\n";
                    pagination = pagination + "<a class=\"active\" href=\"ServletUsers?action=listerLesUtilisateurs&begin=0&end=" + total + "&pagenum=1\">1</a>\n";
                    pagination = pagination + " <a href=\"ServletUsers?action=listerLesUtilisateurs&begin=0&end=" + total + "&pagenum=1\">&raquo;</a>\n";

                } else {
                    liste = gestionnaireUtilisateurs.getAllUsers(begin, end);

                    pagination = "<a href=\"ServletUsers?action=listerLesUtilisateurs&begin=1&end=9&pagenum=1\">&laquo;</a>\n";
                    int i = 0;
                    int count = 0;
                    int start = 1;
                    int fin = 10;
                    Integer page = 1;
                    int finalStart = 0;
                    int lastpage = 1;
                    pagenum = request.getParameter("pagenum");
                    while (i <= total) {
                        count = count + 1;

                        if (count == 10 || i == total - 1) {
                            if (page.toString().equals(pagenum)) {
                                pagination = pagination + "<a class=\"active\" href=\"ServletUsers?action=listerLesUtilisateurs&begin=" + start + "&end=" + (start + count - 1) + "&pagenum=" + page + "\">" + page + "</a>\n";

                            } else {
                                pagination = pagination + "<a href=\"ServletUsers?action=listerLesUtilisateurs&begin=" + start + "&end=" + (start + count - 1) + "&pagenum=" + page + "\">" + page + "</a>\n";
                            }
                            fin = start + count - 1;
                            finalStart = start;
                            lastpage = page;
                            page++;
                            start = start + 10;
                            count = 0;
                        }
                        i++;
                    }
                    pagination = pagination + " <a href=\"ServletUsers?action=listerLesUtilisateurs&begin=" + finalStart + "&end=" + fin + "&pagenum=" + lastpage + "\">&raquo;</a>\n";
                }
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
