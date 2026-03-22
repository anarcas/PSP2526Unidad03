/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VC15_hotel;

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
    private Hotel hotel;

    public HiloServidor(Socket s, Hotel hotel) {
        this.socket = s;
        this.hotel = hotel;
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

                    respuestaHTML = construirRespuesta(OK, PaginasHTML.html_hotel(this.hotel, "", "",0));

                } else if (ruta.startsWith("/reservar") && peticion.startsWith("GET")) {

                    respuestaHTML = construirRespuesta(OK, PaginasHTML.html_hotel(this.hotel, "","", 0));

                } else if (ruta.startsWith("/reservar") && peticion.startsWith("POST")) {

                    System.out.println("Linea separación: " + cuerpo.toString());
                    
                    String diaSemana = cuerpo.toString().split("&")[0].split("=")[1];
                    System.out.println("Día de la semana: " + diaSemana);
                    Integer numReservas = Integer.parseInt(cuerpo.toString().split("&")[1].split("=")[1]);
                    System.out.println("Cantidad: " + numReservas);
                    
                    String mensajeReserva = Hotel.actualizarFichero(diaSemana, numReservas);

                    respuestaHTML = construirRespuesta(OK, PaginasHTML.html_hotel(this.hotel, diaSemana, mensajeReserva,numReservas));

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
