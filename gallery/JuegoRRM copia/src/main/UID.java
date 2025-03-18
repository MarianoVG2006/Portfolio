package main;

import objeto.Obj_llave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UID{
        Juego gp;
        Font arial_40, arial_80B;
        BufferedImage keyImage;
        public boolean mensajeon = false;
        public String mensaje = "";
        int mensajeTiempo = 0;
        public boolean JuegoTerminado = false;

        double tiempodejuego;
        DecimalFormat forma = new DecimalFormat("#0.00");

         public UID (Juego gp){
                this.gp = gp;
                arial_40 = new Font("Arial", Font.BOLD, 40);
                arial_80B = new Font("Arial", Font.BOLD  , 80);
                Obj_llave key = new Obj_llave();
                keyImage = key.image;
         }

         public void mostrarmensaje(String texto){
             mensaje = texto;
             mensajeon = true;
         }

         public void pintar(Graphics2D g2){

             if(JuegoTerminado == true){

                 g2.setFont(arial_40);
                 g2.setColor(Color.BLUE);
                    String texto;
                    int text;
                    int x;
                    int y;
                    texto = "HAS CONSEGUIDO EL TESORO";
                    text =  (int)g2.getFontMetrics().getStringBounds(texto,g2).getWidth();

                  x = gp.pantallawidth/2 - text/2;
                  y = gp.pantallaheight/2 - (gp.total*3);
                 g2.drawString(texto , x , y);

                 texto = "Pulsa R para ir al menú";
                 text =  (int)g2.getFontMetrics().getStringBounds(texto,g2).getWidth();

                 x = gp.pantallawidth/2 - text/2;
                 y = gp.pantallaheight/2 + (gp.total*5);
                 g2.drawString(texto , x , y);

                 texto = "Tu tiempo ha sido de: " +forma.format(tiempodejuego);
                 text =  (int)g2.getFontMetrics().getStringBounds(texto,g2).getWidth();

                 x = gp.pantallawidth/2 - text/2;
                 y = gp.pantallaheight/2 + (gp.total*4 );
                 g2.drawString(texto , x , y);


                 g2.setFont(arial_80B );
                 g2.setColor(Color.yellow);

                 texto = "ENHORABUENA";
                 text =  (int)g2.getFontMetrics().getStringBounds(texto,g2).getWidth();
                  x = gp.pantallawidth/2 - text/2;
                  y = gp.pantallaheight/2 + (gp.total*2);
                 g2.drawString(texto , x , y);

                 gp.juegoThread = null;

             }else{
                 g2.setFont(arial_40);
                 g2.setColor(Color.WHITE);
                 g2.drawImage(keyImage , gp.total/2 , gp.total/2 ,gp.total,gp.total,null);
                 g2.drawString("x " +gp.jugaodr.haskey,74,50 );

                 tiempodejuego += (double)1/60;
                 g2.drawString("Tiempo: " +forma.format(tiempodejuego),gp.total*11,65);



                 if (mensajeon == true){
                     g2.setFont(g2.getFont().deriveFont(30F));
                     g2.drawString(mensaje,gp.total/2,gp.total *5);

                     mensajeTiempo++;

                     if(mensajeTiempo > 120 ){
                         mensajeTiempo = 0;
                         mensajeon = false;

                     }
                 }
             }





         }

}
