package com.blog.apercuservlets;

import jakarta.servlet.RequestDispatcher;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import com.blog.dao.BlogDAO;
import com.blog.tables.Blog;
import com.blog.tables.Compte;

@WebServlet("/apercu")
public class Apercu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("connectedCompte") == null) {
            response.sendRedirect("loginServlet"); // Rediriger vers la page de login
            return;
        }

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.

     // Récupérer l'objet Compte depuis la session
        Compte compte = (Compte) session.getAttribute("connectedCompte");

        // Obtenir l'ID du compte
        int userId = compte.getIdCompte();

        // Appel à la méthode DAO pour obtenir la liste des blogs
        List<Blog> blogs = BlogDAO.getBlogByRecent();

        // Obtenir l'état des likes pour chaque blog pour l'utilisateur connecté
        List<Integer> likedBlogs = BlogDAO.getLikedBlogsByUser(userId);

        // Vérification si la liste des blogs n'est pas vide
        if (blogs != null && !blogs.isEmpty()) {
            request.setAttribute("blogs", blogs);
            request.setAttribute("likedBlogs", likedBlogs);
        } else {
            request.setAttribute("message", "Aucun blog trouvé.");
        }

        // Redirection vers la page JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/apercu.jsp");
        dispatcher.forward(request, response);
	}

	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("connectedCompte") == null) {
		    response.sendRedirect("loginServlet"); // Rediriger vers la page de login
		    return;
		}
		request.getRequestDispatcher("apercu").forward(request, response);
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.

	
	}

}
