/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package uam.lpoo.mx.practica5;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalTime;
import java.util.Scanner;

/**
 * Clase Test Cliente
 * esta clase invoca a la clase cliente para escribir desde consola los datos que se van a serializar
 * se conecta a otra maquina mediante la IP y el numero de puerto, envia la informacion de la consola 
 * a un servidor, se procesa y devuelve los datos requeridos que fueron ingresados desde consola 
 * @author Jesus Alberto, Omar Castillo, Edgar Angeles 
 * @version 1.0
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /**
         * @see <a href = "https://www.programarya.com/Cursos-Avanzados/Java/Sockets"/>Clase Socket</a>
         *  @see <a href = "https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/java/io/ObjectOutputStream.html"/>Clase ObjectOutputStream</a>
         *  @see <a href = "https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/java/io/ObjectInputStream.html"/>Clase ObjectInputStream</a>
         *  @see <a href = "https://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html"/>Clase Scanner</a>
         */
        ObjectOutputStream out;
        ObjectInputStream in;
        Socket cliente;
        Scanner scan = new Scanner (System.in);
        Consulta consulta;
        String entrada = scan.nextLine();
        LocalTime hoy = LocalTime.now();
        consulta = new Consulta(entrada,hoy);
        
        try {
            cliente= new Socket("192.168.8.84", 12345);
            out = new ObjectOutputStream(cliente.getOutputStream());
            out.writeObject(consulta);
            System.out.println("Objeto Serializado........");
            
            in = new ObjectInputStream(cliente.getInputStream());
            String respuesta = (String) in.readObject();
            System.out.println(respuesta);
            
            out.close();
            in.close();
            cliente.close();
        } catch (IOException ex1) {
            System.out.println("Error" + ex1.getMessage());
        } catch (ClassNotFoundException ex2) {
            System.out.println("Error2" + ex2.getMessage());
        }
        
    }
    
}
