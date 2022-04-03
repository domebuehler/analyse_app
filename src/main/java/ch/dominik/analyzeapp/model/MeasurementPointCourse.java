package ch.dominik.analyzeapp.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings("ClassCanBeRecord")
public final class MeasurementPointCourse {

    private static final Logger LOG = LogManager.getLogger(MeasurementPointCourse.class);
    private final Collection<MeasurementPoint> course;
    private MeasurementPoint measurementPointHighestTemperatur;
    private MeasurementPoint measurementPointLowestTemperatur;

    public MeasurementPointCourse() {
        this.course = new ArrayList<>();
    }

    public void addMeasurementPoint(MeasurementPoint measurementPoint) {
        if (measurementPoint == null) {
            throw new NullPointerException("null is not added");
        } else {
            this.course.add(measurementPoint);
            peakCheck(measurementPoint);
        }
    }

    public Temperatur getAverageTemperatur() {
        if (listIsEmpty()) {
            throw new NullPointerException("list is empty");
        } else {
            double average = 0d;
            for (MeasurementPoint measurementPoint : this.course) {
                average += (double) measurementPoint.getTemperatur().getTempCelsius();
            }
            average = average / this.getSize();
            return Temperatur.createFromCelsius((float) average);
        }
    }

    private boolean listIsEmpty() {
        return this.course.size() == 0;
    }

    private int getSize() {
        return this.course.size();
    }

    private void peakCheck(MeasurementPoint measurementPoint) {
        if (isFirstMeasurementPoint()) {
            setMeasurementPointHighestTemperatur(measurementPoint);
            setMeasurementPointLowestTemperatur(measurementPoint);
        } else {
            checkForLowPoint(measurementPoint);
            checkForHighPoint(measurementPoint);
        }
    }

    private boolean isFirstMeasurementPoint() {
        return this.getSize() == 1;
    }

    private void checkForHighPoint(MeasurementPoint measurementPoint) {
        if (isMeasurementPointNewHighPoint(measurementPoint)) {
            setMeasurementPointHighestTemperatur(measurementPoint);
        }
    }

    private void checkForLowPoint(MeasurementPoint measurementPoint) {
        if (isMeasurementPointNewLowPoint(measurementPoint)) {
            setMeasurementPointLowestTemperatur(measurementPoint);
        }
    }

    private boolean isMeasurementPointNewHighPoint(MeasurementPoint measurementPoint) {
        return measurementPoint.compareTo(this.measurementPointHighestTemperatur) > 0;
    }

    private boolean isMeasurementPointNewLowPoint(MeasurementPoint measurementPoint) {
        return measurementPoint.compareTo(this.measurementPointLowestTemperatur) < 0;
    }

    private void setMeasurementPointHighestTemperatur(MeasurementPoint measurementPointHighestTemperatur) {
        this.measurementPointHighestTemperatur = measurementPointHighestTemperatur;
    }

    private void setMeasurementPointLowestTemperatur(MeasurementPoint measurementPointLowestTemperatur) {
        this.measurementPointLowestTemperatur = measurementPointLowestTemperatur;
    }

    public MeasurementPoint getMeasurementPointHighestTemperatur() {
        return measurementPointHighestTemperatur;
    }

    public MeasurementPoint getMeasurementPointLowestTemperatur() {
        return measurementPointLowestTemperatur;
    }
}
