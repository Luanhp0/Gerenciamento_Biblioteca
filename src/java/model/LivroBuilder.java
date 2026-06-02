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
public interface LivroBuilder {
    public LivroBuilder porId(int id);
    
    public LivroBuilder comTitulo(String titulo);
    
    public LivroBuilder comAutor(String autor);
    
    public LivroBuilder comGenero(List<String> genero);
    
    public LivroBuilder comDescricao(String descricao);
    
    public LivroBuilder comFicha(FichaTecnica fichaTecnica);
    
    
    
    public Livro constroi();
}
