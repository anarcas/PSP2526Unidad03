/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package VC13_farmacia;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author anaranjo
 */
public class ServidorHTTP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        // Recurso compartido
        Farmacia viva = new Farmacia();
        
        ServerSocket servidor = new ServerSocket(12349);
        System.out.println("Servidor arrancado en http://localhost:12349");

        
        while (true) {
            Socket cliente = servidor.accept();
            new Thread(new HiloServidor(cliente, viva)).start();
        }
        
    }
    
}
