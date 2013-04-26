package net.suaree.eventbrite;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * @author roger
 */
final class LoggingInputStreamReader extends Reader {
    private static final Log log = LogFactory.getLog(LoggingInputStreamReader.class);

    private final BufferedReader baseReader;
    private final StringReader reader;
    private StringBuilder allContent = new StringBuilder();

    public LoggingInputStreamReader(BufferedReader baseReader) throws IOException {
        this.baseReader = baseReader;

        readAll();

        reader = new StringReader(allContent.toString());
    }

    @Override
    public int read(char[] chars, int i, int i2) throws IOException {
        return reader.read(chars, i, i2);
    }

    @Override
    public void close() throws IOException {
        baseReader.close();
        reader.close();
    }

    private void readAll() throws IOException {
        String line;
        String newline = System.getProperty("line.separator");

        while (null != (line = baseReader.readLine())) {
            allContent.append(line);
            allContent.append(newline);
        }

        if (log.isTraceEnabled()) {
            log.trace(allContent);
        }
    }
}
