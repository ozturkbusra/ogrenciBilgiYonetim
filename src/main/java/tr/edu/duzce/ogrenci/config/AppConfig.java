package tr.edu.duzce.ogrenci.config;

import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tr.edu.duzce.ogrenci.model.*;

/*import static org.hibernate.cfg.AvailableSettings.*;
import static org.hibernate.cfg.AvailableSettings.C3P0_CONFIG_PREFIX;*/


import java.util.Properties;


@PropertySource(value= "classpath:hibernate.properties", encoding = "UTF-8")
@EnableTransactionManagement
@Configuration //Konfigürasyon sınıfı olduğunu belirtir
@ComponentScan(basePackages = {"tr.edu.duzce.ogrenci"}) //Konfigürasyon sınıflarının bulunduğu paket bilgisi

public class AppConfig implements AvailableSettings {

        @Autowired
        private Environment env;

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

        //JDBC Ayarları
        Properties props = new Properties();
        props.put(DRIVER, env.getProperty("mysql.driver"));
        props.put(URL, env.getProperty("mysql.url"));
        props.put(USER, env.getProperty("mysql.user"));
        props.put(PASS, env.getProperty("mysql.password"));

        //HIBERNATE Ayarları
        props.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
        props.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));
        props.put(DIALECT, env.getProperty("hibernate.dialect"));

        //C3P0 Ayarları
        props.put(C3P0_MIN_SIZE, env.getProperty("hibernate.c3p0.min_size"));
        props.put(C3P0_MAX_SIZE, env.getProperty("hibernate.c3p0.max_size"));
        props.put(C3P0_ACQUIRE_INCREMENT, env.getProperty("hibernate.c3p0.acquire_increment"));
        props.put(C3P0_TIMEOUT, env.getProperty("hibernate.c3p0.timeout"));
        props.put(C3P0_MAX_STATEMENTS, env.getProperty("hibernate.c3p0.max_statements"));
        props.put(C3P0_CONFIG_PREFIX + ".initialPoolSize", env.getProperty("hibernate.c3p0.initialPoolSize"));

        factoryBean.setHibernateProperties(props);
        factoryBean.setAnnotatedClasses(FakulteModel.class, BolumModel.class, DanismanModel.class, OgrenciModel.class, LoginModel.class); //Model Sınıfları OgrenciModel.class, BolumModel.class, DanismanModel.class, FakulteModel.class

        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject()); //SessionFactory
        return transactionManager;
    }
}


