package lk.ijse.bo;

import lk.ijse.bo.custom.impl.CourseBOImpl;
import lk.ijse.bo.custom.impl.StudentBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getBoFactory() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        STUDENTBO, COURSEBO
    }

    public SuperBO getBO (BOTypes boTypes) {
        switch (boTypes) {
            case STUDENTBO :
                return new StudentBOImpl();
            case COURSEBO :
                return new CourseBOImpl();
            default:
                return null;
        }
    }

}
