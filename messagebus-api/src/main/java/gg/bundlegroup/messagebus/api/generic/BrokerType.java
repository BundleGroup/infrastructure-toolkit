package gg.bundlegroup.messagebus.api.generic;

/**
 * Represents the different types of supported message brokers
 * that the MessageBus system can interface with.
 */
public enum BrokerType {
    /**
     * RabbitMQ message broker.
     */
    RABBIT_MQ("rabbitmq"),

    /**
     * Redis-based messaging.
     */
    REDIS("redis"),

    /**
     * NATS messaging system.
     */
    NATS("nats"),

    /**
     * Kafka message broker.
     */
    KAFKA("kafka");

    /**
     * A lowercase string representation used for configuration or display.
     */
    private final String friendlyName;

    /**
     * Constructs a BrokerType with a user-friendly name.
     *
     * @param friendlyName the lowercase identifier for this broker
     */
    BrokerType(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    /**
     * Returns the user-friendly name of the broker.
     *
     * @return the broker's friendly name
     */
    public String getFriendlyName() {
        return friendlyName;
    }
}
