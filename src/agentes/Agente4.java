/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agentes;

import agnteinitial.Contenedor;
import contenidoSerializado.Cliente;
import contenidoSerializado.Pagos;
import contenidoSerializado.PagosVentas;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author davpa
 */
public class Agente4 extends Agent{

    Cliente c1;
    PagosVentas pv;
    int a=0;
    //necesita un comportamiento
    @Override
    protected void setup() {//metodo que se ejecuta siempre primero, todo lo que agamos fuera hay que meterlo a llamar aqui
        //configuracion inicial
        addBehaviour(new comportamiento());
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
    
    
    
    class comportamiento extends Behaviour{//comportamiento controlado

        boolean terminado = false;
        @Override
        public void action() {
            System.out.println(getName());
            ACLMessage msj = blockingReceive();//bloqueado hasta que reciba el mensaje, el metodo arroja un ACLMESSAJE
            
            //String temperatura = msj.getContent();
            Object[] cap;
            
            try {
                cap = (Object[]) msj.getContentObject();
                c1 = (Cliente) cap[1];
                pv = (PagosVentas) cap[0];
                //Pagos[] p = pv.getPagos();
                
                System.out.println("=========PAGO=======");
                System.out.println(pv.getPagos()[0].toString());
                System.out.println("=========VENTAS=======");
                System.out.println(pv.getVentas()[0].toString());
                block();
//            String idConv = msj.getConversationId();
//
//            
//            if(idConv.equalsIgnoreCase("COD0102")){
//                
//                    try {
//                        //Mensajes.enviar(ACLMessage.INFORM, "Receptor de info", "Prendiendo ventiladores", "COD0201", getAgent());//receptor enviando mensaje
//
//                        c1 = (Cliente) msj.getContentObject();
//                        
//                    } catch (UnreadableException ex) {
//                        Logger.getLogger(Agente2.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                    a++;
//                //}
//                }else {
//                    if(idConv.equalsIgnoreCase("COD0302")){
//
//                        
//                        try {
//                            pv = (PagosVentas)msj.getContentObject();
//                        } catch (UnreadableException ex) {
//                            Logger.getLogger(Agente2.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                        a++;
//
//                    }
//                }
//            
//            if(a == 2){
//
//
//
//
//                
//                Mensajes.enviarS(ACLMessage.INFORM, "Ag4", cliente, "COD0204c", getAgent());
//                Mensajes.enviarS(ACLMessage.INFORM, "Ag4", pagosVen , "COD0204PV", getAgent());
//                a=0;
//                blockingReceive();
//            }
            } catch (UnreadableException ex) {
                Logger.getLogger(Agente4.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public boolean done() {
            return terminado;//con true lo hace una sola vez
        }
        
    }
    
    
    
    
    
}
