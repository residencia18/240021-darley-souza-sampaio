package com.dansoft.empresaCoelho.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryManager {
    private static EntityManagerFactory emf = null;

    public static void initConnection() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("unit_academico");
        }
    }
    
    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            System.out.println("Conexão não iniciada.\n");
        }
        return emf;
    }

    public static void closeEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
