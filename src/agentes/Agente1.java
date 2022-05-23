/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agentes;

import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import Interface.GUIAgentes;
import Interface.GUICliente;
import agnteinitial.Contenedor;
import contenidoSerializado.Cliente;
import contenidoSerializado.Sensores;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author davpa
 */
public class Agente1 extends GuiAgent{

    
    Cliente c1 = new Cliente();
    
    GUICliente cliente;
    
    //necesita un comportamiento
    @Override
    public void setup() {//metodo que se ejecuta siempre primero, todo lo que agamos fuera hay que meterlo a llamar aqui
        //configuracion inicial
        
        
        //CyclicBehaviour beha = new comportamiento(this);
        addBehaviour(new comportamiento(this));
        cliente = new GUICliente(this);
        cliente.setVisible(true);
        System.out.println("\n\n\n\n======FIN SEtup=======\n\n\n");

        
    }
    //tenemos los comportamientos, que se pueden controlar del agente sea ciclico secuencial, etc
    //podemos crear una clase(interna subclase) solo de comportamientos

    @Override
    protected void takeDown() {//ultima accion antes de morir, este se ejecuta una vez llamado doDelete()
        Contenedor c = (Contenedor)getArguments()[0];//obtenemos el conocimiento
        int i = Integer.parseInt(getArguments()[1].toString());
        i++;
        c.crearHijos("AgenteHijo"+i, new Object[]{c,i});//habra de hacer un bucle para que el alias vaya cambiando, con el i++ ya se hizo
        System.out.println("Muere");
    }
    
    @Override
    public void onGuiEvent(GuiEvent ev){
        c1 = (Cliente)ev.getParameter(0); //como en un arreglo, se recoge la info insertada en la pos. 0
        
        System.out.println("\n\n\n\nEl cliente\n\n\n");
        System.out.println(c1.toString());
        System.out.println("-===-=-=-=-=-=-");
    }
    
    class comportamiento extends CyclicBehaviour{//comportamiento controlado

        boolean terminado = false;
        public comportamiento(GuiAgent ag){
            super(ag);
        }
        
        @Override
        public void action() {
            //todo lo que necesite hacer el agente, red neuronal, AG, Bayes, if-else
            System.out.println(getName());
            //sensor de temperatura........
            //aqui algun codigo que cada hora pida el valor de la temperatura al
            //Mensajes.enviar(ACLMessage.INFORM, "BuscarDatos", "40", "COD0102", getAgent());//se envio el mensaje
            //Cliente cliente = new Cliente("Henry", "Paz", "Ladron de guevara", "099999", "davpas@gmail.com", "Quito", "Ecuador", 1,2 ,1700 , 15, 5000);
            //se envio el mensaje
            
            
            if(c1.getId()!=0){
                
                System.out.println(c1.toString());
                Mensajes.enviarS(ACLMessage.INFORM, "Ag2", c1, "COD0102", getAgent());
                Mensajes.enviar(ACLMessage.INFORM, "Ag3", String.valueOf(c1.getId()), "COD0103", getAgent());
                System.out.println("=======BLoqueo========\n\nCLIENTE\n\n");
                c1.setId(0);
                ACLMessage acl = blockingReceive();
                
            }
            
            //System.out.println(acl.getContent());
        }

        
        
    }
    
    
    
    
    
}
