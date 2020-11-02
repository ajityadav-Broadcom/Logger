package Service;

import Constants.LogLevel;
import Factory.LoggerFactory;
import Modal.Sink;


public class LoggerService implements LogUtil {
    private LoggerFactory loggerFactory;

    public LoggerService(LoggerFactory loggerFactory) {
        this.loggerFactory = loggerFactory;
    }

    public void writeLog(LogLevel logLevel, final String message) throws Exception {
        Sink sink = loggerFactory.getLoggerObject(logLevel);
        if (sink == null) {
            throw new Exception("unKnown log level is passed to function");
        }
        sink.syncDump(message, false);
    }

    public void writeLogAsync(LogLevel logLevel, final String message) throws Exception {
        Sink sink = loggerFactory.getLoggerObject(logLevel);
        if (logLevel == null) {
            throw new Exception("Unknown type of log level is passed to function");
        }
        sink.syncDump(message, true);
    }
}
