<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Confirmation de Suppression</title>
    <link rel="stylesheet" href="confirmation.css">
</head>
<body>
    <div class="overlay" id="confirmationReponse-overlay">
        <div class="confirmation-dialog">
            <h2>Confirmation de Suppression</h2>
            <p>${connectedCompte.utilisateur}, Confirmez la suppression de votre réponse <br> Action irréversible.</p>
            <div class="confirmation-buttons">
                <button class="cancel-button" onclick="closeConfirmationReponse()">Annuler</button>
                <button class="confirm-button" onclick="window.location.href='supprimerReponse?id=${reponse.idReponse}&id2=${blogy.idBlog}';">Supprimer</button>
            </div>
        </div>
    </div>

    <script>
        function openConfirmationReponse() {
            document.getElementById('confirmationReponse-overlay').style.display = 'flex';
        }

        function closeConfirmationReponse() {
            document.getElementById('confirmationReponse-overlay').style.display = 'none';
        }

        function confirmDeletion() {
            // Logic to delete the account goes here
            alert('Compte supprimé !');
            closeConfirmationReponse();
        }
    </script>
</body>
</html>