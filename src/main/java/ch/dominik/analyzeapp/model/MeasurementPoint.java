package ch.dominik.analyzeapp.model;

import java.time.LocalDateTime;
import java.util.Objects;

@SuppressWarnings("ClassCanBeRecord")
public final class MeasurementPoint implements Comparable<MeasurementPoint> {
    private final LocalDateTime timestamp;
    private final Temperatur temperatur;

    public MeasurementPoint(LocalDateTime timestamp, Temperatur temperatur) {
        this.timestamp = timestamp;
        this.temperatur = temperatur;
    }

    @Override
    public int compareTo(MeasurementPoint o) {
        if (o == null) {
            throw new NullPointerException("cannot compare to null");
        } else  if (!this.temperatur.equals(o.temperatur)) {
            return this.temperatur.compareTo(o.temperatur);
        } else {
            return this.timestamp.compareTo(o.timestamp);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MeasurementPoint)) {
            return false;
        }
        MeasurementPoint other = (MeasurementPoint) o;
        return Objects.equals(this.temperatur, other.temperatur) &&
                Objects.equals(this.timestamp, other.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.temperatur, this.timestamp);
    }

    @Override
    public String toString() {
        return this.temperatur + " measured on " + this.timestamp;
    }

    public Temperatur getTemperatur() {
        return temperatur;
    }
}