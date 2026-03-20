/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VC12;

/**
 *
 * @author anaranjo
 */
public class PaginasHTML {

    public static final String html_index = "<html><head><title>Inicio</title><meta charset='UTF-8'>"
            + "<link rel='icon' href='data:;base64,iVBORw0KGgo' />"
            + "<style>"
            + "body{font-family:Arial;background:linear-gradient(135deg,#74ebd5,#ACB6E5);text-align:center;padding-top:60px;}"
            + "h1{color:#333;}"
            + "a.button{display:inline-block;padding:15px 30px;background:#4CAF50;color:white;"
            + "text-decoration:none;border-radius:8px;font-size:18px;transition:0.3s;}"
            + "a.button:hover{background:#45a049;}"
            + "</style></head>"
            + "<body>"
            + "<h1>Servidor de Ejemplo HTTP</h1>"
            + "<p>Pulsa para ir al formulario</p>"
            + "<a class='button' href='/formulario'>Ir al Formulario</a>"
            + "</body></html>";

    public static final String html_formulario = "<html><head><title>Formulario</title><meta charset='UTF-8'>"
            + "<link rel='icon' href='data:;base64,iVBORw0KGgo' />"
            + "<style>"
            + "body{font-family:Arial;background:linear-gradient(135deg,#ffecd2,#fcb69f);text-align:center;padding-top:60px;}"
            + "h1{color:#333;}"
            + "form{background:white;display:inline-block;padding:30px;border-radius:10px;box-shadow:0 0 10px rgba(0,0,0,0.2);}"
            + "input{margin:10px;padding:8px;border-radius:5px;border:1px solid #ccc;font-size:16px;}"
            + "button{padding:10px 25px;background:#2196F3;color:white;border:none;border-radius:6px;font-size:16px;cursor:pointer;}"
            + "button:hover{background:#1976D2;}"
            + "</style></head>"
            + "<body>"
            + "<h1>Introduce tus datos</h1>"
            + "<form method='POST' action='/formulario'>"
            + "<div><input type='text' name='nombre' placeholder='Nombre' required></div>"
            + "<div><input type='number' name='edad' placeholder='Edad' required></div>"
            + "<div><button type='submit'>Enviar</button></div>"
            + "</form>"
            + "</body></html>";

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

    public static String html_index(String nombre) {
        String saludo = "";

        if (nombre != null && !nombre.isBlank()) {
            saludo = "<p style='font-size:22px;color:#333;margin-top:20px;'>Hola <b>"
                    + nombre
                    + "</b>!</p>";
        }

        return "<html><head><title>Inicio</title><meta charset='UTF-8'>"
                + "<link rel='icon' href='data:,/' />"
                + "<style>"
                + "body{font-family:Arial;background:linear-gradient(135deg,#74ebd5,#ACB6E5);text-align:center;padding-top:60px;}"
                + "h1{color:#333;}"
                + "a.button{display:inline-block;padding:15px 30px;background:#4CAF50;color:white;"
                + "text-decoration:none;border-radius:8px;font-size:18px;transition:0.3s;}"
                + "a.button:hover{background:#45a049;}"
                + "</style></head>"
                + "<body>"
                + "<h1>Servidor de Ejemplo HTTP</h1>"
                + saludo
                + "<p>Pulsa para ir al formulario</p>"
                + "<a class='button' href='/formulario'>Ir al Formulario</a>"
                + "</body></html>";
    }

}
