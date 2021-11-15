package ru.nsu.nikita;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Notebook {
    private final ArrayList<Note> book;
    private final String path;
    private final String extension = ".json";

    public Notebook(String path) throws IOException {
        this.path = path + File.pathSeparator;
        this.book = searchNotes();
    }

    private ArrayList<Note> searchNotes() throws IOException {
        File[] jsonFiles = new File(path).listFiles();
        ArrayList<Note> notes = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        assert jsonFiles != null;
        for (File file : jsonFiles) {
            notes.add(mapper.readValue(file, Note.class));
        }

        return notes;
    }

    public void addNote(String[] parameters) throws IOException {
        String name = parameters[0];
        String content = parameters[1];
        ObjectMapper mapper = new ObjectMapper();
        Note note = new Note(name, content);
        try {
            mapper.writeValue(new File(path + name + extension), note);
        } catch (IOException excIo) {
            throw new IOException();
        }
        book.add(note);
        Comparator<Note> comparator = Comparator.comparing(noteObj -> noteObj.dateCreation.toString());
        book.sort(comparator);
    }

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

    public ArrayList<String> showAll() throws IOException {
        ArrayList<String> allNotes = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        for (Note note : book) {
            File jsonNote = new File(path + note.name + extension);
            Note currentNote;
            try {
                currentNote = mapper.readValue(jsonNote, Note.class);
            } catch (IOException ioExc) {
                throw new IOException("Object structure does not fit a json file structure!");
            }
            allNotes.add(currentNote.toString());
        }
        return allNotes;
    }

    public String showByParameters(SimpleDateFormat[] times, ArrayList<String> keywords) {
        StringBuilder result = new StringBuilder();
        Stream<Note> stream = book.stream();
        ArrayList<Note> filtered = (ArrayList<Note>) stream.filter(note -> {
                    boolean isCompared = false;
                    try {
                        isCompared = new SimpleDateFormat().parse(note.dateCreation.toString()).getTime() >=
                                new SimpleDateFormat().parse(times[0].toString()).getTime();
                    } catch (ParseException parseExc) {
                        parseExc.printStackTrace();
                    }
                    return isCompared;
                }).
                collect(Collectors.toList());

        for (Note note : filtered) {
            if (keywords.stream().allMatch(key -> note.content.regionMatches(0, key, 0, key.length()))) {
                result.append(note.toString()).append("--------------\n");
            }
        }
        return result.toString();
    }
}