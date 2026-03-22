/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VC14_farmacia;

import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author anaranjo
 */
public class Farmacia {

    public ConcurrentHashMap<String, Integer> stock = new ConcurrentHashMap<>();

    public Farmacia() {
        stock.put("Paracetamol 1g", 10);
        stock.put("Ibuprofeno 600mg", 10);
        stock.put("VitaminaC 1000mg", 10);
    }

    public static int numPedido = 0;

    public static int getNumPedido() {
        return numPedido;
    }

    public static void setNumPedido(int numPedido) {
        Farmacia.numPedido = numPedido;
    }

    
    
    public String procesarCompra(String producto, int cantidad) {

        String resultado;
        System.out.println("Producto y cantidad: " + producto + " " + cantidad);

        // Leer stock del producto
        Integer cantidadActual = stock.get(producto);

        // Comprobación del stock
        if (cantidadActual >= cantidad) {
            // Cálculo de nuevo stock
            int nuevoStock = cantidadActual - cantidad;

            // Actualización atómica con replace
            boolean actualizado = stock.replace(producto, cantidadActual, nuevoStock);

            if (actualizado) {
                resultado = "Compra exitosa de " + cantidad + " unidad(es) de " + producto + ".";
            } else {
                // Otro hilo modificó el valor? reintentar recursivamente
                resultado = procesarCompra(producto, cantidad);
            }

        } else {
            resultado = "Stock insuficiente de " + producto + ".";
        }

        mostrarStock();
        return resultado;
    }

    private void mostrarStock() {
        System.out.println("Stock:");
        for (ConcurrentHashMap.Entry<String, Integer> entry : stock.entrySet()) {
            System.out.println(" " + entry.getKey() + ": " + entry.getValue());
        }
    }

    public String mostrarStockHTML() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stock:").append("<br/>");
        for (ConcurrentHashMap.Entry<String, Integer> entry : stock.entrySet()) {
            sb.append(" ").append(entry.getKey()).append(": ").append(entry.getValue()).append("<br/>");
        }
        return sb.toString();
    }

    public boolean cerrar() {
        int cantidadStock = 0;
        for (ConcurrentHashMap.Entry<String, Integer> entry : stock.entrySet()) {
            cantidadStock = +entry.getValue();
        }

        if (cantidadStock == 0) {
            return true;
        } else {
            return false;
        }
    }

}
