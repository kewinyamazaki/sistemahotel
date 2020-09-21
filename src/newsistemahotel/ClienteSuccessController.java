/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newsistemahotel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Pichau
 */
public class ClienteSuccessController implements Initializable {

    @FXML
    Button buttonSuccess;
    
    @FXML
    public void success(){
        Stage stage = (Stage) buttonSuccess.getScene().getWindow();
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
