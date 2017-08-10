package dao;

import domine.Term;
import exception.ExceptionDAO;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ник on 28.04.2016.
 */
public class TermDao {
    private static final Logger LOGGER = Logger.getLogger(TermDao.class.getName());
    private FinalyBlock finalyBlock = new FinalyBlock();

    public List<Term> getAllTerms() throws ExceptionDAO {
        List<Term> termsList = new ArrayList<Term>();
        LOGGER.info("Try getAllTerms");

        Term term = null;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            LOGGER.info("Open connection");
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("SELECT * FROM term");
            rs = statement.executeQuery();
            while (rs.next()) {
                term = new Term(rs.getInt("duration"));
                term.setId(rs.getInt("id"));
                termsList.add(term);
            }
            LOGGER.info("List Terms was make");
        } catch (SQLException e) {
            LOGGER.error("Could not get all terms", e);
            e.printStackTrace();
            throw new ExceptionDAO("getAllTerms");
        } finally {
            finalyBlock.withRS(rs, statement, conn);
            return termsList;
        }
    }

    public boolean addNewTerm(Term newTerm, Integer[] discId) throws ExceptionDAO {

        LOGGER.info("Try add New Term to table");
        String sql = "Insert into term ( id, duration) values (?,?);";
        String sql2 = "Insert into term_discipline (term_id, discipline_id) values (?,?)";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {

            LOGGER.info("Open connection");
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setInt(1, 0);
            statement.setInt(2, newTerm.getDuration());
            statement.executeUpdate();
            rs = statement.getGeneratedKeys();
            rs.next();
            newTerm.setId(rs.getInt(1));
            rs.close();
            statement.close();
            for (Integer i : discId) {
                statement = conn.prepareStatement(sql2);
                statement.setInt(1, newTerm.getId());
                statement.setInt(2, i);
                statement.executeUpdate();
                statement.close();
            }
            LOGGER.info("New Term added");
            return true;
        } catch (SQLException e) {
            LOGGER.error("Could not add new term", e);
            e.printStackTrace();
            throw new ExceptionDAO("addNewTerm");
        } finally {
            finalyBlock.withRS(rs, statement, conn);
        }
    }

    public Term getTermById(int id) throws ExceptionDAO {

        LOGGER.info("Try get Term by id");
        Term term = null;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            LOGGER.info("Open connection");
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("SELECT * FROM term WHERE id = ? ");
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                term = new Term(rs.getInt("duration"));
                term.setId(rs.getInt("id"));
            }
            LOGGER.info("Term was get by id ");
        } catch (SQLException e) {
            LOGGER.error("Could not get term by id", e);
            e.printStackTrace();
            throw new ExceptionDAO("getTermById");
        } finally {
            finalyBlock.withRS(rs, statement, conn);
        }
        return term;
    }

    public boolean deleteTermById(int id) throws ExceptionDAO {

        LOGGER.info("Try delete Term by id");
        boolean deleteOK = false;
        Connection conn = null;
        PreparedStatement statement = null;
        try {

            LOGGER.info("Open connection");
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("DELETE FROM term WHERE id= ? ");
            statement.setInt(1, id);
            statement.executeUpdate();
            deleteOK = true;
            LOGGER.info("Term by id was delete");
        } catch (SQLException e) {
            LOGGER.error("Could not delete term by id", e);
            e.printStackTrace();
            throw new ExceptionDAO("deleteTermById");
        } finally {
            finalyBlock.withOutRS(statement, conn);
        }
        return deleteOK;
    }

    public boolean updateTerm(Term term, Integer[] discId) throws ExceptionDAO {

        LOGGER.info("Try update Term in table");
        boolean updateOK = false;
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement statement = null;
        try {

            LOGGER.info("Open connection");
            conn = DBConnection.getConnection();
            List<Integer> termDisciplineId = new ArrayList<Integer>();

            statement = conn.prepareStatement("Update term set duration=? where id=?");
            statement.setInt(1, term.getDuration());
            statement.setInt(2, term.getId());
            statement.executeUpdate();
            statement.close();

            statement = conn.prepareStatement("SELECT * From term_discipline where term_id IN(?)");
            statement.setInt(1, term.getId());
            rs = statement.executeQuery();
            while (rs.next()) {
                termDisciplineId.add(rs.getInt("discipline_id"));
            }
            rs.close();
            statement.close();

            if (termDisciplineId.isEmpty()) {
                for (Integer aDiscId : discId) {
                    statement = conn.prepareStatement("INSERT INTO term_discipline (discipline_id, term_id) VALUES (?,?)");
                    statement.setInt(1, aDiscId);
                    statement.setInt(2, term.getId());
                    statement.executeUpdate();
                    statement.close();
                }
            } else {
                for (Integer idDiscipline : discId) {
                    boolean save = true;
                    for (Integer disciplineId : termDisciplineId) {
                        if (idDiscipline == disciplineId) save = false;
                    }
                        if (save) {
                            statement = conn.prepareStatement("INSERT INTO term_discipline ( discipline_id, term_id) VALUES (?,?)");
                            statement.setInt(1, idDiscipline);
                            statement.setInt(2, term.getId());
                            statement.executeUpdate();
                            statement.close();
                        }
                }

                for (Integer disciplineId : termDisciplineId) {
                    boolean delete = true;
                    for (Integer idDiscipline : discId) {

                        if (disciplineId == idDiscipline) delete = false;
                    }
                        if (delete) {
                            statement = conn.prepareStatement("DELETE FROM mark WHERE term_id=? and discipline_id =?");
                            statement.setInt(1, term.getId());
                            statement.setInt(2, disciplineId);
                            statement.executeUpdate();
                            statement.close();
                            statement = conn.prepareStatement("DELETE FROM term_discipline WHERE term_id=? and discipline_id =?");
                            statement.setInt(1, term.getId());
                            statement.setInt(2, disciplineId);
                            statement.executeUpdate();
                            statement.close();
                        }
                }
            }
            updateOK = true;
            LOGGER.info("Term was update");
        } catch (SQLException e) {
            LOGGER.error("Could not update term", e);
            e.printStackTrace();
            throw new ExceptionDAO("updateTerm");
        } finally {
            finalyBlock.withRS(rs, statement, conn);
        }
        return updateOK;
    }
}

