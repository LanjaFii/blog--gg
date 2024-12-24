package com.blog.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.blog.dao.CompteDAO;
import com.blog.dao.Database;
import com.blog.tables.Compte;

@WebServlet(urlPatterns = "/loginServlet", loadOnStartup = 1)
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
    	Database.init(this.getServletContext());
    }

    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("utilisateur", "");
        request.setAttribute("mdp", "");
        request.setAttribute("msgErreur", "");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String utilisateur = request.getParameter("login");
        String mdp = request.getParameter("mdp");
        
        request.setAttribute("utilisateur", utilisateur);
        request.setAttribute("mdp", mdp);

        Compte connectedCompte = CompteDAO.isValidLogin(utilisateur, mdp);
        if (connectedCompte != null) {
            HttpSession session = request.getSession();
            session.setAttribute("connectedCompte", connectedCompte);
            response.sendRedirect("apercu");
        } else {
            request.setAttribute("msgErreur", "Pas de compte concerné");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

}
