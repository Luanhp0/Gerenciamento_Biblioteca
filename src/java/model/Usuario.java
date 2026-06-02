/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author drak
 */
public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String role;
    private String tipoUsuario;
    private String celular;

    public Usuario(int id, String nome, String email, String senha, String role, String tipoUsuario, String celular ) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.role = role;
        this.tipoUsuario = tipoUsuario;
        this.celular = celular;
    }
    
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }
    public String getRole() { return role; }
    public String getTipoUsuario() { return tipoUsuario; }
    public String getCelular(){return celular;}
    public int getIdRole() {
        switch (role) {
            case "ADMIN": return 1;
            case "BIBLIOTECARIO": return 2;
            case "LEITOR": return 3;
            default: return 3;
        }
    }
    
    public int calcular() {

        switch (tipoUsuario.toUpperCase()) {

            case "PROFESSOR":
            case "PESQUISADOR":
                return 30;

            default:
                return 15;
        }
    }
}
