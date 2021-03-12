package multidb.connector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.sink.SinkConnector;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

import multidb.connector.sink.MultiDBSinkConfig;
import multidb.connector.sink.MultiDBSinkTask;
// import io.confluent.connect.jdbc.JdbcSinkConnector;

public class MultiDBSinkConnector extends SinkConnector {
    // private static final Logger log = LoggerFactory.getLogger(MultiDBSinkConnector.class);

    private Map<String, String> configProps;

    public Class<? extends Task> taskClass() {
      return MultiDBSinkTask.class;
    }

    @Override
    public List<Map<String, String>> taskConfigs(int maxTasks) {
      //log.info("Setting task configurations for {} workers.", maxTasks);
      final List<Map<String, String>> configs = new ArrayList<>(maxTasks);
      for (int i = 0; i < maxTasks; ++i) {
        configs.add(configProps);
      }
      return configs;
    }
  
    @Override
    public void start(Map<String, String> props) {
      configProps = props;
    }
  
    @Override
    public void stop() {
    }

    @Override
    public ConfigDef config() {
      return MultiDBSinkConfig.CONFIG_DEF;
    }

    @Override
    public String version() {
      return "2021";//Version.getVersion();
    }
}