package com.mateolambis.app;

import com.mateolambis.dominio.Jugador;

import javax.swing.*;
import java.util.Random;

public class Guayabita {
    public static void main(String[] args) {


        // Mostrar instrucciones del juego
        ImageIcon icon = new ImageIcon("C:\\Users\\mlamb\\IdeaProjects\\taller4\\src\\main\\java\\com\\mateolambis\\dominio\\guayaba.png");
        String[] opciones = {"Jugar", "Ver Instrucciones"};
        int seleccion = JOptionPane.showOptionDialog(null,
                "Bienvenido al juego de la Guayabita!\n\nQue quieres hacer?",
                "Guayabita",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                icon,
                opciones,
                opciones[0]);

        switch (seleccion) {
            case JOptionPane.YES_OPTION:
                System.out.println("Botón 'Jugar' seleccionado");
                // Lógica para iniciar el juego
                // Solicitar número de jugadores y valor mínimo de apuesta inicial
                int numJugadores = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de jugadores:"));
                int apuestaInicial = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el valor minimo de apuesta inicial:"));

                // Crear arreglo de jugadores
                Jugador[] jugadores = new Jugador[numJugadores];

                // Solicitar nombre y monto de dinero de cada jugador
                for (int i = 0; i < numJugadores; i++) {
                    String nombre = JOptionPane.showInputDialog("Ingrese el nombre del jugador " + (i + 1) + ":");
                    int dinero = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el monto de dinero del jugador " + (i + 1) + ":"));
                    jugadores[i] = new Jugador(nombre, dinero);
                }

                // Calcular pote inicial
                int poteInicial = numJugadores * apuestaInicial;

                // Restar apuesta inicial del dinero de cada jugador
                for (Jugador jugador : jugadores) {
                    jugador.restarDinero(apuestaInicial);
                }

                // Mostrar ventana de juego
                while (poteInicial > 0) {
                    // Mostrar ventana de lanzar dado al jugador 1
                    int opcion = JOptionPane.showOptionDialog(null, "Desea lanzar el dado?", "Guayabita", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon, null, null);

                    // Verificar opción seleccionada
                    if (opcion == JOptionPane.YES_OPTION) {
                        // Lanzar dado
                        int resultadoDado = lanzarDado();

                        // Verificar resultado del dado
                        if (resultadoDado == 1 || resultadoDado == 6) {
                                // Jugador pierde la posibilidad de apostar
                                JOptionPane.showMessageDialog(null,
                            " pierde la posibilidad de apostar");
                        } else {
                            // Jugador tiene la posibilidad de apostar
                            int opcionApostar = JOptionPane.showOptionDialog(null, "Desea apostar?", "Apuesta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon, null, null);

                            // Verificar opción de apostar
                            if (opcionApostar == JOptionPane.YES_OPTION) {
                                // Solicitar monto de apuesta
                                int montoApuesta = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el monto de apuesta:"));

                                // Verificar si el jugador tiene suficiente dinero para apostar
                                if (jugadores[0].getDinero() >= montoApuesta) {
                                    // Tirar nuevamente el dado
                                    int nuevoResultadoDado = lanzarDado();

                                    // Verificar resultado del dado
                                    if (nuevoResultadoDado > resultadoDado) {
                                            // Jugador gana la apuesta
                                            jugadores[0].sumarDinero(montoApuesta);
                                            poteInicial -= montoApuesta;
                                            JOptionPane.showMessageDialog(null, "gana la apuesta");
                                    } else {
                                            // Jugador pierde la apuesta
                                            jugadores[0].restarDinero(montoApuesta);
                                            poteInicial += montoApuesta;
                                            JOptionPane.showMessageDialog(null, "pierde la apuesta");
                                    }
                                } else {
                                        JOptionPane.showMessageDialog(null, "no tiene suficiente dinero para apostar");

                                }
                            } else {
                                    JOptionPane.showMessageDialog(null, "cede su turno");
                                }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "El juego ha terminado");
                        break;
                    }
                }

                // Mostrar cantidad de dinero con la que quedó cada jugador
                for (Jugador jugador : jugadores) {
                    JOptionPane.showMessageDialog(null, "El jugador " + jugador.getNombre() + " quedo con $" + jugador.getDinero());
                }
                break;

            case JOptionPane.NO_OPTION:
                System.out.println("Botón 'Instrucciones' seleccionado");
                String[] botones = {"Jugar", "Cancelar"};
                // Lógica
                int seleccionar = JOptionPane.showOptionDialog(null, "INSTRUCCIONES:\n\n" +
                        "Bienvenido al juego de la Guayabita!!\n" +
                        "------------------------------------------------------------------------------------------" +
                        "------------------------------------------------------\n" +
                        "Para entrar al juego debes tener un monto de 500 monedas para apostar.\n" +
                        "Si al tirar el dado sacas 1 o 6, pierdes el turno y sigue el siguiente jugador.\n" +
                        "Si sacas entre 2 y 5, tienes la posibilidad de apostar la cantidad que desees y tirar de nuevo el dado.\n" +
                        "Si eliges no apostar, cedes el turno.\n\n" +
                        "Si sacas un número mayor al que sacaste en la tirada anterior, te llevas el pote.\n" +
                        "Si sacas igual o inferior, entregas el pote y este aumenta.\n\n" +
                        "Cuando te quedes sin dinero, serás eliminado del juego.\n" +
                        "Gana el último jugador que quede en el juego y se lleva el dinero que haya en el pote.\n\n" +
                        "¡BUENA SUERTE!", "Instrucciones", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon, botones, botones[0]);
                switch (seleccionar) {
                    case JOptionPane.YES_OPTION:
                        System.out.println("Boton 'Jugar' seleccionado");
                        //logica
                        // Solicitar número de jugadores y valor mínimo de apuesta inicial
                        int numJugadores2 = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de jugadores:"));
                        int apuestaInicial2 = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el valor minimo de apuesta inicial:"));

                        // Crear arreglo de jugadores
                        Jugador[] jugadores2 = new Jugador[numJugadores2];

                        // Solicitar nombre y monto de dinero de cada jugador
                        for (int i = 0; i < numJugadores2; i++) {
                            String nombre = JOptionPane.showInputDialog("Ingrese el nombre del jugador " + (i + 1) + ":");
                            int dinero = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el monto de dinero del jugador " + (i + 1) + ":"));
                            jugadores2[i] = new Jugador(nombre, dinero);
                        }

                        // Calcular pote inicial
                        int poteInicial2 = numJugadores2 * apuestaInicial2;

                        // Restar apuesta inicial del dinero de cada jugador
                        for (Jugador jugador : jugadores2) {
                            jugador.restarDinero(apuestaInicial2);
                        }

                        // Mostrar ventana de juego
                        while (poteInicial2 > 0) {
                            // Mostrar ventana de lanzar dado al jugador 1
                            int opcion = JOptionPane.showOptionDialog(null, "Desea lanzar el dado?", "Guayabita", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon, null, null);

                            // Verificar opción seleccionada
                            if (opcion == JOptionPane.YES_OPTION) {
                                // Lanzar dado
                                int resultadoDado = lanzarDado();

                                // Verificar resultado del dado
                                if (resultadoDado == 1 || resultadoDado == 6) {
                                    // Jugador pierde la posibilidad de apostar
                                    JOptionPane.showMessageDialog(null,
                                            "El jugador pierde la posibilidad de apostar");
                                } else {
                                    // Jugador tiene la posibilidad de apostar
                                    int opcionApostar = JOptionPane.showOptionDialog(null, "Desea apostar?", "Apuesta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon, null, null);

                                    // Verificar opción de apostar
                                    if (opcionApostar == JOptionPane.YES_OPTION) {
                                        // Solicitar monto de apuesta
                                        int montoApuesta = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el monto de apuesta:"));

                                        // Verificar si el jugador tiene suficiente dinero para apostar
                                        if (jugadores2[0].getDinero() >= montoApuesta) {
                                            // Tirar nuevamente el dado
                                            int nuevoResultadoDado = lanzarDado();

                                            // Verificar resultado del dado
                                            if (nuevoResultadoDado > resultadoDado) {
                                                // Jugador gana la apuesta
                                                jugadores2[0].sumarDinero(montoApuesta);
                                                poteInicial2 -= montoApuesta;
                                                JOptionPane.showMessageDialog(null, "El jugador gana la apuesta");
                                            } else {
                                                // Jugador pierde la apuesta
                                                jugadores2[0].restarDinero(montoApuesta);
                                                poteInicial2 += montoApuesta;
                                                JOptionPane.showMessageDialog(null, "El jugador pierde la apuesta");
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(null, "El jugador no tiene suficiente dinero para apostar");
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "El jugador cede su turno");
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "El juego ha terminado");
                                break;
                            }
                        }

                        // Mostrar cantidad de dinero con la que quedó cada jugador
                        for (Jugador jugador : jugadores2) {
                            JOptionPane.showMessageDialog(null, "El jugador " + jugador.getNombre() + " quedó con $" + jugador.getDinero());
                        }
                        break;
                    case JOptionPane.CANCEL_OPTION:
                        System.out.println("Boton 'Cancelar' seleccionado");
                        break;
                }
        }
    }

    // Método para lanzar el dado y obtener un número aleatorio del 1 al 6
    public static int lanzarDado() {
        Random random = new Random();
        int result = random.nextInt(6) + 1;
        // Use a switch statement to display the corresponding dice image.
        String mensaje = "Tiraste un: " + result;
        ImageIcon image = new ImageIcon("src/main/java/com/mateolambis/imagenes/dado" + result + ".png");
        // Display the result of the dice roll using another JOptionPane.
        JOptionPane.showMessageDialog(null, mensaje, "Resultado de la tirada de dados", JOptionPane.INFORMATION_MESSAGE, image);

        return result;
    }
}
