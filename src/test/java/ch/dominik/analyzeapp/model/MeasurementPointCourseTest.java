package ch.dominik.analyzeapp.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MeasurementPointCourseTest {

    private static MeasurementPoint measurementPointZeroDegrees;
    private static MeasurementPoint measurementPointMinus20Degrees;
    private static MeasurementPoint measurementPointPlus30Degrees;

    @BeforeAll
    static void beforeAll() {
        LocalDateTime timeOne = LocalDateTime.parse("2022-02-09T21:15:30");
        Temperatur temperaturOne = Temperatur.createFromCelsius(0f);
        measurementPointZeroDegrees = new MeasurementPoint(timeOne, temperaturOne);

        LocalDateTime timeTwo = LocalDateTime.parse("2020-02-09T21:15:30");
        Temperatur temperaturTwo = Temperatur.createFromCelsius(-20f);
        measurementPointMinus20Degrees = new MeasurementPoint(timeTwo, temperaturTwo);

        LocalDateTime timeThree = LocalDateTime.parse("2021-02-09T21:15:30");
        Temperatur temperaturThree = Temperatur.createFromCelsius(30f);
        measurementPointPlus30Degrees = new MeasurementPoint(timeThree, temperaturThree);
    }

    @Test
    void testAddMeasurementPointNullThrowsException() {
        MeasurementPointCourse course = new MeasurementPointCourse();
        assertThatThrownBy(() -> course.addMeasurementPoint(null)).
                isInstanceOf(NullPointerException.class).
                hasMessage("null is not added");
    }

    @Test
    void testGetAverageTemperaturFromEmptyListThrowsException() {
        MeasurementPointCourse course = new MeasurementPointCourse();
        assertThatThrownBy(() -> course.getAverageTemperatur()).
                isInstanceOf(NullPointerException.class).
                hasMessage("list is empty");
    }

    @Test
    void testGetAverageTemperature() {
        MeasurementPointCourse course = new MeasurementPointCourse();
        course.addMeasurementPoint(measurementPointZeroDegrees);
        course.addMeasurementPoint(measurementPointPlus30Degrees);
        Temperatur average = course.getAverageTemperatur();
        assertThat(average).isEqualTo(Temperatur.createFromCelsius(15f));
    }

    @Test
    void testGetMeasurementPointHighestTemperatur() {
        MeasurementPointCourse course = new MeasurementPointCourse();
        course.addMeasurementPoint(measurementPointZeroDegrees);
        course.addMeasurementPoint(measurementPointPlus30Degrees);
        MeasurementPoint highPoint = course.getMeasurementPointHighestTemperatur();
        assertThat(highPoint).isEqualTo(measurementPointPlus30Degrees);
    }

    @Test
    void testGetMeasurementPointLowestTemperatur() {
        MeasurementPointCourse course = new MeasurementPointCourse();
        course.addMeasurementPoint(measurementPointZeroDegrees);
        course.addMeasurementPoint(measurementPointMinus20Degrees);
        MeasurementPoint lowPoint = course.getMeasurementPointLowestTemperatur();
        assertThat(lowPoint).isEqualTo(measurementPointMinus20Degrees);
    }
}