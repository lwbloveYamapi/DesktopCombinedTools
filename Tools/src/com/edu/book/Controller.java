package com.edu.book;

import com.alibaba.fastjson.JSON;
import com.edu.entity.BookInfoEntiry;
import com.edu.entity.BookListEntiry;
import com.edu.entity.SimpleHttpUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.List;

public class Controller {

    private static String DOUBAN_BOOKINFO = "https://api.douban.com/v2/book/search?start=0&count=100&q=";

    @FXML
    private TextField edtBookName;
    @FXML
    private Label txtBookInfo;


    public void OnclickQuery(ActionEvent actionEvent) {
        String bookName = edtBookName.getText();

        txtBookInfo.setText(getDoubanBookInfo(bookName));
    }

    private String getDoubanBookInfo(String bookName){
        String result = "";
        try {
            String bookJson = SimpleHttpUtils.get(DOUBAN_BOOKINFO + bookName);
            BookListEntiry bookListEntiry = JSON.parseObject(bookJson, BookListEntiry.class);
            List<BookInfoEntiry> books = bookListEntiry.getBooks();
            for (int i = 0; i < 20; i++){
                BookInfoEntiry info = books.get(i);
                if (info != null){
                    result += "书名" + info.getTitle() + "\t" + "作者" + info.getAuthor().get(0) + "\t" + "书号" + info.getIsbn13() + "\n";
                } else {
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
