package ch.dominik.analyzeapp.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public final class TemperatureFileStatistic {

    private final String statisticName;
    private MeasurementPoint maximalMeasurementPoint;
    private MeasurementPoint minimalMeasurementPoint;
    private Temperatur averageTemperature;
    private int numOfConvertions = 0;
    private int numOfDataExceptions = 0;
    private int numOfValues = 0;
    private final List<PropertyChangeListener> listeners = new ArrayList<>();

    public TemperatureFileStatistic(String statisticName) {
        this.statisticName = statisticName;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        if (listener != null) {
            this.listeners.add(listener);
        } else {
            throw new NullPointerException("cannot add null");
        }
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        if (listener != null) {
            this.listeners.remove(listener);
        } else {
            throw new NullPointerException("cannot remove null");
        }
    }

    public void firePropertyChangeEvent(PropertyChangeEvent propertyChangeEvent) {
        if (this.listeners.size() > 0) {
            for (PropertyChangeListener listener : this.listeners) {
                listener.propertyChange(propertyChangeEvent);
            }
        }
    }

    public int getNumOfPropertyChangeListeners() {
        return this.listeners.size();
    }

    @Override
    public String toString() {
        return "TemperatureFileStatistic{" +
                "statisticName='" + statisticName + '\'' +
                ", maximalMeasurementPoint=" + maximalMeasurementPoint +
                ", minimalMeasurementPoint=" + minimalMeasurementPoint +
                ", averageTemperature=" + averageTemperature +
                ", numOfConvertions=" + numOfConvertions +
                ", numOfDataExceptions=" + numOfDataExceptions +
                ", numOfValues=" + numOfValues +
                '}';
    }

    public void incrementNumOfConvertions() {
        this.setNumOfConvertions(this.getNumOfConvertions() + 1);
    }

    public void incrementNumOfDataExceptions() {
        this.setNumOfDataExceptions(this.getNumOfDataExceptions() + 1);
    }

    public int getNumOfConvertions() {
        return numOfConvertions;
    }

    public void setNumOfConvertions(int numOfConvertions) {
        int oldValue = this.getNumOfConvertions();
        this.numOfConvertions = numOfConvertions;
        firePropertyChangeEvent(new PropertyChangeEvent(this, "numOfConvertions",
                oldValue, this.getNumOfConvertions()));
    }

    public int getNumOfDataExceptions() {
        return numOfDataExceptions;
    }

    public void setNumOfDataExceptions(int numOfDataExceptions) {
        int oldValue = this.getNumOfDataExceptions();
        this.numOfDataExceptions = numOfDataExceptions;
        firePropertyChangeEvent(new PropertyChangeEvent(this, "numOfDataExceptions",
                oldValue, this.getNumOfDataExceptions()));
    }

    public int getNumOfValues() {
        return numOfValues;
    }

    public void setNumOfValues(int numOfValues) {
        int oldValue = this.getNumOfValues();
        this.numOfValues = numOfValues;
        firePropertyChangeEvent(new PropertyChangeEvent(this, "numOfValues",
                oldValue, this.getNumOfValues()));
    }

    public MeasurementPoint getMaximalMeasurementPoint() {
        return maximalMeasurementPoint;
    }

    public void setMaximalMeasurementPoint(MeasurementPoint maximalMeasurementPoint) {
        this.maximalMeasurementPoint = maximalMeasurementPoint;
    }

    public MeasurementPoint getMinimalMeasurementPoint() {
        return minimalMeasurementPoint;
    }

    public void setMinimalMeasurementPoint(MeasurementPoint minimalMeasurementPoint) {
        this.minimalMeasurementPoint = minimalMeasurementPoint;
    }

    public Temperatur getAverageTemperature() {
        return averageTemperature;
    }

    public void setAverageTemperature(Temperatur averageTemperature) {
        this.averageTemperature = averageTemperature;
    }
}
