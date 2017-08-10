package Tests;

import exception.ExceptionDAO;
import dao.MarkDao;
import dao.TermDao;
import domine.Mark;
import domine.Term;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Ник on 03.05.2016.
 */
public class TestDaoMark {
    public static void main(String[] args) {
        List<Mark> mList1 = new ArrayList<Mark>();
        List<Mark> mList2 = new ArrayList<Mark>();
        TermDao tDao = new TermDao();
        Term term = null;
        try {
            term = tDao.getTermById(1);
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }

        MarkDao mDao = new MarkDao();
        Mark mark = new Mark(5, 3, 22,2);
        Mark mark2 = new Mark(9, 2, 22,3);


        System.out.println("Begin test");
        try {
            mList1 = mDao.getAllMarksStud(22,term);
            for(Mark m:mList1){
                System.out.println(m.getId()+ " "+ m.getMark() +" "+ m.getId_discipline()+" "+ m.getId_student());
            }
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }

        System.out.println("Добавляем новую оценку в базу");
      /*  try {
            mDao.addNewMark(23,2,2, 7);
            mDao.addNewMark(23,2,5, 6);
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }
        System.out.println("Проверяем добавления");
*/
        try {
            mList2 = mDao.getAllMarksStud(22,term);
            for(Mark m:mList2){
                System.out.println(m.getId()+ " "+ m.getMark() +" "+ m.getId_discipline()+" "+ m.getId_student());
            }
        } catch (ExceptionDAO exceptionDAO) {
            exceptionDAO.printStackTrace();
        }
    }
}