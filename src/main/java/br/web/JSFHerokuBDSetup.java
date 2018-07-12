/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.web;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author alexandrelerario
 */
@ManagedBean
@RequestScoped
public class JSFHerokuBDSetup {

    /**
     * Creates a new instance of JSFHerokuBDSetup
     */
    private String conf;
    
    public JSFHerokuBDSetup() {
    }
    
    public String getConf(){
        return conf;
    }
    

    public void configurar() {
        String databaseUrl = System.getenv("DATABASE_URL");
        this.conf = databaseUrl;
        StringTokenizer st = new StringTokenizer(databaseUrl, ":@/");
        String dbVendor = st.nextToken(); //if DATABASE_URL is set
        String userName = st.nextToken();
        String password = st.nextToken();
        String host = st.nextToken();
        String port = st.nextToken();
        String databaseName = st.nextToken();
        String jdbcUrl = String.format("jdbc:postgresql://%s:%s/%s?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory", host, port, databaseName);
        Map<String, String> properties = new HashMap<String, String>();
        properties.replace("javax.persistence.jdbc.url", databaseUrl);
        properties.replace("javax.persistence.jdbc.user", userName);
        properties.replace("javax.persistence.jdbc.password", password);
        properties.replace("javax.persistence.jdbc.driver", "org.postgresql.Driver");
       // properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(br.data.crud.EMNames.EMN1, properties);
        
      //  <property name="javax.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/cfdb"/>
      //<property name="javax.persistence.jdbc.user" value="postgres"/>
      //<property name="javax.persistence.jdbc.driver" value="org.postgresql.Driver"/>
     // <property name="javax.persistence.jdbc.password" value="postgres"/>
    }
}
