package tiles;

import main.Juego;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Tilemanager {
    Juego gp;
    public  Tiles[] tiles;
     public int  maptilenum [][];


    public Tilemanager(Juego gp) {
        this.gp = gp;
        tiles = new Tiles[10];
        maptilenum = new int[gp.maxWorldCol][gp.maxWorldRow];
        gettileimage();
        cargarelmapa("/maps/world01.txt");
    }

    public void gettileimage() {
        try {
            tiles[0] = new Tiles();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/cesped.png"));


            tiles[1] = new Tiles();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/piedra.png"));
            tiles[1].colisionable = true;

            tiles[2] = new Tiles();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/agua.png"));
            tiles[2].colisionable = true;

            tiles[3] = new Tiles();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));


            tiles[4] = new Tiles();
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/arbol.png"));
            tiles[4].colisionable = true;

            tiles[5] = new Tiles();
            tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/arena.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void cargarelmapa(String filePath){
         try{
             InputStream is = getClass().getResourceAsStream(filePath);
             BufferedReader br = new BufferedReader(new InputStreamReader(is));

             int col = 0;
             int row = 0;

             while(col < gp.maxWorldCol && row < gp.maxWorldRow ){
                 String linea = br.readLine();

                 while(col < gp.maxWorldCol){
                     String numbers [] = linea.split(" ");

                     int num = Integer.parseInt(numbers[col]);

                     maptilenum[col][row] = num;
                     col++;
                 }
                 if (col == gp.maxWorldCol   ){
                      col = 0;
                      row ++;
                 }
             }
             br.close();
         }catch (Exception e){

         }
    }
    public void draw(Graphics2D g2){

        int Worldcol = 0;
        int Worldrow = 0;


        while (Worldcol < gp.maxWorldCol && Worldrow < gp.maxWorldRow){

            int titlenum = maptilenum[Worldcol][Worldrow];

            int worldX = Worldcol * gp.total;
            int worldY = Worldrow * gp.total;
            int screenX = worldX - gp.jugaodr.mundox + gp.jugaodr.screenX;
            int screenY = worldY - gp.jugaodr.mundoy + gp.jugaodr.screenY;





            g2.drawImage(tiles[titlenum].image, screenX, screenY, gp.total, gp.total, null);
            Worldcol++;

            if(Worldcol == gp.maxWorldCol){
                Worldcol = 0;

                Worldrow++;

            }
        }

    }
}