/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import java.util.Map;

/**
 *
 * @author kcarl
 */
public abstract class Livro {

    private int id;
    private String titulo;
    private String autor;
    private String descricao;
    private List<String> genero;
    private FichaTecnica fichaTecnica;
    private String tipoLivro;
    

    public Livro() {
    }

    public Livro(int id, String titulo, String autor, String descricao, List<String> genero, FichaTecnica fichaTecnica, String tipoLivro) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.descricao = descricao;
        this.genero = genero;
        this.fichaTecnica = fichaTecnica;
        this.tipoLivro = tipoLivro;
    }
    
    public String getTitulo(){
        return titulo;
    };
    
    public String getAutor(){
        return autor;
    };
    
    public String getDescricao(){
        return descricao;
    }
    
    public int getId(){
        return id;
    }

    public List<String> getGenero() {
        return genero;
    }
    
    public int getPaginas(){
        return fichaTecnica.getPaginas();
    }
    
    public int getAnoDePublicacao(){
        return fichaTecnica.getAnoDePublicacao();
    }
    
    public String getEditora(){
        return fichaTecnica.getEditora();
    }
    
    public String getIdioma(){
        return fichaTecnica.getIdioma();
    }
    
    public FichaTecnica getFichaTecnica(){
        return fichaTecnica;
    }

    public void definirId(int id) {
        if (id > 0) {
            this.id = id;
        }
    }
    
    public abstract String getTipo();
    
    public abstract Map<String, Object> getDadosEspecificos();
    
}
