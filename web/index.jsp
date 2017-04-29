<%-- 
    Document   : index
    Created on : 25 avr. 2017, 14:00:42
    Author     : TOSHIBA PC
--%> 

<%@page contentType="text/html" pageEncoding="UTF-8"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  
    "http://www.w3.org/TR/html4/loose.dtd">  

<!-- Ne pas oublier cette ligne sinon tous les tags de la JSTL seront ignorés ! -->  
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<html>  
    <head>  
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css"  
    href="${pageContext.request.contextPath}/style.css" /> 

        <title>Gestionnaire d'utilisateurs</title>  
    </head>  
    <body>  
        <h1>Gestionnaire d'utilisateurs</h1>  


        <!-- Message qui s'affiche lorsque la page est appelé avec un paramètre http message -->  
        <c:if test="${!empty param['message']}">  
            <h2>Reçu message : ${param.message}</h2>  
        </c:if>  


        <h2 >Menu de gestion des utilisateurs</h2>  
        <ul id="menu">  
            <li><a href="ServletUsers?action=listerLesUtilisateurs">Afficher/raffraichir la liste de tous les utilisateurs</a></li>  
            <p>  
        </ul>  
        <h2>Liste des fonctionnalités à implémenter dans la Servlet (note : après chaque action cette page sera  
            rappelée par la servlet avec la liste des utilisateurs raffraichie et un message de confirmation</h2>  
        <table border="2" color="solid black"> 
            <thead> <td>Création test</td>
                <td>Créer un utilisateur</td>
                <td>Afficher les détails d'un utilisateur</td>
                <td>Modifier les détails d'un utilisateur :</td>
                <td>Supprimer un utilisateur à partir de son login</td>
            </thead>
            <tr> <td><a href="ServletUsers?action=creerUtilisateursDeTest">Créer 4 utilisateurs de test</a></td>
                <td> <table><form action="ServletUsers" method="get" class="form-inline">
                            <tr> 
                                <td> <label for="nom">Nom :</label>        </td>
                                <td> <input type="text" name="nom" id="nom"/></td>
                            </tr>
                            <tr>
                                <td> <label for="prenom">Prénom :</label></td>
                                <td> <input type="text" name="prenom" id="prenom"/></td>
                            </tr>
                            <tr>
                                <td> <label for="login">Login :</label></td>
                                <td> <input type="text" name="login" id="login"/></td>
                            </tr>
                            <tr>
                                <td>  <label for="pwd">Mot de passe :</label></td>
                                <td>  <input type="password" name="motdepasse" id="pwd"></td>
                            </tr>
                            <tr>
                                <!-- Astuce pour passer des paramètres à une servlet depuis un formulaire JSP !-->  
                                <td><input type="hidden" name="action" value="creerUnUtilisateur"/>  </td>
                                <td> <input type="submit" class="btn btn-primary btn-md" value="Créer l'utilisateur" name="submit"/></td>
                            </tr>
                        </form> </table> </td> 
                <td><table><form action="ServletUsers" method="get" class="form-inline">
                            <tr>
                                <td><label for="lgn">login :</label></td>
                                <td><input type="text" name="login" id="lgn"/></td> 
                            </tr>
                            <tr>
                                <td><input type="hidden" name="action" value="chercherParLogin"/> </td> 
                                <td><input type="submit"class="btn btn-primary btn-md" value="Chercher" name="submit"/></td>
                            </tr>  
                        </form></table> </td>
                <td><table><form action="ServletUsers" method="get" class="form-inline">  
                            <tr> 
                                <td> <label for="nom">Nom :</label>        </td>
                                <td> <input type="text" name="nom" id="nom"/></td>
                            </tr>
                            <tr>
                                <td> <label for="prenom">Prénom :</label></td>
                                <td> <input type="text" name="prenom" id="prenom"/></td>
                            </tr>
                            <tr>
                                <td> <label for="login">Login :</label></td>
                                <td> <input type="text" name="login" id="login"/></td>
                            </tr>
                            <tr>  
                                <td><input type="hidden" name="action" value="updateUtilisateur"/>  </td>
                                <td><input type="submit" class="btn btn-primary btn-md" value="Mettre à jour" name="submit"/> </td> </tr> 
                        </form></table></td>
                <td><table><form action="ServletUsers" method="get" class="form-inline">  
                            <tr>
                                <td> <label for="login">Login :</label></td>
                                <td> <input type="text" name="login" id="login"/></td>
                            </tr>  
                            <tr> <td><input type="hidden" name="action" value="deleteUtilisateur"/></td>   
                                <td><input type="submit" class="btn btn-primary btn-md" value="Supprimer" name="submit"/></td>   </tr>
                        </form></table> </td>
            </tr>
        </table>  

        <!-- Fin du menu -->  

        <!-- Zone qui affiche les utilisateurs si le paramètre action vaut listerComptes -->  
        <c:if test="${param['action'] == 'listerLesUtilisateurs'}" >  
            <h2>Liste des utilisateurs</h2>  

            <table border="10">  
                <!-- La ligne de titre du tableau des comptes -->  
                <tr>  
                    <td><b>Login</b></td>  
                    <td><b>Nom</b></td>  
                    <td><b>Prénom</b></td>  
                </tr>  

                <!-- Ici on affiche les lignes, une par utilisateur -->  
                <!-- cette variable montre comment on peut utiliser JSTL et EL pour calculer -->  
                <c:set var="total" value="0"/>  

                <c:forEach var="u" items="${requestScope['listeDesUsers']}">  
                    <tr>  
                        <td>${u.login}</td>  
                        <td>${u.firstname}</td>  
                        <td>${u.lastname}</td>  
                        <!-- On compte le nombre de users -->  
                        <c:set var="total" value="${total+1}"/>  
                    </tr>  
                </c:forEach>  

                <!-- Affichage du solde total dans la dernière ligne du tableau -->  
                <tr><td><b>TOTAL</b></td><td></td><td><b>${total}</b></td><td></td></tr>  
            </table>  

        </c:if>  
        <c:if test="${param['action'] == 'chercherParLogin'}" >  
            <h2>Utilisateur recherché</h2>  

            <table border="10">  
                <!-- La ligne de titre du tableau des comptes -->  
                <tr>  
                    <td><b>Id</b></td>
                    <td><b>Login</b></td>  
                    <td><b>Nom</b></td>  
                    <td><b>Prénom</b></td>  
                </tr>
                <c:if test="${requestScope['utilisateur']== null}">  
                    <tr>  
                        <td> Aucun utilisateur trouvé</td>  

                    </tr>  
                </c:if>  
                <c:if test="${requestScope['utilisateur']!= null}">  
                    <tr> 
                        <td>${utilisateur.id}</td>  
                        <td>${utilisateur.login}</td>  
                        <td>${utilisateur.firstname}</td>  
                        <td>${utilisateur.lastname}</td>  
                    </tr>  
                </c:if>                  
            </table>  

        </c:if>  
        <c:if test="${param['action'] == 'updateUtilisateur'}" >  
            <h2>Nouvelles données</h2>  

            <table border="10">  
                <!-- La ligne de titre du tableau des comptes -->  
                <tr>  
                    <td><b>Id</b></td>
                    <td><b>Login</b></td>  
                    <td><b>Nom</b></td>  
                    <td><b>Prénom</b></td>  
                </tr>
                <c:if test="${requestScope['utilisateur']== null}">  
                    <tr>  
                        <td> Aucun utilisateur trouvé</td>  

                    </tr>  
                </c:if>  
                <c:if test="${requestScope['utilisateur']!= null}">  
                    <tr> 
                        <td>${utilisateur.id}</td>  
                        <td>${utilisateur.login}</td>  
                        <td>${utilisateur.firstname}</td>  
                        <td>${utilisateur.lastname}</td>  
                    </tr>  
                </c:if>                  
            </table>  

        </c:if>
        <c:if test="${param['action'] == 'deleteUtilisateur'}" >  
            <h2>Résultat de la suppression</h2>  

            <table border="10">                 
                <c:if test="${requestScope['test']== false}">  
                    <tr>  
                        <td> échec de la suppression </td>  

                    </tr>  
                </c:if>  
                <c:if test="${requestScope['test']== true}">  
                    <tr> 
                        <td>suppression réussie</td>  

                    </tr>  
                </c:if>                  
            </table>  

        </c:if>
    </body>  
</html>  