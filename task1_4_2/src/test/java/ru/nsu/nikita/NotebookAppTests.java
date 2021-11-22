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
        String[] args = {"-add", "Remove Test", "Removable note content"};
        Note referenceNote = new Note("Remove Test", "Removable note content");
        referenceNote.setDateCreation(new Date());

        NotebookApp.main(args);
        ArrayList<Note> testNotes = notebookApp.getNotebook().getAll();

        Assertions.assertEquals(referenceNote.getName(), testNotes.get(0).getName());
        Assertions.assertEquals(referenceNote.getContent(), testNotes.get(0).getContent());
    }

    @Test
    public void emptyAddTest() throws IOException {
        NotebookApp notebookApp = new NotebookApp("notes");
        String[] args = {"-add", "", ""};
        Note referenceNote = new Note("", "");
        referenceNote.setDateCreation(new Date());

        NotebookApp.main(args);
        ArrayList<Note> testNotes = notebookApp.getNotebook().getAll();

        Assertions.assertEquals(referenceNote.getName(), testNotes.get(1).getName());
        Assertions.assertEquals(referenceNote.getContent(), testNotes.get(1).getContent());
    }

    /*@Test
    public void directoryNotFoundTest() throws IOException {
        NotebookApp notebookApp = new NotebookApp("notes2");
        String[] args = {"-add", "Test Note 1", "Content"};
        Note referenceNote = new Note("Test Note 1", "Content");
        referenceNote.setDateCreation(new Date());

        notebookApp.main(args);
        ArrayList<Note> testNotes = notebookApp.getNotebook().getAll();

        Assertions.assertEquals(referenceNote.getName(),testNotes.get(0).getName());
        Assertions.assertEquals(referenceNote.getContent(),testNotes.get(0).getContent());
    }*/

    @Test
    public void removeTest() throws IOException {
        NotebookApp notebookApp = new NotebookApp("notes");
        String[] args1 = {"-add", "Remove Test", "Removable note content"};
        String[] args2 = {"-rm", "Remove Test"};
        Note referenceNote = new Note("Test Note 1", "Content");
        referenceNote.setDateCreation(new Date());

        notebookApp.main(args1);
        notebookApp.main(args2);

        ArrayList<Note> testNotes = notebookApp.getNotebook().getAll();

        Assertions.assertEquals(referenceNote.getName(), testNotes.get(1).getName());
        Assertions.assertEquals(referenceNote.getContent(), testNotes.get(1).getContent());
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
