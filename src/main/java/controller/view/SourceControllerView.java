package controller.view;

import java.net.URL;
import java.util.ResourceBundle;

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

public class SourceControllerView implements Initializable, ControllerView {

    private static final int CHART_MAXSIZE_W = 40;
    private static final int CHART_MAXSIZE_H = 40;
    private static final int PANE_MAXSIZE_W = 40;
    private static final int PANE_MAXSIZE_H = 40;
    private static final int CHART_LABEL_LINE_LENGTH = 5; 
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

    /**
     * 
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("High", 0),
                new PieChart.Data("Mid", 0),
                new PieChart.Data("Low", 0));
        chart = new PieChart(pieChartData);
        chart.setMaxSize(CHART_MAXSIZE_W, CHART_MAXSIZE_H); 
        chart.setLabelLineLength(CHART_LABEL_LINE_LENGTH);
        paneChart.setMaxSize(PANE_MAXSIZE_W, PANE_MAXSIZE_H);
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

    /**
     * 
     * @param event
     */
    @FXML 
    public void handleAddSpeaker(final Event event) {
        this.ctrSource.addSpeaker();
        this.ctrSource.updatePieChartData();
    }

    /**
     * 
     * @param event
     */
    @FXML 
    public void handleRemoveSpeaker(final Event event) {
        this.ctrSource.removeSpeaker();
        lblX.setText("");
        lblY.setText("");
        this.ctrSource.updatePieChartData();
    }

    /**
     * 
     * @param event
     */
    @FXML 
    public void handleRadioButtonChanged(final Event event) {
        ctrSource.setSpeakerType(this.ctrSource.getSelectedSpeaker(), ((RadioButton) event.getSource()).getId());
        this.ctrSource.updatePieChartData();
    }

    /**
     * 
     */
    public void updateSelectedSpeaker() {
        lblX.setText(Float.toString(this.ctrSource.getSelectedSpeaker().getPosition().getX()));
        lblY.setText(Float.toString(this.ctrSource.getSelectedSpeaker().getPosition().getY()));

        switch (this.ctrSource.getSelectedSpeaker().getType()) {
        case FULL:
            rbtnDefault.setSelected(true);
            break;
        case HIGH:
            rbtnTweeter.setSelected(true);
            break;
        case MID:
            rbtnMidRange.setSelected(true);
            break;
        case LOW:
            rbtnWoofer.setSelected(true);
            break;
        default:
            break;
        }
    }

    /**
     * 
     * @param highValue
     * @param midValue
     * @param lowValue
     */
    public void updatePieChartFreq(final double highValue, final double midValue, final double lowValue) {
        this.pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("High", highValue),
                new PieChart.Data("Mid", midValue),
                new PieChart.Data("Low", lowValue));
        chart.setData(pieChartData);
    }


    @Override
    public void showError(final String error) {

    }
}
