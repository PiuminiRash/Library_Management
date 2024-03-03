package lk.ijse.DAO;

public class DAOFactory {
    private static DAOFactory factory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return factory == null ? new DAOFactory() : factory;
    }

    public enum DAOTypes {
        USER,CUSTOMER,BOOK,TRANSACTION,QUERY
    }

    public SuperDAO getDAO (DAOTypes daoTypes) {
        switch (daoTypes) {
            case USER:
                return null;
            case CUSTOMER:
                return null;
            case BOOK:
                return null;
            case TRANSACTION:
                return null;
            case QUERY:
                return null;
            default:
                return null;
        }
    }
}
