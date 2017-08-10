package Tests;

import dao.DisciplineDao;
import exception.ExceptionDAO;
import domine.Discipline;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ник on 29.04.2016.
 */
public class TestDaoDisc {
    public static void main(String[] args) {
        DisciplineDao disc = new DisciplineDao();
        Discipline newDiscipline = null;
        Discipline testDiscipline = null;
        List<Discipline> disList = new ArrayList<Discipline>();
        List<Discipline> disList2 = new ArrayList<Discipline>();
        List<Discipline> disList3 = new ArrayList<Discipline>();
        List<Discipline> disList4 = new ArrayList<Discipline>();
        System.out.println("Begin test");


        try {
            disList = disc.getAllDisciplines();
            for (Discipline dis : disList) {
                System.out.println(dis.getId() + " " + dis.getDiscipline());
            }
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }
        newDiscipline = new Discipline("Тестирование");
        System.out.println("Добавляем новую дисциплину в базу");
        try {
            disc.addNewDiscipline(newDiscipline);
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }
        System.out.println("Проверяем добавление");
        try {
            disList2 = disc.getAllDisciplines();
            for (Discipline dis : disList2) {
                System.out.println(dis.getId() + " " + dis.getDiscipline());
            }
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }

        System.out.println("Найдём новую дисциплину по id = 3");
        try {
            testDiscipline = disc.getDisciplineById(3);
            System.out.println(testDiscipline.getId() + " " + testDiscipline.getDiscipline());
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }
        System.out.println("Изменим название дисциплины и обновим его данные в базе");
        testDiscipline.setDiscipline("История");
        try {
            disc.updateDiscipline(testDiscipline);
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }
        System.out.println("Теперь сново посмотрим на дисциплину по id = 3");
        try {
            testDiscipline = disc.getDisciplineById(3);
            System.out.println(testDiscipline.getId() + " " + testDiscipline.getDiscipline());
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }
        System.out.println("Теперь удалим дисциплину по id = 20");
        try {
            disc.deleteDisciplineById(20);
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }
        try {
            disList3 = disc.getAllDisciplines();
            for (Discipline dis : disList3) {
                System.out.println(dis.getId() + " " + dis.getDiscipline());
            }
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }

        System.out.println("Теперь посмотрим список дисциплин по idTerm = 1");
        try {
           disList4 = disc.getAllTermDiscipline(1);
            for (Discipline dis : disList4) {
                System.out.println(dis.getId() + " " + dis.getDiscipline());}
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }
        System.out.println("End test");
    }
}