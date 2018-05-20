package ua.kiev.prog;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public abstract class LoggerAPI {
    @Autowired(required = false)
    protected Preprocessor preprocessor;

    public void setPreprocessor(Preprocessor preprocessor) {
        this.preprocessor = preprocessor;
    }

    public void log(String msg) throws IOException {
        if (preprocessor != null)
            msg = preprocessor.prepare(msg);

        doLog(msg);
    }

    protected abstract void doLog(String msg) throws IOException;

    public abstract void open() throws IOException;
    public abstract void close();
}
