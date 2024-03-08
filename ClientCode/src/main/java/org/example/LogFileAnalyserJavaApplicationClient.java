package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.filechooser.FileSystemView;

public class LogFileAnalyserJavaApplicationClient {

    public static void main(String[] args) throws IOException {
        System.out.println(" ----> This Simulator allows test drive of the LogFileAnalyser cross cutting component. <---- \n");
        System.out.println(" This Simulator provides functionality to:");
        System.out.println(" A. Run the cross cutting LogFileAnalyser isValidLogFileName method.");
        System.out.println(" The LogFileAnalyser has code for the current business logic for dealing with operation and failure of a monitored production system");
        System.out.println(" The production system produces a log file detailing how it is operating. This is typical of live systems which produce information on how they");
        System.out.println(" are running which can be monitored by IT to ensure everything is operating correctly");
        System.out.println(" B. Produce simple LOG files");
        System.out.println(" When you use this Simulator to produce a LOG file you are mimicking running a production system which would provide useful functionality for the ");
        System.out.println(" business and also produce a LOG file so that IT can know what is going on with the system");

        System.out.println(" \n");
        System.out.println("Choose from these choices, You must click in the Output Window first");
        System.out.println("-------------------------\n");

        System.out.println("1 - Produce a LOG file simulating the situation that the monitored system crashed");
        System.out.println("2 - Produce a LOG file simulating the situation that the monitored system operated normally");
        System.out.println("3 - Run the cross cutting LogFileAnalyser isValidLogFileName method.");
        System.out.println("4 - Quit the Simulator.\n");

        String path = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "/LogFile/";

        int selection;
        Scanner scanChoice = new Scanner(System.in);
        boolean quit = false;

        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdir();
        }

        do {
            selection = scanChoice.nextInt();
            switch (selection) {
                case 1:
                    try {
                        FileWriter writer = new FileWriter(path + "LogFile.log");
                        writer.write("Monitored production crashed because of fatal Exception");
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        FileWriter writer = new FileWriter(path + "LogFile.log");
                        writer.write("Monitored production working normally");
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    boolean status;
                    LogFileAnalyser myLogger = new LogFileAnalyser();
                    status = myLogger.AnalyseLogFile("LogFile.log");
                    System.out.println("The cross cutting AnalyseLogFile method returned ->" + status);
                    File theLogFile = new File(path + "LogFile.log");
                    if (theLogFile.exists()) {
                        theLogFile.delete();
                    }
                    break;
                case 4:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (!quit);
        System.out.println("Thanks for using this simulator");
    }
}
