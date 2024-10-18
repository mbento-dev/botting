package com.runemate.fenrisfeng.common.logger;

import java.io.*;
import org.apache.logging.log4j.*;

public class FileLogger {
    private final Logger logger;
    private String lastInfo;
    private String fileName = "log.txt";
    private BufferedWriter writer;

    public FileLogger(Logger logger) {
        this.logger = logger;

        try {
            createLogFile();
            writer = new BufferedWriter(new FileWriter(fileName));
        } catch (IOException e) {
            logger.error(e);
        }
    }

    public FileLogger(Logger logger, String fileName) {
        this.fileName = fileName;
        this.logger = logger;

        try {
            writer = new BufferedWriter(new FileWriter(this.fileName));
        } catch (IOException e) {
            logger.error(e);
        }
    }

    public void createLogFile() {
        try{
            logger.info("Starting log file creation");
            File logFile = new File(fileName);
            if(logFile.createNewFile()) {
                logger.info("Log file created at" + logFile.getAbsolutePath());
            } else {
                logger.info("Log file already exists as" + logFile.getAbsolutePath());
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public void info(String message) {
        logger.info(message);
//        if(!Objects.equals(lastInfo, message)) {
//            try {
//                writer.write(new Date() + message);
//                writer.close();
//            }catch (IOException e) {
//                logger.error(e);
//            }
//        }
    }
}
