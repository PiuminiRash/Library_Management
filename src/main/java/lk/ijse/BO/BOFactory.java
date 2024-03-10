package lk.ijse.BO;

import lk.ijse.BO.custom.impl.BookBOImpl;
import lk.ijse.BO.custom.impl.UserBOImpl;

public class BOFactory {
    private static BOFactory bofactory;

    private BOFactory() {}

    public static BOFactory getInstance() {
        return bofactory == null ? new BOFactory() : bofactory;
    }

    public enum BOTypes {
        USER,CUSTOMER,BOOK,TRANSACTION
    }

    public SuperBO getBO(BOTypes boTypes) {
        switch (boTypes) {
            case USER:
                return new UserBOImpl();
            case CUSTOMER:
                return new BookBOImpl();
            case BOOK:
                return null;
            case TRANSACTION:
                return null;
            default:
                return null;
        }
    }
}
