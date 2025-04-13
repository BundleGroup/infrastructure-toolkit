package gg.bundlegroup.messagebus.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import gg.bundlegroup.messagebus.common.MessageBusPlugin;
import org.slf4j.Logger;

import java.nio.file.Path;

@Plugin(
        id = "messagebus",
        name = "MessageBus",
        version = BuildConstants.VERSION
)
public class Main {
    private final MessageBusPlugin plugin;

    @Inject
    public Main(Logger logger, @DataDirectory Path dataDirectory) {
        this.plugin = new MessageBusPlugin(logger, dataDirectory.toFile());
    }

    @Subscribe(order = PostOrder.FIRST)
    public void onInitialize(ProxyInitializeEvent event) {
        plugin.enable();
    }

    @Subscribe(order = PostOrder.LAST)
    public void onShutdown(ProxyShutdownEvent event) {
        plugin.disable();
    }
}
