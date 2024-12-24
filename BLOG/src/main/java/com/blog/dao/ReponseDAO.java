package com.blog.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.blog.tables.Reponse;

public class ReponseDAO extends Database{

	public static void createReponse(Reponse reponse) {
        String strSql = "INSERT INTO reponses (id_compte, id_blog, commentaire, date_reponse) VALUES (?, ?, ?, NOW())";
        
        try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword);
             PreparedStatement statement = connection.prepareStatement(strSql)) {

        	statement.setInt(1, reponse.getIdCompte());
            statement.setInt(2, reponse.getIdBlog());
            statement.setString(3, reponse.getCommentaire());

            statement.executeUpdate();

        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
	
	
	
	
	
	public static List<Reponse> getReponseByRecent(int id_blog) {
	    List<Reponse> reponses = new ArrayList<>();
	    String sql = "SELECT reponses.*, comptes.photo, comptes.utilisateur " +
	                 "FROM reponses " +
	                 "JOIN comptes ON reponses.id_compte = comptes.id_compte " +
	                 "JOIN blogs ON reponses.id_blog = blogs.id_blog " +
	                 "WHERE reponses.id_blog = ? " +
	                 "ORDER BY reponses.date_reponse DESC";

	    try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword);
	         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	         
	        // Définir le paramètre id_blog dans la requête
	        preparedStatement.setInt(1, id_blog);
	        
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            while (resultSet.next()) {
	                Reponse reponse = new Reponse(
	                    resultSet.getInt("id_reponse"),
	                    resultSet.getInt("id_compte"),
	                    resultSet.getInt("id_blog"),
	                    resultSet.getString("commentaire"),
	                    resultSet.getTimestamp("date_reponse"),
	                    resultSet.getInt("jaime"),
	                    null,
	                    sql
	                );
	                // Ajoute la photo de profil à la réponse
	                reponse.setMiniature(resultSet.getBytes("photo"));
	                reponse.setProprio(resultSet.getString("utilisateur"));
	                reponses.add(reponse);
	            }
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Optionnel : loggez l'erreur ou lancez une exception personnalisée
	    }
	    
	    return reponses;
	}

	
	
	
	
	
	public static void deleteReponseById( int idReponse ) {
		try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ){
			String strSql = "DELETE FROM reponses WHERE id_reponse=?";
			try ( PreparedStatement statement  = connection.prepareStatement( strSql ) ) {
				statement.setInt( 1, idReponse );
				statement.executeUpdate();
			}
			
		} catch ( Exception exception ) {
			
			throw new RuntimeException( exception );
			
		}
	}
	
	
	
	
	
}
