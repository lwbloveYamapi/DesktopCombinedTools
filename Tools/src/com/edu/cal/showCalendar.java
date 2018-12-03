package com.edu.cal;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Calendar;

public class showCalendar extends Pane {

    private Calendar calendar = Calendar.getInstance();
    private int year;
    private int month;
    private int day;
    private int firstDayOfWeek;
    private int totalDayOfMonth;


    public void setYear(int year) {
        calendar.set(Calendar.YEAR, year);
    }

    public int getYear() {
        return calendar.get(Calendar.YEAR);
    }

    public void setMonth(int month) {
        calendar.set(Calendar.MONTH, month - 1);
    }

    public int getMonth() {
        return calendar.get(Calendar.MONTH) + 1;
    }

    public void setDay(int day) {
        calendar.set(calendar.DAY_OF_MONTH, day);
    }

    public int getDay() {
        return day;
    }

    public int getFirstDayOfWeek() {
        return firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

    public showCalendar() {
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        totalDayOfMonth = calendar.getActualMaximum(Calendar.DATE);
        calendar.set(Calendar.DAY_OF_MONTH, 1);//将calendar设置为该月的第一天
        firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        getChildren().clear();
        paint();
    }

    public void showCalendar() {
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        totalDayOfMonth = calendar.getActualMaximum(Calendar.DATE);
        calendar.set(Calendar.DAY_OF_MONTH, 1);//将calendar设置为该月的第一天
        firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        getChildren().clear();
        paint();
    }

    public void paint() {
        GridPane calendar_Panel = new GridPane();
        calendar_Panel.setPrefSize(280, 170);
//        calendar_Panel
        String[] week = new String[]{"SUN日", "MON一", "TUE二", "WED三", "THU四", "FRI五", "SAT六"};

        for (int i = 0; i < 7; i++) {
            Label label = new Label(week[i]);
            label.setAlignment(Pos.CENTER);
            if (i == 0) label.setTextFill(Color.RED);
            if (i == 6) label.setTextFill(Color.BLUE);
            calendar_Panel.add(label, i, 0);
            calendar_Panel.getColumnConstraints().add(new ColumnConstraints(40));
            calendar_Panel.getRowConstraints().add(new RowConstraints(24));
            GridPane.setHalignment(label, HPos.CENTER);
            calendar_Panel.setGridLinesVisible(true);
        }
        for (int j = 0; j < totalDayOfMonth; j++) {
            Label label = new Label(j + 1 + "");
            label.setPrefSize(40, 24);
            label.setAlignment(Pos.CENTER);
            if (j + 1 == day) {
                label.setFont(Font.font("Cooper Black", FontWeight.BOLD, 20));
                label.setStyle("-fx-background-color: pink");
            }
            int k = firstDayOfWeek + j;
            if (k % 7 == 0) label.setTextFill(Color.RED);
            if (k % 7 == 6) label.setTextFill(Color.BLUE);
            calendar_Panel.add(label, k % 7, 1 + k / 7);
            GridPane.setHalignment(label, HPos.CENTER);
        }

        getChildren().add(calendar_Panel);
    }

}