package ru.nsu.nikita;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class NotebookAppTests {

    public NotebookApp notebookApp;

    @BeforeEach
    public void clean() throws IOException {
        this.notebookApp = new NotebookApp("notes");
        this.notebookApp.clean();
    }

    @Test
    public void addTest() throws IOException {
        String[] args1 = {"-add", "Test 1", "note content 1"};
        String[] args2 = {"-show"};

        notebookApp.main(args1);
        notebookApp.main(args2);

        Assertions.assertTrue(new File(notebookApp.getPath()).exists());
    }

    @Test
    public void removeTest() throws IOException {
        String[] args1 = {"-add", "Remove Test", "Removable note content"};
        String[] args2 = {"-rm", "Remove Test"};

        notebookApp.main(args1);
        notebookApp.main(args2);

        List<Note> testNotes = notebookApp.getNotebook();

        int ind = 0;
        for (Note note : testNotes) {
            if (note.getName().equals("Remove Test")) {
                break;
            }
            ind++;
        }

        Assertions.assertEquals(0, ind);
    }

    @Test
    public void showAllTest() throws IOException {
        String[][] args = {{"-add", "Test 1", "1 note content"},
                {"-add", "Test 2", "2 note content"},
                {"-add", "Test 3", "3 note content"},
                {"-show"}};

        notebookApp.main(args[0]);
        notebookApp.main(args[1]);
        notebookApp.main(args[2]);
        notebookApp.main(args[3]);
    }

    @Test
    public void showTimePeriodTest() throws IOException {
        this.notebookApp = new NotebookApp("time_test");
        String[][] args = {{"-show"},
                {"-show", "29.11.2021 10:39:00", "29.11.2021 10:41:15"}};

        notebookApp.main(args[0]);
        System.out.println("In time period:\n");
        notebookApp.main(args[1]);
    }


    @Test
    public void showTimePeriodAndKeywordsTest() throws IOException {
        this.notebookApp = new NotebookApp("time_key_test");
        String[][] args = {{"-show"},//{"-add", "Test 3", "3 note content"}"",
                {"-show", "29.11.2021 10:33:00", "29.11.2021 10:34:00", "2"}};

        notebookApp.main(args[0]);
        System.out.println("In time period and with keywords:\n");
        notebookApp.main(args[1]);
    }

    @Test
    public void helpTest() throws IOException {
        String[] args = {"-help"};

        notebookApp.main(args);
    }
}
