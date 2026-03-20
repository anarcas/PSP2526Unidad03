/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VC13_farmacia;

import java.util.Arrays;

/**
 *
 * @author anaranjo
 */
public class Farmacia {

    String[] nombreMedicamento = {"Paracetamol 1g", "Ibuprofeno 600mg", "VitaminaC 1000mg"};
    Integer[] stockMedicamento = {10, 10, 10};
    String nombreFarmacia = "Viva";
    public static int numPedido = 0;

    public synchronized String vender(String medicamento, Integer cantidad) {

        String mensaje = null;
        int indice = Arrays.asList(this.nombreMedicamento).indexOf(medicamento) + 1;

        switch (indice) {

            case 1:
                if (stockMedicamento[0] >= cantidad) {
                    stockMedicamento[0] -= cantidad;
                    mensaje = String.format("El %s ha vendido %d %s y quedan %d en stock", Thread.currentThread().getName(), cantidad, nombreMedicamento[0], stockMedicamento[0]);
                    System.out.println(mensaje);
                    numPedido++;
                } else {
                    mensaje = String.format("El %s ha intentado vender %d %s, pero quedan %d en stock", Thread.currentThread().getName(), cantidad, nombreMedicamento[0], stockMedicamento[0]);
                    System.out.println(mensaje);
                }
                break;

            case 2:
                if (stockMedicamento[1] >= cantidad) {
                stockMedicamento[1] -= cantidad;
                mensaje = String.format("El %s ha vendido %d %s y quedan %d en stock", Thread.currentThread().getName(), cantidad, nombreMedicamento[1], stockMedicamento[1]);
                System.out.println(mensaje);
                numPedido++;
                } else {
                mensaje = String.format("El %s ha intentado vender %d %s, pero quedan %d en stock", Thread.currentThread().getName(), cantidad, nombreMedicamento[1], stockMedicamento[1]);
                System.out.println(mensaje);
                }
                break;

            case 3:
                if (stockMedicamento[2] >= cantidad) {
                stockMedicamento[2] -= cantidad;
                mensaje = String.format("El %s ha vendido %d %s y quedan %d en stock", Thread.currentThread().getName(), cantidad, nombreMedicamento[2], stockMedicamento[2]);
                System.out.println(mensaje);
                numPedido++;
                } else{
                mensaje = String.format("El %s ha intentado vender %d %s, pero quedan %d en stock", Thread.currentThread().getName(), cantidad, nombreMedicamento[2], stockMedicamento[2]);
                System.out.println(mensaje);
                }
                break;

            default:
                System.err.println(String.format("Medicamento inexistente en la farmacia %s", nombreFarmacia));

        }
        return mensaje;
    }

    
    public String mostrarStock(){
    
        StringBuilder sb = new StringBuilder();
        
        sb.append("Stock disponible");
        sb.append("<br>");
        
        
            for (int i = 0; i < nombreMedicamento.length; i++) {
                sb.append(nombreMedicamento[i]);
                sb.append(":");
                sb.append(stockMedicamento[i]);
                sb.append("<br>");
            }
        
        
        return sb.toString();
    }
    
    
    public boolean cerrar() {
        if (stockMedicamento[0] == 0 && stockMedicamento[1] == 0 && stockMedicamento[2] == 0) {
            return true;
        }
        return false;
    }

}
