<%-- 
    Document   : connexion
    Created on : 28 avr. 2017, 15:51:13
    Author     : Youssef
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Connexion</title>
    </head>
    <body>
        <fieldset>
            <legend>Connectez-vous</legend>
            <form action="ServletLogin" method="get" class="form-inline">
                <div class="form-group">
                    <label for="usr">Nom d'utilisateur :</label>
                    <input type="text" class="form-control" id="usr" name="userName" placeholder="votre username" required>
                </div>

                <div class="form-group">
                    <label for="pwd">Mot de passe :</label>
                    <input type="password" class="form-control" id="pwd" name="motDePasse" placeholder="votre mot de passe">
                </div>

                <input type="hidden" name="action" value="connectUser"/>
                <button type="submit" class="btn btn-primary btn-md">Se connecter</button>
            </form>
        </fieldset>
    <c:if test="${requestScope['resultat']!= null}">  
        <h3>  
            ${resultat} 

        </h3>  

    </c:if>
</body>
</html>
