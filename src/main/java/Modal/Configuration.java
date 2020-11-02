package Modal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


public class Configuration implements Serializable {
    @JsonProperty("sink")
    private String sink;

    @JsonProperty("loglevel")
    private String logLevel;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("filelocation")
    private String fileLocation;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("endpoint")
    private String endPoint;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("port")
    private int port;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("timestampformat")
    private String timeStampFormat;

    public Configuration() {
    }


    public String getSink() {
        return sink;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public int getPort() {
        return port;
    }

    public String getTimeStampFormat() {
        return timeStampFormat;
    }
}
