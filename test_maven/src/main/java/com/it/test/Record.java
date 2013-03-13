package com.it.test;

import java.util.Date;
import java.util.List;

public class Record {
    private int id;
    private Date postDate;
    private String message;

    public int getId() {
        return id;
    }

    public Date getPostDate() {
        return postDate;
    }

    public String getMessage() {
        return message;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
