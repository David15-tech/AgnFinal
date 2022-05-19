/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agentes;

import contenidoSerializado.Cliente;
import contenidoSerializado.Pagos;
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
            System.out.println("=========GET NAME=======");
            System.out.println(getName());
            ACLMessage msj = blockingReceive();//bloqueado hasta que reciba el mensaje, el metodo arroja un ACLMESSAJE
            
            //String temperatura = msj.getContent();
            String idConv = msj.getConversationId();
            
            if(idConv.equalsIgnoreCase("COD0102")){
                
                //verf[0] = temperatura+"";
                //if (Integer.parseInt(temperatura)>35){
                    try {
                        //System.out.println("temp mayor a 35, abriendo ventiladores");
                        //necesitamos "actuadores", en este caso algo que aga que el ventilado se prenda....
                        //........
                        
                        
                        //Mensajes.enviar(ACLMessage.INFORM, "Receptor de info", "Prendiendo ventiladores", "COD0201", getAgent());//receptor enviando mensaje
                        
                        //System.out.print(msj);
                        Cliente cliente = (Cliente) msj.getContentObject();
                        System.out.println(cliente);
                        //Sensores s = (Sensores) msj.getContentObject();
                        
                        //System.out.println(s.getRiego()+" "+s.getTemperatura());
                        
                    } catch (UnreadableException ex) {
                        Logger.getLogger(Agente2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                //}
                }else {
                    if(idConv.equalsIgnoreCase("COD0302")){
                        
                        try {
                            
                            System.out.println("=======Mensaje recibido de Ag3=====");
                            Object[] pagosVentas = (Object[])msj.getContentObject();
                            System.out.println("fin de pagosventaas");
                            System.out.println(pagosVentas.length);
                            Pagos pago = (Pagos)pagosVentas[0];
                            Ventas venta = (Ventas)pagosVentas[1];
                            System.out.println(pago);
                            System.out.println(venta);
                            
                            
                            //Mensajes.enviar(ACLMessage.INFORM, "Ag4", "Hola 4", "COD0204", getAgent());
                        } catch (UnreadableException ex) {
                            Logger.getLogger(Agente2.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            
//            if(verf[0] != null){
//                if(verf[1] != null){
//                    String  mens = msj.getContent();
//
//                }
//            }
            
            }
            //System.out.println(msj.getConversationId());
        }
    }
    
