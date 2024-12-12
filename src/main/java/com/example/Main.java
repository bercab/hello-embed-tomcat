package com.example;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.connector.Connector;
import java.io.File;

public class Main {
    public static void main(String[] args) throws LifecycleException {
        try {
            Tomcat tomcat = new Tomcat();
            
            // Configure connector explicitly
            Connector connector = new Connector();
            connector.setPort(8080);
            tomcat.setConnector(connector);
            
            // Create base and work directories
            File baseDir = new File("tomcat");
            baseDir.mkdirs();
            tomcat.setBaseDir(baseDir.getAbsolutePath());
            
            // Ensure webapp directory exists
            File webappDir = new File("src/main/webapp");
            webappDir.mkdirs();
            
            // Create context with absolute path
            Context context = tomcat.addContext("", webappDir.getAbsolutePath());

            // Add servlet
            Tomcat.addServlet(context, "helloServlet", new HelloServlet());
            context.addServletMappingDecoded("/", "helloServlet");

            // Start Tomcat
            tomcat.start();
            System.out.println("Tomcat started on port 8080");
            System.out.println("Server state: " + tomcat.getServer().getState());
            System.out.println("Connector state: " + connector.getState());
            System.out.println("Browse to http://localhost:8080/");
            
            tomcat.getServer().await();
        } catch (Exception e) {
            System.err.println("Error starting Tomcat: " + e.getMessage());
            e.printStackTrace();
        }
    }
}