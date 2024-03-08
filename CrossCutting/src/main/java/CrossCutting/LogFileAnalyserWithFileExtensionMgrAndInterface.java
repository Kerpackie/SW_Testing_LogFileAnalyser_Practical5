package CrossCutting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Calendar;
import javax.swing.filechooser.FileSystemView;

public class LogFileAnalyserWithFileExtensionMgrAndInterface
{

    private Boolean lastLogFileStatus=null;
    public IExtensionMgr _extensionMgr;

    public LogFileAnalyserWithFileExtensionMgrAndInterface(IExtensionMgr extensionMgr)
    {
        _extensionMgr = extensionMgr;
    }

    public Boolean AnalyseLogFile(String fileName)
    {
        // First piece of business logic is to check that the Logfile has a valid extension. Unit tests need to test this.
        if(!fileName.endsWith(".log"))
        {
            return false;  //LogFile extension is invalid
        }

        String status = _extensionMgr.ReadLogFileToGetStatus(fileName);


        // The status is read from the logFile. Next is the business logic which is to be tested by unit tests
        if(status=="Normal Log") // This is business logic which is to be tested by unit tests
        {
            lastLogFileStatus=true;
            return true;
        } else if (status=="Exception Log") {
            lastLogFileStatus=true;
            return true;

        } else if(status=="Monitored system crashed") // This is business logic which is to be tested by unit tests
        {
            Calendar cal = Calendar.getInstance();
            if (cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)
            {
                lastLogFileStatus=true;
                return true;
            }

            lastLogFileStatus=false;
            return false;
        }
        else
            return false;  //Anything else in the Log file indicates a problem
    }
}
