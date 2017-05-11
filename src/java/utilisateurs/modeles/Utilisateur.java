/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilisateurs.modeles;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import utilisateurs.modeles.Addresse;

/**
 *
 * @author Youssef
 */
@Entity(name = "UtilisateurOMUni")
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstname;
    private String lastname;
    private String login;
    private String password;
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Collection<Addresse> listAddresses;
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Collection<PhoneNumber> listPhoneNumbers;

    public Utilisateur() {
    }

    public Utilisateur(String firstname, String lastname, String login, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
    }

    public Utilisateur(Integer id, String firstname, String lastname, String login, String password) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "utilisateur.modeles.Utilisateur[ id=" + id + " ]";
    }

    public Collection<Addresse> getListAddresses() {
        return listAddresses;
    }

    public void setListAddresses(Collection<Addresse> listAddresses) {
        this.listAddresses = listAddresses;
    }

    public Collection<PhoneNumber> getListPhoneNumbers() {
        return listPhoneNumbers;
    }

    public void setListPhoneNumbers(Collection<PhoneNumber> listPhoneNumbers) {
        this.listPhoneNumbers = listPhoneNumbers;
    }

}
