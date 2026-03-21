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
public class PaginasHTML {

    public static final String html_notFound = "<html><head><title>Error 404</title><meta charset='UTF-8'>"
            + "<link rel='icon' href='data:,/' />"
            + "<style>"
            + "body{font-family:Arial;background:linear-gradient(135deg,#ff9a9e,#fad0c4);"
            + "text-align:center;padding-top:60px;}"
            + "h1{color:#333;font-size:48px;margin-bottom:10px;}"
            + "p{font-size:20px;color:#555;}"
            + "a.button{display:inline-block;padding:12px 25px;background:#e74c3c;color:white;"
            + "text-decoration:none;border-radius:8px;font-size:18px;transition:0.3s;margin-top:20px;}"
            + "a.button:hover{background:#c0392b;}"
            + "</style></head>"
            + "<body>"
            + "<h1>Error 404</h1>"
            + "<p>La página que buscas no existe o no se encuentra disponible.</p>"
            + "<a class='button' href='/'>Volver al inicio</a>"
            + "</body></html>";

    public static String html_farmacia(Farmacia farmacia, String medSeleccionado, String mensaje, String mensajeStock, Integer cantidad) {
        String pedidoRealizado = "";
        String mensajeStockHTML = "";
        String mensajeFinal = "";
        String color = null;
        String opcionesSelect = "";

        for (ConcurrentHashMap.Entry<String, Integer> entry : farmacia.stock.entrySet()) {
            System.out.println(" " + entry.getKey() + ": " + entry.getValue());

            if (farmacia.stock.containsKey(medSeleccionado) && (farmacia.stock.get(medSeleccionado) == 0 || cantidad > farmacia.stock.get(medSeleccionado))) {
                color = "#FF0000";
            } else {
                color = "#155724";
            }

            // Generar las opciones de medicamentos del combobox
            opcionesSelect = opcionesSelect
                    + "<option value='" + entry.getKey() + "'>" + entry.getKey() + "</option>";
        }

        // Mensaje de éxito
        if (medSeleccionado != null && !medSeleccionado.isBlank()) {
            pedidoRealizado = "<div style='background:#d4edda; color:" + color + "; padding:15px; "
                    + "border-radius:6px; margin-bottom:20px; border:1px solid #c3e6cb;'>"
                    + "<b>" + mensaje + "</b>"
                    + "</div>";
            mensajeStockHTML = "<div style='background:#d4edda; color:#155724; padding:15px; "
                    + "border-radius:6px; margin-bottom:20px; border:1px solid #c3e6cb;'>"
                    + "<b>" + mensajeStock + "</b>"
                    + "</div>";
            if (farmacia.cerrar()) {
                mensajeFinal = "<div style='background:#d4edda; color:#FF0000; padding:15px; "
                        + "border-radius:6px; margin-bottom:20px; border:1px solid #c3e6cb;'>"
                        + "<b>Sin STOCK!!<br/>Farmacia Cerrada!!</b>"
                        + "</div>";
            }
        }

        return "<html><head><title>Farmacia Central</title><meta charset='UTF-8'>"
                + "<style>"
                + "body{font-family:Arial; background:linear-gradient(135deg, #e0f2f1, #80cbc4); text-align:center; padding-top:60px;}"
                + ".contenedor{background:white; display:inline-block; padding:30px; border-radius:12px; box-shadow:0 4px 15px rgba(0,0,0,0.1); width:400px;}"
                + "select, input{width: 100%; padding: 10px; margin: 10px 0; border-radius: 6px; border: 1px solid #ccc;}"
                + "button{width: 100%; padding: 12px; background:#009688; color:white; border:none; border-radius:6px; cursor:pointer; font-weight:bold;}"
                + "</style></head>"
                + "<body>"
                + "<div class='contenedor'>"
                + "<h1>🌿 Sistema de Pedidos</h1>"
                + pedidoRealizado
                + "<form method='POST' action='/pedido'>"
                + "  <div style='text-align:left; color:#555; font-size:14px; margin-bottom:5px;'>Medicamento:</div>"
                + "  <select name='medicamento'>" + opcionesSelect + "</select>"
                + "  <div style='text-align:left; color:#555; font-size:14px; margin-top:10px; margin-bottom:5px;'>Cantidad:</div>"
                + "  <input type='number' name='cantidad' value='1' min='1'>"
                + "  <button type='submit' style='margin-top:15px;'>🚀 LANZAR PEDIDO</button>"
                + "</form>"
                + mensajeStockHTML
                + mensajeFinal
                + "</div>"
                + "</body></html>";
    }

}
