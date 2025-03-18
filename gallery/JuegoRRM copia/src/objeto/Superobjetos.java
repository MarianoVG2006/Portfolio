package objeto;

import main.Juego;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Superobjetos {

    public BufferedImage image;
    public String nombre;
    public boolean colision = false;
    public int mundox , mundoy;
    public Rectangle solidarea = new Rectangle(0,0,48,48);

    public int solidareax = 0;
    public int solidareay = 0;

    public void draw (Graphics2D g2, Juego gp){

        int screenX = mundox - gp.jugaodr.mundox + gp.jugaodr.screenX;
        int screenY = mundoy - gp.jugaodr.mundoy + gp.jugaodr.screenY;

        if(mundox + gp.total >  gp.jugaodr.mundox - gp.jugaodr.screenX &&
                mundox - gp.total < gp.jugaodr.mundox + gp.jugaodr.screenX &&
                mundoy + gp.total > gp.jugaodr.mundoy - gp.jugaodr.screenY &&
                mundoy - gp.total < gp.jugaodr.mundoy + gp.jugaodr.screenY){
            g2.drawImage(image, screenX,screenY,gp.total,gp.total,null);
        }






    }
}
