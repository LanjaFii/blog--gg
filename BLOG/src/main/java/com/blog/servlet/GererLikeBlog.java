package com.blog.servlet;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import com.blog.dao.BlogDAO;
import com.blog.tables.Compte;
import com.google.gson.Gson;

@WebServlet("/gererLikeBlog")
public class GererLikeBlog extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("connectedCompte") == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Utilisateur non connecté.");
            return;
        }

        // Récupérer l'objet Compte depuis la session
        Compte compte = (Compte) session.getAttribute("connectedCompte");
        int userId = compte.getIdCompte();

        // Vérifier les paramètres
        String blogIdStr = request.getParameter("blogId");
        String likedStr = request.getParameter("liked");

        if (blogIdStr == null || blogIdStr.isEmpty() || likedStr == null || likedStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Paramètres manquants.");
            return;
        }

        int blogId;
        boolean liked;
        try {
            blogId = Integer.parseInt(blogIdStr);
            liked = Boolean.parseBoolean(likedStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Format de paramètres incorrect.");
            return;
        }

        // Réponse JSON
        boolean success = false;
        try {
            if (liked) {
                BlogDAO.addLikeToBlog(userId, blogId);
                success = true;
            } else {
                BlogDAO.removeLikeFromBlog(userId, blogId);
                success = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Créer une réponse JSON
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(new JsonResponse(success));
        out.write(jsonResponse);
        out.flush();
    }

    // Classe interne pour formater la réponse JSON
    private static class JsonResponse {
        private boolean success;

        public JsonResponse(boolean success) {
            this.success = success;
        }

        public boolean isSuccess() {
            return success;
        }
    }
}