package ru.nsu.nikita;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class, which:
 * - extracts <i>Notes</i> from Json file(s);
 * - determines path to Json files with notes;
 * - operates with notes.
 */

public class Notebook {
    private final ArrayList<Note> book;
    private final String path;
    private final String extension = ".json";

    /**
     * Notebook constructor. Extracts and sorts notes in order of creation.
     * @param path place, where to search for Json files with notes
     * @throws IOException throws <i>IOException</i>, if path is incorrect
     */
    public Notebook(String path) throws IOException {
        this.path = path + "/";
        this.book = searchNotes();
        Comparator<Note> comparator = Comparator.comparing(noteObj -> noteObj.getDateCreation().getTime());
        book.sort(comparator);
    }

    /**
     * Extract notes from Json files located by path.
     * <b>Jackson</b> uses old <i>File</i> class. That is why more relevant <i>Path</i> and <i>Files</i> are not used.
     * @return ArrayList of notes
     * @throws IOException throws if a low-level IO problem of mapper.readValue occurs
     */

    private ArrayList<Note> searchNotes() throws IOException {      //в один джейсон файл
        File[] jsonFiles = new File(path).listFiles();
        ArrayList<Note> notes = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        if (jsonFiles != null) {
            for (File file : jsonFiles) {
                notes.add(mapper.readValue(file, Note.class));
            }
            return notes;
        }
        else {
            return new ArrayList<>();
        }
    }

    /**
     * Add new note. Rewrites existing note with the specified name.
     * @param name name of the note
     * @param content content of the note
     * @throws IOException throws if during writing to Json some IO problem occurs
     */

    public void addNote(String name, String content) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Note note = new Note(name, content);
        try {
            mapper.writeValue(new File(path + name + extension), note);
        } catch (IOException excIo) {
            throw new IOException();
        }
        book.add(note);
    }

    /**
     * Remove note by its name. Removes <i>book</i> ArrayList element and deletes Json file with the same name.
     * @param name name of the note and its Json file
     * @throws FileNotFoundException throws, if file with specified <i>name</i> was not found
     */
    public void removeNote(String name) throws FileNotFoundException {
        File jsonFile = new File(path + name + extension);
        if (jsonFile.exists()) {
            jsonFile.delete();
            while (book.iterator().hasNext()) {
                Note note = book.iterator().next();
                if (note.name.equals(name)) {
                    book.remove(note);
                    break;
                }
            }
        } else {
            throw new FileNotFoundException();
        }
    }

    /**
     * Get all notes from <i>book</i> ArrayList.
     * @return returns ArrayList of notes
     */
    public ArrayList<Note> getAll() {
        return book;
    }

    /**
     * Get all notes from <i>book</i> ArrayList,
     * which were created in specified time period (stream filter) and have specified keywords in <i>content</i> (for loop with stream).
     * @param timeStr1 lesser border of time period
     * @param timeStr2 higher border of time period
     * @param keywords words to search in <i>content</i> of notes from period
     * @return found notes ArrayList
     * @throws ParseException throws, if time period borders were specified incorrectly
     */

    public ArrayList<Note> getByParameters(String timeStr1, String timeStr2, ArrayList<String> keywords) throws ParseException {
        ArrayList<Note> result = new ArrayList<>();
        Stream<Note> stream = book.stream();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss", Locale.ENGLISH);
        Date time1 = dateFormatter.parse(timeStr1);
        Date time2 = dateFormatter.parse(timeStr2);

        ArrayList<Note> filtered = (ArrayList<Note>) stream
                .filter(note -> (note.getDateCreation().getTime() >= time1.getTime() &&
                        note.getDateCreation().getTime() <= time2.getTime()))
                .collect(Collectors.toList());

        if (keywords != null) {
            for (Note note : filtered) {
                if (keywords.stream().allMatch(key -> note.getContent().regionMatches(0, key, 0, key.length()))) {
                    result.add(note);
                }
            }
        } else {
            result = filtered;
        }
        return result;
    }
}