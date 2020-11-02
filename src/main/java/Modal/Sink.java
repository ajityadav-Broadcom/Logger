package Modal;

public abstract class Sink {
    private String sinkType;
    private String logLevel;

    public Sink(String sinkType, String logLevel) {
        this.sinkType = sinkType;
        this.logLevel = logLevel;
    }

    public String getSinkType() {
        return sinkType;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public abstract void syncDump(final String message, boolean isAsync);
}
