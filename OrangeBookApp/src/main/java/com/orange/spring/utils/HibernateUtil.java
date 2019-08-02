package com.orange.spring.utils;


import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.orange.spring.model.Account;

public class HibernateUtil {

	  private static StandardServiceRegistry registry;
	  private static SessionFactory sessionFactory;

	  public static SessionFactory getSessionFactory() {
	    if (sessionFactory == null) {
    	  System.out.println("Sesion nula.. creando sesion");
	      try {
	        StandardServiceRegistryBuilder registryBuilder = 
	            new StandardServiceRegistryBuilder();

	        Map<String, String> settings = new HashMap<>();
	        settings.put("hibernate.connection.driver_class", "org.postgresql.Driver");
	        settings.put("hibernate.connection.url", "jdbc:postgresql://192.168.1.23:5432/orangeapi");
	        settings.put("hibernate.connection.username", "postgres");
	        settings.put("hibernate.connection.password", "Sofi@2015");
	        settings.put("hibernate.show_sql", "true");
	        settings.put("hibernate.hbm2ddl.auto", "update");

	        registryBuilder.applySettings(settings);

	        registry = registryBuilder.build();

	        MetadataSources sources = new MetadataSources(registry)
	            .addAnnotatedClass(Account.class);

	        Metadata metadata = sources.getMetadataBuilder().build();

	        sessionFactory = metadata.getSessionFactoryBuilder().build();
	      } catch (Exception e) {
	        System.out.println("SessionFactory creation failed");
	        if (registry != null) {
	          StandardServiceRegistryBuilder.destroy(registry);
	        }
	      }
	    }
	    return sessionFactory;
	  }

	  public static void shutdown() {
    	System.out.println("Hibernate Util Shutdown ....");
	    if (registry != null) {
	      StandardServiceRegistryBuilder.destroy(registry);
	    }
	  }
	}
