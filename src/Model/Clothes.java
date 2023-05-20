package Model;

import java.io.Serializable;

public class Clothes  implements Serializable {
    private String ID;
    private String name;
    private String type;
    private String brand;
    private String year;
    private int quantity;

    public Clothes(String ID, String name, String type, String brand, String year, int quantity){
        this.ID= ID;
        this.name = name;
        this.type = type;
        this.brand = brand;
        this.year = year;
        this.quantity = quantity;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
