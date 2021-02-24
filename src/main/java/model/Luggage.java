package model;

public class Luggage {

    private String owner;
    private double weight;
    private double length;
    private double width;
    private double height;
    private String color;

    public Luggage(String owner, double weight, double length, double width, double height, String color) {
        this.owner = owner;
        this.weight = weight;
        this.length = length;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
