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
public class LivroFisico extends Livro{
    private String tipoCapa;
    private boolean disponivel;
    
    public LivroFisico(int id, String titulo, String autor, String descricao, List<String> genero, FichaTecnica fichaTecnica, String tipoLivro, String tipoCapa, boolean disponivel) {
        super(id, titulo, autor, descricao, genero, fichaTecnica, tipoLivro);
        this.tipoCapa = tipoCapa;
        this.disponivel = disponivel;
    }


    @Override
    public String getTipo() {
        return "FISICO";
    }
    
    
    public String getCapa(){
        return tipoCapa;
    }
    
    public boolean getDisponibilidade(){
        return disponivel;
    }
    
    private String disponivel(){
        if(disponivel){
            return "Disponível";
        } else {
            return "Não Disponível";
        }
    }
    
    @Override
    public Map<String, Object> getDadosEspecificos(){
        Map<String, Object> dados = new HashMap<>();
        
        dados.put("CapaFormato", this.tipoCapa);
        dados.put("DisponivelTamanho", disponivel());
        dados.put("Tipo", "FISICO");
        
        return dados;
    }
    
}
