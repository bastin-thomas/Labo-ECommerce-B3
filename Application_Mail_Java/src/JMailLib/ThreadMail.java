/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JMailLib;

import GUI.HomePage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.swing.table.TableModel;
import javax.swing.*;

/**
 *
 * @author student
 */
public class ThreadMail extends Thread {
    HomePage mainPage;    
    int refresh;
    
    public ThreadMail(HomePage main, int ref) 
    {        
        mainPage = main;
        refresh = ref;
    }
    
    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {
        while(true){
            mainPage.onRefreshMail();
            
            try {
                Thread.sleep(refresh*60000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadMail.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
