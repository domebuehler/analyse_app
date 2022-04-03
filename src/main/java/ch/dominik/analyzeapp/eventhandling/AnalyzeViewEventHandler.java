package ch.dominik.analyzeapp.eventhandling;

import javafx.event.ActionEvent;

public interface AnalyzeViewEventHandler {
    void handleChooseBtnEvent(ActionEvent actionEvent);
    void handleAnalyzeBtnEvent(ActionEvent actionEvent);
    void handleClearBtnEvent(ActionEvent actionEvent);
}
