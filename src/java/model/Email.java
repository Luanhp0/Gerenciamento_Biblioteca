/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author kcarl
 */
public class Email implements Notificacao{
    
    private Livro livro;
    
    // para cadastro de usuario
    public Email(){
        this.livro = null;
    }
    // para notificacao
    public Email(Livro livro){
        this.livro = livro;
    }
    
    @Override
    public String enviarNotificacao(){
        return "Email: Vencimento de emprestimo. Seu emprestimo do livro:" + livro.getTitulo() +" vence hoje. ";
    }
    
    @Override
    public Map<String, Boolean> getNotificacoes(){
        Map<String, Boolean> notificacoes = new HashMap();
        notificacoes.put("Email", true);
        return notificacoes;
    }
}
