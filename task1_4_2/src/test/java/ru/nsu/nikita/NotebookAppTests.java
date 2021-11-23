package ru.nsu.nikita;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class NotebookAppTests {

    @BeforeEach
    public void clean() {
        File directory = new File("../task1_4_2/notes");
        File[] toDelete = directory.listFiles();
        for (File file : toDelete) {
            file.delete();
        }
    }

    @Test
    public void addTest() throws IOException {
        NotebookApp notebookApp = new NotebookApp("notes");
        String[] args = {"-add", "Remove Test", "Removable note content"};
        Note referenceNote = new Note("Remove Test", "Removable note content");
        referenceNote.setDateCreation(new Date());

        NotebookApp.main(args);
        ArrayList<Note> testNotes = notebookApp.getNotebook().getAll();

        int ind = 0;
        for (Note note : testNotes) {
            if (note.name.equals("Remove Test")) {
                break;
            }
            ind++;
        }

        Assertions.assertTrue(new File("notes/Remove Test.json").exists());
        Assertions.assertEquals(referenceNote.getContent(), testNotes.get(ind).getContent());
    }

    @Test
    public void removeTest() throws IOException {
        NotebookApp notebookApp = new NotebookApp("notes");
        String[] args1 = {"-add", "Remove Test", "Removable note content"};
        String[] args2 = {"-rm", "Remove Test"};

        notebookApp.main(args1);
        notebookApp.main(args2);

        ArrayList<Note> testNotes = notebookApp.getNotebook().getAll();

        int ind = 0;
        for (Note note : testNotes) {
            if (note.name.equals("Remove Test")) {
                break;
            }
            ind++;
        }

        Assertions.assertFalse(new File("notes/Remove Test.json").exists());
    }

    @Test
    public void showAllTest() throws IOException {
        NotebookApp notebookApp = new NotebookApp("notes");
        String[][] args = {{"-add", "Test 1", "1 note content"},
                {"-add", "Test 2", "2 note content"},
                {"-add", "Test 3", "3 note content"},
                {"-show"}};

        String[] referenceContents = {"1 note content", "2 note content", "3 note content"};

        notebookApp.main(args[0]);
        notebookApp.main(args[1]);
        notebookApp.main(args[2]);
        notebookApp.main(args[3]);

        ArrayList<Note> testNotes = notebookApp.getNotebook().getAll();
        ArrayList<String> testContents = new ArrayList<>();
        for (Note note : testNotes) {
            testContents.add(note.getContent());
        }

        Assertions.assertTrue(new File(args[0][1]).exists());
        Assertions.assertTrue(new File(args[1][1]).exists());
        Assertions.assertTrue(new File(args[2][1]).exists());
        Assertions.assertArrayEquals(referenceContents, testContents.toArray());
    }

    @Test
    public void showTimePeriodTest() {

    }

    @Test
    public void showTimePeriodAndKeywordsTest() {

    }
}
