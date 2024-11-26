package lk.ijse.bo;

import lk.ijse.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getBoFactory() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        STUDENTBO, COURSEBO, USERBO, REJISTERBO, DASHBOARDBO
    }

    public SuperBO getBO (BOTypes boTypes) {
        switch (boTypes) {
            case STUDENTBO:
                return new StudentBOImpl();
            case COURSEBO:
                return new CourseBOImpl();
            case USERBO:
                return new UserBOImpl();
            case DASHBOARDBO:
                return new DashboardBOImpl();
            case REJISTERBO:
                return new RegisterBOImpl();
            default:
                return null;
        }
    }

}
