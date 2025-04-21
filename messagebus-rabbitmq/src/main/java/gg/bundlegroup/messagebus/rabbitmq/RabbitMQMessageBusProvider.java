package gg.bundlegroup.messagebus.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import gg.bundlegroup.messagebus.api.MessageBus;
import gg.bundlegroup.messagebus.common.MessageBusProvider;
import org.slf4j.Logger;
import org.spongepowered.configurate.ConfigurationNode;

import java.util.AbstractMap;
import java.util.Map;

public class RabbitMQMessageBusProvider implements MessageBusProvider {
    @Override
    public String name() {
        return "rabbitmq";
    }

    @Override
    public MessageBus create(Logger logger, ConfigurationNode config) throws Exception {
        logger.info("Creating RabbitMQ MessageBus");
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(config.node("host").getString("localhost"));

        ConfigurationNode authNode = config.node("credentials");

        Map.Entry<String, String> credentials = new AbstractMap.SimpleEntry<>(
                authNode.node("username").getString(),
                authNode.node("password").getString()
        );

        factory.setUsername(credentials.getKey());
        factory.setPassword(credentials.getValue());

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        logger.info("Works");
        return new RabbitMQMessageBus(connection, channel);
    }
}
