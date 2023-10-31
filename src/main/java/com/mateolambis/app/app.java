package com.mateolambis.app;
import com.mateolambis.dominio.Guayabita;
import javax.swing.*;

public class app {
    private static boolean juegoComenzado = false;

    public static void main(String[] args){
        Guayabita juego = new Guayabita();
        ImageIcon icon = new ImageIcon("C:\\Users\\mlamb\\IdeaProjects\\taller4\\src\\main\\java\\com\\mateolambis\\imagenes\\guayaba.png");
        if (!juegoComenzado){
            String[] opciones= {"Jugar", "Ver Instrucciones"};
            int seleccion = JOptionPane.showOptionDialog(null,
                    "Bienvenido al juego de la app!.\n\nQue quieres hacer?",
                    "app",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    icon,
                    opciones,
                    opciones[0]);

            switch (seleccion) {
                case JOptionPane.YES_OPTION:
                    System.out.println("Boton 'Jugar' seleccionado");
                    //logica para iniciar el juego
                    juego.jugar();
                case JOptionPane.NO_OPTION:
                    System.out.println("Botos 'Instrucciones' seleccionado");
                    String[] botones = {"Jugar", "Cancelar"};
                    int seleccionar =JOptionPane.showOptionDialog(null, "BIENVENIDO AL JUEGO DE LA GUAYABITA!!\n" +
                            "------------------------------------------------------" +
                            "------------------------------------------------------\n" +
                            "Para entrar al juego debes tener un monto de 500 monedas para apostar.\n" +
                            "Si al tirar el dado sacas 1 o 6, pierdes el turno y sigue el siguiente jugador.\n" +
                            "Si sacas entre 2 y 5, tienes la posibilidad de apostar la cantidad que desees y tirar de nuevo el dado.\n" +
                            "Si eliges no apostar, cedes el turno.\n\n" +
                            "Si sacas un número mayor al que sacaste en la tirada anterior, te llevas el pote.\n" +
                            "Si sacas igual o inferior, entregas el pote y este aumenta.\n\n" +
                            "Cuando te quedes sin dinero, serás eliminado del juego.\n" +
                            "Gana el último jugador que quede en el juego y se lleva el dinero que haya en el pote.\n\n" +
                            "BUENA SUERTE!", "Instrucciones", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon, botones, botones[0]);
                    switch (seleccionar) {
                        case JOptionPane.YES_OPTION:
                            System.out.println("Boton 'Jugar' seleccionado");
                            //logica para iniciar el juego
                            juego.jugar();
                        case JOptionPane.CANCEL_OPTION:
                            System.out.println("Boton 'Cancelar' seleccionado");
                            break;
                    }
            }
        }
    }
}