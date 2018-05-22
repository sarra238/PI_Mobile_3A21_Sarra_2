/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import services.AnnonceServices;

/**
 * FXML Controller class
 *
 * @author Win10
 */
public class StatAnnonceController implements Initializable {
    
    @FXML
    private PieChart pie;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       int i,j;
       AnnonceServices A = new AnnonceServices();
       i=A.Count("Promotion");
       j=A.Count("Other");
        ObservableList<PieChart.Data> pieChartData =
               FXCollections.observableArrayList(
                       new PieChart.Data("Promotion",i),
                       new PieChart.Data("Other",j)
               );
        pie.setData(pieChartData);
    }

    
    
}
