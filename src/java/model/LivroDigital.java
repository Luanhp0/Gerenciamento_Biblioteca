/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author drak
 */
public class LivroDigital extends Livro{
    private String formato;
    private double tamanho;
    
    public LivroDigital(int id, String titulo, String autor, String descricao, List<String> genero, FichaTecnica fichaTecnica, String tipoLivro, String formato, double tamanho) {
        super(id, titulo, autor, descricao, genero, fichaTecnica, tipoLivro);
        this.formato = formato;
        this.tamanho = tamanho;
    }

    public String getFormato(){
        return formato;
    }
    
    public double getTamanho(){
        return tamanho;
    }
    
    @Override
    public String getTipo() {
        return "DIGITAL";
    }
    
    @Override
    public Map<String, Object> getDadosEspecificos(){
        Map<String, Object> dados = new HashMap();
        
        dados.put("CapaFormato", this.formato);
        dados.put("DisponivelTamanho", this.tamanho);
        dados.put("Tipo", "DIGITAL");
        
        return dados;
    }
}
