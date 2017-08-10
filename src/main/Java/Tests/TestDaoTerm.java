package Tests;

import dao.DisciplineDao;
import exception.ExceptionDAO;
import dao.TermDao;

import domine.Discipline;
import domine.Term;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ник on 29.04.2016.
 */
public class TestDaoTerm {
    public static void main(String[] args) {
        TermDao term = new TermDao();
        DisciplineDao dis = new DisciplineDao();
        Term newTerm = null;
        Term testTerm = null;
        List<Term> termList = new ArrayList<Term>();
        List<Term> termList2 = new ArrayList<Term>();
        List<Term> termList3 = new ArrayList<Term>();
        List<Discipline> disList4 = new ArrayList<Discipline>();
        List<Discipline> disList5 = new ArrayList<Discipline>();
        Integer[] discId = {1,4, 5, 6};
        System.out.println("Begin test");


        try {
            termList = term.getAllTerms();
            for (Term ter : termList) {
                System.out.println(ter.getId() + " " + ter.getDuration());
            }
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }
        newTerm = new Term(29);
        System.out.println("Добавляем новый семестр в базу");
        try {
            term.addNewTerm(newTerm, discId);
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }
        System.out.println("Проверяем добавление");
        try {
            termList2 = term.getAllTerms();
            for (Term ter : termList2) {
                System.out.println(ter.getId() + " " + ter.getDuration());
            }
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }

        System.out.println("Найдём семестр по id = 22");
        try {
            testTerm = term.getTermById(22);
            System.out.println(testTerm.getId() + " " + testTerm.getDuration());
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }
        System.out.println("Теперь посмотрим список дисциплин по idTerm = 22");
        try {
            disList4 = dis.getAllTermDiscipline(22);
            for (Discipline disc : disList4) {
                System.out.println(disc.getId() + " " + disc.getDiscipline());
            }
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }

        System.out.println("Изменим длительность семестра, набор дисциплин и обновим его данные в базе");
        testTerm.setDuration(23);
        Integer[] discId2 = {1,3,2,4};
        try {
            term.updateTerm(testTerm, discId2);
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }
        System.out.println("Теперь сново посмотрим на семестр по id = 22");
        try {
            testTerm = term.getTermById(22);
            System.out.println(testTerm.getId() + " " + testTerm.getDuration());
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }
        System.out.println("Теперь посмотрим список дисциплин по idTerm = 22");
        try {
            disList5 = dis.getAllTermDiscipline(22);
            for (Discipline disc : disList5) {
                System.out.println(disc.getId() + " " + disc.getDiscipline());
            }
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }

        System.out.println("Теперь удалим дисциплину по id = 25");
        try {
            term.deleteTermById(25);
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }
        try {
            termList3 = term.getAllTerms();
            for (Term ter : termList3) {
                System.out.println(ter.getId() + " " + ter.getDuration());
            }
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }

    }
}
