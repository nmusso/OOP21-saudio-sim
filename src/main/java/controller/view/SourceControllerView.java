package controller.view;

import java.awt.Color;
import java.net.URL;
import java.util.ResourceBundle;

import controller.MainController;
import controller.SourceController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.source.FRSource;
import model.source.FRSourceImpl;
import model.source.SourceType;

public class SourceControllerView implements Initializable, ControllerView {

    @FXML private Button btnAddSpeaker;
    @FXML private Button btnRemoveSpeaker;
    @FXML private RadioButton rbtnDefault;
    @FXML private RadioButton rbtnTweeter;
    @FXML private RadioButton rbtnMidRange;
    @FXML private RadioButton rbtnWoofer;
    @FXML private Label lblX;
    @FXML private Label lblY;
    @FXML private PieChart pieChartSpeaker;
    @FXML private Pane paneChart;
    private SourceController ctrSource;
    private PieChart chart;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setControllerApplication(final MainController ctrMain) {
        this.ctrSource = ctrMain.getSourceController();
        this.ctrSource.setControllerView(this);
        
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("High", 70),
                new PieChart.Data("Mid", 15),
                new PieChart.Data("Low", 15));
        chart = new PieChart(pieChartData);
        chart.setTitle("Freq Distribution");
        chart.setMaxSize(50, 50);
        paneChart.getChildren().add(chart);
    }

    @FXML private void handleAddSpeaker(final Event event) {
        //TODO Add speaker from SourceHub in Environment

    }

    @FXML private void handleRemoveSpeaker(final Event event) {
        FRSource speaker = this.getSelectedSpeaker();

        //TODO Remove speaker from SourceHub in Environment
    }

    @FXML private void handleRadioButtonChanged(final Event event) {
        System.out.println(((RadioButton) event.getSource()).getId());
        ctrSource.setSpeakerType(this.getSelectedSpeaker(), ((RadioButton) event.getSource()).getId());
    }

    private FRSource getSelectedSpeaker() {
        FRSource selected = this.ctrSource.getSelectedSpeaker();
        lblX.setText(Float.toString(selected.getPosition().getX()));
        lblY.setText(Float.toString(selected.getPosition().getY()));
        return selected;
    }

    /**
     * 
     * @param pieChartData
     */
    @FXML public void setPieChartData(final ObservableList<PieChart.Data> pieChartData) {
        chart = new PieChart(pieChartData);
        chart.setTitle("Freq Distribution");
        chart.setMaxSize(50, 50);
        paneChart.getChildren().add(chart);
    }

}
