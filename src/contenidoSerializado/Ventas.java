/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contenidoSerializado;

import java.io.Serializable;

/**
 *
 * @author davpa
 */
public class Ventas implements Serializable{
    
    private int idVentas, idCliente, numeroFactura;
    private double valorNeto;
    private boolean isIVa;//1.12
    private String fecha, descripcionProducto;
    
    public Ventas(){
        idVentas = 0;
    }

    public Ventas(int idVentas, int idCliente, int numeroFactura, double valorNeto, boolean isIVa, String fecha, String descripcion) {
        this.idVentas = idVentas;
        this.idCliente = idCliente;
        this.numeroFactura = numeroFactura;
        this.valorNeto = valorNeto;
        this.isIVa = isIVa;
        this.fecha = fecha;
        this.descripcionProducto = descripcion;
    }
    
    

    public int getIdVentas() {
        return idVentas;
    }

    public void setIdVentas(int idVentas) {
        this.idVentas = idVentas;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public double getValorNeto() {
        return valorNeto;
    }

    public void setValorNeto(double valorNeto) {
        this.valorNeto = valorNeto;
    }

    public boolean isIsIVa() {
        return isIVa;
    }

    public void setIsIVa(boolean isIVa) {
        this.isIVa = isIVa;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcionProducto;
    }

    public void setDescripcion(String descripcion) {
        this.descripcionProducto = descripcion;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }
    
    

    @Override
    public String toString() {
        return "Ventas{" + "idVentas=" + idVentas + ", idCliente=" + idCliente + ", valorNeto=" + valorNeto + ", isIVa=" + isIVa + ", fecha=" + fecha + ", descripcionProducto=" + descripcionProducto + '}';
    }

    

    
    
}
