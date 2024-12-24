<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Créer un Sujet de Blog</title>
    <link rel="stylesheet" href="nouveauBlog.css">
</head>
<body>
<%@ include file="fixe.jsp" %>

    <main>
        <section class="creation-container">
            <h1>Créer un Nouveau Sujet de Blog</h1>
            <form action="nouveauBlog" method="POST" class="form-container" enctype="multipart/form-data">
                <div class="input-group">
                    <label for="title">Titre du Sujet</label>
                    <input type="text" id="title" name="title" placeholder="Entrez le titre ici" value="${titre}" required>
                </div>
                
                <div class="input-group">
                    <label for="category">Catégorie</label>
                    <select id="category" name="category" required>
                        <option value="">Sélectionner une catégorie</option>
                        <option value="people">People</option>
                        <option value="film">Film</option>
                        <option value="sport">Sport</option>
                        <option value="actualite">Actualité</option>
                        <option value="developpement">Développement</option>
                        <option value="technologie">Technologie</option>
                        <option value="politique">Politique</option>
                        <option value="divertissement">Divertissement</option>
                        <option value="autres">Autres</option>
                    </select>
                </div>

                <div class="input-group">
                    <label for="content">Contenu</label>
                    <textarea id="content" name="content" placeholder="Commencez à écrire ici..." rows="10" required>${contenu}</textarea>
                </div>

                <div class="input-group">
                    <label for="tags">Tags</label>
                    <input type="text" id="tags" name="tags" value="${tags}" placeholder="Entrez les tags séparés par des virgules">
                </div>

                <div class="input-group">
                    <label for="sary">Image de Couverture</label>
                    <input type="file" id="sary" name="sary" accept="image/*">
                </div>

                <div class="button-group">
                    <button type="submit" class="btn-submit">Publier</button>
                    <button type="reset" class="btn-reset">Réinitialiser</button>
                </div>
            </form>
        </section>
    </main>
</body>
</html>