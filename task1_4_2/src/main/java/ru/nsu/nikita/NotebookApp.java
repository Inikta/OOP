package ru.nsu.nikita;

import picocli.CommandLine;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * App, which takes commands and its arguments on input
 * and prints some result, if it was returned.
 */

@CommandLine.Command(name = "notebook")
public class NotebookApp {

    private final Notebook notebook;
    private static String fileName = "default_notes";
    private String path;

    private NotebookApp notebookApp;
    /**
     * Constructor with <i>path</i> designation.
     * Throws FileNotFoundException, if directory was not created.
     *
     * @throws IOException if <i>path</i> is incorrect (follows from <i>notebook</i> initialization)
     */
    public NotebookApp(String fileName) throws IOException {
        String separator = "/";
        this.fileName = fileName;
        String extension = ".json";
        this.path = ".." + separator + "task1_4_2" + separator + this.fileName + extension;

        this.notebook = new Notebook(path);
    }

    /**
     * App entrance method. Receives arguments from command line and deals with them.
     *
     * @param args arguments specified in command line
     * @throws IOException throws, if path is incorrect (following from NotebookApp creation)
     */
    public static void main(String[] args) throws IOException {
        CommandLine notebookCommandLine = new CommandLine(new NotebookApp(fileName));
        notebookCommandLine.execute(args);
    }

    /**
     * Subcommand "-add" for adding new note or rewriting old one with the same <i>name</i>.
     *
     * @param name    name of the note
     * @param content contents of the note
     * @throws IOException throws if during writing to Json some IO problem occurs
     */

    @CommandLine.Command(name = "-add")
    private void addNote(@CommandLine.Parameters(paramLabel = "NAME") String name,
                         @CommandLine.Parameters(paramLabel = "CONTENT") String content) throws IOException {
        notebook.addNote(name, content);
    }

    /**
     * Subcommand "-rm" for removing notes and deleting according Json files.
     *
     * @param name name of the note to remove
     * @throws IOException throws if file with specified name was not found
     */

    @CommandLine.Command(name = "-rm")
    private void removeNote(@CommandLine.Parameters(paramLabel = "NAME") String name) throws IOException {
        notebook.removeNote(name);
    }

    /**
     * Subcommand "-show" for printing all the notes, if arguments are not specified.
     * If arguments were specified correctly, then prints all the notes created in specified time period
     * with specified keywords in content (may not be specified).
     *
     * @param time1    lesser border of time period
     * @param time2    higher border of time period
     * @param keywords words to search in content of notes from period (may not be specified)
     * @throws ParseException throws, if time period borders were specified incorrectly (follows from notebook.getByParameters)
     */
    @CommandLine.Command(name = "-show")
    private void showNote(@CommandLine.Parameters(paramLabel = "TIME1", arity = "0..1") String time1,
                          @CommandLine.Parameters(paramLabel = "TIME2", arity = "0..1") String time2,
                          @CommandLine.Parameters(paramLabel = "KEYWORDS", arity = "0..*") ArrayList<String> keywords) throws ParseException {

        if (time1 == null && time2 == null && (keywords == null || keywords.isEmpty())) {
            List<Note> allNotes = notebook.getAll();
            for (Note note : allNotes) {
                System.out.println(note.toString() + "-------------------------------\n");
            }
        } else if (time1 != null && time2 != null) {
            List<Note> notesInPeriod = notebook.getByParameters(time1, time2, keywords);
            for (Note note : notesInPeriod) {
                System.out.println(note.toString() + "-------------------------------\n");
            }
        }
    }

    @CommandLine.Command(name = "-help")
    private void help() {
        System.out.println("Notebook commands:\n");
        System.out.println("-add [NAME] [CONTENT]              \t\t adds new note named by NAME and having content CONTENT\n");
        System.out.println("-rm [NAME]                         \t\t removes note named by NAME\n");
        System.out.println("""
                -show [TIME1] [TIME2] [KEYWORDS...]\t\t shows all notes created in time period between TIME1 and TIME2,
                                                   \t\t and which content contains words from KEYWORDS.
                                                   \t\t If no arguments were passed shows all existing notes.
                                                   \t\t If no KEYWORDS were passed, shows all notes in passed time period.
                """);
    }

    /**
     * Get raw Notebook.
     *
     * @return returns Notebook object
     */
    public List<Note> getNotebook() {
        return this.notebook.getAll();
    }

    /***
     * Returns currently used path of json file.
     * @return path string
     */

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /***
     * Cleans notebook from notes and deletes json file related to it.
     */

    public void clean() throws IOException {
        notebook.clean();
    }
}
