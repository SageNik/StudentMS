package Tests;

import exception.ExceptionDAO;
import dao.StudentDao;
import domine.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ник on 26.04.2016.
 */
public class TestDaoStud {


    public static void main(String[] args){
        StudentDao stud = new StudentDao();
        Student newStudent = null;
        Student testStudent = null;
        List<Student> StList = new ArrayList<Student>();
        List<Student> StList2 = new ArrayList<Student>();
        List<Student> StList3 = new ArrayList<Student>();
        System.out.println("Begin test");

        try {
            StList = stud.getAllStudents();
            for(Student st : StList) {
                System.out.println( st.getId()+" "+ st.getName()+" "+ st.getSurname()+" "+ st.getDate()+" "+ st.getGroupe());
            }
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }
newStudent= new Student("Тест", "Бредовый", "1996-10-15", "АТ-15");
        System.out.println("Добавляем нового студента в базу");
        try {
            stud.addNewStudent(newStudent);
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }
        System.out.println("Проверяем добавление");
        try {
            StList2 = stud.getAllStudents();
            for(Student st : StList2) {
                System.out.println( st.getId()+" "+ st.getName()+" "+ st.getSurname()+" "+ st.getDate()+" "+ st.getGroupe());
            }
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }

        System.out.println("Найдём нового студента по id = 23");
        try {
           testStudent = stud.getStudentById(23);
            System.out.println(testStudent.getId()+" "+testStudent.getName()+" "+testStudent.getSurname()+" "+testStudent.getDate()+" "+testStudent.getGroupe());
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }
        System.out.println("Изменим у студента группу и обновим его данные в базе");
        testStudent.setGroupe("ТФ-15");
        try {
            stud.updateStudent(testStudent);
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }
        System.out.println("Теперь сново посмотрим на студента по id = 23");
        try {
            testStudent = stud.getStudentById(23);
            System.out.println(testStudent.getId()+" "+testStudent.getName()+" "+testStudent.getSurname()+" "+testStudent.getDate()+" "+testStudent.getGroupe());
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }
        System.out.println("Теперь удалим студентов по id = 40");
        try {
            List<Integer> idList =new ArrayList<Integer>();
            idList.add(40);
            stud.deleteStudents(idList);
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }
        try {
            StList3 = stud.getAllStudents();
            for(Student st : StList3) {
                System.out.println( st.getId()+" "+ st.getName()+" "+ st.getSurname()+" "+ st.getDate()+" "+ st.getGroupe());
            }
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }
    }

}
