/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VC15_hotel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 *
 * @author anarcas
 */
public class Hotel {

    private static final Object objLunes = new Object();
    private static final Object objMartes = new Object();
    private static final Object objMiercoles = new Object();
    private static final Object objJueves = new Object();
    private static final Object objViernes = new Object();
    private static final Object objSabado = new Object();
    private static final Object objDomingo = new Object();
    
    private static final int NUM_MAX_RESERVAS=25;
    

    public static String actualizarFichero(String dia, Integer habitaciones) {

        String mensajeSalida="";
        
        switch (dia) {

            case "lunes":
                mensajeSalida=actualizar(habitaciones, objLunes, "lunes.txt");
                break;

            case "martes":
                mensajeSalida=actualizar(habitaciones, objMartes, "martes.txt");
                break;

            case "miercoles":
                mensajeSalida=actualizar(habitaciones, objMiercoles, "miercoles.txt");
                break;

            case "jueves":
                mensajeSalida=actualizar(habitaciones, objJueves, "jueves.txt");
                break;

            case "viernes":
                mensajeSalida=actualizar(habitaciones, objViernes, "viernes.txt");
                break;

            case "sabado":
                mensajeSalida=actualizar(habitaciones, objSabado, "sabado.txt");
                break;

            case "domingo":
                mensajeSalida=actualizar(habitaciones, objDomingo, "domingo.txt");
                break;
                
            default:

        }
        
        return mensajeSalida;
    }

    private static String actualizar(int habitaciones, Object candado, String fichero) {

        String mensajeSalida="";
        try {
            synchronized (candado) {
                
                Path path = Paths.get(fichero);
                int reservas = 0;

                // Se verifica si el archivo existe y tiene contenido para leer el valor previo
                if (Files.exists(path) && Files.size(path) > 0) {

                    reservas = Integer.parseInt(Files.readString(path));

                }

                
                // Se gestiona las reservas, si quedan habitaciones suficientes se procede a llevarla a cabo
                
                if (habitaciones<=(Hotel.NUM_MAX_RESERVAS-reservas)){
                reservas += habitaciones;
                mensajeSalida=String.format("Se ha actualizado el número de reservas del fichero %s a %d reservas.",fichero,reservas);
                
                } else{
                mensajeSalida="No se han llevado a cabo las reservas";
                }
                
                // Se escribe el nuevo total en el fichero
                // Usamos CREATE (crea si no existe) y TRUNCATE_EXISTING (borra lo anterior para poner el nuevo total)
                Files.writeString(path, String.valueOf(reservas), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        
        return mensajeSalida;
    }
}
