import groovy.lang.GroovyObjectSupport;

public class Student extends GroovyObjectSupport implements Cloneable {

    private String nickname;

    private int id;

    private final String[] fullName = new String[3];

    private String repo;
    private String mainBranch;
    private StudentStats studentStats;

    public Student(int id, String nickname, String initials, String repo, String mainBranch) {
        this.nickname = nickname;
        this.repo = repo;
        this.mainBranch = mainBranch;
        this.id = id;
        this.studentStats = new StudentStats();
        parseInitials(initials);
    }

    private void parseInitials(String initials) {
        String[] inits = initials.trim().split(" ");
        for (int i = 0; i < inits.length; i++) {
            fullName[i] = inits[i];
        }
    }

    public String getMainBranch() {
        return mainBranch;
    }

    public StudentStats getStudentStats() {
        return studentStats;
    }

    public void setStudentStats(StudentStats studentStats) {
        this.studentStats = studentStats;
    }

    public void setName(String name) {
        fullName[0] = name;
    }

    public void setSurname(String name) {
        fullName[1] = name;
    }

    public void setPatronymic(String name) {
        fullName[2] = name;
    }

    public String[] getFullName() {
        return fullName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRepo() {
        return repo;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Student clone() {
        try {
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return (Student) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
