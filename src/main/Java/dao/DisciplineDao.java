package dao;

import domine.Discipline;
import exception.ExceptionDAO;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ник on 28.04.2016.
 */
public class DisciplineDao {
    private static final Logger LOGGER = Logger.getLogger(DisciplineDao.class.getName());
    private FinalyBlock finalyBlock = new FinalyBlock();

    public List<Discipline> getAllDisciplines() throws ExceptionDAO {
        List<Discipline> disciplinesList = new ArrayList<Discipline>();
        LOGGER.info("Try getAllDiciplines");

        Discipline dis = null;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            LOGGER.info("Open connection");
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("SELECT * FROM discipline");
            rs = statement.executeQuery();
            while (rs.next()) {
                dis = new Discipline(rs.getString("discipline"));
                dis.setId(rs.getInt("id"));
                disciplinesList.add(dis);
            }
        } catch (SQLException e) {
            LOGGER.error("Could not get All Disciplines", e);
            e.printStackTrace();
            throw new ExceptionDAO("getAllDisciplines");
        } finally {
           finalyBlock.withRS(rs, statement, conn);
            }
            return disciplinesList;
    }

    public boolean addNewDiscipline(Discipline newDisc) throws ExceptionDAO {

        LOGGER.info("Try add New Discipline to table");
        boolean addOK = false;
        Connection conn = null;
        PreparedStatement statement = null;
        try {

            LOGGER.info("Open connection");
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("Insert into discipline ( id, discipline) values (?,?);");
            statement.setInt(1, 0);
            statement.setString(2, newDisc.getDiscipline());
            statement.execute();
            addOK = true;
            LOGGER.info("New Discipline is added");
        } catch (SQLException e) {
            LOGGER.error("Could not add new discipline", e);
            e.printStackTrace();
            throw new ExceptionDAO("addNewDisciplines");
        } finally {
            finalyBlock.withOutRS(statement, conn);
        }
        return addOK;
    }

    public Discipline getDisciplineById(int id) throws ExceptionDAO {

        LOGGER.info("Try get Discipline by id");
        Discipline disc = null;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            LOGGER.info("Open connection");
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("SELECT * FROM discipline WHERE id = ? ");
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                disc = new Discipline(rs.getString("discipline"));
                disc.setId(rs.getInt("id"));
            }
            LOGGER.info("Discipline was get by id ");
        } catch (SQLException e) {
            LOGGER.error("Could not get discipline by id", e);
            e.printStackTrace();
            throw new ExceptionDAO("getDisciplineById");
        } finally {
            finalyBlock.withRS(rs, statement, conn);
        }
        return disc;
    }

    public boolean deleteDisciplineById(int id) throws ExceptionDAO {

        LOGGER.info("Try delete Discipline by id");
        boolean deleteOK = false;
        Connection conn = null;
        PreparedStatement statement = null;
        try {

            LOGGER.info("Open connection");
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("DELETE FROM discipline WHERE id= ? ");
            statement.setInt(1, id);
            statement.executeUpdate();
            deleteOK = true;
            LOGGER.info("Discipline by id is deleted");
        } catch (SQLException e) {
            LOGGER.error("Could not get discipline by id", e);
            e.printStackTrace();
            throw new ExceptionDAO("deleteDisciplineById");
        } finally {
            finalyBlock.withOutRS(statement, conn);
        }
        return deleteOK;
    }

    public boolean updateDiscipline(Discipline disc) throws ExceptionDAO {

        LOGGER.info("Try update Discipline in table");
        boolean updateOK = false;
        Connection conn = null;
        PreparedStatement statement = null;
        try {

            LOGGER.info("Open connection");
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("UPDATE discipline SET discipline=? Where id=?");
            statement.setString(1, disc.getDiscipline());
            statement.setInt(2,disc.getId());
            statement.executeUpdate();
            updateOK = true;
            LOGGER.info("Discipline was update");
        } catch (SQLException e) {
            LOGGER.error("Could not update discipline", e);
            e.printStackTrace();
            throw new ExceptionDAO("updateDiscipline");
        } finally {
            finalyBlock.withOutRS(statement, conn);
        }
        return updateOK;
    }

    public List<Discipline> getAllTermDiscipline(Integer termId)  throws ExceptionDAO {
        List<Discipline> termDisciplinesList = new ArrayList<Discipline>();
        LOGGER.info("Try getAllTermDiciplines");

        Discipline tdis = null;
        String sql = "Select discipline.id, discipline.discipline From discipline inner join term_discipline on term_discipline.discipline_id=discipline.id where term_discipline.term_id=?";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            LOGGER.info("Open connection");
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, termId);
            rs = statement.executeQuery();
            while (rs.next()) {
                tdis = new Discipline(rs.getString("discipline"));
                tdis.setId(rs.getInt("id"));
                termDisciplinesList.add(tdis);
            }
        } catch (SQLException e) {
            LOGGER.error("Could not get all term disciplines", e);
            e.printStackTrace();
            throw new ExceptionDAO("getAllTermDisciplines");
        } finally {
           finalyBlock.withRS(rs, statement, conn);
        }
        return termDisciplinesList;
    }
}




