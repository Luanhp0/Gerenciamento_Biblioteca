/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author kcarl
 */
public class Emprestimo {
    private int id;
    private Usuario usuario;
    private Livro livro;
    private Date dataEmprestimo;
    private Date dataDevolucao;
    private Date dataDevolucaoRealizada;
    
    public Emprestimo(){
        super();
    }
    
    public Emprestimo(int id, Usuario usuario, Livro livro,
                  Date dataEmprestimo, Date dataDevolucao, Date dataDevolucaoRealizada) {
        
    this.id = id;
    this.usuario = usuario;
    this.livro = livro;
    this.dataEmprestimo = dataEmprestimo;
    this.dataDevolucao = dataDevolucao;
    this.dataDevolucaoRealizada = dataDevolucaoRealizada;
}
    
    public int getId(){
        return id;
    }
    
    public int getUsuarioId(){
        return usuario.getId();
    }
    
    public Usuario getCliente(){
        return usuario;
    }
    
    
    
    public int getLivroId(){
        return livro.getId();
    }
    
    public Livro getLivro(){
        return livro;
    }
    
    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }
    
    public Date getDataDevolucao() {
        return dataDevolucao;
    }
    
    public Date getDataDevolucaoRealizada() {
        return dataDevolucaoRealizada;
    }
    
    public boolean estaAtrasado() {
    if (dataDevolucao == null) {
        return false;
    }

    Date hoje = new Date(System.currentTimeMillis());

    return dataDevolucao.before(hoje) && dataDevolucaoRealizada == null;
    }
    
    public boolean foiDevolvidoComAtraso() {
    if (dataDevolucao == null || dataDevolucaoRealizada == null) {
        return false;
    }

    return dataDevolucaoRealizada.after(dataDevolucao);
    }
    
    
}
