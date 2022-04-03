package ch.dominik.analyzeapp.view;

import ch.dominik.analyzeapp.eventhandling.AnalyzeViewEventHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AnalyzeView extends GridPane {

    private final List<EventHandler> handlerList = new ArrayList<>();
    private final List<AnalyzeViewEventHandler> analyzeViewEventHandlerList = new ArrayList<>();

    private final Label chooseStatisticNameLabel;
    private final TextField statisticNameTextField;
    private final Label chooseFileLabel;
    private final Button chooseBtn;
    private final Label analyzeLabel;
    private final Button analyzeBtn;
    private final Label fileLabel;
    private final Label progressBarLabel;
    private final ProgressBar progressBar;
    private final ProgressIndicator progressIndicator;
    private final Label infoLabel;
    private final Button clearBtn;

    public AnalyzeView() {
        this.chooseStatisticNameLabel = new Label("1. Name your statistic");
        this.add(this.chooseStatisticNameLabel, 1, 1);
        this.statisticNameTextField = new TextField();
        this.add(this.statisticNameTextField, 1, 2);

        this.chooseFileLabel = new Label("2. Choose the file to analyze");
        this.add(this.chooseFileLabel, 1, 3);
        this.chooseBtn = new Button("choose File");
        this.chooseBtn.setOnAction(actionEvent -> fireChooseBtnEvent(actionEvent));
        this.add(this.chooseBtn, 1, 4);

        this.fileLabel = new Label();
        this.add(this.fileLabel, 2, 4);

        this.analyzeLabel = new Label("3. Analyze you file");
        this.add(this.analyzeLabel, 1, 5);
        this.analyzeBtn = new Button("analyze File");
        this.analyzeBtn.setOnAction(actionEvent -> fireAnalyzeBtnEvent(actionEvent));
        this.add(this.analyzeBtn, 1, 6);

        this.progressBarLabel = new Label("Progress:");
        this.add(this.progressBarLabel, 1, 7);

        this.progressBar = new ProgressBar(0);
        this.add(this.progressBar, 2, 7);

        this.progressIndicator = new ProgressIndicator(0);
        this.add(this.progressIndicator, 3, 7);

        this.infoLabel = new Label();
        this.add(this.infoLabel, 1, 8);

        this.clearBtn = new Button("clear");
        this.clearBtn.setOnAction(actionEvent -> fireClearBtnEvent(actionEvent));
        this.add(this.clearBtn, 2, 8);

        this.setHgap(5);
        this.setVgap(5);
    }

    public void clear() {
        this.disableClearBtn();
        this.changeProgress(0);
        this.clearFile();
        this.setStatisticNameTextField("type here...");
        this.setInfoText("");
        this.disableAnalyzeBtn();
    }

    public void setInfoText(String infoText) {
        this.infoLabel.setText(infoText);
    }

    public void setStatisticNameTextField(String nameTextField) {
        this.statisticNameTextField.setText(nameTextField);
    }

    public void showFile(File file) {
        this.fileLabel.setText(file.toString());
    }

    public void clearFile() {
        this.fileLabel.setText("");
    }

    public void changeProgress(double value) {
        this.progressBar.setProgress(value);
        this.progressIndicator.setProgress(value);
    }

    public void disableAnalyzeBtn() {
        this.analyzeBtn.setDisable(true);
    }

    public void enableAnalyzeBtn() {
        this.analyzeBtn.setDisable(false);
    }

    public void disableClearBtn() {
        this.clearBtn.setDisable(true);
    }

    public void enableClearBtn() {
        this.clearBtn.setDisable(false);
    }

    public String getStaticName() {
        return this.statisticNameTextField.getText();
    }

    public void addAnalyzeViewEventHandler(AnalyzeViewEventHandler handler) {
        if (handler != null) {
            this.analyzeViewEventHandlerList.add(handler);
        } else {
            throw new NullPointerException("cannot add null");
        }
    }

    public void removeAnalyzeViewEventHandler(AnalyzeViewEventHandler handler) {
        if (handler != null) {
            this.analyzeViewEventHandlerList.remove(handler);
        } else {
            throw new NullPointerException("cannot add null");
        }
    }

    private void fireChooseBtnEvent(ActionEvent actionEvent) {
        for (AnalyzeViewEventHandler handler : analyzeViewEventHandlerList) {
            handler.handleChooseBtnEvent(actionEvent);
        }
    }

    private void fireClearBtnEvent(ActionEvent actionEvent) {
        for (AnalyzeViewEventHandler handler : analyzeViewEventHandlerList) {
            handler.handleClearBtnEvent(actionEvent);
        }
    }

    private void fireAnalyzeBtnEvent(ActionEvent actionEvent) {
        for (AnalyzeViewEventHandler handler : analyzeViewEventHandlerList) {
            handler.handleAnalyzeBtnEvent(actionEvent);
        }
    }
}
