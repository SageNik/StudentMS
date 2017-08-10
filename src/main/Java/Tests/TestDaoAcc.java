package Tests;

import com.sun.media.sound.InvalidDataException;
import dao.AccountDao;
import exception.ExceptionDAO;
import domine.Account;
import domine.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ник on 03.05.2016.
 */
public class TestDaoAcc {
    public static void main(String[] args){
        List<Role>acRole = new ArrayList();
        AccountDao aDao = new AccountDao();
        Account acc;
        Account acc1;

        System.out.println("Begin test");
        try {
           acRole = aDao.getAllAccountsRoles(3);
            for(Role r: acRole){
                System.out.println(r.toString());
            }
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }
        System.out.println("Получим доступ до аккаунта по логину admin");
        try {
            acc=aDao.getAccountByLogin("admin");
            System.out.println(acc.getId()+" "+acc.getLogin()+" "+acc.getPassword());
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }

        System.out.println("Получим доступ к аккаунту по логину, паролю и роле oleg, 123, Администратор");
        try {
            acc1=aDao.aLogin("oleg","123",2);
            System.out.println(acc1.getId()+" "+acc1.getLogin()+" "+acc1.getPassword());
        } catch (InvalidDataException e) {
            e.printStackTrace();
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }
    }
}
