package com.blog.servlet;

import java.io.IOException;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.blog.dao.CompteDAO;

@WebServlet("/servletImage")
public class ServletImage extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id"); // ID de l'image
        byte[] imageBytes = CompteDAO.getImageById(Integer.parseInt(id)); // Méthode pour récupérer l'image depuis la DB
        
        if (imageBytes != null) {
            response.setContentType("image/jpeg"); // ou "image/png" selon le format de l'image
            response.setContentLength(imageBytes.length);
            response.getOutputStream().write(imageBytes);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // Si l'image n'existe pas
        }
    }
}
