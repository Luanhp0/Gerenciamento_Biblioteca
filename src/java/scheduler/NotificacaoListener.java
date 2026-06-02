/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package scheduler;

import DAO.EmprestimoDAO;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

/**
 *
 * @author kcarl
 */
@WebListener
public class NotificacaoListener implements ServletContextListener{
    private Scheduler scheduler;
    
    @Override
    public void contextInitialized(ServletContextEvent servletContext){
        try{
            SchedulerFactory schedulerFactory = new org.quartz.impl.StdSchedulerFactory();
            
            //Instancia o scheduler
            scheduler = schedulerFactory.getScheduler();
            
            scheduler.start();
            System.out.println("Scheduler rodando");
            
            /**
            //Define o job a ser executado
            JobDetail notificar = JobBuilder.newJob(NotificacaoDevolucaoJob.class)
                    .withIdentity("NotificacaoDevolucaoJob", "GrupoBiblioteca")
                    .build();
            
            //Define o trigger do job
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("NotificacaoDevolucaoTrigger", "GrupoBiblioteca")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInSeconds(30)
                            .repeatForever())
                    .build();
                    
            scheduler.scheduleJob(notificar, trigger);
            scheduler.start();
            
            System.out.println("Log: Scheduler rodadando");
            **/
        } catch (Exception exception){
            System.out.println("Erro no scheduler Listener: " + exception.getMessage());
        }
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent servletEvent){
        try{
            if(scheduler != null && !scheduler.isShutdown()){
                System.out.println("Finalizando scheduler");
                scheduler.shutdown(true); //aguarda os jobs terminarem
            }
        } catch(Exception exception){
            exception.printStackTrace();
        }
    }
    
}
