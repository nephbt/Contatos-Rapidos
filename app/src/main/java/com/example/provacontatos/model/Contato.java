package com.example.provacontatos.model;

public class Contato {
    private String nome;
    private String telefone;
    private String email;
    private String linkedin;
    private boolean favorito;

    public Contato(String nome, String telefone, String email, String linkedin, boolean favorito) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.linkedin = linkedin;
        this.favorito = favorito;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }
}
