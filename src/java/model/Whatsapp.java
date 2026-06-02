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
public class Whatsapp extends NotificacaoDecorator{
    
    public Whatsapp(Notificacao notificacao){
        super(notificacao);
    }
    
    @Override
    public Map<String, Boolean> getNotificacoes(){
        Map<String, Boolean> notificacoes = notificacaoDecorator.getNotificacoes();
        notificacoes.put("Whatsapp", true);
        return notificacoes;
    }
    
    @Override
    public String enviarNotificacao(){
        return notificacaoDecorator.enviarNotificacao() + "Whatsapp: Seu emprestimo do livro: ___ vence hoje. ";
    }
}
