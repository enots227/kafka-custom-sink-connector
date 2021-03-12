package multidb.connector.sink;

import java.util.Collection;
import java.util.Map;

import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;

public class MultiDBSinkTask extends SinkTask {
    
    @Override
    public void start(final Map<String, String> props) {
    //   log.info("Starting JDBC Sink task");
    //   config = new JdbcSinkConfig(props);
    //   initWriter();
    //   remainingRetries = config.maxRetries;
    }

    
  @Override
  public void put(Collection<SinkRecord> records) {
    // if (records.isEmpty()) {
    //   return;
    // }
    // final SinkRecord first = records.iterator().next();
    // final int recordsCount = records.size();
    // log.debug(
    //     "Received {} records. First record kafka coordinates:({}-{}-{}). Writing them to the "
    //     + "database...",
    //     recordsCount, first.topic(), first.kafkaPartition(), first.kafkaOffset()
    // );
    // try {
    //   writer.write(records);
    // } catch (SQLException sqle) {
    //   log.warn(
    //       "Write of {} records failed, remainingRetries={}",
    //       records.size(),
    //       remainingRetries,
    //       sqle
    //   );
    //   String sqleAllMessages = "Exception chain:" + System.lineSeparator();
    //   int totalExceptions = 0;
    //   for (Throwable e : sqle) {
    //     sqleAllMessages += e + System.lineSeparator();
    //     totalExceptions++;
    //   }
    //   SQLException sqlAllMessagesException = new SQLException(sqleAllMessages);
    //   sqlAllMessagesException.setNextException(sqle);
    //   if (remainingRetries == 0) {
    //     log.error(
    //         "Failing task after exhausting retries; "
    //           + "encountered {} exceptions on last write attempt. "
    //           + "For complete details on each exception, please enable DEBUG logging.",
    //         totalExceptions);
    //     int exceptionCount = 1;
    //     for (Throwable e : sqle) {
    //       log.debug("Exception {}:", exceptionCount++, e);
    //     }
    //     throw new ConnectException(sqlAllMessagesException);
    //   } else {
    //     writer.closeQuietly();
    //     initWriter();
    //     remainingRetries--;
    //     context.timeout(config.retryBackoffMs);
    //     throw new RetriableException(sqlAllMessagesException);
    //   }
    // }
    // remainingRetries = config.maxRetries;
  }

  public void stop() {
    // log.info("Stopping task");
    // try {
    //   writer.closeQuietly();
    // } finally {
    //   try {
    //     if (dialect != null) {
    //       dialect.close();
    //     }
    //   } catch (Throwable t) {
    //     log.warn("Error while closing the {} dialect: ", dialect.name(), t);
    //   } finally {
    //     dialect = null;
    //   }
    // }
  }

  @Override
  public String version() {
    return "2021"; // Version.getVersion();
  }

}
