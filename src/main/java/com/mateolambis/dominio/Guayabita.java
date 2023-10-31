package com.mateolambis.dominio;
import com.mateolambis.dominio.Jugador;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Guayabita {
    private int jugadorActual = 0;
    private Jugador[] jugadores;
    private int numJugadores;
    private int pote;
    private int turno;
    private int dado = 0;

    public Guayabita() {
        this.pote = 0;
    }

    private boolean iniciarJuego = false;


    public void jugar() {
        int numJugadores = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de jugadores:"));
        int apuestaInicial = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el valor minimo de apuesta inicial:"));

        // Crear arreglo de jugadores
        jugadores = new Jugador[numJugadores];

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

        boolean juegoActivo = true;
        boolean jugadorGano = false;

        ImageIcon icon = new ImageIcon("C:\\Users\\mlamb\\IdeaProjects\\taller4\\src\\main\\java\\com\\mateolambis\\imagenes\\hombre.png");

        while (juegoActivo) {
            jugadorActual = (jugadorActual + 1) % jugadores.length;
            Jugador jugador = jugadores[jugadorActual];
            // Mostrar ventana de lanzar dado al jugador
            int opcion = JOptionPane.showOptionDialog(null, jugador.getNombre() + " Desea lanzar el dado?", "app", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon, null, null);

            // Verificar opción seleccionada
            if (opcion == JOptionPane.YES_OPTION) {
                // Lanzar dado
                int resultadoDado = lanzarDado();

                // Verificar resultado del dado
                if (resultadoDado == 1 || resultadoDado == 6) {
                    ImageIcon image = new ImageIcon("C:\\Users\\mlamb\\IdeaProjects\\chaptaller4\\src\\main\\java\\com\\mateolambis\\imagenes\\eliminar.png");
                    // Jugador pierde la posibilidad de apostar
                    JOptionPane.showOptionDialog(null, jugador.getNombre() +
                            " pierde la posibilidad de apostar", "Apuesta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, image, null, null);
                } else {
                    ImageIcon image = new ImageIcon("C:\\Users\\mlamb\\IdeaProjects\\chaptaller4\\src\\main\\java\\com\\mateolambis\\imagenes\\efectivo.png");
                    // Jugador tiene la posibilidad de apostar
                    int opcionApostar = JOptionPane.showOptionDialog(null, jugador.getNombre() + " Desea apostar? \n (Tienes disponible: " + jugador.getDinero() + ")", "Apuesta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, image, null, null);

                    // Verificar opción de apostar
                    if (opcionApostar == JOptionPane.YES_OPTION) {
                        // Solicitar monto de apuesta
                        int montoApuesta = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el monto de apuesta:"));

                        // Verificar si el jugador tiene suficiente dinero para apostar
                        if (jugador.getDinero() >= montoApuesta) {
                            // Tirar nuevamente el dado
                            int nuevoResultadoDado = lanzarDado();

                            // Verificar resultado del dado
                            if (nuevoResultadoDado > resultadoDado) {
                                // Jugador gana la apuesta
                                jugador.sumarDinero(montoApuesta);
                                poteInicial -= montoApuesta;
                                JOptionPane.showMessageDialog(null, jugador.getNombre() + " gana la apuesta");
                            } else {
                                // Jugador pierde la apuesta
                                jugador.restarDinero(montoApuesta);
                                poteInicial += montoApuesta;
                                JOptionPane.showMessageDialog(null, jugador.getNombre() + " pierde la apuesta");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, jugador.getNombre() + " no tiene suficiente dinero para apostar, has quedado eliminado");
                            eliminarJugador(jugadorActual);

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, jugador.getNombre() + " cede su turno");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "El juego ha terminado");
            }
            if (!quedanJugadoresConDinero() || poteInicial <= 0) {
                juegoActivo = false;
            }
            for (int i = 0; i < jugadores.length; i++) {
                if (jugadores[i].getDinero() > 0) {
                    jugadorGano = true;
                    break;
                }
            }
        }



        if (jugadorGano && poteInicial <= 0) {
            Jugador jugador = jugadores[jugadorActual];
            ImageIcon iconoGanador = new ImageIcon("C:\\Users\\mlamb\\IdeaProjects\\chaptaller4\\src\\main\\java\\com\\mateolambis\\imagenes\\trofeo.png");
            JOptionPane.showMessageDialog(null, jugador.getNombre() + " gano el pote.", "Ganador Final", JOptionPane.QUESTION_MESSAGE, iconoGanador);
        } else if (jugadorGano) {
            ImageIcon iconoCasino = new ImageIcon("C:\\Users\\mlamb\\IdeaProjects\\chaptaller4\\src\\main\\java\\com\\mateolambis\\imagenes\\casino.png");
            JOptionPane.showMessageDialog(null, "Gano el casino!", "Casino Gana", JOptionPane.QUESTION_MESSAGE, iconoCasino);
        }

        // Mostrar cantidad de dinero con la que quedó cada jugador
        for (Jugador jugador : jugadores) {
            JOptionPane.showMessageDialog(null, "El jugador " + jugador.getNombre() + " quedo con $" + jugador.getDinero());
            break;
        }
    }
    private void eliminarJugador(int indice) {
        Jugador[] nuevosJugadores = new Jugador[jugadores.length - 1];
        int contador = 0;
        for (int i = 0; i < jugadores.length; i++) {
            if (i != indice) {
                nuevosJugadores[contador] = jugadores[i];
                contador++;
            }
        }
        jugadores = nuevosJugadores;
        numJugadores--;
        jugadorActual = jugadorActual % numJugadores;
    }

    private boolean quedanJugadoresConDinero() {
        for (Jugador jugador : jugadores) {
            if (jugador.getDinero() > 0) {
                return true;
            }
        }
        return false;
    }

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


    public int getJugadorActual() {
        return jugadorActual;
    }

    public Jugador[] getJugadores() {
        return jugadores;
    }

    public int getPote() {
        return pote;
    }

    public int getTurno() {
        return turno;
    }

    public int getDado() {
        return dado;
    }

    public void setPote(int pote) {
        this.pote = pote;
    }
}



