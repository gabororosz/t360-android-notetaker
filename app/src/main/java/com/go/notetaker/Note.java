package com.go.notetaker;

import java.util.Date;

/**
 * Created by gabororosz on 15/03/15.
 */
public class Note {
    private String mTitle;
    private String mNote;
    private Date mDate;

    public Note(final String title, final String note, final Date date) {
        mTitle = title;
        mNote = note;
        mDate = date;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getNote() {
        return mNote;
    }

    public Date getDate() {
        return mDate;
    }
}