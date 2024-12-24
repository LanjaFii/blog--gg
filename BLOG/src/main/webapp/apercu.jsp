<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.List" %>
<%@ page import="com.blog.tables.Blog" %>
<%@ page import="java.util.Base64" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Blog Aperçu</title>
    <link rel="stylesheet" href="apercu.css">
    
</head>
<body>
    <%@ include file="fixe.jsp" %>
    <%@ include file="infoJaime.jsp" %>
    <c:choose>
        <c:when test="${not empty blogs}">
            <c:forEach var="blog" items="${blogs}">
                <main>
                    <div class="blog-card">
                        <div class="header">
                            <div class="profile-picture">
                                <img src="servletImage?id=${blog.idCompte}" alt="Profil">
                            </div>
                            <div class="user-info">
                                <h3 id="anarana">${blog.proprio}</h3>
                                <span class="date" id="lera">Publié le <fmt:formatDate value="${blog.dateBlog}" pattern="dd/MM/yyyy" /> à <fmt:formatDate value="${blog.dateBlog}" pattern="HH:mm" /></span>
                            </div>
                        </div>
                        <div class="blog-content">
                            <h2 class="blog-title">${blog.titre}</h2>
                            <p id="contenu">
                                ${blog.contenu}
                            </p>
                        </div>
                        <div class="buttons">
                            <button class="like-button" onclick="openConfirmation()">
                                <i class="fas fa-thumbs-up">${blog.jaime}</i> 👍
                            </button>
                            <button class="participate-button" onclick="window.location.href='afficher?id=${blog.idBlog}';">
                                <i class="fas fa-comments"></i> Participer
                            </button>
                        </div>
                    </div>
                </main>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <h2 style="margin-left: 240px; margin-top: 20px; color: #007BFF;">Aucun blog à afficher.</h2>
        </c:otherwise>
    </c:choose>

    <script>
        document.querySelectorAll('.like-button').forEach(function(button) {
            button.addEventListener('click', function() {
                this.classList.toggle('liked');

                // Trouver le nombre de likes actuel
                let likeCount = parseInt(this.querySelector('i').textContent.trim());

                // Incrémente ou décrémente en fonction de l'état actuel
                if (this.classList.contains('liked')) {
                    likeCount++;
                } else {
                    likeCount--;
                }

                // Mettre à jour le nombre de likes affiché
                this.querySelector('i').textContent = likeCount;
            });
        });

    </script>
    
    
</body>
</html>
