import groovy.lang.GroovyObjectSupport;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class TableConfigurator extends GroovyObjectSupport {
    private List<Git> gitList;
    private StudentsGroup studentsGroup;
    private String name;
    private Path reposDir = Path.of("./repositories").toAbsolutePath().normalize();

    public TableConfigurator(StudentsGroup studentsGroup) {
        this.gitList = new ArrayList<>();
        this.studentsGroup = studentsGroup;
        this.name = this.studentsGroup.getName();
    }

    private void createDirectories() throws IOException {
        for (Student student : studentsGroup.getIdMap().values()) {
            Files.createDirectories(reposDir.resolve(Path.of("/" + studentsGroup.getName() + "/" + student.getNickname())));
        }
    }

    private List<Git> assignGits() throws IOException {
        List<Git> gits = new ArrayList<>();
        List<String> branches = new ArrayList<>();
        branches.add("main");
        branches.add("master");
        createDirectories();
        for (Student student : studentsGroup.getIdMap().values()) {
            if (student.getRepo() != null & !Objects.equals(student.getRepo(), "")) {
                try {
                    gits.add(Git.cloneRepository()
                            .setURI(student.getRepo())
                            .setDirectory(reposDir.resolve(Path.of("/" + studentsGroup.getName() + "/" + student.getNickname())).toFile())
                            .setBranchesToClone(branches)
                            .setBranch(student.getMainBranch())
                            .call());
                } catch (GitAPIException gitExc) {
                    gitExc.printStackTrace();
                }
            }
        }

        return gits;
    }

    public void setGit(int id, String repo) {
        studentsGroup.getStudentByID(id).setRepo(repo);
    }

    public void setGit(String name, String repo) {
        studentsGroup.getStudentByName(name).setRepo(repo);
    }

    public List<Git> getGitList() {
        return new ArrayList<>(gitList);
    }

    public StudentsGroup getStudentsGroup() {
        return studentsGroup;
    }

    public void setStudentsGroup(StudentsGroup studentsGroup) {
        this.studentsGroup = studentsGroup;
        setName(this.studentsGroup.getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.studentsGroup.setName(name);
    }
}
