package com.blog.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.blog.dao.BlogDAO;
import com.blog.dao.CompteDAO;
import com.blog.tables.Compte;

@WebServlet("/supprimerCompte")
public class SupprimerCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("connectedCompte") == null) {
		    response.sendRedirect("loginServlet"); // Rediriger vers la page de login
		    return;
		}
		
		Compte hosupprimena = (Compte) session.getAttribute("connectedCompte");
		CompteDAO.deleteCompteById(hosupprimena.getIdCompte());
		
		if (session != null) {
            session.invalidate();
        }
		response.sendRedirect("loginServlet");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("connectedCompte") == null) {
		    response.sendRedirect("loginServlet"); // Rediriger vers la page de login
		    return;
		}
	}

}
