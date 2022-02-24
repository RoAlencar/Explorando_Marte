package br.com.raxinformatica.explorandomarte.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;

@Entity
public class Spaceship {//implements Control {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSpaceship")
    private int id;

    @Column(name = "positionX", length = 2)
    private int positionX;

    @Column(name = "positionY", length = 2)
    private int positionY;

    @Column(name = "spaceshipCoordinate", length = 1)
    private String spaceshipDirection;

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
        return spaceshipDirection;
    }

    public void setSpaceshipDirection(String spaceshipDirection) {
        this.spaceshipDirection = spaceshipDirection;
    }

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    public void turnRight() {
        int actualCoordinate = cardinalPoints.indexOf(spaceshipDirection);
        if (actualCoordinate == 3) {
            spaceshipDirection = cardinalPoints.get(0);
        } else {
            spaceshipDirection = cardinalPoints.get(actualCoordinate + 1);
        }
    }

    public void turnLeft() {
        int actualCoordinate = cardinalPoints.indexOf(spaceshipDirection);
        if (actualCoordinate == 0) {
            spaceshipDirection = cardinalPoints.get(0);
        } else {
            spaceshipDirection = cardinalPoints.get(actualCoordinate - 1);
        }
    }
    public void makeMove() {
        if(this.spaceshipDirection.equals("N") && this.positionY < this.planet.getY()){
            this.positionY++;
        }else if(this.spaceshipDirection.equals("S") && this.positionY > 0){
            this.positionY--;
        }else if(this.spaceshipDirection.equals("E") && this.positionX < this.planet.getX()){
            this.positionX++;
        }else if(this.spaceshipDirection.equals("W") && this.positionX > 0){
            this.positionX--;
        }else{
            System.out.println("movement not computed, please insert one of the valid movements. (Ex: N,S,E,W)");
        }
    }
}






