package Modal;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SinkFile extends Sink {
    private String fileLocation;
    private String timeStampFormat;
    private Object object = new Object();

    public SinkFile(String sinkType, String logLevel, String fileLocation, String timeStampFormat) {
        super(sinkType, logLevel);
        this.fileLocation = fileLocation;
        this.timeStampFormat = timeStampFormat;
    }

    @Override
    public void syncDump(String message, boolean isAsync) {
        if (isAsync) {
            Runnable target;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        writeData(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        } else {
            try {
                writeData(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void writeData(final String message) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileLocation, true));
        try {
            synchronized (object) {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(timeStampFormat);
                String current = dateTimeFormatter.format(LocalDateTime.now());
                current = current + " " + message;
                bufferedWriter.write(current);
                bufferedWriter.write("\n");

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            bufferedWriter.close();
        }

    }
}
