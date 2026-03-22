/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VC15_hotel;


/**
 *
 * @author anaranjo
 */
public class PaginasHTML {
    
   private static final String[] DIAS_SEM ={"lunes","martes","miercoles","jueves","viernes","sabado","domingo"};
    

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

    public static String html_hotel(Hotel hotel, String diaSeleccionado, String mensajeEntrada, Integer habitaciones) {
        String reservaRealizada = "";
        String opcionesSelect = "";
               
        // Alimentar el comboBox días de la semana
        for (String dia:DIAS_SEM){
            opcionesSelect = opcionesSelect
                    + "<option value='" + dia + "'>" + dia + "</option>";
        }

        // Mensaje de éxito
        if (diaSeleccionado != null && !diaSeleccionado.isBlank()) {
            reservaRealizada = "<div style='background:#fff3cd; color:#856404; padding:15px; "
                + "border-radius:6px; margin-bottom:20px; border:1px solid #ffeeba;'>"
                + "<b>" + mensajeEntrada + "</b>"
                + "</div>";
        }

        return "<html><head><title>Grand Hotel - Reservas</title><meta charset='UTF-8'>"
            + "<style>"
            + "body{font-family:'Segoe UI',Arial; background:linear-gradient(135deg, #f5f5f5, #cfd8dc); text-align:center; padding-top:60px;}"
            + ".contenedor{background:white; display:inline-block; padding:30px; border-radius:12px; box-shadow:0 10px 25px rgba(0,0,0,0.2); width:420px; border-top: 5px solid #b8860b;}"
            + "select, input{width: 100%; padding: 10px; margin: 10px 0; border-radius: 6px; border: 1px solid #ccc; background:#fafafa;}"
            + "button{width: 100%; padding: 12px; background:#2c3e50; color:white; border:none; border-radius:6px; cursor:pointer; font-weight:bold; letter-spacing:1px;}"
            + "button:hover{background:#b8860b;}"
            + "</style></head>"
            + "<body>"
            + "<div class='contenedor'>"
            + "<h1>🛎️ Gestión de Estancias</h1>"
            + reservaRealizada
            + "<form method='POST' action='/reservar'>"
            + "  <div style='text-align:left; color:#2c3e50; font-weight:bold; font-size:14px; margin-bottom:5px;'>Día de llegada:</div>"
            + "  <select name='dia'>" + opcionesSelect + "</select>"
            + "  <div style='text-align:left; color:#2c3e50; font-weight:bold; font-size:14px; margin-top:10px; margin-bottom:5px;'>Número de habitaciones:</div>"
            + "  <input type='number' name='habitaciones' value='1' min='1' max='5'>"
            + "  <button type='submit' style='margin-top:15px;'>📝 CONFIRMAR RESERVA</button>"
            + "</form>"
            + "</div>"
            + "</body></html>";
    }

}
