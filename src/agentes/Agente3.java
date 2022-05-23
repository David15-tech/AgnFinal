/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agentes;

import Interface.GUIAgentes;
import agnteinitial.Contenedor;
import contenidoSerializado.Pagos;
import contenidoSerializado.Ventas;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 *
 * @author davpa
 */
public class Agente3 extends GuiAgent{

    
    Pagos p1 = new Pagos();
    Ventas v1 = new Ventas();
    GUIAgentes gui;
    int op =0;
    
    //necesita un comportamiento
    @Override
    protected void setup() {//metodo que se ejecuta siempre primero, todo lo que agamos fuera hay que meterlo a llamar aqui
        //configuracion inicial 
        
        gui = new GUIAgentes(this);//Iniciamos nuestro GUI y establecemos un enlace agente-GUI
        
        //y mostramos el GUI
        gui.setVisible(true);
        addBehaviour(new comportamiento(this));
        
        //gui.setVisible(true); ya hay una interface en curso
        
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
    public void onGuiEvent(GuiEvent ge) {
        
        String op = (String) ge.getParameter(0);
        
        if(("pagos".equals(op))){
            System.out.println("\n\n\n======ES UN PAGO========\n\n\n");
            p1 = (Pagos) ge.getParameter(1);
        }else{
            v1 = (Ventas) ge.getParameter(2);
        }
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
            //Object[] pagosVentas = new Object[]{new Pagos(1, 1, 100, "2022-10-10"),new Ventas(1, 1,10, 20221, true, "2022-10-10", "Productos 1 Descrip")};
            
            //Mensajes.enviarS(ACLMessage.INFORM, "BuscarDatos", pagosVentas, "COD0302", getAgent());//se envio el mensaje
            //
            
            if(op==0){
                ACLMessage msj = blockingReceive();//bloqueado hasta que reciba el mensaje, el metodo arroja un ACLMESSAJE
                String idCli = msj.getContent();
                gui.txtidClienteP.setText(idCli);
                gui.txtidClienteV.setText(idCli);
                op++;
            }else{
                
                if(p1.getIdPago()!=0){
                    System.out.println(p1.toString());
                    Mensajes.enviarS(ACLMessage.INFORM, "Ag2", p1, "COD0302-p", getAgent());
                    System.out.println("=======BLoqueo========\n\nPAGO\n\n");
                    p1.setIdPago(0);
                    ACLMessage acl = blockingReceive();

                }
                if(v1.getIdVentas()!=0){

                    System.out.println(v1.toString());
                    Mensajes.enviarS(ACLMessage.INFORM, "BuscarDatos", v1, "COD0302-v", getAgent());
                    System.out.println("=======BLoqueo========\n\nVENTA\n\n");
                    p1.setIdPago(0);
                    ACLMessage acl = blockingReceive();

                }
            }
            
            //terminado = true;//dependiendo lo que se necesite....si no ejecuta esta linea siempre se ejucutara de manera infinita
            //doDelete();//matar al agente, liberas memoria, lo quitas de contenedor ya no se usa(comentar el teminado, pa ejecutar)
            
            //sensor de Humedad........
            //altas o bajas
            //Mensajes.enviar(ACLMessage.INFORM, "BuscarDatos", "alta", "COD0302", getAgent());//se envio el mensaje
            //ACLMessage acl = blockingReceive();
            //System.out.println(acl.getContent());
        }


        
    }
    
    
    
    
    
}
