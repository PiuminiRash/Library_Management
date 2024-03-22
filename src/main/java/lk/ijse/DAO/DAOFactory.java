package lk.ijse.DAO;

import lk.ijse.DAO.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daofactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaofactory() {
        return (daofactory==null)?daofactory=new DAOFactory():daofactory;
    }

    public enum DAOTypes {
        ADMIN,USER,BOOK,TRANSACTION,BRANCH,QUERY
    }

    public SuperDAO getDAO (DAOTypes daoTypes) {
        switch (daoTypes) {
            case ADMIN:
                return new AdminDAOImpl();
            case USER:
                return new UserDAOImpl();
            case BOOK:
                return new BookDAOImpl();
            case TRANSACTION:
                return new TransactionDAOImpl();
            case BRANCH:
                return new BranchDAOImpl();
            case QUERY:
                return null;
            default:
                return null;
        }
    }
}
