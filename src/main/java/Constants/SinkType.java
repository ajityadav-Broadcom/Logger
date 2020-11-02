package Constants;

public enum SinkType {
    DB("db"), FILE("file");
    private String sinkType;

    SinkType(String sinkType) {
        this.sinkType = sinkType;
    }

    public String getSinkType() {
        return sinkType;
    }
}
