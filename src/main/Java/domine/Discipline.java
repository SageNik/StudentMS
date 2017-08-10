package domine;

/**
 * Created by Ник on 24.04.2016.
 */
public class Discipline {
    private Integer id;
    private String discipline;

    public Discipline (String disc){
        this.discipline = disc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }
}
