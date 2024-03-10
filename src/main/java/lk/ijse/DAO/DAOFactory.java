package lk.ijse.DAO;

import lk.ijse.DAO.custom.impl.BookDAOImpl;
import lk.ijse.DAO.custom.impl.UserDAOImpl;

public class DAOFactory {
    private static DAOFactory daofactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaofactory() {
        return (daofactory==null)?daofactory=new DAOFactory():daofactory;
    }

    public enum DAOTypes {
        USER,CUSTOMER,BOOK,TRANSACTION,QUERY
    }

    public SuperDAO getDAO (DAOTypes daoTypes) {
        switch (daoTypes) {
            case USER:
                return new UserDAOImpl();
            case CUSTOMER:
                return null;
            case BOOK:
                return new BookDAOImpl();
            case TRANSACTION:
                return null;
            case QUERY:
                return null;
            default:
                return null;
        }
    }
}
