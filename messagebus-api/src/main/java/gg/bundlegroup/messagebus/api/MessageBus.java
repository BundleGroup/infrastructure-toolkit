package gg.bundlegroup.messagebus.api;

import gg.bundlegroup.messagebus.api.generic.Subscription;
import gg.bundlegroup.messagebus.api.messages.Message;
import org.jetbrains.annotations.ApiStatus;

/**
 * Core entry point for interacting with the message bus system.
 *
 * <p>This interface provides methods to construct subscriptions and messages
 * for a supported messaging backend (e.g., RabbitMQ, Kafka).</p>
 *
 * <p>Use {@link MessageBus#get()} to retrieve the active instance.</p>
 */
@ApiStatus.Internal
public interface MessageBus extends AutoCloseable {

    /**
     * Returns the global instance of the {@link MessageBus}.
     *
     * @return the active message bus instance
     */
    static MessageBus get() {
        return Holder.instance;
    }

    /**
     * Creates a new builder for configuring and constructing a {@link Subscription}.
     *
     * @return a subscription builder
     */
    Subscription.Builder createSubscriptionBuilder();

    /**
     * Creates a new builder for configuring and constructing a {@link Message}.
     *
     * @return a message builder
     */
    Message.Builder createMessageBuilder();

    /**
     * Holds the singleton instance of the {@link MessageBus}.
     *
     * <p>This class is intended for internal use only.</p>
     */
    @ApiStatus.Internal
    class Holder {
        static MessageBus instance;

        /**
         * Sets the global {@link MessageBus} instance.
         *
         * @param instance the message bus implementation to use
         */
        public static void setInstance(MessageBus instance) {
            Holder.instance = instance;
        }
    }
}
