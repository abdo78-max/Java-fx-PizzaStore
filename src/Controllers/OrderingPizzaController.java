/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import enums.CrustType;
import enums.Sizes;
import enums.WhereToEat;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Compu City
 */
public class OrderingPizzaController implements Initializable {

    @FXML
    private Label Size;

    @FXML
    private Label Type;

    @FXML
    private Label toppingsPrice;

    @FXML
    private ChoiceBox<String> crustType;

    @FXML
    private ChoiceBox<String> sizes;

    @FXML
    private Label totalPrice;

    @FXML
    private Label where;

    @FXML
    private ChoiceBox<String> whereToEat;
    @FXML
    private ListView<String> toppingList;

    private double basePrice;

    @FXML
    private Label toppingsInBill;

    @FXML
    private Label vaildition;

    @FXML
    void MenuAction(ActionEvent event) {
        Parent parent;
        try {
            parent = FXMLLoader.load(getClass().getResource("/PizzaStoreDesign/menu.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(OrderNowPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void confirmOrderAction(ActionEvent event) {
        if (sizes.getValue() != null && crustType.getValue() != null && whereToEat.getValue() != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/PizzaStoreDesign/Bill.fxml"));
            Parent parent;
            try {
                parent = loader.load();
                BillController controller = loader.getController();
                ArrayList<String> toppingsList = new ArrayList<>(toppingList.getSelectionModel().getSelectedItems());
                controller.setData(Size.getText(), Type.getText(), where.getText(), toppingsPrice.getText(), toppingsList, totalPrice.getText());
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(OrderingPizzaController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            vaildition.setText("you must select all to confirm order");
            vaildition.setWrapText(true);
            PauseTransition pauseTransition = new PauseTransition();
            pauseTransition.setDuration(Duration.seconds(3));
            pauseTransition.setOnFinished(e -> {
                vaildition.setText("");
            });
            pauseTransition.play();
        }
    }

    @FXML
    void aboutAsAction(ActionEvent event) {
        Parent parent;
        try {
            parent = FXMLLoader.load(getClass().getResource("/PizzaStoreDesign/ABoutUs.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(OrderNowPageController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void restForm(ActionEvent event) {
        totalPrice.setText("0.00");
        toppingsPrice.setText("0.00");
        toppingsInBill.setText("Nothing");
        Size.setText("Size:");
        Type.setText("Crust Type:");
        where.setText("Eat:");
        sizes.getSelectionModel().clearSelection();
        crustType.getSelectionModel().clearSelection();
        whereToEat.getSelectionModel().clearSelection();
        toppingList.getSelectionModel().clearSelection();
        basePrice = 0;
    }

    public void updatePrice() {
        String selectedSize = sizes.getValue();
        if (selectedSize == null) {
            return;
        }
        if (selectedSize.equalsIgnoreCase("Small")) {
            basePrice = 28.0;
        } else if (selectedSize.equalsIgnoreCase("Medium")) {
            basePrice = 30.0;
        } else {
            basePrice = 32.0;
        }
        double sum = Double.parseDouble(toppingsPrice.getText());
        double total = basePrice + sum;

        String selectedWhereToEat = whereToEat.getValue();
        if (selectedWhereToEat != null && selectedWhereToEat.equalsIgnoreCase("In")) {
            total = total + total * .10;
        }
        totalPrice.setText(String.valueOf(total));
        Size.setText("Size: " + selectedSize);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sizes.getItems().addAll(Sizes.SMALL.getSize(), Sizes.MEDIUM.getSize(), Sizes.LARGE.getSize());
        crustType.getItems().addAll(CrustType.THICKCRUST.getType(), CrustType.THINCRUST.getType());
        whereToEat.getItems().addAll(WhereToEat.EATIN.getEat(), WhereToEat.EATOUT.getEat());
        sizes.setOnAction(event
                -> {
            updatePrice();
        });
        crustType.setOnAction(event2 -> {
            String selectedCryst = crustType.getValue();
            if (selectedCryst == null) {
                return;
            }
            Type.setText("Crust Type : " + selectedCryst);
        });
        whereToEat.setOnAction(event3 -> {
            String selectedWhereToEat = whereToEat.getValue();
            if (selectedWhereToEat == null) {
                return;
            }
            where.setText("Eat: " + selectedWhereToEat);
            updatePrice();
        });
        Map<String, Double> toppingPrices = new HashMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Toppings.txt"));) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split("=");
                String toppings = parts[0];
                Double prices = Double.valueOf(parts[1]);
                toppingPrices.put(toppings, prices);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OrderingPizzaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OrderingPizzaController.class.getName()).log(Level.SEVERE, null, ex);
        }

        toppingList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        toppingList.getItems().addAll(toppingPrices.keySet());
        toppingList.getSelectionModel().getSelectedItems().addListener((ListChangeListener<String>) change -> {
            ArrayList<String> selected = new ArrayList<>(toppingList.getSelectionModel().getSelectedItems());
            if (selected.isEmpty()) {
                toppingsInBill.setText("Nothing");
                totalPrice.setText("0.00");
                toppingsPrice.setText("0.00");
            } else {
                String result = String.join(", ", selected);
                toppingsInBill.setText(result);
                toppingsInBill.setWrapText(true);
                double sum = 0;
                for (String topping : selected) {
                    sum += toppingPrices.get(topping);
                    totalPrice.setText(String.valueOf(basePrice + sum));
                    toppingsPrice.setText(String.valueOf(sum));
                }
                updatePrice();

            }

        }
        );
    }
}
