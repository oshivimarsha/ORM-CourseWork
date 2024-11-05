package lk.ijse.bo;

import lk.ijse.bo.custom.impl.StudentBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getBoFactory() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        STUDENTBO
    }

    public SuperBO getBO (BOTypes boTypes) {
        switch (boTypes) {
            case STUDENTBO :
                return new StudentBOImpl();
            default:
                return null;
        }
    }

}
