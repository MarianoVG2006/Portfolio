package entidad;
import main.Juego;
import main.Recojerporteclado;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Jugaodr extends Entidad {
        Juego gp;
        Recojerporteclado keyh;

        public final int screenX;
        public final int screenY ;
        public int haskey = 0;

        public Jugaodr(Juego gp, Recojerporteclado keyh) {
            this.gp = gp;
            this.keyh = keyh;

            screenX = gp.pantallawidth/2 - (gp.total/2);
            screenY = gp.pantallaheight/2 - (gp.total/2 );

          soidArea = new Rectangle();
          soidArea.x = 8;
          soidArea.y = 16;
          solidareaX = soidArea.x;
          solidareay = soidArea.y;
          soidArea.width = 32;
          soidArea.height = 32;

            setvalordefecto();
            getjugadorimagen();
        }

        public void setvalordefecto(){
            mundox = gp.total * 23  ;
            mundoy = gp.total * 21 ;
            velocidad = 4;
            direccion = "down";
        }


        public void getjugadorimagen(){
            try{
                up1 = ImageIO.read(getClass().getResourceAsStream("/jugador/boy_up_1.png"));
                up2 = ImageIO.read(getClass().getResourceAsStream("/jugador/boy_up_2.png"));
                down1 = ImageIO.read(getClass().getResourceAsStream("/jugador/boy_down_1.png"));
                down2 = ImageIO.read(getClass().getResourceAsStream("/jugador/boy_down_2.png"));
                left1 = ImageIO.read(getClass().getResourceAsStream("/jugador/boy_left_1.png"));
                left2 = ImageIO.read(getClass().getResourceAsStream("/jugador/boy_left_2.png"));
                right1 = ImageIO.read(getClass().getResourceAsStream("/jugador/boy_right_1.png"));
                right2 = ImageIO.read(getClass().getResourceAsStream("/jugador/boy_right_2.png"));

            }catch (IOException e){
                e.printStackTrace();
            }

        }

        public void update(){
            if (keyh.upPressed == true || keyh.downPressed == true || keyh.leftPressed == true || keyh.rightPressed == true){
                if(keyh.upPressed == true){
                    direccion = "up";

                } else if (keyh.downPressed == true) {
                    direccion = "down";

                }else if (keyh.leftPressed == true){
                    direccion = "left";

                }else if (keyh.rightPressed == true){
                    direccion = "right";

                }
                //chequear colision
                colisionOn = false;

                gp.colisioncheck.colisiontiles(this);
                gp.colisioncheck.checkObject(this , true);

                int objIndex =  gp.colisioncheck.checkObject(this , true);

                pickobjeto( objIndex);
                //if
                if (colisionOn == false){
                    switch (direccion){
                        case "up": mundoy -= velocidad;break;
                        case "down": mundoy += velocidad;break;
                        case "left": mundox -= velocidad;break;
                        case "right": mundox += velocidad; break;

                    }
                }

                spritecounter++;
                if(spritecounter >= 10){
                    if(spritenumber == 1){
                        spritenumber = 2;
                    } else if (spritenumber == 2) {
                        spritenumber = 1 ;
                    }
                    spritecounter = 0;
                 }

            }

        }

        public void pickobjeto(int i){
                if ( i != 999){
                        String objectName = gp.obj[i].nombre;

                        switch (objectName){
                            case "key":
                                gp.playse(3);
                                haskey++;
                                gp.obj[i] = null;
                                gp.textopantalla.mostrarmensaje("¡Cogiste una llave!");
                                break;
                            case "puerta" :
                                if ( haskey > 0){

                                    gp.playse(1);

                                    gp.obj[i] = null;
                                    haskey--;
                                    gp.textopantalla.mostrarmensaje("¡Abriste la puerta!");
                                }
                                else {
                                    gp.textopantalla.mostrarmensaje("Cerrado.Consigue las llaves ");
                                }
                                break;
                            case "botas" :
                                gp.playse(4);
                                velocidad += 1;
                                gp.obj[i] = null;
                                gp.textopantalla.mostrarmensaje("Aceleraaa ");
                                break;
                            case "cofre" :
                                gp.textopantalla.JuegoTerminado = true;
                                gp.pararmusica();
                                break;
                        }
                }
        }
        public void draw(Graphics2D g2){



            BufferedImage image =  null;
            switch (direccion){
                case "up":
                    if(spritenumber == 1){
                        image = up1;
                    }
                    if(spritenumber == 2){
                        image = up2;
                    }

                    break;
                case "down":
                    if(spritenumber == 1){
                        image = down1;
                    }
                    if(spritenumber == 2){
                        image = down2;
                    }

                    break;
                case "left":
                    if(spritenumber == 1){
                        image = left1;
                    }
                    if(spritenumber == 2){
                        image = left2;
                    }

                    break;

                    case "right":
                        if(spritenumber == 1){
                            image = right1;
                        }
                        if(spritenumber == 2){
                            image = right2;
                        }

            }
            g2.drawImage(image,screenX,screenY,gp.total,gp.total,null);
        }

}
