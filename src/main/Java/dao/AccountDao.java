package dao;

import com.sun.media.sound.InvalidDataException;
import domine.Account;
import domine.Role;
import exception.ExceptionDAO;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ник on 03.05.2016.
 */
public class AccountDao {
    private static final Logger LOGGER = Logger.getLogger(AccountDao.class.getName());
    private FinalyBlock finalyBlock = new FinalyBlock();

    public List<Role> getAllAccountsRoles(Integer idAccount) throws ExceptionDAO {
        List<Role> arolesList = new ArrayList<Role>();
        LOGGER.info("Try getAllAccountsRoles");

        Role rol = null;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            LOGGER.info("Open connection");
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("SELECT * FROM role inner join account_role on account_role.role_id=role.id Where account_role.account_id =?");
            statement.setInt(1, idAccount);
            rs = statement.executeQuery();
            while (rs.next()) {
                rol = new Role(rs.getString("role"));
                rol.setId(rs.getInt("id"));
                arolesList.add(rol);
            }
        } catch (SQLException e) {
            LOGGER.error("Could not get all account roles", e);
            e.printStackTrace();
            throw new ExceptionDAO("getAllAccountsRoles");
        } finally {
            finalyBlock.withRS(rs, statement, conn);
        }
        return arolesList;
    }

    public Account getAccountByLogin(String login) throws ExceptionDAO{
        LOGGER.info("Try getAccount by Login");

        Account acc = null;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            LOGGER.info("Open connection");
            conn = DBConnection.getConnection();
            statement = conn.prepareStatement("SELECT * FROM account WHERE login=?");
            statement.setString(1, login);
            rs = statement.executeQuery();
            while (rs.next()) {
                acc = new Account(rs.getString("login"), rs.getString("password"));
                acc.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            LOGGER.error("Could not get account by login", e);
            e.printStackTrace();
            throw new ExceptionDAO("getAccountByLogin");
        } finally {
            finalyBlock.withRS(rs, statement, conn);
        }
        return acc;
    }

    public Account aLogin(String username, String password, Integer roleId) throws InvalidDataException, ExceptionDAO {
        LOGGER.info("Try get account by aLogin");
        Account acc = null;
        Account result = null;
        if (!StringUtils.isBlank(username)) {
            acc = getAccountByLogin(username);

            if (acc != null && acc.getPassword().equals(password)) {

                List<Role> roles = getAllAccountsRoles(acc.getId());
                for (Role rol : roles) {
                    if (rol.getId() == roleId) {
                        result = acc;
                    }
                }
            } else throw new InvalidDataException("not find password");
        } else throw new InvalidDataException("not find username");

        return result;

    }
}