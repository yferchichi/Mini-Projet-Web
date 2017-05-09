/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateurs.gestionnaires;

import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import utilisateurs.modeles.Utilisateur;

/**
 *
 * @author TOSHIBA PC
 */
@Stateless
public class GestionnaireUtilisateurs {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    // Ici injection de code : on n'initialise pas. L'entity manager sera créé  
    // à partir du contenu de persistence.xml  
    @PersistenceContext
    private EntityManager em;
    
    public void creerUtilisateursDeTest() {
        creeUtilisateur("John", "Lennon", "jlennon", "test");
        creeUtilisateur("Paul", "Mac Cartney", "pmc", "test");
        creeUtilisateur("Ringo", "Starr", "rstarr", "test");
        creeUtilisateur("Georges", "Harisson", "georgesH", "test");
    }
    
    public void creer30UtilisateursDeTest() {
        for (int i = 0; i < 50; i++) {
            creeUtilisateur("Prenom " + i, "Nom " + i, "login " + i, "password " + i);
        }        
    }
    
    public Utilisateur creeUtilisateur(String nom, String prenom, String login, String password) {
        Utilisateur u = new Utilisateur(nom, prenom, login, password);
        em.persist(u);
        return u;
    }
    
    public Utilisateur trouverUtilisateur(String login) {
        // Utilisateur u=em.find(Utilisateur.class, login);         
        //  return u;
        Utilisateur u = new Utilisateur();
        u.setLogin(null);
        u.setPassword(null);
        Query q = em.createQuery("select u from Utilisateur u where u.login=:login");
        q.setParameter("login", login);
        try {
            u = (Utilisateur) q.getSingleResult();
        } catch (Exception e) {
            
        }
        return u;
    }
    
    public Utilisateur modifierUtilisateur(String nom, String prenom, String login) {
        // Utilisateur u=em.find(Utilisateur.class, login);         
        //  return u;
        Utilisateur u = this.trouverUtilisateur(login);
        u.setFirstname(nom);
        u.setLastname(prenom);
        em.merge(u);
        return (u);
    }
    
    public Collection<Utilisateur> getAllUsers(int begin, int end) {
        // Exécution d'une requête équivalente à un select *
        Query q = em.createQuery("select u from Utilisateur u order by u.id");
        
        Collection<Utilisateur> listUsers = null;
        if (end == 0) {
            listUsers = q.getResultList();
        } else {
            listUsers = q.getResultList().subList(begin, end);
        }
        return listUsers;
    }
    // Add business logic below. (Right-click in editor and choose  
    // "Insert Code > Add Business Method")  

    public boolean supprimerUtilisateur(String login) {
        boolean test = false;
        Utilisateur u = this.trouverUtilisateur(login);
        try {
            em.remove(u);
            test = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return test;
    }
      public Long countNombresElements(){
          
          Query q = em.createQuery("select count(u.id) as nb from Utilisateur u");
          return (Long) q.getSingleResult();
      }
    
}
