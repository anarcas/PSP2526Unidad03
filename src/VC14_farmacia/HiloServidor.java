/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VC14_farmacia;

import VC13_farmacia.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author anaranjo
 */
public class HiloServidor implements Runnable {

    private final int OK = 200;
    private final int NOTFOUND = 404;

    private Socket socket;
    private Farmacia farmacia;

    public HiloServidor(Socket s, Farmacia farmacia) {
        this.socket = s;
        this.farmacia = farmacia;
    }

    @Override
    public void run() {

        try (Socket s = this.socket; BufferedReader entrada = new BufferedReader(new InputStreamReader(s.getInputStream())); PrintWriter salida = new PrintWriter(s.getOutputStream(), true)) {

            String peticion = entrada.readLine();
            System.out.println("Petición: " + peticion);

            if (peticion != null && (peticion.startsWith("GET") || peticion.startsWith("POST"))) {
                String ruta = peticion.split(" ")[1];
                System.out.println("Ruta: " + ruta);
                int contentLength = 0;
                String linea;

                while (!(linea = entrada.readLine()).isBlank()) {
                    // Aquí se leerían las cabeceras adicionales si fuera necesario
                    if (linea.startsWith("Content-Length:")) {
                        contentLength = Integer.parseInt(linea.split(":")[1].trim());
                    }
                    System.out.println("Metadato: " + linea);
                }
                System.out.println("Content-Length = " + contentLength);
                System.out.println("Línea en blanco");

                StringBuilder cuerpo = new StringBuilder();
                if (contentLength > 0) {

                    for (int i = 0; i < contentLength; i++) {
                        cuerpo.append((char) entrada.read());

                    }

                }
                System.out.println("Cuerpo: " + cuerpo);

                String respuestaHTML;

                if (ruta.equals("/")) {

                    respuestaHTML = construirRespuesta(OK, PaginasHTML.html_farmacia(this.farmacia, "", "","",0));

                } else if (ruta.startsWith("/pedido") && peticion.startsWith("GET")) {

                    respuestaHTML = construirRespuesta(OK, PaginasHTML.html_farmacia(this.farmacia, "", "","",0));

                } else if (ruta.startsWith("/pedido") && peticion.startsWith("POST")) {

                    System.out.println("Linea separación: " + cuerpo.toString());
                    String nombreMedicamento = cuerpo.toString().split("&")[0].split("=")[1].split("\\+")[0];
                    String composicion = cuerpo.toString().split("&")[0].split("=")[1].split("\\+")[1];
                    String medicamento = nombreMedicamento + " " + composicion;
                    System.out.println("Nombre: " + medicamento);
                    Integer cantidad = Integer.parseInt(cuerpo.toString().split("&")[1].split("=")[1]);
                    System.out.println("Cantidad: " + cantidad);
                    
                    String mensajePedido = farmacia.procesarCompra(medicamento, cantidad);
                    String mensajeStock=farmacia.mostrarStockHTML();

                    respuestaHTML = construirRespuesta(OK, PaginasHTML.html_farmacia(this.farmacia, medicamento, mensajePedido,mensajeStock, cantidad));

                } else {

                    respuestaHTML = construirRespuesta(NOTFOUND, PaginasHTML.html_notFound);

                }

                System.out.println("RespuestaHTML: \n" + respuestaHTML);

                salida.print(respuestaHTML);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String construirRespuesta(int codigo, String contenido) {
        return (codigo == 200 ? "HTTP/1.1 200 OK" : "HTTP/1.1 404 Not Found") + "\n" // Línea inicial
                + "Content-Type: text/html; charset=UTF-8" + "\n" // Metadatos
                + "Content-Length: " + contenido.length() + "\n"
                + "\n" // Línea vacía
                + contenido;                                                            // Cuerpo
    }

}
