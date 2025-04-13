package gg.bundlegroup.messagebus.api.storage;

import gg.bundlegroup.messagebus.api.generic.Subscription;

import java.io.Closeable;

public interface StorageSubscription extends Subscription, Closeable {
    @Override
    void close();
}
