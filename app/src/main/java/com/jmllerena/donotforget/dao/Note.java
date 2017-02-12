package com.jmllerena.donotforget.dao;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.Date;
import java.util.List;

/**
 * This Note class represent a Note inserted in the application. Notes can be BLOCs or LIST of items.
 */
public class Note extends SugarRecord {

    public enum TypeNote {
        BLOC, LIST
    }

    private String description;
    private Date date_pub;
    private TypeNote type;
    private String text;
    private Date date_concluded;

    public Note() {
    }

    /**
     * Constructor for Notes
     * @param description decription of the note
     * @param date_pub the date when the note was published
     * @param type the type of note (BLOC or LIST)
     */
    public Note(String description, Date date_pub, TypeNote type) {
        this.description = description;
        this.date_pub = date_pub;
        this.type = type;
    }

    /**
     * Constructor for Notes with all parameters
     * @param description decription of the note
     * @param date_pub the date when the note was published
     * @param type the type of note (BLOC or LIST)
     * @param text text for BLOC type
     * @param date_concluded the date when the LIST Note was concluded
     */
    public Note(String description, Date date_pub, TypeNote type, String text, Date date_concluded) {
        this.description = description;
        this.date_pub = date_pub;
        this.type = type;
        this.text = text;
        this.date_concluded = date_concluded;
    }

    // Get all books of this author
    List<Item> getItems() {
        return Item.find(Item.class, "note = ?", this.getId().toString());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_pub() {
        return date_pub;
    }

    public void setDate_pub(Date date_pub) {
        this.date_pub = date_pub;
    }

    public TypeNote getType() {
        return type;
    }

    public void setType(TypeNote type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate_concluded() {
        return date_concluded;
    }

    public void setDate_concluded(Date date_concluded) {
        this.date_concluded = date_concluded;
    }

    @Override
    public String toString() {
        if (type == TypeNote.BLOC) {
            return "BLOC: " + description;
        }
        else {
            return "LIST: " + description;
        }
    }
}
