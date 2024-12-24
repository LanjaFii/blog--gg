package com.blog.servlet;


import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;

import com.blog.dao.CompteDAO;
import com.blog.tables.Compte;

@MultipartConfig
@WebServlet("/createAccountServlet")
public class CreateAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("usr", "");
        request.setAttribute("email", "");
        request.setAttribute("motdepasse", "");
        request.setAttribute("mdpConf", "");
        request.setAttribute("photo", null);
        request.getRequestDispatcher("/creer.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String motdepasse = request.getParameter("mdpee");
        String confirmPassword = request.getParameter("confirm-password");
        Part filePart = request.getPart("profile-pic");

        // Validation simple
        if (!motdepasse.equals(confirmPassword)) {
        	request.setAttribute("usr", username);
        	request.setAttribute("email", email);
        	request.setAttribute("motdepasse", motdepasse);
        	request.setAttribute("mdpConf", confirmPassword);
        	request.setAttribute("error", "Les mots de passe ne correspondent pas.");
            request.getRequestDispatcher("/creer.jsp").forward(request, response);
        } else if (filePart == null) {
        	request.setAttribute("usr", username);
        	request.setAttribute("email", email);
        	request.setAttribute("motdepasse", motdepasse);
        	request.setAttribute("mdpConf", confirmPassword);
        	request.setAttribute("error", "Pas de photo de profil." + username);
            request.getRequestDispatcher("/creer.jsp").forward(request, response);
        } else {
        	try (InputStream fileContent = filePart.getInputStream()) {
	            byte[] photo = fileContent.readAllBytes();
	            Compte compteCree = new Compte(0, username, email, motdepasse, photo);
        	
	            CompteDAO.createCompte(compteCree);
                HttpSession session = request.getSession(true);
                session.setAttribute("connectedCompte", CompteDAO.isValidLogin(username, motdepasse));
                response.sendRedirect("apercu");
        	} catch (Exception e) {
				throw new ServletException("Erreur lors du traitement du fichier", e);
				
			}
        }
    }
}
