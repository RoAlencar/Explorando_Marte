package br.com.raxinformatica.explorandomarte.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;

@Entity
public class SpaceProbe implements Control {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSpaceprobe")
    private int id;

    @Column(name = "positionX", length = 2)
    private int positionX;

    @Column(name = "positionY", length = 2)
    private int positionY;

    @Column(name = "spaceprobeCoordinate", length = 1)
    private String spaceProbeDirection;

    @ManyToOne
    @JoinColumn(name = "idPlanet")
    private Planet planet;

    @Transient
    private ArrayList<String> cardinalPoints = new ArrayList<String>(Arrays.asList("N", "E", "S", "W"));

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public String getSpaceshipDirection() {
        return spaceProbeDirection;
    }

    public void setSpaceProbeDirection(String spaceProbeDirection) {
        this.spaceProbeDirection = spaceProbeDirection;
    }

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    public void turnRight() {
        int actualCoordinate = cardinalPoints.indexOf(spaceProbeDirection);
        if (actualCoordinate == 3) {
            spaceProbeDirection = cardinalPoints.get(0);
        } else {
            spaceProbeDirection = cardinalPoints.get(actualCoordinate + 1);
        }
    }

    public void turnLeft() {
        int actualCoordinate = cardinalPoints.indexOf(spaceProbeDirection);
        if (actualCoordinate == 0) {
            spaceProbeDirection = cardinalPoints.get(3);
        } else {
            spaceProbeDirection = cardinalPoints.get(actualCoordinate - 1);
        }
    }
    public void makeMove() {
        if(this.spaceProbeDirection.equals("N") && this.positionY < this.planet.getY()){
            this.positionY++;
        }else if(this.spaceProbeDirection.equals("S") && this.positionY > 0){
            this.positionY--;
        }else if(this.spaceProbeDirection.equals("E") && this.positionX < this.planet.getX()){
            this.positionX++;
        }else if(this.spaceProbeDirection.equals("W") && this.positionX > 0){
            this.positionX--;
        }else{
            System.out.println("movement not computed, please insert one of the valid movements. (Ex: N,S,E,W)");
        }
    }
}






