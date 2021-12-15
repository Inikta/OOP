package ru.nsu.nikita;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Note, which has <i>dateFormat</i> time formatter and 3 fields:
 * - <i>dateCreation</i> - time, when note has been created
 * - <i>name</i> - name of the note, which is used to distinguish it from other notes
 * - <i>content</i> - text content of the note
 */

public class Note implements Cloneable {
    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
    private Date dateCreation;
    private String content;
    private String name;

    /**
     * Default constructor for Jackson usage only
     */
    public Note() {
        this.content = "";
        this.name = "";
        this.dateCreation = new Date();
    }

    /**
     * Standard constructor for usage in program
     *
     * @param name String name of the note
     * @param text String content of the note
     */
    public Note(String name, String text) {
        this.content = text;
        this.name = name;
        this.dateCreation = new Date();
    }

    public static SimpleDateFormat getDateFormatter() {
        return dateFormatter;
    }

    /**
     * Output format
     *
     * @return String formatted output
     */
    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "Created: " + dateFormatter.format(dateCreation) + "\n" +
                "Content: \n" + content + "\n";
    }

    /**
     * Pack of field getters
     */

    public Date getDateCreation() {
        return dateCreation;
    }

    /**
     * Pack of field setters
     */

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Note clone() {
        try {
            return (Note) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
