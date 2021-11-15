package ru.nsu.nikita;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Note {
    protected SimpleDateFormat dateCreation;
    protected String content;
    public String name;

    public Note(String name, String text) {
        this.content = text;
        this.name = name;
        dateCreation = new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm:ss", new Locale("ru").getDefault());
    }

    @Override
    public String toString () {
        return "Name: " + name + "\n" +
                "Created: " + dateCreation.toString() + "\n" +
                "Content: \n" + content + "\n";
    }

    public SimpleDateFormat getDateCreation() {
        return dateCreation;
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }
}
