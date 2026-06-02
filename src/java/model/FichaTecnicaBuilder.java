/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author drak
 */
public class FichaTecnicaBuilder {
    private int id;
    private int paginas;
    private int anoDePublicacao;
    private String editora;
    private String idioma;
    
    
    public FichaTecnicaBuilder porId(int id) {
        this.id = id;
        return this;
    }

    public FichaTecnicaBuilder comPaginas(int paginas) {
        this.paginas = paginas;
        return this;
    }

    public FichaTecnicaBuilder comAnoDePublicacao(int ano) {
        this.anoDePublicacao = ano;
        return this;
    }

    public FichaTecnicaBuilder comEditora(String editora) {
        this.editora = editora;
        return this;
    }

    public FichaTecnicaBuilder comIdioma(String idioma) {
        this.idioma = idioma;
        return this;
    }
    
    public FichaTecnica constroi(){
        return new FichaTecnica(id, paginas, anoDePublicacao, editora, idioma);
    }
}
