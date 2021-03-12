confluent_platform_version="6.1.0"
confluent_home=/opt/confluent-$confluent_platform_version

# KSQL Environment Configuration ########
cd $confluent_home/etc/ksqldb

# set listeners ===========
# ensures / -> \/ (forward slashes escape the sed find and replace)
KSQL_LISTENERS=$(echo $KSQL_LISTENERS | sed "s/\//\\\\\//g")

FIND="^listeners=.*$"
REPLACE="listeners=${KSQL_LISTENERS}"
sed -i "s/${FIND}/${REPLACE}/" ksql-server.properties

# set brokers ============
FIND="^bootstrap.servers=.*$"
REPLACE="bootstrap.servers=${KSQL_BROKERS}"
sed -i "s/${FIND}/${REPLACE}/" ksql-server.properties

# set schema registry ====
# ensures / -> \/ (forward slashes escape the sed find and replace)
KSQL_SCHEMA_REGISTRY=$(echo $KSQL_SCHEMA_REGISTRY | sed "s/\//\\\\\//g")

FIND="^#\{0,1\}\s*ksql.schema.registry.url=.*$"
REPLACE="ksql.schema.registry.url=${KSQL_SCHEMA_REGISTRY}"
sed -i "s/${FIND}/${REPLACE}/" ksql-server.properties

# set kafka connect integration ====
# ensures / -> \/ (forward slashes escape the sed find and replace)
KSQL_CONNECT_URL=$(echo $KSQL_CONNECT_URL | sed "s/\//\\\\\//g")

FIND="^#\{0,1\}\s*ksql.connect.url=.*$"
REPLACE="ksql.connect.url=${KSQL_CONNECT_URL}"
sed -i "s/${FIND}/${REPLACE}/" ksql-server.properties

# stark ksql ##############################
cd $confluent_home
bin/ksql-server-start etc/ksqldb/ksql-server.properties
