package com.jmllerena.donotforget.dao;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by José Manuel on 11/02/2017.
 */

public class Item extends SugarRecord {
    String text;
    boolean concluded;
    Date date_concluded;

    Note note_owner;

    public Item() {
    }

    public Item(String text, boolean concluded, Date date_concluded, Note note_owner) {
        this.text = text;
        this.concluded = concluded;
        this.date_concluded = date_concluded;
        this.note_owner = note_owner;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isConcluded() {
        return concluded;
    }

    public void setConcluded(boolean concluded) {
        this.concluded = concluded;
    }

    public Date getDate_concluded() {
        return date_concluded;
    }

    public void setDate_concluded(Date date_concluded) {
        this.date_concluded = date_concluded;
    }

    public Note getNote_owner() {
        return note_owner;
    }

    public void setNote_owner(Note note_owner) {
        this.note_owner = note_owner;
    }
}
