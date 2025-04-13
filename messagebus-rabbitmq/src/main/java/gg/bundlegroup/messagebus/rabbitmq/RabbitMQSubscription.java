package gg.bundlegroup.messagebus.rabbitmq;

import com.rabbitmq.client.Channel;
import gg.bundlegroup.messagebus.api.generic.Subscription;
import gg.bundlegroup.messagebus.api.generic.SubscriptionListener;

import java.nio.charset.StandardCharsets;

public class RabbitMQSubscription extends MessageListener implements Subscription {
    private final Channel channel;
    private final String subject;
    private final String type;
    private final SubscriptionListener listener;

    public RabbitMQSubscription(Channel channel, String subject, String type, SubscriptionListener listener) throws Exception {
        this.channel = channel;
        this.subject = subject;
        this.type = type;
        this.listener = listener;

        register(channel);
    }

    @Override
    public String queue() {
        return subject;
    }

    @Override
    public void onMessage(String message) {
        listener.onMessage(new RabbitMQMessage(
                channel,
                message.getBytes(StandardCharsets.UTF_8),
                message,
                subject,
                subject,
                type
        ));
    }

    @Override
    public void close() {}
}
