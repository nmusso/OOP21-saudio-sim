package controller.view;

import java.net.URL;
import java.util.ResourceBundle;

import javax.sound.sampled.AudioSystem;

import controller.MainController;
import controller.SourceController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Pane;
import model.source.FRSource;
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
    @FXML private Pane paneChart;
    private PieChart chart;
    private ObservableList<PieChart.Data> pieChartData;
    private SourceController ctrSource;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("High", 33),
                new PieChart.Data("Mid", 33),
                new PieChart.Data("Low", 33));
        chart = new PieChart(pieChartData);
        chart.setTitle("Freq Distribution");
        chart.setMaxSize(30, 30);
        chart.setLabelLineLength(10);
        paneChart.getChildren().add(chart);

    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void setControllerApplication(final MainController ctrMain) {
        this.ctrSource = ctrMain.getSourceController();
        this.ctrSource.setControllerView(this);
    }

    @FXML 
    private void handleAddSpeaker(final Event event) {
        this.ctrSource.addSpeaker();
    }

    @FXML 
    private void handleRemoveSpeaker(final Event event) {
        this.ctrSource.removeSpeaker();
        //TODO clear
        lblX.setText("");
        lblY.setText("");
    }

    @FXML 
    private void handleRadioButtonChanged(final Event event) {
        ctrSource.setSpeakerType(this.getSelectedSpeaker(), ((RadioButton) event.getSource()).getId());
    }

    private FRSource getSelectedSpeaker() {
        return this.ctrSource.getSelectedSpeaker();
    }

    /**
     * 
     */
    public void updateSelectedSpeaker() {
        lblX.setText(Float.toString(this.getSelectedSpeaker().getPosition().getX()));
        lblY.setText(Float.toString(this.getSelectedSpeaker().getPosition().getY()));

        //TODO clear
        switch (this.getSelectedSpeaker().getType()) {
        case FULL:
            if (!rbtnDefault.isSelected()) {
                this.updatePieChartFreq(33, 33, 33);
                rbtnDefault.setSelected(true);
            }
            break;
        case HIGH:
            if (!rbtnTweeter.isSelected()) {
                rbtnTweeter.setSelected(true);
                this.updatePieChartFreq(70, 15, 15);
            }
            break;
        case MID:
            if (!rbtnMidRange.isSelected()) {
                rbtnMidRange.setSelected(true);
                this.updatePieChartFreq(25, 50, 25);
            }
            break;
        case LOW:
            if (!rbtnWoofer.isSelected()) {
                rbtnWoofer.setSelected(true);
                this.updatePieChartFreq(15, 10, 75);
            }
            break;
        default:
            break;
        }
    }

    /**
     * 
     */
    public void updatePieChartFreq(final int highValue, final int midValue, final int lowValue) {
        this.pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("High", highValue),
                new PieChart.Data("Mid", midValue),
                new PieChart.Data("Low", lowValue));
        chart.setData(pieChartData);

    }


    @Override
    public void showError(String error) {
        // TODO Auto-generated method stub
        
    }

}
