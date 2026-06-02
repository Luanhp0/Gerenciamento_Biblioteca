/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author drak
 */
public class EmprestimoBuilder {
    
    private int id;
    private Usuario usuario;
    private Livro livro;
    private Date dataEmprestimo;
    private Date dataDevolucao;
    private Date dataDevolucaoRealizada;
    
    public EmprestimoBuilder porId(int id){
        this.id = id;
        return this;
    }
    
    public EmprestimoBuilder comUsuario(Usuario usuario){
        this.usuario = usuario;
        return this;
    }
    
    public EmprestimoBuilder comLivro(Livro livro){
        this.livro = livro;
        return this;
    }

    public EmprestimoBuilder comDataEmprestimo(Date dataEmprestimo){
        this.dataEmprestimo = dataEmprestimo;
        return this;
    }

    public EmprestimoBuilder comDataDevolucao(Date dataDevolucao){
        this.dataDevolucao = dataDevolucao;
        return this;
    }

    public EmprestimoBuilder comDataDevolucaoRealizada(Date dataDevolucaoRealizada){
        this.dataDevolucaoRealizada = dataDevolucaoRealizada;
        return this;
    }

    public Emprestimo constroi(){
        return new Emprestimo(id, usuario, livro, dataEmprestimo, dataDevolucao, dataDevolucaoRealizada);
    }
}
