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
public class NotificacaoDecorator implements Notificacao{
    protected Notificacao notificacaoDecorator;
    
    public NotificacaoDecorator(Notificacao notificacao){
        this.notificacaoDecorator = notificacao;
    }
    
    @Override
    public String enviarNotificacao(){
        return notificacaoDecorator.enviarNotificacao();
    }

    @Override
    public Map<String, Boolean> getNotificacoes(){
        Map<String, Boolean> notificacoes = new HashMap();
        return notificacoes;
    }
}
