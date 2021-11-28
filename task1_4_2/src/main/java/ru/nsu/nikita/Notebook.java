package ru.nsu.nikita;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class, which:
 * - extracts <i>Notes</i> from Json file(s);
 * - determines path to Json files with notes;
 * - operates with notes.
 */

public class Notebook {
    private List<Note> book;
    private final ObjectMapper jsonMapper = new ObjectMapper();
    private File jsonFile;
    private final String path;

    /**
     * Notebook constructor. Extracts and sorts notes in order of creation.
     *
     * @param path place, where to search for Json files with notes
     * @throws IOException throws <i>IOException</i>, if path is incorrect
     */
    public Notebook(String path) throws IOException {
        this.path = path;
        this.jsonFile = new File(path);
        if (!jsonFile.exists()) {
            jsonFile.createNewFile();
        }
        this.book = searchNotes();
        Comparator<Note> comparator = Comparator.comparing(noteObj -> noteObj.getDateCreation().getTime());
        this.book.sort(comparator);
    }

    /**
     * Extract notes from Json files located by path.
     * <b>Jackson</b> uses old <i>File</i> class. That is why more relevant <i>Path</i> and <i>Files</i> are not used.
     *
     * @return ArrayList of notes
     * @throws IOException throws if a low-level IO problem of mapper.readValue occurs
     */

    private List<Note> searchNotes() throws IOException {      //в один джейсон файл
        List<Note> result;
        try {
            result = jsonMapper.readValue(jsonFile, new TypeReference<>() {
            });
        } catch (MismatchedInputException exception) {
            result = new ArrayList<>();
        }
        return result;
    }

    /**
     * Add new note. Rewrites existing note with the specified name.
     *
     * @param name    name of the note
     * @param content content of the note
     * @throws IOException throws if during writing to Json some IO problem occurs
     */

    public void addNote(String name, String content) throws IOException {
        Note newNote = new Note(name, content);
        this.book.removeIf(note -> note.getName().equals(name));
        this.book.add(newNote);
        try {
            jsonMapper.writeValue(jsonFile, book);
        } catch (IOException excIo) {
            throw new IOException("Note serialization error during ADDING. Path value may be incorrect.\n");
        }

    }

    /**
     * Remove note by its name. Removes <i>book</i> ArrayList element and deletes Json file with the same name.
     *
     * @param name name of the note and its Json file
     * @throws FileNotFoundException throws, if file with specified <i>name</i> was not found
     */
    public void removeNote(String name) throws IOException {
        book.removeIf(note -> note.getName().equals(name));
        try {
            jsonMapper.writeValue(jsonFile, book);
        } catch (IOException excIo) {
            throw new IOException("Note serialization error during REMOVING. Path value may be incorrect.\n");
        }
    }

    /**
     * Get all notes from <i>book</i> ArrayList.
     *
     * @return returns ArrayList of notes
     */
    public List<Note> getAll() {
        return this.book;
    }

    /**
     * Get all notes from <i>book</i> ArrayList,
     * which were created in specified time period (stream filter) and have specified keywords in <i>content</i> (for loop with stream).
     *
     * @param timeStr1 lesser border of time period
     * @param timeStr2 higher border of time period
     * @param keywords words to search in <i>content</i> of notes from period
     * @return found notes ArrayList
     * @throws ParseException throws, if time period borders were specified incorrectly
     */

    public List<Note> getByParameters(String timeStr1, String timeStr2, ArrayList<String> keywords) throws ParseException {
        List<Note> result;
        //SimpleDateFormat dateFormat = Note.getDateFormat();
        Date time1 = Note.getDateFormatter().parse(timeStr1);
        Date time2 = Note.getDateFormatter().parse(timeStr2);

        Stream<Note> filteredByTime = book
                .stream()
                .filter(note -> (note.getDateCreation().getTime() >= time1.getTime() &&
                        note.getDateCreation().getTime() <= time2.getTime()));

        if (keywords != null) {
            result = filteredByTime
                    .filter(note -> keywords
                            .stream()
                            .allMatch(key -> note.getContent()
                                    .contains(key)))
                    .collect(Collectors.toList());
        } else {
            result = filteredByTime
                    .collect(Collectors.toList());
        }
        return result;
    }

    public void clean() throws IOException {
        book = new ArrayList<>();
        if (!jsonFile.exists()) {
            jsonFile = new File(path);
        }
        else {
            jsonMapper.writeValue(jsonFile, book);
        }
    }
}