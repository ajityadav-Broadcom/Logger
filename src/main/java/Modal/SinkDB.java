package Modal;

public class SinkDB extends Sink {
    private String endPoint;
    private int port;
    private String timeStampFormat;
    private Object object = new Object();

    public SinkDB(String sinkType, String logLevel, String endPoint, int port, String timeStampFormat) {
        super(sinkType, logLevel);
        this.endPoint = endPoint;
        this.port = port;
        this.timeStampFormat = timeStampFormat;
    }

    @Override
    public void syncDump(String message, boolean isAsync) {

    }

    private void writeToFile(final String message) {


    }
}
