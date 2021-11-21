package ru.nsu.nikita;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class NotebookAppTests {

    @Test
    public void addTest() throws IOException {
        NotebookApp notebookApp = new NotebookApp("notes");
        String[] args = {"-add", "Testing Note 1", "Interesting content #1 aaa"};
        Note referenceNote = new Note("Testing Note 1","Interesting content #1 aaa");
        referenceNote.setDateCreation(new Date());

        NotebookApp.main(args);
        ArrayList<Note> testNotes = notebookApp.getNotebook().getAll();

        Assertions.assertEquals(referenceNote.getName(),testNotes.get(0).getName());
        Assertions.assertEquals(referenceNote.getContent(),testNotes.get(0).getContent());
        Assertions.assertEquals(referenceNote.getDateCreation(),testNotes.get(0).getDateCreation());

    }

    @Test
    public void emptyAddTest() {

    }

    @Test
    public void directoryNotFoundTest() {

    }

    @Test
    public void removeTest() {

    }

    @Test
    public void emptyRemoveTest() {

    }

    @Test
    public void showAllTest() {

    }

    @Test
    public void showTimePeriodTest() {

    }

    @Test
    public void showTimePeriodAndKeywordsTest() {

    }
}
