package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;

public class RecojerportecladoMenu implements KeyListener {
    Juego gp;
    public RecojerportecladoMenu(Juego gp) {
        this.gp = gp;
    }


    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();



            if (gp.estadoJuego == gp.titleState) {
                if (code == KeyEvent.VK_ENTER) {
                    gp.pararmusica();  // Detener la música del menú
                    gp.estadoJuego = gp.playState;
                    gp.setupgame();  // Iniciar el juego y su música
                } else if (code == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
            }


        if (gp.estadoJuego == gp.titleState) {
            if (code == KeyEvent.VK_ENTER) {
                gp.estadoJuego = gp.playState;  // Cambiar a la pantalla del juego
                gp.setupgame();  // Preparar el juego
            } else if (code == KeyEvent.VK_ESCAPE) {
                System.exit(0);  // Salir del juego
            }
        }



        if (gp.estadoJuego == gp.titleState) {
            if (code == KeyEvent.VK_I) { // Si presiona "I"
                mostrarInstrucciones();
            }
        }
    }




    @Override
    public void keyReleased(KeyEvent e) {}

    public  void mostrarInstrucciones() {
    JOptionPane.showMessageDialog(null,
            "Bienvenido al juego. Tu misión es encontrar las llaves y llegar a la meta." +
                    "- Usa las flechas para moverte." +
                    "- Evita obstáculos y recoge objetos." +
                    "- ¡Buena suerte!",
            "¿Cómo jugar?",
            JOptionPane.INFORMATION_MESSAGE);
}
}


