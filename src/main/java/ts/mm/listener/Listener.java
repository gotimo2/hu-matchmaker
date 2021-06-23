package ts.mm.listener;

import ts.mm.persistence.PersistenceManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Listener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            PersistenceManager.LoadMatches();
        } catch (Exception e) {
            System.out.println("error during match loading: " + e);
        }
        try {
            PersistenceManager.loadPersonen();
        } catch (Exception e) {
            System.out.println("error during Persoon loading: " + e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            PersistenceManager.saveMatches();
        } catch (Exception e) {
            System.out.println("error during match saving: " + e);
        }
        try {
            PersistenceManager.savePersonen();
        } catch (Exception e) {
            System.out.println("error during persoon saving: " + e);
        }
    }
}
