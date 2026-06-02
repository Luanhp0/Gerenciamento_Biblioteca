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
public class LivroDigitalBuilder implements LivroBuilder{
    private int id;
    private String titulo;
    private String autor;    
    private String descricao;
    private List<String> genero;
    private FichaTecnica fichaTecnica;
    private String formato;
    private double tamanho;
    private String tipoLivro;
    
    @Override
    public LivroDigitalBuilder porId(int id){
        this.id = id;
        return this;
    }
    
    @Override
    public LivroDigitalBuilder comTitulo(String titulo){
        this.titulo = titulo;
        return this;
    }
    
    @Override
    public LivroDigitalBuilder comAutor(String autor){
        this.autor = autor;
        return this;
    }
    
    @Override
    public LivroDigitalBuilder comGenero(List<String> genero){
        this.genero = genero;
        return this;
    }
    
    @Override
    public LivroDigitalBuilder comDescricao(String descricao){
        this.descricao = descricao;
        return this;
    }
    
    @Override
    public LivroDigitalBuilder comFicha(FichaTecnica fichaTecnica){
        this.fichaTecnica = fichaTecnica;
        return this;
    }
    
    public LivroDigitalBuilder comFormato(String formato){
        this.formato = formato;
        return this;
    }
    
    public LivroDigitalBuilder comTamanho(double tamanho){
        this.tamanho = tamanho;
        return this;
    }
    
    public LivroDigitalBuilder porTipoLivro(String tipoLivro){
        this.tipoLivro = tipoLivro;
        return this;
    }
    
    /**
     *
     * @return
     */
    @Override
    public Livro constroi(){
        return new LivroDigital(id, titulo, autor, descricao, genero, fichaTecnica, tipoLivro, formato, tamanho);
    }
}
