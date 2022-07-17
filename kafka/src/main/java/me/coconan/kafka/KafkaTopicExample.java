package me.coconan.kafka;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.config.TopicConfig;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class KafkaTopicExample {

    public void createTopic(String topicName) throws Exception {
        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");

        try (Admin admin = Admin.create(properties)) {
            deleteTopic(topicName, admin);

            int partitions = 1;
            short replicationFactor = 1;
            NewTopic newTopic = new NewTopic(topicName, partitions, replicationFactor);

            CreateTopicsResult result = admin.createTopics(Collections.singleton(newTopic));

            // get the async result for the new topic creation
            KafkaFuture<Void> future = result.values().get(topicName);

            // call get() to block until topic creation has completed or failed
            future.get();
        }
    }

    public void createTopicWithOptions(String topicName) throws Exception {
        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");

        try (Admin admin = Admin.create(properties)) {
//            deleteTopic(topicName, admin);

            int partitions = 1;
            short replicationFactor = 1;
            NewTopic newTopic = new NewTopic(topicName, partitions, replicationFactor);

            CreateTopicsOptions topicsOptions = new CreateTopicsOptions().validateOnly(true).retryOnQuotaViolation(true);

            CreateTopicsResult result = admin.createTopics(Collections.singleton(newTopic), topicsOptions);

            // get the async result for the new topic creation
            KafkaFuture<Void> future = result.values().get(topicName);

            // call get() to block until topic creation has completed or failed
            future.get();
        }
    }

    public void createCompactedTopicWithCompression(String topicName) throws Exception {
        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");

        try (Admin admin = Admin.create(properties)) {
            deleteTopic(topicName, admin);

            int partitions = 1;
            short replicationFactor = 1;

            // create a compacted topic with 'lz4' compression codec
            Map<String, String> newTopicConfig = new HashMap<>();
            newTopicConfig.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_COMPACT);
            newTopicConfig.put(TopicConfig.COMPRESSION_TYPE_CONFIG, "lz4");

            NewTopic newTopic = new NewTopic(topicName, partitions, replicationFactor).configs(newTopicConfig);

            CreateTopicsResult result = admin.createTopics(Collections.singleton(newTopic));

            // get the async result for the new topic creation
            KafkaFuture<Void> future = result.values().get(topicName);

            // call get() to block until topic creation has completed or failed
            future.get();
        }
    }

    private void deleteTopic(String topicName, Admin admin) throws Exception {
        DeleteTopicsResult deleteResult = admin.deleteTopics(Collections.singleton(topicName));
        while (!deleteResult.values().get(topicName).isDone()) {
            Thread.sleep(1000);  // why should a sleep be here?
        }
    }

    public static void main(String[] args) throws Exception {
        new KafkaTopicExample().createTopic("KAFKA_TOPIC");
        new KafkaTopicExample().createTopicWithOptions("KAFKA_TOPIC_WITH_OPTIONS");
        new KafkaTopicExample().createCompactedTopicWithCompression("KAFKA_TOPIC_WITH_COMPRESSION");
    }
}