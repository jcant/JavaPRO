package ua.kiev.prog;

import java.io.IOException;

public class ConsoleLoggerAPI extends LoggerAPI {
    @Override
    protected void doLog(String msg) throws IOException {
        System.out.println(msg);
    }

    @Override
    public void open() throws IOException {
        // do nothing
    }

    @Override
    public void close() {
        // do nothing
    }
}
