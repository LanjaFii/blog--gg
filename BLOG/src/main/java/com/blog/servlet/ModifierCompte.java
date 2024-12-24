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
@WebServlet("/modifierCompte")
public class ModifierCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("connectedCompte") == null) {
		    response.sendRedirect("loginServlet"); // Rediriger vers la page de login
		    return;
		}
		
		Compte hovaina = (Compte) session.getAttribute("connectedCompte");
		
		request.setAttribute("usr", hovaina.getUtilisateur());
        request.setAttribute("email", hovaina.getEmail());
        request.setAttribute("oldMdp", "");
        request.setAttribute("motdepasse", "");
        request.setAttribute("mdpConf", "");
        request.setAttribute("error", "");
        request.setAttribute("photo", hovaina.getPhoto());
		
		request.getRequestDispatcher("/modifCompte.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("connectedCompte") == null) {
		    response.sendRedirect("loginServlet"); // Rediriger vers la page de login
		    return;
		}
		
		String username = request.getParameter("username");
        String email = request.getParameter("email");
        String ancienmdp = request.getParameter("old-password");
        String motdepasse = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm-password");
        Part filePart = request.getPart("profile-pic");
        
        Compte actuel = (Compte) session.getAttribute("connectedCompte");

        // Validation simple
        if (!motdepasse.equals(confirmPassword) || !ancienmdp.equals(actuel.getMdp())) {
        	request.setAttribute("usr", username);
        	request.setAttribute("email", email);
        	request.setAttribute("oldMdp", ancienmdp);
        	request.setAttribute("motdepasse", motdepasse);
        	request.setAttribute("mdpConf", confirmPassword);
        	request.setAttribute("error", "Les mots de passe ne correspondent pas.");
            request.getRequestDispatcher("/modifCompte.jsp").forward(request, response);
        } else if (filePart == null || filePart.getSize() == 0) {
        	try (InputStream fileContent = filePart.getInputStream()) {
	            byte[] photo = fileContent.readAllBytes();
	            Compte compteModifie = new Compte(actuel.getIdCompte(), username, email, motdepasse, photo);
        	
	            CompteDAO.updateCompteSansPhoto(compteModifie);
                session.setAttribute("connectedCompte", CompteDAO.isValidLogin(username, motdepasse));
                response.sendRedirect("apercu");
        	} catch (Exception e) {
				throw new ServletException("Erreur lors du traitement du fichier", e);
				
			}
        } else {
        	try (InputStream fileContent = filePart.getInputStream()) {
	            byte[] photo = fileContent.readAllBytes();
	            Compte compteModifie = new Compte(actuel.getIdCompte(), username, email, motdepasse, photo);
        	
	            CompteDAO.updateCompte(compteModifie);
                session.setAttribute("connectedCompte", CompteDAO.isValidLogin(username, motdepasse));
                response.sendRedirect("apercu");
        	} catch (Exception e) {
				throw new ServletException("Erreur lors du traitement du fichier", e);
				
			}
        }
	}

}
