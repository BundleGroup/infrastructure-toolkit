package gg.bundlegroup.messagebus.common;

import gg.bundlegroup.messagebus.api.MessageBus;
import gg.bundlegroup.messagebus.api.generic.BrokerType;
import org.slf4j.Logger;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.File;
import java.util.ServiceLoader;

public class MessageBusPlugin {
    private final Logger logger;
    private final File dataFolder;
    private MessageBus messageBus;

    public MessageBusPlugin(Logger logger, File dataFolder) {
        this.logger = logger;
        this.dataFolder = dataFolder;
    }

    public boolean enable() {
        try {
            YamlConfigurationLoader loader = YamlConfigurationLoader.builder()
                    .file(new File(dataFolder, "config.yml"))
                    .build();
            CommentedConfigurationNode config = loader.load();
            String type = config.node("type").getString("rabbitmq");
            MessageBus bus = null;
            for (MessageBusProvider provider : ServiceLoader.load(MessageBusProvider.class, getClass().getClassLoader())) {
                if (provider.name().equals(type)) {
                    bus = provider.create(logger, config.node(type));
                }
            }
            if (bus == null) {
                logger.error("Unknown message bus type: {}", type);
                return false;
            }
            messageBus = bus;
            MessageBus.Holder.setInstance(messageBus);
        } catch (Exception e) {
            logger.error("Failed to initialize message bus", e);
            return false;
        }
        return true;
    }

    public void disable() {
        if (messageBus != null) {
            try {
                messageBus.close();
            } catch (Exception e) {
                logger.error("Failed to close message bus", e);
            }
            messageBus = null;
        }
    }
}
