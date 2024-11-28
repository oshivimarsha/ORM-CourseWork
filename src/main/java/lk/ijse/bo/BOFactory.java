package lk.ijse.bo;

import lk.ijse.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getBoFactory() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        STUDENTBO, PROGRAMBO, USERBO, REJISTERBO, DASHBOARDBO, LOGINBO, PAYMENTBO
    }

    public SuperBO getBO (BOTypes boTypes) {
        switch (boTypes) {
            case STUDENTBO:
                return new StudentBOImpl();
            case PROGRAMBO:
                return new ProgramBOImpl();
            case USERBO:
                return new UserBOImpl();
            case DASHBOARDBO:
                return new DashboardBOImpl();
            case REJISTERBO:
                return new RegisterBOImpl();
            case LOGINBO:
                return new LoginBOImpl();
            case PAYMENTBO:
                return new PaymentBOImpl();
            default:
                return null;
        }
    }

}
