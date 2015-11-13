package com.example.kj.dibbits;

import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

public class Dibbit {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mIsDone;

    public Dibbit() {
        // Makes new Dibbit with random ID and empty date
        mId = UUID.randomUUID();
        mDate = new Date();

    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public boolean isDone() {
        return mIsDone;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public void setDone(boolean isDone) {
        mIsDone = isDone;
    }

    static String formatDate(Date date) {
        return DateFormat.getDateInstance().format(date);
    }
}