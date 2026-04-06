/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 *
 * @author Compu City
 */
public class BillController {

    @FXML
    private Label size;
    @FXML
    private Label crustType;
    @FXML
    private Label whereToEat;
    @FXML
    private Label toppingsPrice;
    @FXML
    private Label toppingList;
    @FXML
    private Label totalPrice;

    public void setData(String size, String crust, String where, String toppingsPrice, ArrayList<String> toppingsList, String totalPrice) {
        this.size.setText(size);
        crustType.setText(crust);
        whereToEat.setText(where);
        this.toppingsPrice.setText(toppingsPrice);
        String result = String.join(",", toppingsList);
        if (toppingsList.isEmpty()) {
            this.toppingList.setText("Nothing");
        } else {
            this.toppingList.setText(result);
            this.toppingList.setWrapText(true);
        }
        this.totalPrice.setText(totalPrice);

    }
}
