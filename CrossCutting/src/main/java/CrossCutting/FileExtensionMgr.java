package CrossCutting;

import javax.swing.filechooser.FileSystemView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileExtensionMgr implements IExtensionMgr
{

    private String path = FileSystemView.getFileSystemView().getDefaultDirectory().getPath()+ "/LogFile/";

    @Override
    public String ReadLogFileToGetStatus(String fileName) {
        // Next open the Log file and read the log status
        String readMeText;
        String status = null;
        try
        {
            System.out.println("Reading actual log file on disk --> You should not be seeing this from Unit tests");
            File file = new File(path+fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            readMeText = br.readLine();
            if (readMeText.contains("Exception"))
            {
                status= "Monitored system crashed";  //As the word Exception was in the log file
            }
            else //anything else in the log is a normal log
            {
                status= "Normal Log";
            }
            br.close();
            file.delete();  // Delete the log file as I don't want to process it again
        }
        catch  (Exception e)
        {
            e.printStackTrace();
            System.out.println("You must pick Option 1. or Option 2 before you pick Option 3. Run the Simulator again!\n");
            //     System.exit(0);
        }

        return status;
    }
}
