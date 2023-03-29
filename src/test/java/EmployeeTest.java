import org.example.EmployePayroleService;
import org.example.EmployeePayroleData;
import org.example.FileUtils;
import org.example.Java8WatchService;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.example.EmployePayroleService.IOService.FILE_IO;

public class EmployeeTest {

    private static String HOME = System.getProperty("user.home");
    private static String PLAY_WITH_NIO = "TempPlayGround";

    @Test
    public void givenPathWhenTested_ShouldPassTheTest() throws IOException {
        Path homePath = Paths.get(HOME);
        Assert.assertTrue(Files.exists(homePath));

        Path playPath = Paths.get(HOME + "/" + PLAY_WITH_NIO);
        if (Files.exists(playPath)) FileUtils.deleteFiles(playPath.toFile());
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
    public void givenADirectory_WhenWatchedListsAllTheActivities() throws IOException {
        Path dir = Paths.get(HOME + "/" + PLAY_WITH_NIO);
        Files.list(dir).filter(Files::isRegularFile).forEach(System.out::println);
        new Java8WatchService(dir).processEvents();
    }

    @Test
    public void given3Employees_WhenWritten_ShouldStoreInAFile() {
        EmployeePayroleData[]arayOfEmps = {
                new EmployeePayroleData(1, "Shrey", 25000.0),
                new EmployeePayroleData(2, "Kartikeya", 28000.0),
                new EmployeePayroleData(3, "Akshay", 27500.5)};
        EmployePayroleService object = new EmployePayroleService(Arrays.asList(arayOfEmps));
        object.writeEmployeePayrollData(FILE_IO);
        object.printData(FILE_IO);
    }
}
