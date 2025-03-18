package entidad;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entidad {
    public int mundox , mundoy;
    public int velocidad;

    public BufferedImage up1 , up2, down1, down2, left1, left2, right1, right2;
    public String direccion;

    public int spritecounter = 0;
    public int spritenumber = 1 ;

    public Rectangle soidArea;
    public int solidareaX, solidareay;
    public boolean colisionOn = false;
}
