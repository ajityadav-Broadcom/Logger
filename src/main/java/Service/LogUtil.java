package Service;

import Constants.LogLevel;

public interface LogUtil {
    public void writeLog(LogLevel logLevel, final String message) throws Exception;

    public void writeLogAsync(LogLevel logLevel, final String message) throws Exception;
}
