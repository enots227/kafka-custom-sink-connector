# kafka-sink-connector-using-ksql
Similar to my previous repository [kafkacdc-simple-postgres-to-console](https://github.com/enots227/kafkaCDC-simple-postgres-to-console#kafkacdc-simple-postgres-to-console), this project is to gain more of an understanding of the Kakfka ecosystem. Initially, I was going to complete the cycle of the previous project and change the sink connector from the console to another instance of a Postgres database; however, came across this [YouTube video by Robin Moffatt](https://www.youtube.com/watch?v=b-3qN_tlYR4) describing a sink connector using KSQL and realized KSQL could assist in monitoring the Kafka environment and more easily read and write to a Kafka cluster for testing. So in the project, I manually configured a KSQL application server and Schema Registry.

#
## Expected Result
- Manual creation of Confluent's KSQL and Confluent's schema registry.
- Messing around with KSQL commands.
- Creating a Postgres sink connector.
- Inserting Kafka cluster data usign KSQL.

#
## Software Required
- Docker - https://www.docker.com/products/docker-desktop
- Custom images from previous repository.
    - See [kafkacdc-simple-postgres-to-console](https://github.com/enots227/kafkaCDC-simple-postgres-to-console#kafkacdc-simple-postgres-to-console)

#
## Create Docker Images for KSQL and Schema Registry
### Create Confluent Image
In the previous repository, we only used the confluent community platform for the connector image. For KSQL and schema registry we also need to use it, so to avoid each image individually install it I will create a intermediate confluent image that will pull from the kafka image and be used by all the images that need to use the confluent platform.

Alternatively, you could probably find a Confluent Docker image in the Docker Hub if you do not want to create your own.

Steps in `confluent/Dockerfile` and `confluent/confluent-install.sh`:
- Builds from the Kafka image.
    - Already has the JVM.
    - Already has Kafka installed.
- Install unzip
    - This will allow us to extract the confluent folders.
- Install curl
    - Curl could be useful for registrying connectors. I use Postman myself.
- Extracts the Confluent Community
    - The Confluent JDBC requires this program for it to work, so we will install it to the /opt folder.
- Extracts the Confluent JDBC
    - This is the Confluent JDBC source and sink connector that we will use for syncing the Kafka cluster with changes to the postgres database. Postgres will have a sink connector in the build.

### Confluent Community License
The KSQL and Schema Registry applications are under the [Confluent Community License](https://www.confluent.io/confluent-community-license-faq).

```
[The license] does not allow hosting of Confluent ksqlDB, Confluent Schema Registry, Confluent REST Proxy, or other software licensed under the Confluent Community License as online service offerings that compete with Confluent SaaS products or services that provide the same software. If you are not doing what is excluded, this license change will not affect you.
...
What if future Confluent products compete with mine?
    Let’s go through a specific example. Say that you are building a SaaS Hotel Booking Engine and you want to include ksqlDB in the implementation of that offering. Of course you can do that, this service does not compete with any Confluent product that “provides the software”. Note that this would be true, even if in the future Confluent had its own hotel booking product (not likely!). The excluded purpose for ksqlDB is limited to competition with Confluent’s SaaS offering of ksqlDB.
```

From the above statement, I take it as long as you are using the product to enhance your backend process and you are not directly selling a competitive Event Streaming Platform, then you should be okay.

However, another gray area to consider, that I do not know the answer to, is if you start using these products in your backend and decide to create your own internal application to monitor the Kafka environment and you use Confluent KSQL for that application. Well Confluent Control Center is an application on the Confluent Enterprise License, which is a product that directly does this task. So even though you are not selling this internal tool to other people is it a competitive product? 

> (!) Disclaimer I am not a lawyer and you should read the license yourself to determine where your software fits.

### Create Schema Registry
Schema Registry is an application designed to store and link JSON schema metadata to a JSON structure. This allows the sink connector to understand what type each value from a Kafka topic is suppose to be within a database. This allows the connector to auto create the table for the target destinations.

Steps in `schema-registry/schema-registry-start.sh`:
- Builds from the Confluent image.
- Sets `kafkastore.bootstrap.servers` property to the broker containers.
- Start the schema server.

...very simple container.

### Create KSQL
KSQL is a language designed to interface with the Kafka cluster with easy syntax. This application is great for learning Kafka. Do keep in mind before considering for your company servers the [Confluent Community License](https://www.confluent.io/confluent-community-license-faq).

Steps in `ksql/ksql-install.sh` and `ksql/ksql-start.sh`:
- Builds from the Confluent image.
- Appends the default `ksql.connect.url` configuration property to the `ksql-server.properties` file
    - This allows the start up script to modify it. Don't what to append it in the start up process since restarting the Docker image will constantly append the same property.
    - This property is needed since the connector application is being ran on another Docker container.
- Sets the `bootstrap.servers` property to the broker containers.
- Sets the `ksql.schema.registry.url` property to the schema registry container.
- Sets the `ksql.connect.url` property that we appended to the file earlier.

#
## Test and Experiment with KSQL 
At this point I followed the [YouTube video by Robin Moffatt](https://www.youtube.com/watch?v=b-3qN_tlYR4) entirely with the exception of creating the stream.

- New versions of KSQL requires you to directly specify the Kafka key column with a KEY tag.

```KSQL
CREATE STREAM TEST01 (RK KEY VARCHAR(255), COL1 INT, COL2 VARCHAR(MAX)) WITH (TOPIC='test01',PARTITIONS=1,VALUE_FORMAT='AVRO')
```

## Resources
Tutorials:
- Kafka Connect in Action: JDBC Sink - https://www.youtube.com/watch?v=b-3qN_tlYR4

Documentation:
- https://docs.confluent.io/4.1.0/ksql/docs/installation/installing.html
- https://docs.confluent.io/5.4.2/ksql/docs/installation/server-config/avro-schema.html
- https://docs.confluent.io/platform/current/schema-registry/installation/deployment.html
- https://docs.ksqldb.io/en/latest/developer-guide/ksqldb-reference/create-stream/
- https://help.aiven.io/en/articles/4278191-setting-up-jdbc-sink-connector-with-aiven-for-kafka
- https://docs.confluent.io/platform/current/schema-registry/connect.html

## Problems Encountered:
- Line Endings in Linux vs Windows
    - Started working on this project on a new computer using a different version of Docker.
    - All the SH scripts that I created stopped working.
        - Started getting `/tmp/java-install.sh: not found` errors.
    - This was due to the line ending being encoded in CRLF and not LF.
        - This setting is changable in VS Code at the bottom right.