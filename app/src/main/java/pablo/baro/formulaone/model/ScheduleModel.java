package pablo.baro.formulaone.model;

public class ScheduleModel {
    String season;
    String round;
    String raceName;

    String date;
    String information;

    public ScheduleModel(String season, String round, String raceName, String date, String information) {
        this.season = season;
        this.round = round;
        this.raceName = raceName;
        this.date = date;
        this.information = information;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
