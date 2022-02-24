package br.com.raxinformatica.explorandomarte.builders;

import java.lang.String;
import br.com.raxinformatica.explorandomarte.models.Planet;



public class PlanetBuilder {
    private Planet elemento;
    private PlanetBuilder(){}

    public static PlanetBuilder umPlanet() {
        PlanetBuilder builder = new PlanetBuilder();
        inicializarDadosPadroes(builder);
        return builder;
    }

    public static void inicializarDadosPadroes(PlanetBuilder builder) {
        builder.elemento = new Planet();
        Planet elemento = builder.elemento;


        elemento.setId(0);
        elemento.setPlanetName("");
        elemento.setX(0);
        elemento.setY(0);
    }

    public PlanetBuilder comId(int param) {
        elemento.setId(param);
        return this;
    }

    public PlanetBuilder comPlanetName(String param) {
        elemento.setPlanetName(param);
        return this;
    }

    public PlanetBuilder comX(int param) {
        elemento.setX(param);
        return this;
    }

    public PlanetBuilder comY(int param) {
        elemento.setY(param);
        return this;
    }

    public Planet agora() {
        return elemento;
    }
}

