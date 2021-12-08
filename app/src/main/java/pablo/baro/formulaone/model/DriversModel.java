package pablo.baro.formulaone.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "driver_table")

public class DriversModel {
    public static final String DRIVER_KEY = "driver_id";
    public static final List<DriversModel> DRIVER_LIST = new ArrayList<>();

    @PrimaryKey
    private int id;

    String name;


    String surname;
    String nationality;
    String number;
    int img;

    public DriversModel(int id, String name, String surname, String nationality, String number, int img){
        this.id = id;
        this.name=  name;
        this.surname = surname;
        this.nationality = nationality;
        this.number = number;
        this.img = img;
        DRIVER_LIST.add(this);
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
