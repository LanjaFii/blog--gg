package com.blog.apercuservlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.blog.dao.BlogDAO;
import com.blog.tables.Blog;

@WebServlet("/blogPopulaire")
public class BlogPopulaire extends HttpServlet {
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
		
		Blog blog = BlogDAO.getBlogPopulaire();
		
		int id_populaire = blog.getIdBlog();
		
		response.sendRedirect("afficher?id=" + id_populaire);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("connectedCompte") == null) {
		    response.sendRedirect("loginServlet"); // Rediriger vers la page de login
		    return;
		}
	}

}
