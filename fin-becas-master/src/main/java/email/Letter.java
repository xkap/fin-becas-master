/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email;

/**
 * Organiza el contenido de un correo electronico
 * @author Sebastian Groselj
 * @version 1.0
 */
public class Letter {
    
    //Parametros
    public String emailTo;
    public String name;
    public String subject;
    public String greeting;
    public String text;
    public String farewell;
    
    //Constructores
    
    /**
     * Crear una carta sin contenido (Lo que no se rellene, Postman.send(Letter) dejara valores default)
     */
    public Letter(){
        
    }
    
    /**
     * Crear una carta, basado en otra
     */
    public Letter(Letter other){
        this.emailTo = other.emailTo;
        this.name = other.name;
        this.subject = other.subject;
        this.greeting = other.greeting;
        this.text = other.text;
        this.farewell = other.farewell;
    }
}
