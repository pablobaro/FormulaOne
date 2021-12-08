package pablo.baro.formulaone.model;

import androidx.room.Entity;

public class DriversModel {
    String name;
    String surname;
    String nationality;
    String number;
    int img;

    public DriversModel(String name, String surname, String nationality, String number, int img){
        this.name=  name;
        this.surname = surname;
        this.nationality = nationality;
        this.number = number;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
