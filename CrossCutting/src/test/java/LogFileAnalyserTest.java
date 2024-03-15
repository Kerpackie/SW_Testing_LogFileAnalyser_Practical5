import CrossCutting.LogFileAnalyserWithFileExtensionMgrAndInterface;
import org.junit.jupiter.api.Test;

import javax.swing.filechooser.FileSystemView;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogFileAnalyserTest {

    @Test
    public void AnalyseLogFile_ValidNormalLog_ReturnsTrue() {
        System.out.println("AnalyseLogFile_ValidNormalLog_ReturnsTrue");

        // Arrange
        StubExtensionMgr stubExtensionMgr = new StubExtensionMgr();
        stubExtensionMgr.setLogFileStatus("Normal Log");

        StubCalendarProvider stubCalendarProvider = new StubCalendarProvider();

        LogFileAnalyserWithFileExtensionMgrAndInterface instance = new LogFileAnalyserWithFileExtensionMgrAndInterface(stubExtensionMgr, stubCalendarProvider);
        Boolean expResult = true;
        // Act
        Boolean result = instance.AnalyseLogFile("LogFile.log");
        // Assert
        assertEquals(expResult, result);
    }


    @Test
    public void AnalyseLogFile_ValidCrashLog_ReturnsFalse() throws IOException {
        // Arrange
        System.out.println("AnalyseLogFile_ValidCrashLog_ReturnsFalse");

        StubCalendarProvider stubCalendarProvider = new StubCalendarProvider();

        String fileName = "CrashLogFile.log"; // Change file name
        StubExtensionMgr stubExtensionMgr = new StubExtensionMgr();
        stubExtensionMgr.setLogFileStatus("Monitored system crashed");
        LogFileAnalyserWithFileExtensionMgrAndInterface instance = new LogFileAnalyserWithFileExtensionMgrAndInterface(stubExtensionMgr, null);
        Boolean expResult = false;
        createLogFileWithContent(fileName, "Exception");
        // Act
        Boolean result = instance.AnalyseLogFile(fileName);
        // Assert
        assertEquals(expResult, result);
    }

    @Test
    public void AnalyseLogFile_InvalidExtension_ReturnsFalse() {
        // Arrange
        System.out.println("AnalyseLogFile_InvalidExtension_ReturnsFalse");
        String fileName = "InvalidExtensionLogFile.txt"; // Change file name
        StubExtensionMgr stubExtensionMgr = new StubExtensionMgr();
        LogFileAnalyserWithFileExtensionMgrAndInterface instance = new LogFileAnalyserWithFileExtensionMgrAndInterface(stubExtensionMgr, null);
        Boolean expResult = false;
        // Act
        Boolean result = instance.AnalyseLogFile(fileName);
        // Assert
        assertEquals(expResult, result);
    }

    @Test
    public void AnalyseLogFile_NoExceptionInContent_ReturnsTrue() throws IOException {
        // Arrange
        System.out.println("AnalyseLogFile_NoExceptionInContent_ReturnsFalse");

        String fileName = "NoExceptionLogFile.log";

        StubExtensionMgr stubExtensionMgr = new StubExtensionMgr();
        stubExtensionMgr.setLogFileStatus("Exception Log");
        LogFileAnalyserWithFileExtensionMgrAndInterface instance = new LogFileAnalyserWithFileExtensionMgrAndInterface(stubExtensionMgr, null);
        Boolean expResult = true;

        createLogFileWithContent(fileName, "This is a log file without any exception.");

        // Act
        Boolean result = instance.AnalyseLogFile(fileName);

        // Assert
        assertEquals(expResult, result);
    }

    @Test
    public void AnalyseLogFile_ValidCrashLogOnWeekend_ReturnsTrue() throws IOException {
        // Arrange
        StubExtensionMgr stubExtensionMgr = new StubExtensionMgr();
        stubExtensionMgr.setLogFileStatus("Monitored system crashed");

        StubCalendarProvider stubCalendarProvider = new StubCalendarProvider();
        stubCalendarProvider.setDayOfWeek(Calendar.SATURDAY);

        LogFileAnalyserWithFileExtensionMgrAndInterface instance = new LogFileAnalyserWithFileExtensionMgrAndInterface(stubExtensionMgr, stubCalendarProvider);
        Boolean expResult = true;
        // Act
        Boolean result = instance.AnalyseLogFile("CrashLogFile.log");
        // Assert
        assertEquals(expResult, result);
    }

    private void createLogFileWithContent(String fileName, String content) throws IOException {
        String path = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "/LogFile/";
        Files.createDirectories(Paths.get(path));
        BufferedWriter writer = new BufferedWriter(new FileWriter(path + fileName));
        writer.write(content);
        writer.close();
    }
}
