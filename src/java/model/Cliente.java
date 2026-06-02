/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author kcarl
 */
public class Cliente {
    private int id;
    private String nome;
    private String email;
    private String tipoUsuario;
    private int idUsuario;
    
    public Cliente(){
        super();
    }
    
    public Cliente(int id, String nome, String email, String tipoUsuario, int idUsuario){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.tipoUsuario = tipoUsuario;
        this.idUsuario = idUsuario;
    }
    
    public String getNome(){
        return nome;
    }
    
    public String getEmail(){
        return email;
    }
    
    public int getId(){
        return id;
    }
    
    public String getTipoUsuario(){
        return tipoUsuario;
    }
    
    public int getIdUsuario(){
        return idUsuario;
    }
}
