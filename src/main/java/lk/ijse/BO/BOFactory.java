package lk.ijse.BO;

public class BOFactory {
    private static BOFactory factory;

    private BOFactory() {}

    public static BOFactory getInstance() {
        return factory == null ? new BOFactory() : factory;
    }

    public enum BOTypes {
        USER,CUSTOMER,BOOK,TRANSACTION
    }

    public SuperBO getBO(BOTypes boTypes) {
        switch (boTypes) {
            case USER:
                return null;
            case CUSTOMER:
                return null;
            case BOOK:
                return null;
            case TRANSACTION:
                return null;
            default:
                return null;
        }
    }
}
