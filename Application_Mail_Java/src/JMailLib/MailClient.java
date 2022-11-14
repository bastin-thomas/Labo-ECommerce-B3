/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JMailLib;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.*;
import java.util.logging.*;

/**
 *
 * @author student
 */
public class MailClient {   
    static String charset = "iso-8859-1";
    
    private String ident;
    private Session sess;
    private Store st;
    private Folder fold;
    
    
    
    public MailClient(String serverHost, String protocol, String ident, String password) throws NoSuchProviderException, MessagingException{
        String serveurReception;
        String port = "-1";
        
        Properties props = System.getProperties();
        
        props.put("mail.smtp.host", serverHost);
        
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.starttls.enable","true");
        props.put("file.encoding", charset);        
        props.put("mail.mime.charset", "utf-8");
        
        
        if(serverHost.equalsIgnoreCase("smtp.gmail.com")){
            props.put("mail.smtp.port", "465");
            
            if(protocol.equalsIgnoreCase("pop3")){
                serveurReception = "pop.gmail.com";
                port = "995";
            }
            else if(protocol.equalsIgnoreCase("imap")){
                serveurReception = "imap.gmail.com";
                port = "993";
            }
            else{
                System.out.println("Erreur, choix du serveur inconnu.");
                System.exit(0);
            }
        }
        
        
        if(serverHost.equalsIgnoreCase("smtp-mail.outlook.com")){
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.socketFactory.port", "587");
            props.put("mail.host", "outlook.office365.com");
            
            if(protocol.equalsIgnoreCase("pop3")){
                serveurReception = "outlook.office365.com";
                port = "995";
            }
            else if(protocol.equalsIgnoreCase("imap")){
                serveurReception = "outlook.office365.com";
                port = "993";
            }
            else{
                System.out.println("Erreur, choix du serveur inconnu.");
                System.exit(0);
            }
        }
        
        
        if(protocol.equalsIgnoreCase("imap")){
            props.put("mail.imap.ssl.enable", "true");
        }
        
        props.put("mail." + protocol + ".ssl.protocols", "TLSv1.2");
        props.put("mail." + protocol + ".socketFactory.port", port);
        props.put("mail." + protocol + ".socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail." + protocol + ".socketFactory.fallback", "false");
        
        
        Authenticator conn = new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ident, password);
            }
        };
        
        
        props.toString();        
        System.out.println("Session Created");
        sess = Session.getInstance(props, conn);
        System.out.println("Store Created");
        st = sess.getStore(protocol);
        
        System.out.println("Try To Connect on: " + serverHost + ", Id: " + ident + ", Pass: " + password );
        st.connect(serverHost, ident, password);
        
        fold = st.getFolder("INBOX");
        fold.open(Folder.READ_ONLY);
    }
    
    
    public Message[] GetListMail() throws MessagingException{
        Message[] msg = null;
        
        getFold().close();
        setFold(getSt().getFolder("INBOX"));
        getFold().open(Folder.READ_ONLY);
        
        System.out.println("Obtention des messages");
        msg = getFold().getMessages();
        
        return msg;
    }
    
    public int GetMessageCount() throws MessagingException{
        return getFold().getMessageCount();
    }
    
    public int GetNewMessageCount() throws MessagingException{
        return getFold().getNewMessageCount();
    }
    
    public int GetUnreadMessageCount() throws MessagingException{
        return getFold().getUnreadMessageCount();
    }
    
    
    public void Close() throws MessagingException{
        getFold().close(false);
        getSt().close();
    }
    
    /**
     * @return the sess
     */
    public Session getSess() {
        return sess;
    }

    /**
     * @param sess the sess to set
     */
    public void setSess(Session sess) {
        this.sess = sess;
    }
    
    /**
     * @return the ident
     */
    public String getIdent() {
        return ident;
    }

    /**
     * @param ident the ident to set
     */
    public void setIdent(String ident) {
        this.ident = ident;
    }
    
    /**
     * @return the st
     */
    public Store getSt() {
        return st;
    }

    /**
     * @param st the st to set
     */
    public void setSt(Store st) {
        this.st = st;
    }

    /**
     * @return the fold
     */
    public Folder getFold() {
        return fold;
    }

    /**
     * @param fold the fold to set
     */
    public void setFold(Folder fold) {
        this.fold = fold;
    }
}
