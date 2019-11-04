package utils;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;

public class MariaDbLauncher {
    private static Logger log = LoggerFactory.getLogger(MariaDbLauncher.class);
//*************************************
//    Call closeDB() method at the end of the tests
//*************************************

    private MariaEmbeddedDB mariaDb;
    private DB database;

    public MariaDbLauncher() {
    }

    public void launch() {
        mariaDb = MariaEmbeddedDB.Builder.aMariaEmbeddedDB().build();

        try {
            database = mariaDb.createOrGetDB();
        } catch (ManagedProcessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        try {
            database.start();
            log.info("New instance of MariaDB launched, hash:{}", database.hashCode());
        } catch (ManagedProcessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        try {
            database.source("init.sql");
        } catch (ManagedProcessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        try {
            Class.forName(mariaDb.getDriverClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public MariaEmbeddedDB getMariaDb() {
        return mariaDb;
    }

    public DB getDatabase() {
        return database;
    }

    public void closeDB() throws ManagedProcessException {
        int i = database.hashCode();
        database.stop();
        log.info("MariaDb stopped, hash:{}", i);
    }

    public void persist(Object... objects){
        EntityManager em = mariaDb.createOrGetEntityManager();
        em.getTransaction().begin();

        for (Object object : objects) {
            em.persist(object);
        }

        em.getTransaction().commit();
    }

    public void remove(Object... objects){
        EntityManager em = mariaDb.createOrGetEntityManager();
        em.getTransaction().begin();

        for (Object object : objects) {
            em.remove(object);
        }

        em.getTransaction().commit();
    }
}
