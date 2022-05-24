/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agentes;

import contenidoSerializado.Cliente;
import contenidoSerializado.Pagos;
import contenidoSerializado.PagosVentas;
import contenidoSerializado.Sensores;
import contenidoSerializado.Ventas;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author davpa
 */
public class Agente2 extends Agent{

    
    //necesita un comportamiento
    int a = 0;
    Cliente cliente;
    PagosVentas pagosVen;
    
    @Override
    protected void setup() {//metodo que se ejecuta siempre primero, todo lo que agamos fuera hay que meterlo a llamar aqui
        //configuracion inicial
        addBehaviour(new comportamiento());
    }
    //tenemos los comportamientos, que se pueden controlar del agente sea ciclico secuencial, etc
    //podemos crear una clase(interna subclase) solo de comportamientos
    
    class comportamiento extends CyclicBehaviour{
        
        //String verf[] = new String[2];
        

        @Override
        public void action() {
            //todo lo que necesite hacer el agente, red neuronal, AG, Bayes, if-else
            System.out.println(getName());
            ACLMessage msj = blockingReceive();//bloqueado hasta que reciba el mensaje, el metodo arroja un ACLMESSAJE
            
            //String temperatura = msj.getContent();
            String idConv = msj.getConversationId();
            
            if(idConv.equalsIgnoreCase("COD0102")){
                
                    try {
                        //Mensajes.enviar(ACLMessage.INFORM, "Receptor de info", "Prendiendo ventiladores", "COD0201", getAgent());//receptor enviando mensaje

                        cliente = (Cliente) msj.getContentObject();
                        System.out.println(cliente);
                        
                    } catch (UnreadableException ex) {
                        Logger.getLogger(Agente2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    a++;
                //}
                }else {
                    if(idConv.equalsIgnoreCase("COD0302")){
                        
                        
                        try {
                            pagosVen = (PagosVentas)msj.getContentObject();
                        } catch (UnreadableException ex) {
                            Logger.getLogger(Agente2.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        a++;

                    }
                }
            
            if(a == 2){
                Mensajes.enviarS(ACLMessage.INFORM, "Ag4", new Object[]{pagosVen,cliente}, "COD0204c", getAgent());
                //Mensajes.enviarS(ACLMessage.INFORM, "Ag4", pagosVen , "COD0204PV", getAgent());
                a=0;
                blockingReceive();
            }
            
            }
        }
    }
    
