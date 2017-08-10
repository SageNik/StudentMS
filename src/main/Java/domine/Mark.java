package domine;

/**
 * Created by Ник on 24.04.2016.
 */
public class Mark {
    private Integer id;
    private Integer mark;
    private Integer id_student;
    private Integer id_term;
    private Integer id_discipline;


    public Mark(Integer mark, Integer id_term, Integer id_student, Integer id_discipline) {
        this.mark = mark;
        this.id_term = id_term;
        this.id_student = id_student;
        this.id_discipline = id_discipline;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Integer getId_student() {
        return id_student;
    }

    public void setId_student(Integer id_student) {
        this.id_student = id_student;
    }

    public Integer getId_term() {
        return id_term;
    }

    public void setId_term(Integer id_term) {
        this.id_term = id_term;
    }

    public Integer getId_discipline() {
        return id_discipline;
    }

    public void setId_discipline(Integer id_discipline) {
        this.id_discipline = id_discipline;
    }
}
