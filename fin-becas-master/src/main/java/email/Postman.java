/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 * Se encarga de enviar un correo electronico, desde un correo predeterminado.
 * Leer documentación de configuracion si aparecen errores.
 * @author Sebastian Groselj
 * @version 1.1
 */
public class Postman {
    
    //Parametros
    private static String email_from = "x@gmail.com";
    private static String email_from_password = "pass";
    
    /**
     * Configurar el correo y contraseña desde el que se envia (actualmente funciona solo con Gmail y con cuenta correctamente configurada, mirar documentación)
     * @param mail
     * @param password 
     */
    public static void setPostman(String mail, String password){
        email_from = mail;
        email_from_password = password;
    }
    
    /**
     * Enviar un email default sin requerir del Letter
     * @param email_to
     * @param text
     * @return 
     */
    public static boolean send(String email_to, String text){
        System.out.println(">>> Preparing to send email");
        Boolean valor = false;
        //Get session object
        Properties props = new Properties();    
        props.put("mail.smtp.auth", "true");    
        props.put("mail.smtp.starttls.enable", "true");    
        props.put("mail.smtp.host", "smtp.gmail.com");    
        props.put("mail.smtp.port", "587"); 
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

          
        //Session 
        Session session;
        session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email_from,email_from_password);
            } 
        });
        
        //Compose the message
        try{
            Message message = prepareMessage(session, email_to, text);
            valor=true;
            //Send message
            Transport.send(message);
            System.out.println(">>> Message sent successfully...");
        }
        catch (Exception ex){
            System.out.println("<<< Error 'Postman.send(String,String)': Preparar mensaje y enviar, " + ex.getMessage());
            
        }
        return valor;
    }
    
    /**
     * Preparar un Message de solo cuerpo
     * @param session
     * @param recipient
     * @param text
     * @return
     */
    private static Message prepareMessage(Session session, String recipient, String text){
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email_from)); //mail FROM
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient)); //mail TO
            message.setSubject("Notificacion Sistema Becas");
            message.setText("Saludos Sr. \n\n" + text);
            return message;
        } catch (Exception ex) {
            System.out.println("<<< Error 'Postman.prepareMessage(Session,String,String)': Preparar mensaje, " + ex.getMessage());
        }
        return null;
    }
    
    /**
     * Enviar un correo simple y facilmente configurable
     * Por defecto tiene texto default
     * @param letter
     * @return true: si se envio correctamente el email, false: si hubo un problema
     */
    public static boolean send(Letter letter){
        System.out.println("Preparing to send email");
        //Get session object
        Properties props = new Properties();    
        props.put("mail.smtp.auth", "true");    
        props.put("mail.smtp.starttls.enable", "true");    
        props.put("mail.smtp.host", "smtp.gmail.com");    
        props.put("mail.smtp.port", "587"); 
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

          
        //Session 
        Session session;
        session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email_from,email_from_password);
            } 
        });
        
        //Compose the message
        try{
            Message message = new MimeMessage(session);
            LetterDefault(letter, true);
            message.setFrom(new InternetAddress(email_from)); //mail FROM
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(letter.emailTo)); //mail TO
            message.setSubject(letter.subject);
            message.setText(letter.greeting + " " + letter.name + " \n\n" + letter.text + "\n\n" + letter.farewell);
            
            //Send message
            Transport.send(message);
            System.out.println(">>> Message sent successfully...");
        }
        catch (Exception ex){
            System.out.println("<<< Error 'Postman.send(Letter)': Preparar mensaje y enviar, " + ex.getMessage());
            return false;
        }
        
        return true;
    }
    
    /**
     * Enviar un correo simple y facilmente configurable
     * @param letter
     * @param defaultTextNull si hay texto nulo en el Letter, se cambia por uno default
     * @return true: si se envio correctamente el email, false: si hubo un problema
     */
    public static boolean send(Letter letter, boolean defaultTextNull){
        System.out.println("Preparing to send email");
        //Get session object
        Properties props = new Properties();    
        props.put("mail.smtp.auth", "true");    
        props.put("mail.smtp.starttls.enable", "true");    
        props.put("mail.smtp.host", "smtp.gmail.com");    
        props.put("mail.smtp.port", "587"); 
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

          
        //Session 
        Session session;
        session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email_from,email_from_password);
            } 
        });
        
        //Compose the message
        try{
            Message message = new MimeMessage(session);
            LetterDefault(letter, defaultTextNull);
            message.setFrom(new InternetAddress(email_from)); //mail FROM
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(letter.emailTo)); //mail TO
            message.setSubject(letter.subject);
            message.setText(letter.greeting + " " + letter.name + " \n\n" + letter.text + "\n\n" + letter.farewell);
            
            //Send message
            Transport.send(message);
            System.out.println(">>> Message sent successfully...");
        }
        catch (Exception ex){
            System.out.println("<<< Error 'Postman.send(Letter)': Preparar mensaje y enviar, " + ex.getMessage());
            return false;
        }
        
        return true;
    }
    
    /**
     * Intercambia los valores null de letter por un mensaje default
     * @param letter
     * @throws Exception Lanza una excepcion si no existe un mail de destino o un cuerpo
     */
    public static void LetterDefault(Letter letter, boolean defaultTextNull) throws Exception{
        if (letter.emailTo == null){
            throw new Exception("Hasn't EmailTo in letter");
        }
        if (letter.subject == null){
            if (defaultTextNull)
                letter.subject = "Notificación Sistema de Becas";
            else
                letter.subject = "";
        }
        if (letter.greeting == null){
            if (defaultTextNull)
                letter.greeting = "Estimado/a Sr./a.";
            else
                letter.greeting = "";
        }
        if (letter.name == null){
            letter.name = "";
        }
        if (letter.text == null){
            throw new Exception("Hasn't Text Body in letter");
        }
        if (letter.farewell == null){
            if (defaultTextNull)
                letter.farewell = "El sistema de becas.";
            else
                letter.farewell = "";
        }
    }
    
}
