package controller.view;

import java.net.URL;
import java.util.List;
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

/**
 * Controller of the view SourceView.
 *
 */
public class SourceControllerView implements Initializable, ControllerView, SourceView {

    private static final int CHART_MAXSIZE_W = 40;
    private static final int CHART_MAXSIZE_H = 40;
    private static final int PANE_MAXSIZE_W = 40;
    private static final int PANE_MAXSIZE_H = 40;
    private static final int CHART_LABEL_LINE_LENGTH = 5; 
    private static final double POS_PRECISION = 100.0; 
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
    private List<RadioButton> rbtns;

    /**
     * Initialize the View.
     * 
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        rbtns = List.of(rbtnDefault, rbtnTweeter, rbtnMidRange, rbtnWoofer);
        this.pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("High", 0),
                new PieChart.Data("Mid", 0),
                new PieChart.Data("Low", 0));
        this.chart = new PieChart(pieChartData);
        this.chart.setMaxSize(CHART_MAXSIZE_W, CHART_MAXSIZE_H); 
        this.chart.setLabelLineLength(CHART_LABEL_LINE_LENGTH);
        this.chart.setLabelsVisible(false);
        this.paneChart.setMaxSize(PANE_MAXSIZE_W, PANE_MAXSIZE_H);
        this.paneChart.getChildren().add(chart);
        this.speakerIsSelected(false);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setControllerApplication(final MainController ctrMain) {
        this.ctrSource = ctrMain.getSourceController();
        this.ctrSource.setControllerView(this);
    }

    /**
     * Adds a default speaker in the environment.
     * 
     * @param event
     */
    @FXML 
    public void handleAddSpeaker(final Event event) {
        this.ctrSource.addSpeaker();
        this.ctrSource.updatePieChartData();
    }

    /**
     * Removes the selected speaker from the environment.
     * 
     * @param event
     */
    @FXML 
    public void handleRemoveSpeaker(final Event event) {
        this.ctrSource.removeSpeaker();
        this.lblX.setText("");
        this.lblY.setText("");
        this.ctrSource.updatePieChartData();
        this.speakerIsSelected(false);
    }

    /**
     * Changes the type of the selected speaker.
     * 
     * @param event
     */
    @FXML 
    public void handleRadioButtonChanged(final Event event) {
        this.ctrSource.setSpeakerType(this.ctrSource.getSelectedSpeaker(), ((RadioButton) event.getSource()).getId());
        this.ctrSource.updatePieChartData();
    }

    /**
     * Updates the radio button group selection in order to the selected speaker.
     */
    @Override
    public void updateSelectedSpeaker() {
        this.lblX.setText(String.valueOf(Math.round(this.ctrSource.getSelectedSpeaker().getPosition().getX() * POS_PRECISION) / POS_PRECISION));
        this.lblY.setText(String.valueOf(Math.round(this.ctrSource.getSelectedSpeaker().getPosition().getY() * POS_PRECISION) / POS_PRECISION));


        switch (this.ctrSource.getSelectedSpeaker().getType()) {
        case FULL:
            this.rbtnDefault.setSelected(true);
            break;
        case HIGH:
            this.rbtnTweeter.setSelected(true);
            break;
        case MID:
            this.rbtnMidRange.setSelected(true);
            break;
        case LOW:
            this.rbtnWoofer.setSelected(true);
            break;
        default:
            break;
        }
        this.speakerIsSelected(true);
    }

    /**
     * Updates the PieChart data.
     * 
     * @param highValue
     * @param midValue
     * @param lowValue
     */
    @Override
    public void updateFreqData(final double highValue, final double midValue, final double lowValue) {
        this.chart.setLabelsVisible(true);
        this.pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("High", highValue),
                new PieChart.Data("Mid", midValue),
                new PieChart.Data("Low", lowValue));
        this.chart.setData(pieChartData);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void speakerIsSelected(final boolean isSelected) {
        this.rbtns.forEach(rbtn -> rbtn.setDisable(!isSelected));
        this.btnRemoveSpeaker.setDisable(!isSelected);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setDisableAddSpeaker(final boolean isDisable) {
        this.btnAddSpeaker.setDisable(isDisable);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void showError(final String error) {

    }
}
