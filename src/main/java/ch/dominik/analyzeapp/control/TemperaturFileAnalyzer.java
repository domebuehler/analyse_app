package ch.dominik.analyzeapp.control;

import ch.dominik.analyzeapp.model.LineConverter;
import ch.dominik.analyzeapp.model.MeasurementPointCourse;
import ch.dominik.analyzeapp.model.TemperatureFileStatistic;
import ch.dominik.analyzeapp.model.MeasurementPoint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

public class TemperaturFileAnalyzer {

    private static final Logger LOG = LogManager.getLogger(TemperaturFileAnalyzer.class);

    private final MeasurementPointCourse course = new MeasurementPointCourse();
    private List<String> stringList = new ArrayList<>();
    private TemperatureFileStatistic temperatureFileStatistic;


    public TemperatureFileStatistic analyzeFile(File file, String statisticName, PropertyChangeListener listener) {
        setUpAnalyzer(statisticName, listener);
        readFile(file);
        handleNumbOfValues();
        analyzeTheLines();
        fillTemperatureFileStatistic();
        return this.temperatureFileStatistic;
    }

    private void setUpAnalyzer(String statisticName, PropertyChangeListener listener) {
        this.temperatureFileStatistic = new TemperatureFileStatistic(statisticName);
        this.temperatureFileStatistic.addPropertyChangeListener(listener);
    }

    private void readFile(File file) {
        try {
            this.stringList = TemperaturFileReader.
                    readTemperaturFile(file);
        } catch (FileNotFoundException e) {
            LOG.error("file does not exist", e);
        }
    }

    private void handleNumbOfValues() {
        this.temperatureFileStatistic.setNumOfValues(stringList.size());
    }

    private void analyzeTheLines() {
        for (String line : this.stringList) {
            tryToHandleNewLine(line);
        }
    }

    private void tryToHandleNewLine(String line) {
        try {
            handleNewLine(line);
        } catch (DataFormatException e) {
            handleDataFormatException();
        }
    }

    private void handleNewLine(String line) throws DataFormatException {
        addMeasurementPointCreatedFromNewLine(line);
        this.temperatureFileStatistic.incrementNumOfConvertions();
    }

    private void addMeasurementPointCreatedFromNewLine(String line) throws DataFormatException {
        MeasurementPoint measurementPoint = LineConverter.convertLineToMeasurementPoint(line);
        course.addMeasurementPoint(measurementPoint);
    }

    private void handleDataFormatException() {
        this.temperatureFileStatistic.incrementNumOfDataExceptions();
    }

    private void fillTemperatureFileStatistic() {
        this.temperatureFileStatistic.setAverageTemperature(this.course.getAverageTemperatur());
        this.temperatureFileStatistic.setMaximalMeasurementPoint(this.course.getMeasurementPointHighestTemperatur());
        this.temperatureFileStatistic.setMinimalMeasurementPoint(this.course.getMeasurementPointLowestTemperatur());
    }
}