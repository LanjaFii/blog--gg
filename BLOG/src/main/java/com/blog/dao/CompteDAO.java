package com.blog.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.blog.tables.Compte;

public class CompteDAO extends Database{
	
	
	public static int getCompteCount() {
		try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ){

			String strSql = "SELECT count(id_compte) FROM comptes";
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
	
	
	public static Compte getCompteById( int idCompte ) {
		try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ){
			String strSql = "SELECT * FROM comptes WHERE id_compte=?";
			try ( PreparedStatement statement  = connection.prepareStatement( strSql ) ) {
				statement.setInt( 1, idCompte );
				try ( ResultSet resultSet = statement.executeQuery() ) {
					resultSet.next();
					return new Compte(
							resultSet.getInt("id_compte"),
							resultSet.getString( "utilisateur" ),
							resultSet.getString( "email" ),
							resultSet.getString( "mdp" ),
							resultSet.getBytes("photo")
					);
				}
			}
			
		} catch ( Exception exception ) {
			
			throw new RuntimeException( exception );
			
		}
	}
	
	
	
	
	
	
	public static void deleteCompteById( int idCompte ) {
		try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ){
			String strSql = "DELETE FROM comptes WHERE id_compte=?";
			try ( PreparedStatement statement  = connection.prepareStatement( strSql ) ) {
				statement.setInt( 1, idCompte );
				statement.executeUpdate();
			}
			
		} catch ( Exception exception ) {
			
			throw new RuntimeException( exception );
			
		}
	}
	
	
	
	
	
	
	public static void updateCompte( Compte compte ) {
		try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ){

			String strSql = "UPDATE comptes SET utilisateur=?, email=?, mdp=?, photo=? WHERE id_compte=?";
			try ( PreparedStatement statement  = connection.prepareStatement( strSql ) ) {
				statement.setString( 1, compte.getUtilisateur() );
				statement.setString( 2, compte.getEmail() );
				statement.setString( 3, compte.getMdp() );
				statement.setBytes( 4, compte.getPhoto() );
				statement.setInt( 5, compte.getIdCompte() );
				statement.executeUpdate();
			}
			
		} catch ( Exception exception ) {
			
			throw new RuntimeException( exception );
			
		}
	}
	
	
	
	public static void updateCompteSansPhoto( Compte compte ) {
		try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ){

			String strSql = "UPDATE comptes SET utilisateur=?, email=?, mdp=? WHERE id_compte=?";
			try ( PreparedStatement statement  = connection.prepareStatement( strSql ) ) {
				statement.setString( 1, compte.getUtilisateur() );
				statement.setString( 2, compte.getEmail() );
				statement.setString( 3, compte.getMdp() );
				statement.setInt( 4, compte.getIdCompte() );
				statement.executeUpdate();
			}
			
		} catch ( Exception exception ) {
			
			throw new RuntimeException( exception );
			
		}
	}
	
	
	
	
	
	
	public static void createCompte( Compte compte ) {
		try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ){
			String strSql = "INSERT INTO comptes (utilisateur, email, mdp, photo) VALUES (?, ?, ?, ?)";
			try ( PreparedStatement statement  = connection.prepareStatement( strSql ) ) {
				statement.setString( 1, compte.getUtilisateur() );
				statement.setString( 2, compte.getEmail() );
				statement.setString( 3, compte.getMdp() );
				statement.setBytes( 4, compte.getPhoto() );
				statement.executeUpdate();
			}
			
		} catch ( Exception exception ) {
			
			throw new RuntimeException( exception );
			
		}
	}

	
	public static Compte isValidLogin( String login, String password ) {
		try ( Connection connection = DriverManager.getConnection( dbURL, dbLogin, dbPassword ) ) {
			//String strSql = "SELECT * FROM T_Users WHERE login='" + login + "' AND password='" + password + "'";
			String strSql = "SELECT * FROM comptes WHERE utilisateur=? AND mdp=?";
			try ( PreparedStatement statement  = connection.prepareStatement( strSql ) ) {
				statement.setString( 1, login );
				statement.setString( 2, password );
				try ( ResultSet resultSet = statement.executeQuery() ) {
					if ( resultSet.next() ) {
						return new Compte(
								resultSet.getInt( "id_compte" ),
								resultSet.getString( "utilisateur" ),
								resultSet.getString( "email" ),
								resultSet.getString( "mdp" ),
								resultSet.getBytes( "photo" )
						);
					} else {
						return null;
					}
				}
			}
		} catch ( Exception exception ) {
			throw new RuntimeException( exception );
		}
	}
	
	
	
	public static byte[] getImageById(int id) {
	    try (Connection connection = DriverManager.getConnection(dbURL, dbLogin, dbPassword)) {
	        String sql = "SELECT photo FROM comptes WHERE id_compte = ?";
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setInt(1, id);
	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next()) {
	                    return resultSet.getBytes("photo");
	                }
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	
	}
}