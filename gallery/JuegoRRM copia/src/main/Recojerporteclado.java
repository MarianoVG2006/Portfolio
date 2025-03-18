package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Recojerporteclado implements KeyListener {

    public boolean upPressed, downPressed , leftPressed , rightPressed;

    Juego gp;

    public Recojerporteclado(Juego gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int codigo = e.getKeyCode();

        if (gp.textopantalla.JuegoTerminado) {
            if (codigo == KeyEvent.VK_R) {
                gp.reiniciarJuego();
            }
        }

        if (codigo == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (codigo == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (codigo == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (codigo == KeyEvent.VK_D) {
            rightPressed = true;
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
    int codigo = e.getKeyCode();
        if(codigo == KeyEvent.VK_W){
            upPressed = false;
        }
        if(codigo == KeyEvent.VK_S){
            downPressed = false;
        }
        if(codigo == KeyEvent.VK_A){
            leftPressed = false;
        }
        if (codigo == KeyEvent.VK_D){
            rightPressed = false;
        }


    }
}
