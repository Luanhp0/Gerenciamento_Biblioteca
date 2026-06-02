/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author kcarl
 */
public class LivroFisicoBuilder implements LivroBuilder{
    private int id;
    private String titulo;
    private String autor;    
    private String descricao;
    private List<String> genero;
    private FichaTecnica fichaTecnica;
    private String tipoCapa;
    private boolean disponivel;
    private String tipoLivro;
    private int quantidade;
    
    @Override
    public LivroFisicoBuilder porId(int id){
        this.id = id;
        return this;
    }
    
    @Override
    public LivroFisicoBuilder comTitulo(String titulo){
        this.titulo = titulo;
        return this;
    }
    
    @Override
    public LivroFisicoBuilder comAutor(String autor){
        this.autor = autor;
        return this;
    }
    
    @Override
    public LivroFisicoBuilder comGenero(List<String> genero){
        this.genero = genero;
        return this;
    }
    
    @Override
    public LivroFisicoBuilder comDescricao(String descricao){
        this.descricao = descricao;
        return this;
    }
    
    @Override
    public LivroFisicoBuilder comFicha(FichaTecnica fichaTecnica){
        this.fichaTecnica = fichaTecnica;
        return this;
    }
    
    public LivroFisicoBuilder comCapa(String tipoCapa){
        this.tipoCapa = tipoCapa;
        return this;
    }
    
    public LivroFisicoBuilder comDisponibilidade(boolean disponivel){
        this.disponivel = disponivel;
        return this;
    }
    
    public LivroFisicoBuilder porTipoLivro(String tipoLivro){
        this.tipoLivro = tipoLivro;
        return this;
    }
    
    public LivroFisicoBuilder comQuantidade(int quantidade){
        this.quantidade = quantidade;
        return this;
    }

    @Override   
    public Livro constroi(){
        return new LivroFisico(id, titulo, autor, descricao, genero, fichaTecnica, tipoLivro, tipoCapa, disponivel);
    }
}
