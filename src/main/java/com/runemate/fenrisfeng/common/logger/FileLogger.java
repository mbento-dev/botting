package com.runemate.fenrisfeng.common.logger;

import java.io.*;
import org.apache.logging.log4j.*;

public class FileLogger {
    private final Logger logger;
    private String fileName = "log.txt";

    public FileLogger(Logger logger) {
        this.logger = logger;
    }

    public void info(String message) {
        logger.info(message);
    }
}
