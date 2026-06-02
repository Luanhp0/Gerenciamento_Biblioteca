/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author kcarl
 */
public class ClienteBuilder {
    private int id;
    private String nome;
    private String email;
    private String tipoUsuario;
    private int idUsuario;
    
    public ClienteBuilder porId(int id){
        this.id = id;
        return this;
    }
    
    public ClienteBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public ClienteBuilder comEmail(String email) {
        this.email = email;
        return this;
    }
    
    public ClienteBuilder comTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
        return this;
    }
    
    public ClienteBuilder comIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }
    
    public Cliente constroi(){
    return new Cliente(id, nome, email, tipoUsuario, idUsuario);
}
    
    
    
}
