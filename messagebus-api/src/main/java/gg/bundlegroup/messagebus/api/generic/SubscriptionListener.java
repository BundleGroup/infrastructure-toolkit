package gg.bundlegroup.messagebus.api.generic;

import gg.bundlegroup.messagebus.api.messages.Message;

@FunctionalInterface
public interface SubscriptionListener {
    void onMessage(Message message);
}
