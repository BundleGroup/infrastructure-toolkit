package gg.bundlegroup.messagebus.rabbitmq;

import com.rabbitmq.client.Channel;
import gg.bundlegroup.messagebus.api.generic.Subscription;
import gg.bundlegroup.messagebus.api.generic.SubscriptionListener;

public class RabbitMQSubscriptionBuilder implements Subscription.Builder {
    private final Channel channel;

    String subject;
    private SubscriptionListener listener;

    public RabbitMQSubscriptionBuilder(Channel connection) {
        this.channel = connection;
    }

    @Override
    public Subscription.Builder subject(String subject) {
        this.subject = subject;
        return this;
    }

    @Override
    public Subscription.Builder listener(SubscriptionListener listener) {
        this.listener = listener;
        return this;
    }

    @Override
    public Subscription build() {
        return null;
    }
}
