package gg.bundlegroup.messagebus.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import gg.bundlegroup.messagebus.api.MessageBus;
import gg.bundlegroup.messagebus.api.generic.Subscription;
import gg.bundlegroup.messagebus.api.messages.Message;

public class RabbitMQMessageBus implements MessageBus {
    private final Connection connection;
    private final Channel channel;

    public RabbitMQMessageBus(Connection connection, Channel channel) {
        this.connection = connection;
        this.channel = channel;
    }

    @Override
    public Subscription.Builder createSubscriptionBuilder() {
        return new RabbitMQSubscriptionBuilder(channel);
    }

    @Override
    public Message.Builder createMessageBuilder() {
        return new RabbitMQMessageBuilder(channel);
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
