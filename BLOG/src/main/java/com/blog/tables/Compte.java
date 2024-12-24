package com.blog.tables;

public class Compte {
    private int idCompte;
    private String utilisateur;
    private String email;
    private String mdp;
    private byte[] photo;

    public Compte(int idCompte, String utilisateur, String email, String mdp, byte[] photo) {
        this.idCompte = idCompte;
        this.utilisateur = utilisateur;
        this.email = email;
        this.mdp = mdp;
        this.photo = photo;
    }

    // Getters et Setters
    public int getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(int idCompte) {
        this.idCompte = idCompte;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}

