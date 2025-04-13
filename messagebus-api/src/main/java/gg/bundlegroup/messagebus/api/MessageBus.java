package gg.bundlegroup.messagebus.api;

import gg.bundlegroup.messagebus.api.generic.Subscription;
import gg.bundlegroup.messagebus.api.messages.Message;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public interface MessageBus extends AutoCloseable {
    static MessageBus get() {
        return Holder.instance;
    }

    Subscription.Builder createSubscriptionBuilder();
    Message.Builder createMessageBuilder();

    @ApiStatus.Internal
    class Holder {
        static MessageBus instance;

        public static void setInstance(MessageBus instance) {
            Holder.instance = instance;
        }
    }
}
