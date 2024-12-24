<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="creer.css">
    <title>Créer un Compte</title>
</head>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>

<body>
    <div class="header">
        <h1>${connectedCompte.utilisateur}</h1>
        <h2>Modifier le compte</h2>
    </div>
    <div class="form-container">
        <form action="" method="post" enctype="multipart/form-data">
            <fieldset id="create-account">
                <legend>Modifications</legend>
                <p class="blabla">Veuillez modifier les informations ci-dessous pour effectuer des changements dans votre compte.</p>
                
                <div class="form-group">
                    <label for="username">Nom d'utilisateur:</label>
                    <input type="text" id="username" name="username" value="${usr}" required>
                </div>
                
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" value="${email}" required>
                </div>
                
                <div class="form-group">
                    <label for="old-password">Ancien mot de passe:</label>
                    <input type="password" id="old-password" name="old-password" value="${oldMdp}" required>
                </div>

                <div class="form-group">
                    <label for="password">Nouveau mot de passe:</label>
                    <input type="password" id="password" name="password" value="${motdepasse}" required>
                </div>
                
                <div class="form-group">
                    <label for="confirm-password">Confirmer le nouveau mot de passe:</label>
                    <input type="password" id="confirm-password" name="confirm-password" value="${mdpConf}" required>
                </div>
                
                <div class="form-group">
                    <label>Photo de profil:</label>
                    <div class="file-input-wrapper">
                        <label for="profile-pic" id="pdp">Changer la photo 📷</label>
                        <input type="file" id="profile-pic" name="profile-pic" accept="image/*" onchange="previewImage(event)">
                        <img id="preview" src="servletImage?id=${connectedCompte.idCompte}" alt="">
                    </div>
                </div>
                
                <div class="form-buttons">
                    <button type="submit" id="btnsubmit">Accepter les modifications</button>
                    <button id="btncancel" onclick="window.location.href='apercu';">Annuler</button>
                </div>
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