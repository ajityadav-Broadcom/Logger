package Factory;

import Constants.LogLevel;
import Constants.SinkType;
import Modal.Configuration;
import Modal.Sink;
import Modal.SinkDB;
import Modal.SinkFile;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoggerFactory {
    Map<LogLevel, Sink> loggerObject;

    public LoggerFactory(final String filePath, ObjectMapper objectMapper) throws Exception {
        this.loggerObject = new HashMap<LogLevel, Sink>();

        List<Configuration> configurationList = Arrays.asList(objectMapper.readValue(new File(filePath), Configuration[].class));
        for (Configuration configuration : configurationList) {
            if (configuration.getSink().equalsIgnoreCase(SinkType.DB.getSinkType())) {
                addDbObject(configuration);
            } else if (configuration.getSink().equalsIgnoreCase(SinkType.FILE.getSinkType())) {
                addFileObject(configuration);
            }
        }
    }

    private void addFileObject(Configuration configuration) throws Exception {
        if (configuration.getFileLocation().isEmpty())
            throw new Exception("FileLocation is empty");
        if (configuration.getLogLevel().toUpperCase().equals("DEBUG")) {
            loggerObject.put(LogLevel.DEBUG, new SinkFile(configuration.getSink(), configuration.getLogLevel(), configuration.getFileLocation(), configuration.getTimeStampFormat()));
        } else if (configuration.getLogLevel().toUpperCase().equals("INFO")) {
            loggerObject.put(LogLevel.INFO, new SinkFile(configuration.getSink(), configuration.getLogLevel(), configuration.getFileLocation(), configuration.getTimeStampFormat()));

        } else if (configuration.getLogLevel().toUpperCase().equals("WARNING")) {
            loggerObject.put(LogLevel.WARNING, new SinkFile(configuration.getSink(), configuration.getLogLevel(), configuration.getFileLocation(), configuration.getTimeStampFormat()));

        } else if (configuration.getLogLevel().toUpperCase().equals("ERROR")) {
            loggerObject.put(LogLevel.ERROR, new SinkFile(configuration.getSink(), configuration.getLogLevel(), configuration.getFileLocation(), configuration.getTimeStampFormat()));

        }

    }

    private void addDbObject(Configuration configuration) throws Exception {
        if (configuration.getEndPoint().isEmpty() || configuration.getTimeStampFormat().isEmpty())
            throw new Exception("Db configuration is empty");

        if (configuration.getLogLevel().equals("DEBUG")) {
            loggerObject.put(LogLevel.DEBUG, new SinkDB(configuration.getSink(), configuration.getLogLevel(), configuration.getEndPoint(), configuration.getPort(), configuration.getTimeStampFormat()));
        } else if (configuration.getLogLevel().equals("INFO")) {
            loggerObject.put(LogLevel.DEBUG, new SinkDB(configuration.getSink(), configuration.getLogLevel(), configuration.getEndPoint(), configuration.getPort(), configuration.getTimeStampFormat()));
        } else if (configuration.getLogLevel().equals("WARNING")) {
            loggerObject.put(LogLevel.DEBUG, new SinkDB(configuration.getSink(), configuration.getLogLevel(), configuration.getEndPoint(), configuration.getPort(), configuration.getTimeStampFormat()));
        } else if (configuration.getLogLevel().equals("ERROR")) {
            loggerObject.put(LogLevel.DEBUG, new SinkDB(configuration.getSink(), configuration.getLogLevel(), configuration.getEndPoint(), configuration.getPort(), configuration.getTimeStampFormat()));
        }
    }

    public Sink getLoggerObject(LogLevel logLevel) {
        return loggerObject.get(logLevel);
    }
}
