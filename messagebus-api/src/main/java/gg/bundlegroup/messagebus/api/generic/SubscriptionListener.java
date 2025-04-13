package gg.bundlegroup.messagebus.api.generic;

import gg.bundlegroup.messagebus.api.messages.Message;

/**
 * Functional interface representing a listener for incoming messages
 * on a {@link Subscription}.
 *
 * <p>Implementations of this interface are invoked when a message is received.</p>
 */
@FunctionalInterface
public interface SubscriptionListener {

    /**
     * Called when a message is received on a subscribed subject.
     *
     * @param message the received message
     */
    void onMessage(Message message);
}
