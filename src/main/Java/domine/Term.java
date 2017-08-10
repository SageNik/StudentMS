package domine;


/**
 * Created by Ник on 24.04.2016.
 */
public class Term {
    private Integer id;
    private Integer duration;

    public Term (Integer duration){
        this.duration = duration;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
