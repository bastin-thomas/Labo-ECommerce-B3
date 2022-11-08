/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JMailLib.Mail;

import java.util.Date;

/**
 *
 * @author student
 */
public abstract class Mail {
    private Date sendDate;
    private String From;
    private String To;
    private String Cc;
    private String Bcc;
    private String Subject;
    private String Message;
    
    public Mail() {}

    public Mail(String From, String To) {
        this.From = From;
        this.To = To;
    }

    public Mail(String From, String To, String Message) {
        this.From = From;
        this.To = To;
        this.Message = Message;
    }

    public Mail(String From, String To, String Subject, String Message) {
        this.From = From;
        this.To = To;
        this.Subject = Subject;
        this.Message = Message;
    }
    
    public Mail(Date sendDate, String From, String To, String Subject, String Message) {
        this.sendDate = sendDate;
        this.From = From;
        this.To = To;
        this.Subject = Subject;
        this.Message = Message;
    }
    
    public Mail(Date sendDate, String From, String To, String Cc, String Bcc, String Subject, String Message) {
        this.sendDate = sendDate;
        this.From = From;
        this.To = To;
        this.Cc = Cc;
        this.Bcc = Bcc;
        this.Subject = Subject;
        this.Message = Message;
    }
    
    
    @Override
    public String toString() {
        return "Mail{" + "sendDate=" + sendDate + ", From=" + From + ", To=" + To + ", Cc=" + Cc + ", Bcc=" + Bcc + ", Objet=" + Subject + ", Message=" + Message + '}';
    }
    
    
    /**
     * @return the sendDate
     */
    public Date getSendDate() {
        return sendDate;
    }

    /**
     * @param sendDate the sendDate to set
     */
    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    /**
     * @return the From
     */
    public String getFrom() {
        return From;
    }

    /**
     * @param From the From to set
     */
    public void setFrom(String From) {
        this.From = From;
    }

    /**
     * @return the To
     */
    public String getTo() {
        return To;
    }

    /**
     * @param To the To to set
     */
    public void setTo(String To) {
        this.To = To;
    }

    /**
     * @return the Cc
     */
    public String getCc() {
        return Cc;
    }

    /**
     * @param Cc the Cc to set
     */
    public void setCc(String Cc) {
        this.Cc = Cc;
    }

    /**
     * @return the Bcc
     */
    public String getBcc() {
        return Bcc;
    }

    /**
     * @param Bcc the Bcc to set
     */
    public void setBcc(String Bcc) {
        this.Bcc = Bcc;
    }

    /**
     * @return the Subject
     */
    public String getSubject() {
        return Subject;
    }

    /**
     * @param Subject the Subject to set
     */
    public void setSubject(String Subject) {
        this.Subject = Subject;
    }

    /**
     * @return the Message
     */
    public String getMessage() {
        return Message;
    }

    /**
     * @param Message the Message to set
     */
    public void setMessage(String Message) {
        this.Message = Message;
    }
}
