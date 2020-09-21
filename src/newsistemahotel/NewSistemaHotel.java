/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newsistemahotel;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;

/**
 *
 * @author Pichau
 */
public class NewSistemaHotel extends Application {
    
    public static void createDataBase(){
      Connection c = null;
      Statement stmt = null;
      
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:clientes.db");
         System.out.println("Opened database successfully");
         try{
            stmt = c.createStatement();
          
            String sql = "CREATE TABLE CLIENTE " +
                        " (ID_CLIENTE               INTEGER  PRIMARY KEY AUTOINCREMENT,"+
                        " NOME                      TEXT     NOT NULL, " + 
                        " ENDERECO                  TEXT     NOT NULL, " +
                        " TELEFONE                  TEXT     NOT NULL, " +
                        " RG                        TEXT     NOT NULL, " +
                        " TIPO_ACOMODACAO           TEXT     NOT NULL, " +
                        " DESCRICAO_ACOMODACAO      TEXT     NOT NULL, " +
                        " DATA_ENTRADA_RESERVA      TEXT     NOT NULL, " +
                        " DATA_SAIDA_RESERVA        TEXT     NOT NULL);";
             stmt.executeUpdate(sql);
             
             String sql2 = "CREATE TABLE GASTOS "+
                     " (ID_GASTO            INTEGER  PRIMARY KEY AUTOINCREMENT,"+
                     "  ID_CLIENTE          INTEGER  , "+
                     "  PRODUTO             TEXT     NOT NULL, "+
                     "  PRECO               TEXT     NOT NULL, "+
                     "  FOREIGN KEY(ID_CLIENTE) REFERENCES CLIENTE(ID_CLIENTE));";
             stmt.executeUpdate(sql2);
             
             stmt.close();
             c.commit();
             c.close();
             System.out.println("Table created successfully!");
         }catch(Exception e){
             System.out.println("Table Already Exists!");
         }
      }catch(Exception e){
          System.out.println("Something went wrong!");
      }
    }
    
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     * 
     */
    public static void main(String[] args){
        createDataBase();
        launch(args);
    }
    
}
