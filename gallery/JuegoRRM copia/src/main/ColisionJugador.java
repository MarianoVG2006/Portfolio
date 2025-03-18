package main;

import entidad.Entidad;


public class ColisionJugador {

    Juego gp;

    public ColisionJugador(Juego gp) {
        this.gp = gp;
    }

    public void colisiontiles(Entidad entity) {
        int entidadleftX = entity.mundox + entity.soidArea.x;
        int entidadrightX = entity.mundox + entity.soidArea.x + entity.soidArea.width;
        int entidadtopY = entity.mundoy + entity.soidArea.y;
        int entidadbottomY = entity.mundoy + entity.soidArea.y + entity.soidArea.height;

        int entidadLeftCol = entidadleftX / gp.total;
        int entidadrightcol = entidadrightX / gp.total;
        int entidadTopRow = entidadtopY / gp.total;
        int entidadbottomrow = entidadbottomY / gp.total;

        int tilenumero1, tilenumero2;

        switch (entity.direccion) {
            case "up":
                entidadTopRow = (entidadtopY - entity.velocidad) / gp.total;
                tilenumero1 = gp.tilem.maptilenum[entidadLeftCol][entidadTopRow];
                tilenumero2 = gp.tilem.maptilenum[entidadrightcol][entidadTopRow];
                if (gp.tilem.tiles[tilenumero1].colisionable == true || gp.tilem.tiles[tilenumero2].colisionable == true) {
                    entity.colisionOn = true;
                }
                break;
            case "down":
                entidadbottomrow = (entidadbottomY + entity.velocidad) / gp.total;
                tilenumero1 = gp.tilem.maptilenum[entidadLeftCol][entidadbottomrow];
                tilenumero2 = gp.tilem.maptilenum[entidadrightcol][entidadbottomrow];
                if (gp.tilem.tiles[tilenumero1].colisionable == true || gp.tilem.tiles[tilenumero2].colisionable == true) {
                    entity.colisionOn = true;
                }
                break;
            case "left":
                entidadLeftCol = (entidadleftX - entity.velocidad) / gp.total;
                tilenumero1 = gp.tilem.maptilenum[entidadLeftCol][entidadTopRow];
                tilenumero2 = gp.tilem.maptilenum[entidadLeftCol][entidadbottomrow];
                if (gp.tilem.tiles[tilenumero1].colisionable == true || gp.tilem.tiles[tilenumero2].colisionable == true) {
                    entity.colisionOn = true;
                }
                break;
            case "right":
                entidadrightcol = (entidadrightX - entity.velocidad) / gp.total;
                tilenumero1 = gp.tilem.maptilenum[entidadrightcol][entidadTopRow];
                tilenumero2 = gp.tilem.maptilenum[entidadrightcol][entidadbottomrow];
                if (gp.tilem.tiles[tilenumero1].colisionable == true || gp.tilem.tiles[tilenumero2].colisionable == true) {
                    entity.colisionOn = true;
                }
                break;

        }


    }

    public int checkObject(Entidad entity, boolean jugador) {
        int index = 999;
        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] != null) {
                entity.soidArea.x = entity.mundox + entity.soidArea.x;
                entity.soidArea.y = entity.mundoy + entity.soidArea.y;

                gp.obj[i].solidarea.x = gp.obj[i].mundox + gp.obj[i].solidarea.x;
                gp.obj[i].solidarea.y = gp.obj[i].mundoy + gp.obj[i].solidarea.y;

                switch (entity.direccion) {
                    case "up":
                        entity.soidArea.y -= entity.velocidad;
                        if (entity.soidArea.intersects(gp.obj[i].solidarea)) {
                            if(gp.obj[i].colision == true){
                                entity.colisionOn = true;
                            }
                            if (jugador == true){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.soidArea.y += entity.velocidad;
                        if (entity.soidArea.intersects(gp.obj[i].solidarea)) {
                            if(gp.obj[i].colision == true){
                                entity.colisionOn = true;
                            }
                            if (jugador == true){
                                index = i;
                            }
                        }

                        break;
                    case "left":
                        entity.soidArea.x -= entity.velocidad;
                        if (entity.soidArea.intersects(gp.obj[i].solidarea)) {
                            if(gp.obj[i].colision == true){
                                entity.colisionOn = true;
                            }
                            if (jugador == true){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.soidArea.x += entity.velocidad;
                        if (entity.soidArea.intersects(gp.obj[i].solidarea)) {
                            if(gp.obj[i].colision == true){
                                entity.colisionOn = true;
                            }
                            if (jugador == true){
                                index = i;
                            }
                        }
                       break;
                }
                entity.soidArea.x = entity.solidareaX;
                entity.soidArea.y = entity.solidareay;
                gp.obj[i].solidarea.x = gp.obj[i].solidareax;
                gp.obj[i].solidarea.y = gp.obj[i].solidareay;
            }
        }
        return index;

    }
}