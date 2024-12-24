package com.blog.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import com.blog.dao.BlogDAO;
import com.blog.dao.ReponseDAO;
import com.blog.tables.Blog;
import com.blog.tables.Compte;
import com.blog.tables.Reponse;

@WebServlet("/nouvelleReponse")
public class NouvelleReponse extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("connectedCompte") == null) {
		    response.sendRedirect("loginServlet"); // Rediriger vers la page de login
		    return;
		}
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		
		String reponse = request.getParameter("comms");
		String idBlog = request.getParameter("idBlog");
		
		Compte tompony = (Compte) session.getAttribute("connectedCompte");
		
		final Timestamp CURRENT_TIMESTAMP = new Timestamp(System.currentTimeMillis());
		
		Reponse reponseCree = new Reponse(0, tompony.getIdCompte(), Integer.parseInt(idBlog) , reponse, CURRENT_TIMESTAMP, 0, tompony.getPhoto(), tompony.getUtilisateur());
        ReponseDAO.createReponse(reponseCree);
        response.sendRedirect("afficher?id=" + idBlog);
		
	}

}
