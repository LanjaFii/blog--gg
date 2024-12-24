package com.blog.tables;

import java.sql.Timestamp;

public class Blog {
    private int idBlog;
    private int idCompte;
    private String titre;
    private String categorie;
    private String contenu;
    private String tags;
    private Timestamp dateBlog;
    private byte[] couverture;
    private int jaime;
    private byte[] miniature;
    private String proprio;

    public Blog(int idBlog, int idCompte, String titre, String categorie, String contenu, String tags, Timestamp dateBlog, byte[] couverture, int jaime, byte[] miniature, String proprio) {
        this.idBlog = idBlog;
        this.idCompte = idCompte;
        this.titre = titre;
        this.categorie = categorie;
        this.contenu = contenu;
        this.tags = tags;
        this.dateBlog = dateBlog;
        this.couverture = couverture;
        this.jaime = jaime;
        this.miniature = miniature;
        this.proprio = proprio;
    }
    
    

	// Getters et Setters





	public int getIdBlog() {
		return idBlog;
	}

	public void setIdBlog(int idBlog) {
		this.idBlog = idBlog;
	}

	public int getIdCompte() {
		return idCompte;
	}

	public void setIdCompte(int idCompte) {
		this.idCompte = idCompte;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Timestamp getDateBlog() {
		return dateBlog;
	}

	public void setDateBlog(Timestamp dateBlog) {
		this.dateBlog = dateBlog;
	}

	public byte[] getCouverture() {
		return couverture;
	}

	public void setCouverture(byte[] couverture) {
		this.couverture = couverture;
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

