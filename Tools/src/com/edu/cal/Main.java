package com.edu.cal;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        showCalendar calendar = new showCalendar();

        FlowPane pane = new FlowPane();//设置主面板
        pane.setPrefWidth(560);
        pane.setPrefHeight(400);
        FlowPane top_pane = new FlowPane();//设置面板上部分，包含“上年”、“下年”等控件
        top_pane.setPrefHeight(30);
        top_pane.setPrefWidth(pane.getPrefWidth());
        FlowPane center_pane = new FlowPane();//设置面板中间部分，包含日历、时钟、和记事本
        center_pane.setPrefHeight(340);
        center_pane.setPrefWidth(pane.getPrefWidth());
        FlowPane left_of_cen_pane = new FlowPane();//设置面板中间部分的左半边部分，包含日历和时钟；
        left_of_cen_pane.setPrefHeight(center_pane.getPrefHeight());
        left_of_cen_pane.setPrefWidth(center_pane.getPrefWidth() / 2);
        StackPane calendar_pane = new StackPane();//日历面板
        calendar_pane.setPrefSize(left_of_cen_pane.getPrefWidth(), left_of_cen_pane.getPrefHeight() / 2);
        StackPane clock_pane = new StackPane();//时钟面板
        clock_pane.setStyle("-fx-background-color: gray");
        clock_pane.setPrefSize(left_of_cen_pane.getPrefWidth(), left_of_cen_pane.getPrefHeight() / 2);
        FlowPane right_of_cen_pane = new FlowPane();//设置面板中间右半部分，即记事本
        right_of_cen_pane.setPrefHeight(center_pane.getPrefHeight());
        right_of_cen_pane.setPrefWidth(center_pane.getPrefWidth() / 2);
        FlowPane bottom_pane = new FlowPane();//设置面板底部，包含“保存日志”，“删除日志”等控件
        bottom_pane.setPrefHeight(30);
        bottom_pane.setPrefWidth(pane.getPrefWidth());
        pane.setOrientation(Orientation.VERTICAL);
        pane.getChildren().addAll(top_pane, center_pane, bottom_pane);
        center_pane.setOrientation(Orientation.HORIZONTAL);
        center_pane.getChildren().addAll(left_of_cen_pane, right_of_cen_pane);


        Button pre_year = new Button("上年");
        Button next_year = new Button("下年");
        Button pre_month = new Button("上月");
        Button next_month = new Button("下月");
        TextField text_year = new TextField(calendar.getYear() + "");
        text_year.setPrefWidth(60);
        text_year.setAlignment(Pos.CENTER);
        TextField text_month = new TextField(calendar.getMonth() + "");
        text_month.setPrefWidth(60);
        text_month.setAlignment(Pos.CENTER);
        top_pane.setOrientation(Orientation.HORIZONTAL);
        top_pane.setAlignment(Pos.CENTER);
        top_pane.getChildren().addAll(pre_year, text_year, next_year, pre_month, text_month, next_month);


        Button save_log = new Button("保存日志");
        save_log.setPrefWidth(70);
        Button delete_log = new Button("删除日志");
        delete_log.setPrefWidth(70);
        Button read_log = new Button("读取日志");
        read_log.setPrefWidth(70);
        bottom_pane.setOrientation(Orientation.HORIZONTAL);
        bottom_pane.setAlignment(Pos.CENTER);
        bottom_pane.getChildren().addAll(save_log, delete_log, read_log);


        Label show_date = new Label(calendar.getYear() + "年" + calendar.getMonth() + "月" + calendar.getDay() + "日");
        show_date.setAlignment(Pos.CENTER);
        show_date.setPrefWidth(280);
        show_date.setPrefHeight(25);
        show_date.setStyle("-fx-background-color: pink");
        TextArea textArea = new TextArea();
        textArea.setPrefSize(280, 315);
        right_of_cen_pane.setOrientation(Orientation.VERTICAL);
        right_of_cen_pane.getChildren().addAll(show_date, textArea);


        //设置时钟
        ClockPane clock = new ClockPane();
        EventHandler<ActionEvent> eventEventHandler = e -> {
            clock.setCurrentTime();
        };
        Timeline animation = new Timeline(
                new KeyFrame(Duration.millis(1000), eventEventHandler)
        );
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
        clock_pane.getChildren().add(clock);

        //设置日历
        calendar_pane.getChildren().add(calendar);
        left_of_cen_pane.setOrientation(Orientation.VERTICAL);
        left_of_cen_pane.getChildren().add(calendar_pane);
        left_of_cen_pane.getChildren().add(clock_pane);

        /**
         *设置事件响应
         */
        pre_year.setOnAction(event -> {//上一年
            int year = calendar.getYear() - 1;
            calendar.setYear(year);
            text_year.setText(year + "");
            calendar.showCalendar();
            show_date.setText(calendar.getYear() + "年" + calendar.getMonth() + "月" + calendar.getDay() + "日");
        });
        next_year.setOnAction(event -> {//下一年
            int year = calendar.getYear() + 1;
            calendar.setYear(year);
            text_year.setText(year + "");
            calendar.showCalendar();
            show_date.setText(calendar.getYear() + "年" + calendar.getMonth() + "月" + calendar.getDay() + "日");
        });
        pre_month.setOnAction(event -> {//上一月
            int month = calendar.getMonth() - 1;
            if (month < 1) {
                ;
            } else {
                calendar.setMonth(month);
                text_month.setText(month + "");
                calendar.showCalendar();
                show_date.setText(calendar.getYear() + "年" + calendar.getMonth() + "月" + calendar.getDay() + "日");
            }
            System.out.println(calendar.getMonth());
        });
        next_month.setOnAction(event -> {//下一月
            int month = calendar.getMonth() + 1;
            if (month > 12) {
                ;
            } else {
                calendar.setMonth(month);
                text_month.setText(month + "");
                calendar.showCalendar();
                show_date.setText(calendar.getYear() + "年" + calendar.getMonth() + "月" + calendar.getDay() + "日");
            }
            System.out.println(calendar.getMonth());
        });
        text_year.setOnAction(event -> {//输入年份
            int year = Integer.parseInt(text_year.getText());
            calendar.setYear(year);
            calendar.showCalendar();
            show_date.setText(calendar.getYear() + "年" + calendar.getMonth() + "月" + calendar.getDay() + "日");
        });
        text_month.setOnAction(event -> {//输入月份
            int month = Integer.parseInt(text_month.getText());
            calendar.setMonth(month);
            calendar.showCalendar();
            show_date.setText(calendar.getYear() + "年" + calendar.getMonth() + "月" + calendar.getDay() + "日");
        });
        save_log.setOnAction(event -> {//保存日志
            notepad note = new notepad();
            try {
                note.storeLog(textArea.getText(), calendar.getYear() + "", calendar.getMonth() + "", calendar.getDay() + "");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        delete_log.setOnAction(event -> {//删除日志
            notepad note = new notepad();
            note.deleteLog(calendar.getYear() + "", calendar.getMonth() + "", calendar.getDay() + "");
        });
        read_log.setOnAction(event -> {//读取日志
            notepad note = new notepad();
            try {
                textArea.setText(note.readLog(calendar.getYear() + "", calendar.getMonth() + "", calendar.getDay() + "", textArea));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        calendar.setOnMouseClicked(event -> {//获取鼠标坐标，计算日期
            double x = event.getX();
            double y = event.getY();
            int day = (int) y / 24 * 7 + (int) x / 40 + 1 - 7 - calendar.getFirstDayOfWeek();
            if (day > 0 && day < 32) {
                calendar.setDay(day);
                calendar.showCalendar();
                show_date.setText(calendar.getYear() + "年" + calendar.getMonth() + "月" + calendar.getDay() + "日");
            }
        });

        Scene scene = new Scene(pane, 550, 400);
        primaryStage.setTitle("万年历");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}

