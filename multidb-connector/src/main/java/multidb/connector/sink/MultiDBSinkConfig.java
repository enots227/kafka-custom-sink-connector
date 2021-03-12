package multidb.connector.sink;

import java.util.Map;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigException;
import org.apache.kafka.common.config.types.Password;

public class MultiDBSinkConfig extends AbstractConfig {
    
    // public static final String CONNECTION_USER = JdbcSourceConnectorConfig.CONNECTION_USER_CONFIG;
    // private static final String CONNECTION_USER_DOC = "JDBC connection user.";
    // private static final String CONNECTION_USER_DISPLAY = "JDBC User";

    public static final ConfigDef CONFIG_DEF = new ConfigDef();
        // Connection
        // .define(
        //     CONNECTION_USER,
        //     ConfigDef.Type.STRING,
        //     null,
        //     ConfigDef.Importance.HIGH,
        //     CONNECTION_USER_DOC,
        //     CONNECTION_GROUP,
        //     2,
        //     ConfigDef.Width.MEDIUM,
        //     CONNECTION_USER_DISPLAY
        // );

    public MultiDBSinkConfig(Map<?, ?> props) {
        super(CONFIG_DEF, props);
        // connectorName = ConfigUtils.connectorName(props);
        // connectionUrl = getString(CONNECTION_URL);
        // connectionUser = getString(CONNECTION_USER);
        // connectionPassword = getPasswordValue(CONNECTION_PASSWORD);
        // connectionAttempts = getInt(CONNECTION_ATTEMPTS);
        // connectionBackoffMs = getLong(CONNECTION_BACKOFF);
        // tableNameFormat = getString(TABLE_NAME_FORMAT).trim();
        // batchSize = getInt(BATCH_SIZE);
        // deleteEnabled = getBoolean(DELETE_ENABLED);
        // maxRetries = getInt(MAX_RETRIES);
        // retryBackoffMs = getInt(RETRY_BACKOFF_MS);
        // autoCreate = getBoolean(AUTO_CREATE);
        // autoEvolve = getBoolean(AUTO_EVOLVE);
        // insertMode = InsertMode.valueOf(getString(INSERT_MODE).toUpperCase());
        // pkMode = PrimaryKeyMode.valueOf(getString(PK_MODE).toUpperCase());
        // pkFields = getList(PK_FIELDS);
        // dialectName = getString(DIALECT_NAME_CONFIG);
        // fieldsWhitelist = new HashSet<>(getList(FIELDS_WHITELIST));
        // String dbTimeZone = getString(DB_TIMEZONE_CONFIG);
        // timeZone = TimeZone.getTimeZone(ZoneId.of(dbTimeZone));
    
        // if (deleteEnabled && pkMode != PrimaryKeyMode.RECORD_KEY) {
        //   throw new ConfigException(
        //       "Primary key mode must be 'record_key' when delete support is enabled");
        // }
        // tableTypes = TableType.parse(getList(TABLE_TYPES_CONFIG));
      }

}
