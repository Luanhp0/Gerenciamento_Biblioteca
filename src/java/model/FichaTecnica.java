/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author kcarl
 */
public class FichaTecnica {
    private int id;
    private int paginas;
    private int anoDePublicacao;
    private String editora;
    private String idioma;
    
    public FichaTecnica(){
        super();
    }
    
    public FichaTecnica(int id, int paginas, int anoDePublicacao, String editora, String idioma){
        this.paginas = paginas;
        this.anoDePublicacao = anoDePublicacao;
        this.editora = editora;
        this.idioma = idioma;
    }
    
    public int getId(){
        return id;
    }
    
    public int getPaginas(){
        return paginas;
    }
    
    public int getAnoDePublicacao(){
        return anoDePublicacao;
    }
    
    public String getEditora(){
        return editora;
    }
    
    public String getIdioma(){
        return idioma;
    }
}
