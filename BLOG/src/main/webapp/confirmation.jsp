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
    <div class="overlay" id="confirmation-overlay">
        <div class="confirmation-dialog">
            <h2>Confirmation de Suppression</h2>
            <p>${connectedCompte.utilisateur}, Êtes-vous sûr de vouloir supprimer votre compte ? <br> Si vouz appuillez sur "Supprimer" votre compte disparaitra tout de suite de Blog --GG. <br> Cette action est irréversible.</p>
            <div class="confirmation-buttons">
                <button class="cancel-button" onclick="closeConfirmation()">Annuler</button>
                <button class="confirm-button" onclick="window.location.href='supprimerCompte';">Supprimer</button>
            </div>
        </div>
    </div>

    <script>
        function openConfirmation() {
            document.getElementById('confirmation-overlay').style.display = 'flex';
        }

        function closeConfirmation() {
            document.getElementById('confirmation-overlay').style.display = 'none';
        }

        function confirmDeletion() {
            // Logic to delete the account goes here
            alert('Compte supprimé !');
            closeConfirmation();
        }
    </script>
</body>
</html>