/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newsistemahotel;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Pichau
 */
public class AlterarDadosController implements Initializable {

    int clientId;
    
    public void getClientIdA(int clientId){
        this.clientId = clientId;
    }
    
    @FXML
    TextField nome,rg,endereco,descricao,dataEntrada,dataSaida,tipo,telefone;
    
    @FXML
    Button alterarButton;
    
    @FXML
    public void alterar(){ 
        
        Connection c = null;
     Statement stmt = null;
     
        try{
             Class.forName("org.sqlite.JDBC");
             c = DriverManager.getConnection("jdbc:sqlite:clientes.db");
             c.setAutoCommit(false);
             System.out.println("Opened database successfully");
             
             stmt = c.createStatement();
             
             String sql1 = "UPDATE CLIENTE SET NOME = "
                     +nome.getText()
                     + " WHERE ID_CLIENTE = '"+this.clientId+"';";
             stmt.executeUpdate(sql1);
             stmt.close();
             c.commit();
             c.close();
             
        }catch(Exception e){
             System.out.println("Something went wrong on refresh()");
        }
        
        //...
        Stage stage = (Stage) alterarButton.getScene().getWindow();
        stage.close();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
