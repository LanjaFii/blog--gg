<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.blog.tables.Blog" %>
<%@ page import="com.blog.tables.Compte" %>
<%@ page import="java.util.Base64" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Blog Aperçu</title>
    <link rel="stylesheet" href="afficher.css">
</head>
<body>
	<%@ include file="fixe.jsp" %>
	
    <main>
        <div class="blog-container">
        
        	<div id="blaogy">
            <div class="blog-header">
                <div class="profile-info">
                    <div class="profile-picture">
                        <img src="servletImage?id=${blogy.idCompte}" alt="">
                    </div>
                    <div class="user-details">
                        <h3>${blogy.proprio}</h3>
                        <span>Publié le <fmt:formatDate value="${blogy.dateBlog}" pattern="dd/MM/yyyy" /> à <fmt:formatDate value="${blogy.dateBlog}" pattern="HH:mm" /></span>
                    </div>
                </div>
                
                <%
	                
	             	// Récupérer l'objet Blog depuis l'attribut de la requête
	                Blog blogy = (Blog) request.getAttribute("blogy");
                	
                	Compte iza = (Compte) session.getAttribute("connectedCompte");
                	int id_iza = iza.getIdCompte();
                	if(id_iza == blogy.getIdCompte()){
                %>
                
                <div class="blog-menu">
                    <button class="menu-button">...</button>
                    <div class="dropdown-menu">
                        <button class="dropdown-item" onclick="window.location.href='modifierBlog?id=${blogy.idBlog}'">Modifier</button>
                        <button class="dropdown-item" onclick="openConfirmationBlog()">Supprimer</button>
                    </div>
                </div>
                
                <%
                	}
                %>
                
            </div>
            
            
            <div class="blog-content">
                <h2 class="blog-title">${blogy.titre}</h2>
                <p class="category">Catégorie: <span id="categ">${blogy.categorie}</span></p>
                <img src="servletImageCouverture?id=${blogy.idBlog}" alt="" class="cover-image"><br>
                <c:forEach var="tag" items="${fn:split(blogy.tags, ';')}">
				    <p class="tags">#${tag}</p>
				</c:forEach>
                <p class="content">${blogy.contenu}</p>
            </div>
            <div class="blog-footer">
                <button class="like-button"><i class="fas fa-thumbs-up"></i> <span class="like-count">${blogy.jaime}</span> 👍</button>
            </div>
            
            
            <form action="nouvelleReponse?idBlog=${blogy.idBlog}" method="POST">
            	<div class="input-group">
                    <label for="content">Donnez votre avis</label>
                    <textarea id="content" name="comms" placeholder="Commencez à écrire ici..." rows="4" required></textarea>
            	</div>
            	<div class="button-group">
                    <button type="submit" class="btn-submit">Répondre</button>
            	</div>
            </form>
            
           <div class="overlay" id="confirmationBlog-overlay">
				<div class="confirmation-dialog">
					<h2>Confirmation de Suppression</h2>
					<p>${connectedCompte.utilisateur}, Confirmez la suppression de votre Blog "${blogy.titre}" <br> L'action est irréversible.</p>
					<div class="confirmation-buttons">
						<button class="cancel-button" onclick="closeConfirmationBlog()">Annuler</button>
						<button class="confirm-button" onclick="window.location.href='supprimerBlog?id=${blogy.idBlog}';">Supprimer</button>
					</div>
				</div>
			</div> 
            
            </div>
            
            
            
            
            
            
            
            
            <div class="responses-section">
                <h3>Réponses</h3>   
		            <c:choose>
		            	<c:when test="${not empty reponses}">
		            		<c:forEach var="reponse" items="${reponses}">
		            			<div class="response-card">
				                    <div class="response-header">				                    	
				                    	<div class="profile-picture">
					                        <img src="servletImage?id=${reponse.idCompte}" alt="Profil">
					                    </div>
					                    <div class="user-details">
					                        <h4>${reponse.proprio}</h4>
					                    	<span>Participation du <fmt:formatDate value="${reponse.dateReponse}" pattern="dd/MM/yyyy" /> à <fmt:formatDate value="${reponse.dateReponse}" pattern="HH:mm" /></span>
					                    </div>
					                    <!-- Vérification de l'utilisateur connecté -->
				                        <c:if test="${connectedCompte.idCompte == blogy.idCompte or connectedCompte.idCompte == reponse.idCompte}">
				                            <div class="blog-menu">
				                                <button class="menu-button">...</button>
				                                <div class="dropdown-menu">
				                                    <button class="dropdown-item" onclick="openConfirmationReponse(${reponse.idReponse})">Supprimer</button>
				                                </div>
				                            </div>
				                        </c:if>
				                        
				                    </div>
				                    <div class="response-content">
				                        <p>${reponse.commentaire}</p>
				                    </div>
				                    <div class="response-footer">
				                        <button class="like-button"><i class="fas fa-thumbs-up"></i> <span class="like-count">${reponse.jaime}</span> 👍</button>
				                    </div>
			                		<div class="overlay" id="confirmationReponse-overlay-${reponse.idReponse}">
								        <div class="confirmation-dialog">
								            <h2>Confirmation de Suppression</h2>
								            <p>${connectedCompte.utilisateur}, Confirmez la suppression de votre réponse <br> Action irréversible.</p>
								            <div class="confirmation-buttons">
								                <button class="cancel-button" onclick="closeConfirmationReponse()">Annuler</button>
								                <button class="confirm-button" onclick="window.location.href='supprimerReponse?id=${reponse.idReponse}&id2=${blogy.idBlog}';">Supprimer</button>
								            </div>
								        </div>
								    </div>
			                	</div>       	
		            		</c:forEach>
		            	</c:when>
		            	<c:otherwise>
		            		<p>Aucune réponse à afficher pour le moment.</p>
		        		</c:otherwise>
		            </c:choose>
            </div>
            
            
            
        </div>
    </main>
    
    <script>
        document.querySelectorAll('.like-button').forEach(button => {
            button.addEventListener('click', function() {
                this.classList.toggle('liked');
                const likeCount = this.querySelector('.like-count');
                if (this.classList.contains('liked')) {
                    likeCount.textContent = parseInt(likeCount.textContent) + 1;
                } else {
                    likeCount.textContent = parseInt(likeCount.textContent) - 1;
                }
            });
        });

        document.querySelectorAll('.menu-button').forEach(button => {
            button.addEventListener('click', function() {
                this.nextElementSibling.classList.toggle('show');
            });
        });

        window.addEventListener('click', function(e) {
            if (!e.target.matches('.menu-button')) {
                document.querySelectorAll('.dropdown-menu').forEach(menu => {
                    if (menu.classList.contains('show')) {
                        menu.classList.remove('show');
                    }
                });
            }
        });
        
        
        
        
        
        function openConfirmationReponse(id) {
            document.getElementById('confirmationReponse-overlay-'+id).style.display = 'flex';
        }
        
        function openConfirmationBlog() {
            document.getElementById('confirmationBlog-overlay').style.display = 'flex';
        }

        function closeConfirmationReponse() {
            document.getElementById('confirmationReponse-overlay-'+id).style.display = 'none';
        }
        
        function closeConfirmationBlog() {
            document.getElementById('confirmationBlog-overlay').style.display = 'none';
        }

        function confirmDeletion() {
            // Logic to delete the account goes here
            alert('Compte supprimé !');
            closeConfirmationReponse();
        }

    </script>
    
</body>
</html>
