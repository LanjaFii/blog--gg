package com.blog.dao;

import java.sql.Connection;

import java.sql.Timestamp;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.blog.tables.Blog;

public class BlogDAO extends Database{
	
	public static int getBlogCount() {
		try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ){

			String strSql = "SELECT count(id_blog) FROM blogs";
			try ( Statement statement  = connection.createStatement() ) {
				try ( ResultSet resultSet = statement.executeQuery( strSql ) ) {
					resultSet.next();
					return resultSet.getInt( 1 );
				}
			}
			
		} catch ( Exception exception ) {
			
			throw new RuntimeException( exception );
			
		}
	}
	
	
	public static Blog getBlogById( int idBlog ) {
		try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ){
			String strSql = "SELECT blogs.*, comptes.photo, comptes.utilisateur FROM blogs JOIN comptes ON blogs.id_compte = comptes.id_compte WHERE blogs.id_blog = ?";
			try ( PreparedStatement statement  = connection.prepareStatement( strSql ) ) {
				statement.setInt( 1, idBlog );
				try ( ResultSet resultSet = statement.executeQuery() ) {
					resultSet.next();
					return new Blog(
							resultSet.getInt("id_blog"),
							resultSet.getInt("id_compte"),
							resultSet.getString( "titre" ),
							resultSet.getString( "categorie" ),
							resultSet.getString( "contenu" ),
							resultSet.getString( "tags" ),
							resultSet.getTimestamp("date_blog"),
							resultSet.getBytes("couverture"),
							resultSet.getInt( "jaime" ),
							resultSet.getBytes("photo"),
							resultSet.getString("utilisateur")
					);
				}
			}
			
		} catch ( Exception exception ) {
			
			throw new RuntimeException( exception );
			
		}
	}
	
	
	
		
	public static List<Blog> getBlogByRecent() {
        List<Blog> blogs = new ArrayList<>();
        String sql = "SELECT blogs.*, comptes.photo, comptes.utilisateur FROM blogs JOIN comptes ON blogs.id_compte = comptes.id_compte ORDER BY blogs.date_blog DESC";
        
        try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword);
        		PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()) {
             
            while (resultSet.next()) {
                Blog blog = new Blog(
                    resultSet.getInt("id_blog"),
                    resultSet.getInt("id_compte"),
                    resultSet.getString("titre"),
                    resultSet.getString("categorie"),
                    resultSet.getString("contenu"),
                    resultSet.getString("tags"),
                    resultSet.getTimestamp("date_blog"),
                    resultSet.getBytes("couverture"),
                    resultSet.getInt("jaime"), null, sql
                );
                // Ajoute la photo de profil au blog
                blog.setMiniature(resultSet.getBytes("photo"));
                blog.setProprio(resultSet.getString("utilisateur"));
                blogs.add(blog);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            // Optionnel : loggez l'erreur ou lancez une exception personnalisée
        }
        
        return blogs;
    }
		
		

	

	public static List<Blog> getMyBlogs( int idCompte ) {
        List<Blog> blogs = new ArrayList<>();
        String sql = "SELECT blogs.*, comptes.photo, comptes.utilisateur FROM blogs JOIN comptes ON blogs.id_compte = comptes.id_compte WHERE comptes.id_compte = ? ORDER BY blogs.date_blog DESC";
        try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)){
        		try( PreparedStatement statement  = connection.prepareStatement( sql ) ) {
        			statement.setInt( 1, idCompte );
        			try ( ResultSet resultSet = statement.executeQuery() ) {
        				while (resultSet.next()) {
        	                Blog blog = new Blog(
        	                    resultSet.getInt("id_blog"),
        	                    resultSet.getInt("id_compte"),
        	                    resultSet.getString("titre"),
        	                    resultSet.getString("categorie"),
        	                    resultSet.getString("contenu"),
        	                    resultSet.getString("tags"),
        	                    resultSet.getTimestamp("date_blog"),
        	                    resultSet.getBytes("couverture"),
        	                    resultSet.getInt("jaime"), null, sql
        	                );	
        	                blog.setMiniature(resultSet.getBytes("photo"));
        	                blog.setProprio(resultSet.getString("utilisateur"));
        	                blogs.add(blog);	
        				}
        			}
        		}
		}catch ( Exception exception ) {
			
			throw new RuntimeException( exception );		
		}
       
        return blogs;
    }
	
	
	
	
	
	public static List<Blog> getMyBlogsPaticipe( int idCompte ) {
        List<Blog> blogs = new ArrayList<>();
        String sql = "SELECT DISTINCT b.*, comptes.photo, comptes.utilisateur " +
                "FROM blogs b " +
                "JOIN comptes ON b.id_compte = comptes.id_compte " +
                "JOIN reponses r ON b.id_blog = r.id_blog " +
                "WHERE r.id_compte = ? " +
                "ORDER BY b.date_blog DESC";
        try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)){
        		try( PreparedStatement statement  = connection.prepareStatement( sql ) ) {
        			statement.setInt( 1, idCompte );
        			try ( ResultSet resultSet = statement.executeQuery() ) {
        				while (resultSet.next()) {
        	                Blog blog = new Blog(
        	                    resultSet.getInt("id_blog"),
        	                    resultSet.getInt("id_compte"),
        	                    resultSet.getString("titre"),
        	                    resultSet.getString("categorie"),
        	                    resultSet.getString("contenu"),
        	                    resultSet.getString("tags"),
        	                    resultSet.getTimestamp("date_blog"),
        	                    resultSet.getBytes("couverture"),
        	                    resultSet.getInt("jaime"), null, sql
        	                );	
        	                blog.setMiniature(resultSet.getBytes("photo"));
        	                blog.setProprio(resultSet.getString("utilisateur"));
        	                blogs.add(blog);	
        				}
        			}
        		}
		}catch ( Exception exception ) {
			
			throw new RuntimeException( exception );		
		}
       
        return blogs;
    }
	
	
	
	
	
	
	
	
	public static List<Blog> getBlogsByCategorie( String categ ) {
        List<Blog> blogs = new ArrayList<>();
        String sql = "SELECT blogs.*, comptes.photo, comptes.utilisateur FROM blogs JOIN comptes ON blogs.id_compte = comptes.id_compte WHERE blogs.categorie = ? ORDER BY blogs.date_blog DESC";
        try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)){
        		try( PreparedStatement statement  = connection.prepareStatement( sql ) ) {
        			statement.setString( 1, categ );
        			try ( ResultSet resultSet = statement.executeQuery() ) {
        				while (resultSet.next()) {
        	                Blog blog = new Blog(
        	                    resultSet.getInt("id_blog"),
        	                    resultSet.getInt("id_compte"),
        	                    resultSet.getString("titre"),
        	                    resultSet.getString("categorie"),
        	                    resultSet.getString("contenu"),
        	                    resultSet.getString("tags"),
        	                    resultSet.getTimestamp("date_blog"),
        	                    resultSet.getBytes("couverture"),
        	                    resultSet.getInt("jaime"), null, sql
        	                );	
        	                blog.setMiniature(resultSet.getBytes("photo"));
        	                blog.setProprio(resultSet.getString("utilisateur"));
        	                blogs.add(blog);	
        				}
        			}
        		}
		}catch ( Exception exception ) {
			
			throw new RuntimeException( exception );		
		}
       
        return blogs;
    }


	
	
	
	
	
	
	public static void updateBlog( Blog blog ) {
		try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ){

			String strSql = "UPDATE blogs SET titre=?, categorie=?, contenu=?, tags=?, couverture=? WHERE id_blog=?";
			try ( PreparedStatement statement  = connection.prepareStatement( strSql ) ) {
				statement.setString( 1, blog.getTitre() );
				statement.setString( 2, blog.getCategorie() );
				statement.setString( 3, blog.getContenu() );
				statement.setString( 4, blog.getTags() );
				statement.setBytes( 5, blog.getCouverture() );
				statement.setInt( 6, blog.getIdBlog() );
				statement.executeUpdate();
			}
			
		} catch ( Exception exception ) {
			throw new RuntimeException( exception );
		}
	}
	
	
	
	
	
	public static void updateBlogNoCateg( Blog blog ) {
		try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ){

			String strSql = "UPDATE blogs SET titre=?, contenu=?, tags=?, couverture=? WHERE id_blog=?";
			try ( PreparedStatement statement  = connection.prepareStatement( strSql ) ) {
				statement.setString( 1, blog.getTitre() );
				statement.setString( 2, blog.getContenu() );
				statement.setString( 3, blog.getTags() );
				statement.setBytes( 4, blog.getCouverture() );
				statement.setInt( 5, blog.getIdBlog() );
				statement.executeUpdate();
			}
			
		} catch ( Exception exception ) {
			throw new RuntimeException( exception );
		}
	}
	
	
	
	
	
	public static void updateBlogNoCouv( Blog blog ) {
		try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ){

			String strSql = "UPDATE blogs SET titre=?, categorie=?, contenu=?, tags=? WHERE id_blog=?";
			try ( PreparedStatement statement  = connection.prepareStatement( strSql ) ) {
				statement.setString( 1, blog.getTitre() );
				statement.setString( 2, blog.getCategorie() );
				statement.setString( 3, blog.getContenu() );
				statement.setString( 4, blog.getTags() );
				statement.setInt( 5, blog.getIdBlog() );
				statement.executeUpdate();
			}
			
		} catch ( Exception exception ) {
			throw new RuntimeException( exception );
		}
	}
	
	
	
	
	
	public static void updateBlogNoCategNoCouv( Blog blog ) {
		try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ){

			String strSql = "UPDATE blogs SET titre=?, contenu=?, tags=? WHERE id_blog=?";
			try ( PreparedStatement statement  = connection.prepareStatement( strSql ) ) {
				statement.setString( 1, blog.getTitre() );
				statement.setString( 2, blog.getContenu() );
				statement.setString( 3, blog.getTags() );
				statement.setInt( 4, blog.getIdBlog() );
				statement.executeUpdate();
			}
			
		} catch ( Exception exception ) {
			throw new RuntimeException( exception );
		}
	}
	

	
	
	
	
	
	
	
	public static void createBlog(Blog blog) {
        String strSql = "INSERT INTO blogs (id_compte, titre, categorie, contenu, tags, date_blog, couverture) VALUES (?, ?, ?, ?, ?, NOW(), ?)";
        
        try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword);
             PreparedStatement statement = connection.prepareStatement(strSql)) {

        	statement.setInt(1, blog.getIdCompte());
            statement.setString(2, blog.getTitre());
            statement.setString(3, blog.getCategorie());
            statement.setString(4, blog.getContenu());
            statement.setString(5, blog.getTags());
            statement.setBytes(6, blog.getCouverture());

            statement.executeUpdate();

        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
	
	
	
	
	public static byte[] getImageById(int id) {
	    try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
	        String sql = "SELECT couverture FROM blogs WHERE id_blog = ?";
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setInt(1, id);
	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next()) {
	                    return resultSet.getBytes("couverture");
	                }
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	
	}
	
	
	
	
	
	
	
	public static Blog getBlogPopulaire() {
		try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ){
			String strSql = "SELECT blogs.*, comptes.photo, comptes.utilisateur FROM blogs JOIN comptes ON blogs.id_compte = comptes.id_compte ORDER BY jaime DESC LIMIT 1";
			try ( PreparedStatement statement  = connection.prepareStatement( strSql ) ) {
				try ( ResultSet resultSet = statement.executeQuery() ) {
					resultSet.next();
					return new Blog(
							resultSet.getInt("id_blog"),
							resultSet.getInt("id_compte"),
							resultSet.getString( "titre" ),
							resultSet.getString( "categorie" ),
							resultSet.getString( "contenu" ),
							resultSet.getString( "tags" ),
							resultSet.getTimestamp("date_blog"),
							resultSet.getBytes("couverture"),
							resultSet.getInt( "jaime" ),
							resultSet.getBytes("photo"),
							resultSet.getString("utilisateur")
					);
				}
			}
			
		} catch ( Exception exception ) {
			
			throw new RuntimeException( exception );
			
		}
	}
	
	
	
	
	
	
	
	public static void deleteBlogById( int idBlog ) {
		try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ){
			String strSql = "DELETE FROM blogs WHERE id_blog=?";
			try ( PreparedStatement statement  = connection.prepareStatement( strSql ) ) {
				statement.setInt( 1, idBlog );
				statement.executeUpdate();
			}
			
		} catch ( Exception exception ) {
			
			throw new RuntimeException( exception );
			
		}
	}
	
	
	
	
	
	
	// Récupérer les blogs aimés par un utilisateur
    public static List<Integer> getLikedBlogsByUser(int userId) {
        List<Integer> likedBlogs = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword);
             PreparedStatement stmt = connection.prepareStatement("SELECT id_blog FROM likes_blog WHERE id_compte = ?")) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                likedBlogs.add(rs.getInt("id_blog"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return likedBlogs;
    }

    // Ajouter un like à un blog
    public static void addLikeToBlog(int userId, int blogId) {
        try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword);
             PreparedStatement stmt = connection.prepareStatement("INSERT INTO likes_blog (id_compte, id_blog) VALUES (?, ?)")) {
            stmt.setInt(1, userId);
            stmt.setInt(2, blogId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Supprimer un like d'un blog
    public static void removeLikeFromBlog(int userId, int blogId) {
        try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword);
             PreparedStatement stmt = connection.prepareStatement("DELETE FROM likes_blog WHERE id_compte = ? AND id_blog = ?")) {
            stmt.setInt(1, userId);
            stmt.setInt(2, blogId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	
	
}