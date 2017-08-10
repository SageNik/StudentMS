package dao;

import domine.Student;
import exception.ExceptionDAO;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ник on 24.04.2016.
 */
public class StudentDao {
    private static final Logger LOGGER = Logger.getLogger(StudentDao.class.getName());
    private FinalyBlock finalyBlock = new FinalyBlock();

    public List<Student> getAllStudents() throws ExceptionDAO {
        List<Student> studentsList = new ArrayList<Student>();
        LOGGER.info("Try getAllStudents");

        Student student = null;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            LOGGER.info("Open connection");
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("SELECT * FROM student");
            rs = statement.executeQuery();
            while (rs.next()) {
                student = new Student(rs.getString("name"), rs.getString("surname"), rs.getString("date"), rs.getString("groupe"));
                student.setId(rs.getInt("id"));
                studentsList.add(student);
            }
        } catch (SQLException e) {
            LOGGER.error("Could not get all students", e);
            e.printStackTrace();
            throw new ExceptionDAO("getAllStudents");
        } finally {
            finalyBlock.withRS(rs, statement, conn);
        }
        return studentsList;
    }

    public boolean addNewStudent(Student newStudent) throws ExceptionDAO {

        LOGGER.info("Try add New Student to table");
        boolean addOK = false;
        Connection conn = null;
        PreparedStatement statement = null;
        try {

            LOGGER.info("Open connection");
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("Insert into student ( id, name, surname, date, groupe) values (?,?,?,?,?);");
            statement.setInt(1, 0);
            statement.setString(2, newStudent.getName());
            statement.setString(3, newStudent.getSurname());
            statement.setDate(4, newStudent.getDate());
            statement.setString(5, newStudent.getGroupe());
            statement.execute();
            addOK = true;
            LOGGER.info("New Student is added");
        } catch (SQLException e) {
            LOGGER.error("Could not get new student", e);
            e.printStackTrace();
            throw new ExceptionDAO("addNewStudent");
        } finally {
           finalyBlock.withOutRS(statement, conn);
        }
        return addOK;
    }

    public Student getStudentById(int id) throws ExceptionDAO {

        LOGGER.info("Try get Student by id");
        Student student = null;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            LOGGER.info("Open connection");
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("SELECT * FROM student WHERE id = ? ");
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                student = new Student(rs.getString("name"), rs.getString("surname"), rs.getString("date"), rs.getString("groupe"));
                student.setId(rs.getInt("id"));
            }
            LOGGER.info("Student was get by id ");
        } catch (SQLException e) {
            LOGGER.error("Could not get student by id", e);
            e.printStackTrace();
            throw new ExceptionDAO("getStudentById");
        } finally {
            finalyBlock.withRS(rs, statement, conn);
        }
        return student;
    }

    public boolean deleteStudentById(int id) throws ExceptionDAO {

        LOGGER.info("Try delete Student by id");
        boolean deleteOK = false;
        Connection conn = null;
        PreparedStatement statement = null;
        try {

            LOGGER.info("Open connection");
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("DELETE FROM student WHERE id= ? ");
            statement.setInt(1, id);
            statement.executeUpdate();
            deleteOK = true;
            LOGGER.info("Student by id is deleted");
        } catch (SQLException e) {
            LOGGER.error("Could not delete student by id", e);
            e.printStackTrace();
            throw new ExceptionDAO("deleteStudentById");
        } finally {

            finalyBlock.withOutRS(statement, conn);
        }
        return deleteOK;
    }

    public boolean updateStudent(Student student) throws ExceptionDAO {

        LOGGER.info("Try update Student in table");
        boolean updateOK = false;
        Connection conn = null;
        PreparedStatement statement = null;
        try {

            LOGGER.info("Open connection");
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("UPDATE student SET name=?, surname=?, date=?, groupe=? Where id=?");
            statement.setString(1, student.getName());
            statement.setString(2, student.getSurname());
            statement.setDate(3, student.getDate());
            statement.setString(4, student.getGroupe());
            statement.setInt(5, student.getId());
            statement.executeUpdate();
            updateOK = true;
            LOGGER.info("Student is updated");
        } catch (SQLException e) {
            LOGGER.error("Could not update student", e);
            e.printStackTrace();
            throw new ExceptionDAO("updateStudent");
        } finally {
           finalyBlock.withOutRS(statement, conn);
        }
        return updateOK;
    }

    public boolean deleteStudents(List<Integer> idStud) throws ExceptionDAO {

        LOGGER.info("Try delete Students by id");
        int count = 0;
        boolean deleteAllOK = false;
        for (Integer i : idStud) {
            if (deleteStudentById(i)) {
                count++;
            }
        }
        if (count == idStud.size()) {
            deleteAllOK = true;
        }
        return deleteAllOK;
    }
}
