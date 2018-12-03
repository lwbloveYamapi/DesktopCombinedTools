package com.edu.cal;

import javafx.scene.control.TextArea;

import javax.swing.*;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class notepad {
    public void storeLog(String text, String year, String month, String day) throws Exception {
        String str1 = "";
        String str2 = "";
        if (Integer.parseInt(month) < 10)
            str1 = "0" + month;
        else
            str1 = month;
        if (Integer.parseInt(day) < 10)
            str2 = "0" + day;
        else
            str2 = day;
        String str = year + str1 + str2;
        File path = new File("/");
        File file = new File(path, str + ".text");
        if (!(file.exists())) {
            file.createNewFile();
        }
        PrintWriter output = new PrintWriter(file);
        output.print(text);
        output.close();
//        JOptionPane.showMessageDialog(null, year + "年" + "日" + "日志已保存在/" + str + ".text文件中");
    }

    public String readLog(String year, String month, String day, TextArea jtalog) throws Exception {
        try {
            String str1 = "";
            String str2 = "";
            if (Integer.parseInt(month) < 10)
                str1 = "0" + month;
            else
                str1 = month;
            if (Integer.parseInt(day) < 10)
                str2 = "0" + day;
            else
                str2 = day;
            String str = year + str1 + str2;
            StringBuilder text = new StringBuilder();
            java.io.File path = new java.io.File("/");
            java.io.File file = new java.io.File(path, str + ".text");
            if (!(file.exists())) {
                throw new Exception();
            }
            Scanner input = new Scanner(file);
            while (input.hasNext()) {
                text.append(input.nextLine());
            }
            input.close();
            return text.toString();
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "text already dose not exist !!!");
            return jtalog.getText();
        }
    }

    public void deleteLog(String year, String month, String day) {
        String str1 = "";
        String str2 = "";
        if (Integer.parseInt(month) < 10)
            str1 = "0" + month;
        else
            str1 = month;
        if (Integer.parseInt(day) < 10)
            str2 = "0" + day;
        else
            str2 = day;
        String str = year + str1 + str2;
        java.io.File path = new java.io.File("/");
        java.io.File file = new java.io.File(path, str + ".text");
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
//                JOptionPane.showMessageDialog(null, "删除成功！");
            } else {
//                JOptionPane.showMessageDialog(null, "删除失败！");
            }
        } else {
//            JOptionPane.showMessageDialog(null, "文件不存在！");
        }
    }
}
