/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kcarl
 */
public class SMS extends NotificacaoDecorator{
    
    private Livro livro;
    
    public SMS(Notificacao notificacao, Livro livro){
        super(notificacao);
        this.livro = livro;
    }
    
    @Override
    public Map<String, Boolean> getNotificacoes(){
        Map<String, Boolean> notificacoes = notificacaoDecorator.getNotificacoes();
        notificacoes.put("SMS", true);
        return notificacoes;
    }
    
    @Override
    public String enviarNotificacao(){
        return notificacaoDecorator.enviarNotificacao() + "1800-1024: Seu emprestimo do Livro: "+livro.getTitulo()+" vence hoje. ";
    }
}
