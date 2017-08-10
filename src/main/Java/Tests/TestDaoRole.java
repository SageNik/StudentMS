package Tests;

import exception.ExceptionDAO;
import dao.RoleDao;
import domine.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ник on 03.05.2016.
 */
public class TestDaoRole {
    public static void main(String[] args) {
        List<Role> rList = new ArrayList<Role>();
        RoleDao rDao = new RoleDao();
        Role role;
        System.out.println("Begin test");

        try {
            rList = rDao.getAllRoles();
            for(Role r: rList){
                System.out.println(r.getId()+" "+r.getRole());
            }
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }
        System.out.println("Найдём теперь роль по id = 2");
        try {
            role = rDao.getRoleById(2);
            System.out.println(role.toString());
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }
    }
}