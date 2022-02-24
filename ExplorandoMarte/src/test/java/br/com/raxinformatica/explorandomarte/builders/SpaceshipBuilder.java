package br.com.raxinformatica.explorandomarte.builders;

import br.com.raxinformatica.explorandomarte.model.Planet;
import br.com.raxinformatica.explorandomarte.model.Spaceship;


public class SpaceshipBuilder {
    private Spaceship elemento;
    private SpaceshipBuilder(){}

    public static SpaceshipBuilder umSpaceship() {
        SpaceshipBuilder builder = new SpaceshipBuilder();
        inicializarDadosPadroes(builder);
        return builder;
    }

    public static void inicializarDadosPadroes(SpaceshipBuilder builder) {
        builder.elemento = new Spaceship();
        Spaceship elemento = builder.elemento;


        elemento.setId(0);
        elemento.setPositionX(0);
        elemento.setPositionY(0);
        elemento.setSpaceshipDirection("");
        elemento.setPlanet(null);
    }

    public SpaceshipBuilder comId(int param) {
        elemento.setId(param);
        return this;
    }

    public SpaceshipBuilder comPositionX(int param) {
        elemento.setPositionX(param);
        return this;
    }

    public SpaceshipBuilder comPositionY(int param) {
        elemento.setPositionY(param);
        return this;
    }

    public SpaceshipBuilder comSpaceshipDirection(String param) {
        elemento.setSpaceshipDirection(param);
        return this;
    }

    public SpaceshipBuilder comPlanet(Planet param) {
        elemento.setPlanet(param);
        return this;
    }

    public Spaceship agora() {
        return elemento;
    }
}