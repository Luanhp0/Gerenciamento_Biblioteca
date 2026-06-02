/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author kcarl
 */
public class UsuarioBuilder {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String role;
    private String tipoUsuario;
    private String celular;
    
    public UsuarioBuilder comIdUsuario(int id){
        this.id = id;
        return this;
    }
    
    public UsuarioBuilder comNome(String nome){
        this.nome = nome;
        return this;
    }
    
    public UsuarioBuilder comEmail(String email){
        this.email = email;
        return this;
    }
    
    public UsuarioBuilder comSenha(String senha){
        this.senha = senha;
        return this;
    }
    
    public UsuarioBuilder comRole(String role){
        this.role = role;
        return this;
    }
    
    public UsuarioBuilder comTipoUsuario(String tipoUsuario){
        this.tipoUsuario = tipoUsuario;
        return this;
    }
    
    public UsuarioBuilder comCelular(String celular){
        this.celular = celular;
        return this;
    }
    
    public Usuario constroi(){
        return new Usuario(id, nome, email, senha, role, tipoUsuario, celular);
    }
}
