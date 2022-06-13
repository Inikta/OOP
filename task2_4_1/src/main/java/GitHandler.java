import configs.GroupConfig;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GitHandler {
    private static final String gitsFolder = "..\\repos";

    /*public static String cloneRepo(GroupConfig groupConfig, String nickname) throws GitAPIException, IOException {
        String uri = groupConfig.getByNickname(nickname).getGitAddress();
        String directory = makeDirectory(groupConfig.getName(), nickname);
        Git.cloneRepository()
                .setURI(uri)
                .setDirectory(new File(directory))
                .call();

        return directory;
    }

    private static String makeDirectory(String groupName, String nickname) throws IOException {
        if (!Files.isDirectory(Path.of(gitsFolder + "\\" + groupName + "\\" + nickname).toAbsolutePath().normalize())) {
            Files.createDirectories(Path.of(gitsFolder + "\\" + groupName + "\\" + nickname).toAbsolutePath().normalize());
        }

        return gitsFolder + "\\" + groupName + "\\" + nickname;
    }*/
}
