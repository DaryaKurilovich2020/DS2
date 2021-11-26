package sample;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Filler {
    private SimpleStringProperty FIO;
    private SimpleStringProperty date;
    private SimpleIntegerProperty number;
    private SimpleDoubleProperty arrears;
    private SimpleDoubleProperty paid;

    public Filler(String FIO, String date, int number, Double arrears, Double paid) {
        this.FIO = new SimpleStringProperty(FIO);
        this.date = new SimpleStringProperty(date);
        this.number = new SimpleIntegerProperty(number);
        this.arrears = new SimpleDoubleProperty(arrears);
        this.paid = new SimpleDoubleProperty(paid);
    }

    public String getFIO() {
        return FIO.get();
    }

    public SimpleStringProperty FIOProperty() {
        return FIO;
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public int getNumber() {
        return number.get();
    }

    public SimpleIntegerProperty numberProperty() {
        return number;
    }

    public double getArrears() {
        return arrears.get();
    }

    public SimpleDoubleProperty arrearsProperty() {
        return arrears;
    }

    public double getPaid() {
        return paid.get();
    }

    public SimpleDoubleProperty paidProperty() {
        return paid;
    }
}
