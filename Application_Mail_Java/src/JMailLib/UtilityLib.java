/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JMailLib;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.Math.exp;
import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 *
 * @author student
 */
public class UtilityLib {
    
    public static ArrayList GetHeadSender(Message[] msg) throws MessagingException{
        ArrayList headerSender = new ArrayList();
        
        for (int i=0; i<msg.length; i++) {
            Vector<String> vec = new Vector<String>();
            
            String From =  convertAddress(msg[i].getFrom());            
            String Subject = msg[i].getSubject();
            
            vec.add(From);
            vec.add(Subject);
            headerSender.add(vec.clone());
        }
        
        return headerSender;
    }
    
    public static String convertAddress(Address[] fromAdd) throws MessagingException{
        String From = "";
        int i = 0;
        
        if(fromAdd == null) return "";
        
        //Creation disposition'une String sur base disposition'adresse
        for (Address Add : fromAdd) {
            if (fromAdd != null) {
                if (i == fromAdd.length-1) {
                    if (Add.getClass().equals(InternetAddress.class)) {
                        InternetAddress iad = (InternetAddress) Add;
                        From += iad.getAddress();
                    } else {
                        From += fromAdd[0].toString();
                    }
                } else {
                    if (Add.getClass().equals(InternetAddress.class)) {
                        InternetAddress iad = (InternetAddress) Add;
                        From += iad.getAddress() + ", ";
                    } else {
                        From += fromAdd[0].toString() + ", ";
                    }
                }
            }
            i++;
        }
        return From;
    }
    
    
    public static Enumeration getAllHeadersFrom(int index, Message[] msg) throws MessagingException{
        Enumeration e = null;
        
        e = msg[index].getAllHeaders();
        
        return e;
    }
    
    
    
    public static Message[] AddToArray(Message[] initialArray , Message newValue) {
        Message[] newArray = new Message[initialArray.length + 1];
        for (int index = 0; index < initialArray.length; index++) {
            newArray[index] = initialArray[index];
        }

        newArray[newArray.length - 1] = newValue;
        return newArray;
    }
    
    
    
    
    public static String getTextFromMessage(Message msg, List<String> fichier) throws MessagingException, IOException{
        String txt = "";
        
        if(msg.isMimeType("multipart/*")) {
            Multipart MultiMsg = (Multipart) msg.getContent();
            int n = MultiMsg.getCount();
            System.out.println("PartCount: " + n);
            
            for (int j = 0; j < n; j++) {
                Part part = MultiMsg.getBodyPart(j);
                String disposition = part.getDisposition();
                
                //Message Principal si en Alternative (message avec des embeds)
                Multipart MultiMsg2;
                if(part.isMimeType("multipart/alternative")){
                    
                    MultiMsg2 = (Multipart) part.getContent();
                    int n2 = MultiMsg2.getCount();
                    Part part2 = MultiMsg2.getBodyPart(0);
                    
                    if(part2.isMimeType("text/plain")){
                        txt += MimeUtility.decodeText((String) part2.getContent());
                    }
                    else{
                        txt = "Error Message Not Found";
                    }
                }
                
                //Message Principal
                if (part.isMimeType("text/plain")) { //&& part.getFileName() == null
                    System.out.println("This is the message" + part.getContentType());
                    txt += MimeUtility.decodeText((String) part.getContent());
                }
                
                //Attachment
                if (disposition != null && disposition.equalsIgnoreCase(Part.ATTACHMENT)) {
                    String FileName = MimeUtility.decodeText(part.getFileName());
                    
                    fichier.add(FileName);
                    System.out.println("Nom fichier : " + FileName);
                }
            }
        } 
        //SimplePart Message:
        else {
            txt =  MimeUtility.decodeText((String) msg.getContent());
        }    
        return txt;
    }
    
    
    public static void saveFileTo(String directory, String FileName, Message m) throws MessagingException, IOException{
        if (m.isMimeType("multipart/*")) {
            Multipart msgMP = (Multipart) m.getContent();
            int np = msgMP.getCount();

            //Passage dans les BodyParts:
            for (int j = 0; j < np; j++) {

                Part part = msgMP.getBodyPart(j);
                String disposition = part.getDisposition();

                if (disposition != null && disposition.equalsIgnoreCase(Part.ATTACHMENT)) {
                    if(part.getFileName().equals(FileName)){
                        System.out.println("Fichier a télécharger : " + part.getFileName());
                        int c;
                        
                        InputStream readStream = part.getInputStream();
                        FileOutputStream writeStream = new FileOutputStream(directory);
                        ByteArrayOutputStream tmp = new ByteArrayOutputStream();
                        
                        while ((c = readStream.read()) != -1) {
                            tmp.write(c);
                        }
                        tmp.writeTo(writeStream);
                        writeStream.close();
                    }
                }
            }
        }
    }
    
    
    /*Ajoute les fichier joints au message principal*/
    public static void setFilePart(Multipart msg, String FilePath, Vector<String> FileList) throws IOException, MessagingException{
            String[] Names = FilePath.split("/");
            String Name = Names[Names.length - 1];
            MimeBodyPart BodyPart = new MimeBodyPart();
            
            //Idiqué le type de multipart
            BodyPart.attachFile(new File(FilePath));            
            BodyPart.setDataHandler (new DataHandler(new FileDataSource (FilePath)));
            BodyPart.setFileName(Name);
            
            msg.addBodyPart(BodyPart);
            
            FileList.add(Name);
    }
    
    public static Address[] convertAddr(Vector<String> vec) throws AddressException{
        Vector<Address> Addresses = new Vector<Address>();
        
        Iterator i = vec.iterator();
        while(i.hasNext()){
            String tmp = (String) i.next();
            if(!tmp.equals("")){
                Addresses.add(new InternetAddress(tmp));
            }
        }
        
        Object[] tmp = Addresses.toArray();
        return Arrays.copyOf(tmp, tmp.length, Address[].class);
    }
    
    /*Envoi de message texte basique sans piece jointe*/
    public static void createMessageSimple(MimeMessage msg, Address[] To, Address[] Cc, String Subject, String Text) throws MessagingException{
            System.out.println("Création Message Simple");
            msg.setFrom();
            
            msg.setRecipients(Message.RecipientType.TO, To);
            msg.setRecipients(Message.RecipientType.CC, Cc);
            
            msg.setSubject(Subject); 
            msg.setText (Text);
    }

    
    /*Envoi d'un message avec piece jointe multiple et document texte*/
    public static void createMessageMultiPart(MimeMessage msg, Multipart Multip, Address[] To, Address[] Cc, String Subject, String Text) throws MessagingException{
        
        msg.setFrom();
        
        msg.setRecipients(Message.RecipientType.TO, To);
        msg.setRecipients(Message.RecipientType.CC, Cc);

        msg.setSubject(Subject);
        
        System.out.println("Création Message MultiPart");
            //Ajout du Texte comme premier composant
            MimeBodyPart msgBP = new MimeBodyPart(); 
            msgBP.setText(Text);
            Multip.addBodyPart(msgBP);
            
            msg.setContent(Multip);
    }
}
