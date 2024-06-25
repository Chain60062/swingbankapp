package viniciusmiranda.db;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAConfig {
    EntityManagerFactory factory;
    public void setUp(){
        factory = Persistence.createEntityManagerFactory("bank");
    }
}
