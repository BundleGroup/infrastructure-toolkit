package gg.bundlegroup.messagebus.api.generic;

import gg.bundlegroup.messagebus.api.MessageBus;

import java.io.Closeable;

public interface Subscription extends Closeable {
    @Override
    void close();

    static Builder builder() { return MessageBus.get().createSubscriptionBuilder(); }

    interface Builder {
        Builder subject(String subject);

        Builder listener(SubscriptionListener listener);

        Subscription build();
    }
}
