package utils;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;
import org.hibernate.jpa.boot.internal.PersistenceUnitInfoDescriptor;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.hibernate.jpa.boot.spi.PersistenceUnitDescriptor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.persistenceunit.MutablePersistenceUnitInfo;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Collections;
import java.util.Objects;
import java.util.Properties;

public class MariaEmbeddedDB {

    private String driverClassName;
    private String dbUrl;
    private int dbPort;
    private String dbSchema;
    private String dbUser;
    private String dbPassword;
    private String persistenceUnitName;


    private EntityManager entityManager;
    private DataSource dataSource;
    private DB database;

    private MariaEmbeddedDB() {
    }

    public EntityManager createOrGetEntityManager() {
        if (Objects.isNull(entityManager)) {
            entityManager = createEntityManager();
        }
        return entityManager;
    }

    public DataSource createOrGetDataSource() {
        if (Objects.isNull(dataSource)) {
            dataSource = createDataSource();
        }
        return dataSource;
    }

    public DB createOrGetDB() throws ManagedProcessException {
        if (Objects.isNull(database)) {
            database = DB.newEmbeddedDB(dbPort);
        }
        return database;
    }

    public String getConnectionUrl(){
        Assert.notNull(dbUrl, "dbUser cannot be null");
        Assert.notNull(dbSchema, "dbSchema cannot be null");
        return String.format("%s:%s/%s", dbUrl, dbPort, dbSchema);
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public int getDbPort() {
        return dbPort;
    }

    public String getDbSchema() {
        return dbSchema;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public String getPersistenceUnitName() {
        return persistenceUnitName;
    }

    public String getFullUrl(){
        return String.format("%s:%s/%s", dbUrl, dbPort, dbSchema);
    }

    private EntityManager createEntityManager() {

        MutablePersistenceUnitInfo mutablePersistenceUnitInfo = new MutablePersistenceUnitInfo() {
            @Override
            public ClassLoader getNewTempClassLoader() {
                return null;
            }
        };

        mutablePersistenceUnitInfo.setPersistenceUnitName(persistenceUnitName);
        mutablePersistenceUnitInfo.setPersistenceProviderClassName(HibernatePersistenceProvider.class.getName());

        Properties props = new Properties();
        props.put("hibernate.connection.url", getFullUrl());
        props.put("hibernate.connection.username", dbUser);

        if (StringUtils.hasText(dbPassword)) {
            props.put("hibernate.connection.password", dbPassword);
        }

        mutablePersistenceUnitInfo.setProperties(props);

        ClassPathScanningCandidateComponentProvider scanner =
                new ClassPathScanningCandidateComponentProvider(false);

        scanner.addIncludeFilter(new AnnotationTypeFilter(Entity.class));

        for (BeanDefinition bd : scanner.findCandidateComponents("lv.tsi.conference")){
            mutablePersistenceUnitInfo.addManagedClassName(bd.getBeanClassName());
        }

        PersistenceUnitDescriptor persistenceUnitDescriptor = new PersistenceUnitInfoDescriptor(
                mutablePersistenceUnitInfo);

        EntityManagerFactoryBuilder entityManagerFactoryBuilder = new EntityManagerFactoryBuilderImpl(
                persistenceUnitDescriptor, Collections.EMPTY_MAP);

        EntityManagerFactory entityManagerFactory = entityManagerFactoryBuilder.build();

        return entityManagerFactory.createEntityManager();

    }


    private DataSource createDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(getFullUrl());
        dataSource.setUsername(dbUser);
        dataSource.setPassword(dbPassword);

        return dataSource;
    }


    public static final class Builder {
        private String driverClassName = "com.mysql.jdbc.Driver";
        private String dbUrl = "jdbc:mysql://localhost";
        private int port = 3307;
        private String dbSchema = "conference";
        private String dbUser = "root";
        private String dbPassword = "";
        private String persistenceUnitName = "TestUnit";

        private Builder() {
        }

        public static Builder aMariaEmbeddedDB() {
            return new Builder();
        }

        public Builder withDriverClassName(String driverClassName) {
            this.driverClassName = driverClassName;
            return this;
        }

        public Builder withDbUrl(String dbUrl) {
            this.dbUrl = dbUrl;
            return this;
        }

        public Builder withDbSchema(String dbSchema) {
            this.dbSchema = dbSchema;
            return this;
        }

        public Builder withDbUser(String dbUser) {
            this.dbUser = dbUser;
            return this;
        }

        public Builder withDbPassword(String dbPassword) {
            this.dbPassword = dbPassword;
            return this;
        }

        public Builder withPersistenceUnitName(String persistenceUnitName) {
            this.persistenceUnitName = persistenceUnitName;
            return this;
        }

        public Builder withPort(int port) {
            Assert.isTrue(port < 0, "Port cannot be less then 0");
            this.port = port;
            return this;
        }

        public MariaEmbeddedDB build() {
            MariaEmbeddedDB mariaEmbeddedDB = new MariaEmbeddedDB();
            mariaEmbeddedDB.driverClassName = this.driverClassName;
            mariaEmbeddedDB.dbSchema = this.dbSchema;
            mariaEmbeddedDB.dbUrl = this.dbUrl;
            mariaEmbeddedDB.persistenceUnitName = this.persistenceUnitName;
            mariaEmbeddedDB.dbUser = this.dbUser;
            mariaEmbeddedDB.dbPassword = this.dbPassword;
            mariaEmbeddedDB.dbPort = this.port;
            return mariaEmbeddedDB;
        }
    }
}



