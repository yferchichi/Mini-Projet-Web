/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package outils;

import java.util.Collection;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import utilisateurs.gestionnaires.GestionnaireUtilisateurs;
import utilisateurs.modeles.Utilisateur;

/**
 *
 * @author TOSHIBA PC
 */
@Stateless
public class Paginateur {

    int begin = 0;
    int end = 10;
    String pagenum = "1";
    String pagination = "";
    Collection<Utilisateur> liste = null;

    public String pagine(Long total, GestionnaireUtilisateurs gestionnaireUtilisateurs, HttpServletRequest request) {
        begin = Integer.parseInt(request.getParameter("begin"));
        end = Integer.parseInt(request.getParameter("end"));
        if (total < 10) {
            liste = gestionnaireUtilisateurs.getAllUsers(0, 0);

            pagination = "<a href=\"ServletUsers?action=listerLesUtilisateurs&begin=1&end=" + total + "&pagenum=1\">&laquo;</a>\n";
            pagination = pagination + "<a class=\"active\" href=\"ServletUsers?action=listerLesUtilisateurs&begin=0&end=" + total + "&pagenum=1\">1</a>\n";
            pagination = pagination + " <a href=\"ServletUsers?action=listerLesUtilisateurs&begin=0&end=" + total + "&pagenum=1\">&raquo;</a>\n";

        } else {
            liste = gestionnaireUtilisateurs.getAllUsers(begin, end);
            pagination = "<a href=\"ServletUsers?action=listerLesUtilisateurs&begin=1&end=10&pagenum=1\">&laquo;</a>\n";
            int i = 0;
            int count = 0;
            int start = 0;
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
                        pagination = pagination + "<a href=\"ServletUsers?action=listerLesUtilisateurs&begin=" + start + "&end=" + (start + count) + "&pagenum=" + page + "\">" + page + "</a>\n";
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
        return pagination;
    }

    public Collection<Utilisateur> getListe() {
        return liste;
    }

    public void setListe(Collection<Utilisateur> liste) {
        this.liste = liste;
    }

}
