package ch.dominik.analyzeapp.view;

import ch.dominik.analyzeapp.control.TemperatureFileReaderControl;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public final class MainView extends TabPane {

    private final AnalyzeView analyzeView;
    private final StatisticView statisticView;
    private final Tab analyzeTab;
    private final Tab statisticsTab;

    public MainView() {
        this.analyzeTab = new Tab("Analyze");
        this.statisticsTab = new Tab("Statistics");
        this.analyzeView = new AnalyzeView();
        this.statisticView = new StatisticView();

        this.analyzeTab.setContent(this.analyzeView);
        this.statisticsTab.setContent(this.statisticView);

        this.getTabs().addAll(this.analyzeTab, this.statisticsTab);

        this.analyzeTab.setClosable(false);
        this.statisticsTab.setClosable(false);
    }

    public void addEventHandler(TemperatureFileReaderControl temperatureFileReaderControl) {
        this.analyzeView.addAnalyzeViewEventHandler(temperatureFileReaderControl);
        this.statisticView.addStatisticViewEventHandler(temperatureFileReaderControl);
    }

    public AnalyzeView getAnalyzeView() {
        return analyzeView;
    }

    public StatisticView getStatisticView() {
        return statisticView;
    }
}
