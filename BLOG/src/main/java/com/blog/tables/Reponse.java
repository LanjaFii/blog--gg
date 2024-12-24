package com.blog.tables;

import java.sql.Timestamp;

public class Reponse {
	private int idReponse;
	private int idCompte;
	private int idBlog;
	private String commentaire;
	private Timestamp dateReponse;
	private int jaime;
	private byte[] miniature;
	private String proprio;
	
		
	public Reponse(int id_reponse, int id_compte, int id_blog, String commentaire, Timestamp date_reponse, int jaime, byte[] miniature, String proprio) {
		super();
		this.idReponse = id_reponse;
		this.idCompte = id_compte;
		this.idBlog = id_blog;
		this.commentaire = commentaire;
		this.dateReponse = date_reponse;
		this.jaime = jaime;
		this.miniature = miniature;
		this.proprio = proprio;
	}

	
	// Getters et setters

	public int getIdReponse() {
		return idReponse;
	}


	public void setIdReponse(int idReponse) {
		this.idReponse = idReponse;
	}


	public int getIdCompte() {
		return idCompte;
	}


	public void setIdCompte(int idCompte) {
		this.idCompte = idCompte;
	}


	public int getIdBlog() {
		return idBlog;
	}


	public void setIdBlog(int idBlog) {
		this.idBlog = idBlog;
	}


	public String getCommentaire() {
		return commentaire;
	}


	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}


	public Timestamp getDateReponse() {
		return dateReponse;
	}


	public void setDateReponse(Timestamp dateReponse) {
		this.dateReponse = dateReponse;
	}


	public int getJaime() {
		return jaime;
	}


	public void setJaime(int jaime) {
		this.jaime = jaime;
	}


	public byte[] getMiniature() {
		return miniature;
	}


	public void setMiniature(byte[] miniature) {
		this.miniature = miniature;
	}


	public String getProprio() {
		return proprio;
	}


	public void setProprio(String proprio) {
		this.proprio = proprio;
	}	
	

		

}
