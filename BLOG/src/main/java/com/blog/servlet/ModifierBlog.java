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
import java.sql.Timestamp;

import com.blog.dao.BlogDAO;
import com.blog.tables.Blog;
import com.blog.tables.Compte;

@MultipartConfig
@WebServlet("/modifierBlog")
public class ModifierBlog extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("connectedCompte") == null) {
		    response.sendRedirect("loginServlet"); // Rediriger vers la page de login
		    return;
		}
		
		String idBlog = request.getParameter("id");
		Blog homodifiena = BlogDAO.getBlogById(Integer.parseInt(idBlog));
		
		request.setAttribute("idBlog", idBlog);
		request.setAttribute("title", homodifiena.getTitre());
        request.setAttribute("category", homodifiena.getCategorie());
        request.setAttribute("content", homodifiena.getContenu());
        request.setAttribute("tags", homodifiena.getTags());
		
		request.getRequestDispatcher("/modifierBlog.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("connectedCompte") == null) {
		    response.sendRedirect("loginServlet"); // Rediriger vers la page de login
		    return;
		}
		
		String idBlog = request.getParameter("id");
		int idFinal = Integer.parseInt(idBlog);
		
		String titre = request.getParameter("title");
        String categorie = request.getParameter("category");
        String contenu = request.getParameter("content");
        String tags = request.getParameter("tags");
        Part filePart = request.getPart("sary");
        
        Compte tompony = (Compte) session.getAttribute("connectedCompte");
        
        if ((filePart == null || filePart.getSize() == 0) && (categorie.equals(""))) {
        	try (InputStream fileContent = filePart.getInputStream()) {
                byte[] photo = fileContent.readAllBytes();
                final Timestamp CURRENT_TIMESTAMP = new Timestamp(System.currentTimeMillis());

                Blog blogModifie = new Blog( idFinal, tompony.getIdCompte(), titre, categorie, contenu, tags, CURRENT_TIMESTAMP, null, 0, tompony.getPhoto(), tompony.getUtilisateur());
                BlogDAO.updateBlogNoCategNoCouv(blogModifie);
                response.sendRedirect("afficher?id=" + blogModifie.getIdBlog());
        	} catch (Exception e) {
    			throw new ServletException("Erreur lors du traitement du fichier", e);
    			
    		}
        } else if ((filePart == null || filePart.getSize() == 0) && (!categorie.equals(""))) {
        	try (InputStream fileContent = filePart.getInputStream()) {
                byte[] photo = fileContent.readAllBytes();
                final Timestamp CURRENT_TIMESTAMP = new Timestamp(System.currentTimeMillis());

                Blog blogModifie = new Blog(idFinal, tompony.getIdCompte(), titre, categorie, contenu, tags, CURRENT_TIMESTAMP, null, 0, tompony.getPhoto(), tompony.getUtilisateur());
                BlogDAO.updateBlogNoCouv(blogModifie);
                response.sendRedirect("afficher?id=" + blogModifie.getIdBlog());
        	} catch (Exception e) {
    			throw new ServletException("Erreur lors du traitement du fichier", e);
    			
    		}
        } else if ((filePart.getSize() != 0) && (categorie.equals(""))) {
        	try (InputStream fileContent = filePart.getInputStream()) {
                byte[] photo = fileContent.readAllBytes();
                final Timestamp CURRENT_TIMESTAMP = new Timestamp(System.currentTimeMillis());

                Blog blogModifie = new Blog(idFinal, tompony.getIdCompte(), titre, categorie, contenu, tags, CURRENT_TIMESTAMP, photo, 0, tompony.getPhoto(), tompony.getUtilisateur());
                BlogDAO.updateBlogNoCateg(blogModifie);
                response.sendRedirect("afficher?id=" + blogModifie.getIdBlog());
        	} catch (Exception e) {
    			throw new ServletException("Erreur lors du traitement du fichier", e);
    			
    		}
        } else {
        	try (InputStream fileContent = filePart.getInputStream()) {
                byte[] photo = fileContent.readAllBytes();
                final Timestamp CURRENT_TIMESTAMP = new Timestamp(System.currentTimeMillis());

                Blog blogModifie = new Blog(idFinal, tompony.getIdCompte(), titre, categorie, contenu, tags, CURRENT_TIMESTAMP, photo, 0, tompony.getPhoto(), tompony.getUtilisateur());
                BlogDAO.updateBlog(blogModifie);
                response.sendRedirect("afficher?id=" + blogModifie.getIdBlog());
        	} catch (Exception e) {
    			throw new ServletException("Erreur lors du traitement du fichier", e);
    			
    		}
        }
     
		
	}

}
