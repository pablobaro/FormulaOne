package pablo.baro.formulaone.model;

public class ChampionshipModel implements Comparable<ChampionshipModel>{
    String name;
    String surname;
    double points;
    String constructorName;
    int position;

    public ChampionshipModel(int position, String name, String surname, double points, String constructorName){
        this.position = position;
        this.name = name;
        this.surname = surname;
        this.points = points;
        this.constructorName = constructorName;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public String getConstructorName() {
        return constructorName;
    }

    public void setConstructorName(String constructorName) {
        this.constructorName = constructorName;
    }

    @Override
    public int compareTo(ChampionshipModel o) {
        int position = ((ChampionshipModel) o).getPosition();

        return this.position - position;
    }
}