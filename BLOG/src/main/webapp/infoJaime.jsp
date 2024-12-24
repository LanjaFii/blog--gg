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
    <div class="overlay" id="beta">
        <div class="confirmation-dialog">
            <h2>Blog --GG (Version beta)</h2>
            <p>${connectedCompte.utilisateur}, Cette fonctionnalité n'est pas diponible pour le moment</p>
            <div class="confirmation-buttons">
                <button class="cancel-button" onclick="closeConfirmation()">D'accord</button>
            </div>
        </div>
    </div>

    <script>
        function openConfirmation() {
            document.getElementById('beta').style.display = 'flex';
        }

        function closeConfirmation() {
            document.getElementById('beta').style.display = 'none';
        }

        function confirmDeletion() {
            // Logic to delete the account goes here
            alert('Compte supprimé !');
            closeConfirmation();
        }
    </script>
</body>
</html>