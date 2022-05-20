/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agentes;

import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import Interface.GUIAgentes;
import agnteinitial.Contenedor;
import contenidoSerializado.Cliente;
import contenidoSerializado.Sensores;
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

    GUIAgentes gui;
    Cliente c1 = new Cliente();
    
    //necesita un comportamiento
    @Override
    public void setup() {//metodo que se ejecuta siempre primero, todo lo que agamos fuera hay que meterlo a llamar aqui
        //configuracion inicial
        
        
        //CyclicBehaviour beha = new comportamiento(this);
        addBehaviour(new comportamiento(this));
        gui = new GUIAgentes(this);//Iniciamos nuestro GUI y establecemos un enlace agente-GUI
        
        //y mostramos el GUI
        gui.setVisible(true);
        
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
            //Mensajes.enviarS(ACLMessage.INFORM, "UnirInformacion", cliente, "COD0102", getAgent());//se envio el mensaje
            System.out.println("=======BLoqueo========");
            ACLMessage acl = blockingReceive();
            //System.out.println(acl.getContent());
        }

        
        
    }
    
    
    
    
    
}
