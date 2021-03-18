package multidb.connector.sink;

import java.util.Collection;
import java.util.Map;

import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;
import org.apache.kafka.connect.data.Struct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MultiDBSinkTask extends SinkTask {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(MultiDBSinkTask.class);
    private static Connection conx;

    @Override
    public void start(final Map<String, String> props) {
        log.info("Starting MultiDB Sink task");
        String dbURL = "jdbc:postgresql://postgres:5432/db_user?user=db_user&password=db_password";
        try {
            conx = DriverManager.getConnection(dbURL);
            log.info("DATABASE CONNECTED!");
        } catch (SQLException e) {
            log.info("DATABASE CONNECTION COULD NOT BE MADE!!! {}", e.getMessage());
        }
    }


    @Override
    public void put(Collection<SinkRecord> records) {
        if ( records.isEmpty() )
          return;

        final SinkRecord first = records.iterator().next();
        final int recordsCount = records.size();
        log.info(
            "Received {} records. First record kafka coordinates:({}-{}-{}). Writing them to the "
            + "database...",
            recordsCount, first.topic(), first.kafkaPartition(), first.kafkaOffset()
        );
        for (SinkRecord record : records) {
            log.info(
                "{}",
                record.value()
            );
            //Statement query = conx.createStatement();

            String key = record.key().toString();
            try {
                final Struct value = (Struct) record.value();

                log.info("KEY: {}, VAL: {}", key, value.toString());

                final Statement query = conx.createStatement();
                final String sql =
                    "INSERT INTO clients (RK, COL1, COL2) " +
                    "VALUES ('" +
                        key + "'," +
                        value.get("COL1")  + ",'" +
                        value.get("COL2") +
                    "') " +
                    "ON CONFLICT (RK) " +
                    "DO UPDATE " +
                    "SET COL1 = EXCLUDED.COL1, " +
                        "COL2 = EXCLUDED.COL2 ";

                log.info("SQL: {}", sql);
                if ( query.executeUpdate(sql) >= 1 ) {
                    log.info("Record upserted {}", key);
                } else {
                    log.info("Record unsuccessully upserted. :(");
                }
            } catch (ClassCastException e) {
                log.info("CAST EXCEPTION");
            } catch (SQLException e) {
                log.info("SQL EXCEPTION: " + e.getMessage());
            }
        }
    }

    public void stop() {
        log.info("Stopping task");
        try {
            conx.close();
        } catch (SQLException e) {

        }
    }

    @Override
    public String version() {
        return "2021"; // Version.getVersion();
    }

  }
