<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>Connexion</title>
</head>
<body>
    <div class="header">
        <h1>Blog --GG</h1>
        <h2>Connexion ou Inscription</h2>
    </div>
    <div class="form-container">
        <form action="loginServlet" method="post">
            <fieldset id="connection">
                <legend>Blog --GG Connexion</legend>
                <p class="blabla">Connectez-vous pour donner votre avis sur de nombreux sujets de Blog --GG</p>
                <div class="form-group">
                    <label for="login">Login:</label>
                    <input type="text" id="login" name="login" value="${utilisateur}" autocomplete="off" autofocus="autofocus" required>
                </div>
                <div class="form-group">
                    <label for="mdp">Mot de passe:</label>
                    <input type="password" id="mdp" name="mdp" value="" required>
                </div>
                <div>
                    <input type="submit" value="Se connecter" id="btnconnection">
                </div>
                <!-- Affichage du message d'erreur -->
                    <p style="color: red;">${msgErreur}</p>
            </fieldset>
        </form>
    </div>
    <div class="form-container">
        <fieldset id="inscription">
            <legend>Blog --GG Inscription</legend>
            <p class="blabla">Vous ne possédez pas encore de compte? <br> Veuillez vous inscrire en appuyant sur le bouton "Créer un compte"</p>
            <button id="btncreer" onclick="window.location.href='createAccountServlet';">Créer un compte</button>
        </fieldset>
    </div>
</body>
</html>
