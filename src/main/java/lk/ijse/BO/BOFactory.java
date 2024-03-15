package lk.ijse.BO;

import lk.ijse.BO.custom.impl.BookBOImpl;
import lk.ijse.BO.custom.impl.AdminBOImpl;
import lk.ijse.BO.custom.impl.TransactionBOImpl;
import lk.ijse.BO.custom.impl.UserBOImpl;

public class BOFactory {
    private static BOFactory bofactory;

    private BOFactory() {}

    public static BOFactory getInstance() {
        return bofactory == null ? new BOFactory() : bofactory;
    }

    public enum BOTypes {
        ADMIN,USER,BOOK,TRANSACTION
    }

    public SuperBO getBO(BOTypes boTypes) {
        switch (boTypes) {
            case ADMIN:
                return new AdminBOImpl();
            case USER:
                return new UserBOImpl();
            case BOOK:
                return new BookBOImpl();
            case TRANSACTION:
                return new TransactionBOImpl();
            default:
                return null;
        }
    }
}
