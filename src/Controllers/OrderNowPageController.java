/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Compu City
 */
public class OrderNowPageController {
    @FXML
    private AnchorPane OrderNow;

    @FXML
    void OrderNowAction(ActionEvent event) {
        OrderNow.getScene().getWindow().hide();
        Parent parent;
        try {
            parent = FXMLLoader.load(getClass().getResource("/PizzaStoreDesign/OrderingPizza.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(OrderNowPageController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
