package com.orange.spring.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.orange.spring.model.Account;
@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
public class UtilConfig {


	@Autowired
	private Environment env;
	
	private StandardServiceRegistry registry;
	private SessionFactory sessionFactory;
	
	Properties props = new Properties();
	
	public SessionFactory getSessionFactory() throws IOException {
	    if (sessionFactory == null) {
    	  //System.out.println("Sesion nueva.. creando sesion "); 
    	  Properties props = getProperties();
    	  
	      try {
	        StandardServiceRegistryBuilder registryBuilder = 
	            new StandardServiceRegistryBuilder();

	        Map<String, String> settings = new HashMap<>();
	        settings.put("hibernate.connection.driver_class", props.getProperty("postgresql.driver")); 	// "org.postgresql.Driver"
	        settings.put("hibernate.connection.url", props.getProperty("postgresql.url")); 				// "jdbc:postgresql://192.168.1.23:5432/orangeapi"
	        settings.put("hibernate.connection.username", props.getProperty("postgresql.user"));  		// "postgres"
	        settings.put("hibernate.connection.password", props.getProperty("postgresql.password"));	// "Sofi@2015"
	        settings.put("hibernate.default_schema",  props.getProperty("postgresql.default_schema"));	// "public"
	        settings.put("hibernate.show_sql", props.getProperty("hibernate.show_sql"));				// "true");
	        settings.put("hibernate.hbm2ddl.auto", props.getProperty("hibernate.hbm2ddl.auto"));		//"update");

	        registryBuilder.applySettings(settings);

	        registry = registryBuilder.build();

	        MetadataSources sources = new MetadataSources(registry)
	            .addAnnotatedClass(Account.class);

	        Metadata metadata = sources.getMetadataBuilder().build();

	        sessionFactory = metadata.getSessionFactoryBuilder().build();
	      } catch (Exception e) {
	        //System.out.println("SessionFactory creation failed");
	        if (registry != null) {
	          StandardServiceRegistryBuilder.destroy(registry);
	        }
	      }
	    }
	    return sessionFactory;
	}

	public void shutdown() {
    	System.out.println("Hibernate Util Shutdown ....");
	    if (registry != null) {
	      StandardServiceRegistryBuilder.destroy(registry);
	    }
	}

	
	public Properties getProperties() throws IOException {
		
		Properties retProp = new Properties();
		String dbpropFile = "db.properties";
		// System.out.println(dbpropFile);
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(dbpropFile);
		if (inputStream != null) {
			retProp.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + dbpropFile + "' not founf in the classpath" );
		}
		return retProp;
	}
	
}
