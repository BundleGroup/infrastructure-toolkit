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
import java.util.Optional;

public class RabbitMQMessageBusProvider implements MessageBusProvider {
    @Override
    public String name() {
        return "rabbitmq";
    }

    @Override
    public MessageBus create(Logger logger, ConfigurationNode config) throws Exception {
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
        Optional<Channel> channel = connection.openChannel();

        if (channel.get() != null) {
            logger.info("Channel exists, connecting..., {}", channel.get().getChannelNumber());
            return new RabbitMQMessageBus(connection, channel.get());
        } else {
            logger.info("Channel does not yet exist, creating new channel.");
            Channel createdChannel = connection.createChannel();
            return new RabbitMQMessageBus(connection, createdChannel);
        }

    }
}
