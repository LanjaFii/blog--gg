<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="creer.css">
    <title>Créer un Compte</title>
</head>
<body>
    <div class="header">
        <h1>Blog --GG</h1>
        <h2>Créer un Compte</h2>
    </div>
    <div class="form-container">
        <form action="createAccountServlet" method="post" enctype="multipart/form-data">
            <fieldset id="create-account">
                <legend>Créer un Compte</legend>
                <p class="blabla">Veuillez remplir les informations ci-dessous pour créer votre compte.</p>
                
                <div class="form-group">
                    <label for="username">Nom d'utilisateur:</label>
                    <input type="text" id="username" name="username" autocomplete="off" autofocus="autofocus" value="${usr}" required>
                </div>
                
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" value="${email}" required>
                </div>
                
                <div class="form-group">
                    <label for="password">Mot de passe:</label>
                    <input type="password" id="password" name="mdpee" value="${motdepasse}" required>
                </div>
                
                <div class="form-group">
                    <label for="confirm-password">Confirmer le mot de passe:</label>
                    <input type="password" id="confirm-password" name="confirm-password" value="${mdpConf}" required>
                </div>
                
                <div class="form-group">
                    <label>Photo de profil:</label>
                    <div class="file-input-wrapper">
                        <label for="profile-pic" id="pdp">Choisir une photo 📷</label>
                        <input type="file" id="profile-pic" name="profile-pic" accept="image/*" onchange="previewImage(event)" required>
                        <img id="preview" src="#" alt="Photo">
                    </div>
                </div>
                
                <div class="form-buttons">
                    <button type="submit" id="btnsubmit">Créer un compte</button>
                    <button type="button" id="btncancel" onclick="window.location.href='loginServlet';">Annuler</button>
                </div>
                <!-- Affichage des messages d'erreur -->
                
                    <p style="color: red;">${error}</p>
                
            </fieldset>
        </form>
    </div>
    <script>
        function previewImage(event) {
            const preview = document.getElementById('preview');
            const file = event.target.files[0];
            const reader = new FileReader();
            
            reader.onload = function() {
                preview.src = reader.result;
            };
            
            if (file) {
                reader.readAsDataURL(file);
            } else {
                preview.src = '#';
            }
        }
    </script>
</body>
</html>
