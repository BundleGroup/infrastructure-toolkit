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
    private MessageBus instance;

    public MessageBusPlugin(final Logger logger, final File dataFolder) {
        this.logger = logger;
        this.dataFolder = dataFolder;
    }

    public boolean enable() {
        try {
            YamlConfigurationLoader loader = YamlConfigurationLoader.builder()
                    .file(new File(dataFolder, "config.yml"))
                    .build();

            CommentedConfigurationNode config = loader.load();
            String type = config.node("type").getString(null);

            if (type == null) {
                throw new IllegalArgumentException("Missing 'type' in config.");
            }

            BrokerType broker;
            try {
                broker = BrokerType.valueOf(type.toLowerCase());
            }catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid 'type' in config.");
            }

            CommentedConfigurationNode brokerConfig = config.node(type);
            MessageBus bus = null;

            for (MessageBusProvider provider : ServiceLoader.load(MessageBusProvider.class, getClass().getClassLoader())) {
                if (provider.name().equals(type)) {
                    bus = provider.create(logger, config.node(type));
                }
            }

            if (bus == null) {
                logger.error("Unknown bus type {}", type);
                return false;
            }

            instance = bus;
            MessageBus.Holder.setInstance(instance);

        } catch (Exception e) {
            logger.error("Failed to initialize MessageBus: {}", e.getMessage());
            return false;
        }

        return true;
    }

    public void disable() {
        if (instance != null) {
            try {
                instance.close();
            } catch (Exception e) {
                logger.error("Failed to close MessageBus: {}", e.getMessage());
            }
            instance = null;
        }
    }
}
