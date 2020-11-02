import Constants.LogLevel;
import Factory.LoggerFactory;
import Modal.Configuration;
import Service.LogUtil;
import Service.LoggerService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {

    public static void main(String[] args) throws Exception {
        final String configurationFilePath = "D:\\Loggers\\src\\main\\resources\\configurationFilePath.json";
        ObjectMapper objectMapper = new ObjectMapper();
        List<Configuration> configurationList = Arrays.asList(objectMapper.readValue(new File(configurationFilePath), Configuration[].class));
        for (Configuration configuration : configurationList) {
            String val = objectMapper.writeValueAsString(configuration);
            System.out.println(val);
        }
        LogUtil logManager = new LoggerService(new LoggerFactory(configurationFilePath, new ObjectMapper()));
        System.out.println("Everything is okay");
        logManager.writeLog(LogLevel.DEBUG, "hi");
        logManager.writeLogAsync(LogLevel.DEBUG, "bye");
        logManager.writeLog(LogLevel.INFO, "dihieh");
        logManager.writeLog(LogLevel.DEBUG, "hi");
        logManager.writeLogAsync(LogLevel.DEBUG, "bye");
        logManager.writeLog(LogLevel.INFO, "dihieh");
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 400; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        logManager.writeLog(LogLevel.DEBUG, "hi");
                        logManager.writeLogAsync(LogLevel.DEBUG, "bye");
                        logManager.writeLog(LogLevel.INFO, "dihieh");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executorService.shutdown();
    }
}