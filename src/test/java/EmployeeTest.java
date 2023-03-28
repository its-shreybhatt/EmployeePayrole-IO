import org.apache.commons.io.FileUtils;
import org.example.Java8WatchService;
import org.junit.Assert;
import org.junit.Test;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EmployeeTest {

    private static String HOME = System.getProperty("user.home");
    private static String PLAY_WITH_NIO = "TempPlayGround";

    @Test
    public void givenPathWhenTested_ShouldPassTheTest() throws IOException {
        Path homePath = Paths.get(HOME);
        Assert.assertTrue(Files.exists(homePath));

        Path playPath = Paths.get(HOME + "/" + PLAY_WITH_NIO);
        if (Files.exists(playPath)) FileUtils.deleteQuietly(playPath.toFile());
        Assert.assertTrue(Files.notExists(playPath));


        Files.createDirectory(playPath);
        Assert.assertTrue(Files.exists(playPath));


        IntStream.range(1, 10).forEach(cntr -> {
            Path tempfile = Paths.get(playPath + "/" + cntr);
            Assert.assertTrue(Files.notExists(tempfile));
            try {
                Files.createFile(tempfile);
            } catch (IOException e) {
            }
            Assert.assertTrue(Files.exists(tempfile));
        });

        Files.list(playPath).filter(Files::isRegularFile).forEach(System.out::println);
        Files.newDirectoryStream(playPath).forEach(System.out::println);
        Files.newDirectoryStream(playPath, path -> path.toFile().isFile() &&
                path.toString().startsWith("temp")).forEach(System.out::println);
    }

    @Test
    public void givenADirectory_WhenWatchedListsAllTheActivities()throws IOException {
        Path dir= Paths.get(HOME+"/"+PLAY_WITH_NIO);
        Files.list(dir).filter(Files::isRegularFile).forEach(System.out::println);
        new Java8WatchService(dir).processEvents();
    }
}
