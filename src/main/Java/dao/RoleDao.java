package dao;

import domine.Role;
import exception.ExceptionDAO;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ник on 28.04.2016.
 */
public class RoleDao {
    private static final Logger LOGGER = Logger.getLogger(RoleDao.class.getName());
    private FinalyBlock finalyBlock = new FinalyBlock();

    public List<Role> getAllRoles() throws ExceptionDAO {
        List<Role> rolesList = new ArrayList<Role>();
        LOGGER.info("Try getAllRoles");

        Role role = null;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            LOGGER.info("Open connection");
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("SELECT * FROM role");
            rs = statement.executeQuery();
            while (rs.next()) {
                role = new Role(rs.getString("role"));
                role.setId(rs.getInt("id"));
                rolesList.add(role);
            }
            LOGGER.info("List Roles was make");
        } catch (SQLException e) {
            LOGGER.error("Could not get all roles", e);
            e.printStackTrace();
            throw new ExceptionDAO("getAllRoles");
        } finally {
           finalyBlock.withRS(rs, statement, conn);
            }

            return rolesList;
        }

    public Role getRoleById(int id) throws ExceptionDAO {

        LOGGER.info("Try get Role by id");
        Role role = null;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            LOGGER.info("Open connection");
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("SELECT * FROM role WHERE id = ? ");
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                role = new Role(rs.getString("role"));
                role.setId(rs.getInt("id"));
            }
            LOGGER.info("Role was get by id ");
        } catch (SQLException e) {
            LOGGER.error("Could not get role by id", e);
            e.printStackTrace();
            throw new ExceptionDAO("getRoleById");
        } finally {
           finalyBlock.withRS(rs, statement, conn);
        }
        return role;
    }
}