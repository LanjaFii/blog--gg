package com.blog.servlet;

import jakarta.servlet.ServletException;

import java.sql.Timestamp;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import com.blog.dao.BlogDAO;
import com.blog.tables.Blog;
import com.blog.tables.Compte;


@MultipartConfig
@WebServlet("/nouveauBlog")
public class NouveauBlog extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session.getAttribute("connectedCompte") == null) {
			response.sendRedirect("loginServlet");
			return;
		}
		request.setAttribute("titre", "");
        request.setAttribute("contenu", "");
        request.setAttribute("tags", "");
        request.getRequestDispatcher("/nouveauBlog.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session.getAttribute("connectedCompte") == null) {
			response.sendRedirect("loginServlet");
			return;
		}
		String titre = request.getParameter("title");
        String categorie = request.getParameter("category");
        String contenu = request.getParameter("content");
        String tags = request.getParameter("tags");
        Part filePart = request.getPart("sary");

        // Validation simple
        request.setAttribute("titre", titre);
        request.setAttribute("contenu", contenu);
        request.setAttribute("tags", tags);
        
        Compte tompony = (Compte) session.getAttribute("connectedCompte");
		
        
        try (InputStream fileContent = filePart.getInputStream()) {
            byte[] photo = fileContent.readAllBytes();
            final Timestamp CURRENT_TIMESTAMP = new Timestamp(System.currentTimeMillis());

            Blog blogCree = new Blog(0, tompony.getIdCompte(), titre, categorie, contenu, tags, CURRENT_TIMESTAMP, photo, 0, tompony.getPhoto(), tompony.getUtilisateur());
            BlogDAO.createBlog(blogCree);
            response.sendRedirect("apercu");
    	} catch (Exception e) {
			throw new ServletException("Erreur lors du traitement du fichier", e);
			
		}
        
	}

}
