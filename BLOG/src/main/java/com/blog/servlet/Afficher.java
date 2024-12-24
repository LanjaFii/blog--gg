package com.blog.servlet;

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
import com.blog.dao.ReponseDAO;
import com.blog.tables.Blog;
import com.blog.tables.Reponse;

@WebServlet("/afficher")
public class Afficher extends HttpServlet {
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
		
		String id = request.getParameter("id");
		
		Blog blogy = BlogDAO.getBlogById( Integer.parseInt(id));
		
		List<Reponse> reponses = ReponseDAO.getReponseByRecent( Integer.parseInt(id) );
		if (reponses != null && !reponses.isEmpty()) {
            // Passer la liste des blogs à la JSP via la requête
            request.setAttribute("reponses", reponses);
        }
		
		if (blogy != null) {
            // Passer le blog à la JSP via la requête
            request.setAttribute("blogy", blogy);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/afficher.jsp");
            dispatcher.forward(request, response);
        }
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
