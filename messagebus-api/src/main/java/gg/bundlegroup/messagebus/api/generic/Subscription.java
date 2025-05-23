package gg.bundlegroup.messagebus.api.generic;

import gg.bundlegroup.messagebus.api.MessageBus;

import java.io.Closeable;

/**
 * Represents an active subscription to a subject on the message bus.
 *
 * <p>Subscriptions listen for incoming messages and dispatch them to a {@link SubscriptionListener}.</p>
 */
public interface Subscription extends Closeable {

    /**
     * Closes the subscription and stops receiving messages.
     */
    @Override
    void close();

    /**
     * Creates a new {@link Builder} for constructing a {@link Subscription}.
     *
     * @return a new subscription builder
     */
    static Builder builder() {
        return MessageBus.get().createSubscriptionBuilder();
    }

    /**
     * Builder for constructing {@link Subscription} instances.
     */
    interface Builder {

        /**
         * Sets the subject to subscribe to.
         *
         * @param subject the target subject
         * @return this builder instance
         */
        Builder subject(String subject);

        /**
         * Sets the listener that will be called when messages are received.
         *
         * @param listener the subscription listener
         * @return this builder instance
         */
        Builder listener(SubscriptionListener listener);

        /**
         * Builds and activates the subscription.
         *
         * @return the constructed subscription
         */
        Subscription build() throws Exception;
    }
}
