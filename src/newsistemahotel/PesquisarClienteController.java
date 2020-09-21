/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newsistemahotel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.sql.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
/**
 * FXML Controller class
 *
 * @author Pichau
 */
public class PesquisarClienteController implements Initializable {
    
    @FXML
    TextField rg;
    
    @FXML
    Button button, voltar;
    
    String nomeCliente1, rgCliente, tipo_acomodacao1, endereco1, descricao1, data_entrada1, data_saida1, telefone1;
    
    int clientId1;
    
    @FXML
    public void pesquisar(){
         
         Connection c = null;
         Statement stmt = null;
         
         rgCliente = rg.getText();
         
         try{
             Class.forName("org.sqlite.JDBC");
             c = DriverManager.getConnection("jdbc:sqlite:clientes.db");
             c.setAutoCommit(false);
             System.out.println("Opened database successfully");
             
             stmt = c.createStatement();
             
             String sql1 = "SELECT COUNT(*) FROM CLIENTE WHERE RG = '"+rgCliente+"';";
             ResultSet rs1 = stmt.executeQuery(sql1);
             
             int count    = rs1.next() ? rs1.getInt(1) : 0;
             //System.out.println("Count: "+count);
             if(count == 0){
                 try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("clienteNaoExiste.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initStyle(StageStyle.DECORATED);
                    stage.setScene(new Scene(root1));  
                    stage.show();//
                    rs1.close();
                    stmt.close();
                    c.close();//
                } catch(Exception e) {
                    System.out.println("Can not open window!");
                }
             }else {
                 rs1.close();
                 String sql = "SELECT * FROM CLIENTE;";
                 ResultSet rs = stmt.executeQuery(sql);
                 System.out.println("rg: "+rgCliente);
                 while ( rs.next() ) {
                    if((rs.getString("rg")).equals(rgCliente)){
                        clientId1 = rs.getInt("id_cliente");
                        nomeCliente1 = rs.getString("nome");
                        tipo_acomodacao1 = rs.getString("tipo_acomodacao");
                        endereco1 = rs.getString("endereco");
                        descricao1 = rs.getString("descricao_acomodacao");
                        data_entrada1 = rs.getString("data_entrada_reserva");
                        data_saida1 = rs.getString("data_saida_reserva");
                        telefone1 = rs.getString("telefone");
                        System.out.println("I've got hereX!");
                    }
                }      
                rs.close();
                stmt.close();
                c.close();
                
                try {
                    FXMLLoader painelCliente = new FXMLLoader(getClass().getResource("painelCliente.fxml"));
                    //Parent root1 = (Parent) fxmlLoader2.load();
                    Pane ap = painelCliente.load();
                    PainelClienteController controller = painelCliente.getController();
                    controller.getVariables(clientId1,nomeCliente1, rgCliente, tipo_acomodacao1, endereco1, descricao1, data_entrada1, data_saida1, telefone1);
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initStyle(StageStyle.DECORATED);
                    stage.setScene(new Scene(ap));
                    stage.show();
                } catch(Exception e) {
                    System.out.println("Can not open window!");
                }

             }
         }catch(Exception e){
             System.out.println("Something went worng!");
         }
        
        //if(!checkClient()){ create a new dialog saying that the client doesn't exists}
        //else{go to the next page(painelCliente.fxml)}
        
        // ...
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }
    /**
     * Initializes the controller class.
     */
    
    @FXML
    public void voltar(){
        Stage stage = (Stage) voltar.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}