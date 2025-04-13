package gg.bundlegroup.messagebus.common;

import gg.bundlegroup.messagebus.api.MessageBus;
import org.slf4j.Logger;
import org.spongepowered.configurate.ConfigurationNode;

public interface MessageBusProvider {
    String name();

    MessageBus create(Logger logger, ConfigurationNode config) throws Exception;
}
