package dao;

import domine.Mark;
import domine.Term;
import exception.ExceptionDAO;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ник on 28.04.2016.
 */
public class MarkDao {
    private static final Logger LOGGER = Logger.getLogger(MarkDao.class.getName());
    private FinalyBlock finalyBlock = new FinalyBlock();

    public List<Mark> getAllMarksStud(Integer id_student, Term term) throws ExceptionDAO {
        List<Mark> marksList = new ArrayList<Mark>();
        LOGGER.info("Try getAllMarks");

        Mark mark = null;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            LOGGER.info("Open connection");
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("SELECT*FROM mark WHERE student_id=? and term_id=?");
            statement.setInt(1, id_student);
            statement.setInt(2, term.getId());
            rs = statement.executeQuery();
            while (rs.next()) {
                mark = new Mark(rs.getInt("mark"), rs.getInt("term_id"), rs.getInt("student_id"),rs.getInt("discipline_id"));
                mark.setId(rs.getInt("id"));
                marksList.add(mark);
            }
            LOGGER.info("Mark list done");
        } catch (SQLException e) {
            LOGGER.error("Could not get all marks students", e);
            e.printStackTrace();
            throw new ExceptionDAO("getAllMarksStud");
        } finally {
            finalyBlock.withRS(rs, statement, conn);
            }
            return marksList;
        }

    public boolean addNewMark(Integer student_id, Integer term_id, Integer discipline_id, Integer mark) throws ExceptionDAO {

        LOGGER.info("Try add New Mark to table");
        Connection conn = null;
        PreparedStatement statement = null;
        try {

            LOGGER.info("Open connection");
            conn = DBConnection.getConnection();

            statement = conn.prepareStatement("Insert into mark ( id, student_id, discipline_id, term_id, mark) values (?,?,?,?,?);");
            statement.setInt(1, 0);
            statement.setInt(2, student_id);
            statement.setInt(3, discipline_id);
            statement.setInt(4, term_id);
            statement.setInt(5, mark);
            statement.executeUpdate();
            LOGGER.info("New Mark was add");
            return true;
        } catch (SQLException e) {
            LOGGER.error("Could not add new mark", e);
            e.printStackTrace();
            throw new ExceptionDAO("addNewMark");
        } finally {
            finalyBlock.withOutRS(statement, conn);
        }
    }

    public Mark getMark(Integer student_id, Integer term_id, Integer discipline_id) throws ExceptionDAO {

        LOGGER.info("Try get Mark");
        Mark mark = null;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            LOGGER.info("Open connection");
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("Select * from mark  Where mark.student_id=? and mark.term_id=? and mark.discipline_id=?");
            statement.setInt(1, student_id);
            statement.setInt(2, term_id);
            statement.setInt(3, discipline_id);
            rs = statement.executeQuery();
            while (rs.next()) {
              mark = new Mark(rs.getInt("mark"), rs.getInt("term_id"), rs.getInt("student_id"),rs.getInt("discipline_id"));
                if (mark != null) {
                    mark.setId(rs.getInt("id"));
                }
            }
            LOGGER.info("Mark was get ");
        } catch (SQLException e) {
            LOGGER.error("Could not get mark", e);
            e.printStackTrace();
            throw new ExceptionDAO("getMark");
        } finally {
            finalyBlock.withRS(rs, statement, conn);
        }
        return mark;
    }

    public boolean updateMark(Mark mark) throws ExceptionDAO {

        LOGGER.info("Try update Mark in table");
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            LOGGER.info("Open connection");
            conn = DBConnection.getConnection();

            statement = conn.prepareStatement("Update mark set student_id=?, term_id=?, discipline_id=?, mark=? where id=?");
            statement.setInt(1, mark.getId_student());
            statement.setInt(2, mark.getId_term());
            statement.setInt(3, mark.getId_discipline());
            statement.setInt(4, mark.getMark());
            statement.setInt(5, mark.getId());
            statement.executeUpdate();

            LOGGER.info("Mark was update");
            return true;
        } catch (SQLException e) {
            LOGGER.error("Could not update mark", e);
            e.printStackTrace();
            throw new ExceptionDAO("updateMark");
        } finally {
            finalyBlock.withOutRS(statement, conn);
        }
    }
}



