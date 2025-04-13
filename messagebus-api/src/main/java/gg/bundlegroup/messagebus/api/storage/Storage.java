package gg.bundlegroup.messagebus.api.storage;

import gg.bundlegroup.messagebus.api.MessageBus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public interface Storage {
    static @NotNull Entry entry(@NotNull String key) {
        return MessageBus.get().getStorage().get(key);
    }

    @NotNull Entry get(@NotNull String key);

    interface Entry {
        @NotNull String key();

        @NotNull Value get();

        void set(byte @NotNull [] data) throws IOException;

        boolean update(Value old, byte @NotNull [] data) throws IOException;

        void delete() throws IOException;

        @NotNull StorageSubscription subscribe(StorageListener listener);
    }

    interface Value {
        byte @Nullable [] data();
    }
}
