package domine;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Ник on 24.04.2016.
 */
public class Student {
    private Integer id;
    private String name ;
    private String surname;
    private Date date;
    private String groupe;

    public Student (String name, String surname, String date, String groupe){


        this.name = name;
        this.surname = surname;
        this.date = this.formatDate(date);
        this.groupe = groupe;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public Date formatDate (String date){
        String pattern = "yyyy-mm-dd";
        SimpleDateFormat sdate = new SimpleDateFormat(pattern);
        java.util.Date utilDate = null;
        try {
            utilDate = sdate.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date sqlDate = new Date(utilDate.getTime());
        return sqlDate;
    }
}
