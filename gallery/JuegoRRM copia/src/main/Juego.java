package main;

import entidad.Jugaodr;
import objeto.Superobjetos;
import tiles.Tilemanager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static java.lang.Thread.sleep;

public class Juego extends JPanel implements Runnable {
    // atributos
    // declaramos los anchos de pantalla
        final int titulo = 16;
        final int escala = 3;
        public final int total = titulo * escala;
        public final int maxscreenalto = 20;
        public final int maxscreenbajo = 14;
        public final int pantallawidth = total * maxscreenalto;
        public final int pantallaheight = total * maxscreenbajo;
        public final int maxWorldCol = 50;
        public final int maxWorldRow = 50;

        BufferedImage tempscreen;
        Graphics2D g2;

        // Pantalla de inicio
        public final int titleState = 0;
        public final int playState = 1;

        int estadoJuego = titleState;  // Estado inicial es el menú

        int fps = 60; //fps juego

        Tilemanager tilem = new Tilemanager(this); //Estructura del mapa (Cesped, agua, arbol...)

        Musica musica = new Musica(); // musica para el juego
        Musica se = new Musica();

        Recojerporteclado keyh = new Recojerporteclado(this); //Recojemos por teclado las teclas que funciones para mover el personaje
        Thread juegoThread;

        public ColisionJugador colisioncheck = new ColisionJugador(this); // colision del jugador
        public Rectangle infoButtonBounds;
        public RecojerportecladoMenu keyhmenu = new RecojerportecladoMenu (this); // funciones de tecleo en el panel del menu
        public Jugaodr jugaodr = new Jugaodr(this, keyh); // personaje del juego
        public Assetsetter aset = new Assetsetter(this); // Posicion de los Objetos en el mapa
        public UID textopantalla = new UID(this); // texto por pantalla
        public Superobjetos obj[] = new Superobjetos[10]; // numero de objetos para poner en el mapa + las colisiones

        // constructor
        public Juego() {
            this.setPreferredSize(new Dimension(pantallawidth, pantallaheight));
            this.setBackground(Color.BLACK);
            this.setDoubleBuffered(true);
            this.addKeyListener(keyh);
            this.addKeyListener(keyhmenu);
            this.setFocusable(true);
            this.empezareljuego();

        }


    public void reiniciarJuego() {
        // Restaurar estado del juego
        estadoJuego = titleState;  // Volver al menú principal

        // Resetear el jugador
        jugaodr.setvalordefecto();
        jugaodr.haskey = 0; // Resetear llaves obtenidas

        // Resetear objetos y mapa
        aset.setObjecto();

        // Resetear tiempo
        textopantalla.tiempodejuego = 0;
        textopantalla.JuegoTerminado = false;

        // Reiniciar música
        pararmusica();
        empezarmusica(5); // Música del menú

        // Reiniciar el hilo del juego
        if (juegoThread == null) {
            empezareljuego();
        }
    }


     // metodo

    public void setupgame() {

            aset.setObjecto();
            empezarmusica(0);
            tempscreen = new BufferedImage(pantallawidth , pantallaheight, BufferedImage.TYPE_INT_ARGB);
            g2 = (Graphics2D)tempscreen.getGraphics();
        }

        //hace que empieze el juego
        public void empezareljuego() {
            juegoThread = new Thread(this);
            juegoThread.start();
        }

        // metodo que cuenta los freins por segundo del juego

        public void run() {
            double drawInterval = 1000000000 / fps;

            double delta = 0;
            long lasttime = System.nanoTime();
            long timer = 0;
            int drawCount = 0;

            while (juegoThread != null) {
                long currentTime = System.nanoTime();

                delta += (currentTime - lasttime) / drawInterval;
                timer += (currentTime - lasttime);
                lasttime = currentTime;

                if (delta >= 1) {
                    update();
                    repaint();
                    delta--;
                    drawCount++;
                }

                if (timer >= 1000000000) {
                    System.out.println("FPS: " + drawCount);
                    drawCount = 0;
                    timer = 0;
                }
            }
        }

        public void update() {
            if (estadoJuego == playState) {
                jugaodr.update();
            }
        }

        // metodo que hace pintar el panel

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            if (estadoJuego == titleState) {
                drawMenu(g2);
            } else if (estadoJuego == playState) {
                tilem.draw(g2);

                for (int i = 0; i < obj.length; i++) {
                    if (obj[i] != null) {
                        obj[i].draw(g2, this);
                    }
                }

                jugaodr.draw(g2);
                textopantalla.pintar(g2);
            }

            g2.dispose();
        }

    private int centrarTexto(Graphics2D g2, String texto) {
        return pantallawidth / 2 - g2.getFontMetrics().stringWidth(texto) / 2;
    }

    private void mostrarInstrucciones() {
        JOptionPane.showMessageDialog(this,
                "Bienvenido al juego. Tu misión es encontrar las llaves y llegar a la meta.\n" +
                        "- Usa las flechas para moverte.\n" +
                        "- Evita obstáculos y recoge objetos.\n" +
                        "- ¡Buena suerte!",
                "¿Cómo jugar?",
                JOptionPane.INFORMATION_MESSAGE);
    }


    //  metodo Dibuja el menú principal
        public void drawMenu(Graphics2D g2) {

            Image fondo = new ImageIcon(getClass().getResource("/Menu/JuegoMenu.png")).getImage();

            g2.drawImage(fondo, 0, 0, pantallawidth, pantallaheight, null);


            String start = "Presiona ENTER para comenzar";
            String exit = "Presiona ESC para salir";
            String info = "Presione I para las instrucciones";

            g2.setFont(new Font("Arial", Font.BOLD, 40));
            g2.setColor(Color.blue);

            g2.setFont(new Font("Arial", Font.BOLD, 30));

            g2.drawString(start, pantallawidth / 2 - g2.getFontMetrics().stringWidth(start) / 2, pantallaheight - 80);
            g2.drawString(exit, pantallawidth / 2 - g2.getFontMetrics().stringWidth(exit) / 2, pantallaheight - 80 - 40);

            int startY = pantallaheight - 80;
            int exitY = startY - 40;
            int infoY = exitY - 40;

            g2.drawString(start, centrarTexto(g2, start), startY);
            g2.drawString(exit, centrarTexto(g2, exit), exitY);
            g2.setColor(Color.blue);
            g2.drawString(info, centrarTexto(g2, info), infoY);

            // Guarda la posición del botón invisible
            Rectangle infoButtonBounds = new Rectangle(centrarTexto(g2, info), infoY - 30, g2.getFontMetrics().stringWidth(info), 40);
        }


        // empieza la musica del juego
        public void empezarmusica(int i) {
            musica.setFile(i);
            musica.play();
            musica.loop();
        }
        // metodo para parar la musica
        public void pararmusica() {
            musica.parar();
        }

        public void playse(int i) {
            se.setFile(i);
            se.play();
        }



    }

