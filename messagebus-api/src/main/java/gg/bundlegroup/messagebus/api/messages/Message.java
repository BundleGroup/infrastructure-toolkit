package gg.bundlegroup.messagebus.api.messages;

import gg.bundlegroup.messagebus.api.MessageBus;

import java.io.IOException;

public interface Message {
    byte[] data();

    String text();

    String subject();

    String replyTo();

    void publish() throws IOException;

    default Builder responseBuilder() {
        return builder().subject(replyTo());
    }

    static Builder builder() {
        return MessageBus.get().createMessageBuilder();
    }

    interface Builder {
        Builder content(byte[] data);

        Builder content(String text);

        Builder subject(String subject);

        Builder replyTo(String replyTo);

        Message build();
    }
}
