package logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseLogger {
	// this is a class that helps with the logging throughout the system. 
	// there are two types of logs they (error, and info). 
	// logs can be written using the write to log method it uses the filewriter class. 
	// and a specific format for the current date.(uses the simpledateformatclass).
	
	
	//the locations of the txt files
    private static final String INFO_LOG_FILE = "application_info.txt";
    private static final String ERROR_LOG_FILE = "application_error.txt";
    // log types (error or info)
    private String type;
    private String logMessage;
    private PrintWriter writer;
    
    private BaseLogger(String type) {
        this.type = type;
    }

    public static BaseLogger info() {
        return new BaseLogger("INFO");
    }

    public static BaseLogger error() {
        return new BaseLogger("ERROR");
    }

    public BaseLogger log(String message) {
        this.logMessage = message;
        return this;
    }

    public void writeToLog() {
        if ("INFO".equals(type)) {
            writeLog(INFO_LOG_FILE, "[INFO] ");
        } else if ("ERROR".equals(type)) {
            writeLog(ERROR_LOG_FILE, "[ERROR] ");
        }
    }

    private void writeLog(String filename, String prefix) {
        try {
            writer = new PrintWriter(new FileWriter(filename, true));
            String timestamp = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").format(new Date());
            writer.println("[" + timestamp + "]" + prefix + logMessage);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
