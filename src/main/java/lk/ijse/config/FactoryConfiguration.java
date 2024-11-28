package lk.ijse.config;


import lk.ijse.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration(){
        /*Properties properties = new Properties();

        //add already created hibernate file to properies in current thread
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("hibernate.properties"));   //set path
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*/
        Configuration config = new Configuration().configure().addAnnotatedClass(Student.class)
                .addAnnotatedClass(Program.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Register.class)
                .addAnnotatedClass(Payment.class);

        sessionFactory = config.buildSessionFactory();

    }

    public static FactoryConfiguration getInstance(){
        if (factoryConfiguration == null) {
            factoryConfiguration = new FactoryConfiguration();
        }
        return factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
