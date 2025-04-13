package gg.bundlegroup.messagebus.api.messages;

import gg.bundlegroup.messagebus.api.MessageBus;

import java.io.IOException;

/**
 * Represents a generic message within the MessageBus system.
 * Messages contain data, subject identifiers, and optional reply subjects.
 */
public interface Message {

    /**
     * Returns the raw byte content of the message.
     *
     * @return the message content as a byte array
     */
    byte[] data();

    /**
     * Returns the message content interpreted as a UTF-8 string.
     *
     * @return the message content as a string
     */
    String text();

    /**
     * Returns the subject this message was sent to.
     *
     * @return the subject string
     */
    String subject();

    /**
     * Returns the subject to reply to, if any.
     *
     * @return the reply-to subject string, or null if not set
     */
    String replyTo();

    /**
     * Publishes the message to its specified subject.
     *
     * @throws IOException if the message cannot be published
     */
    void publish() throws IOException;

    /**
     * Creates a new {@link Builder} pre-configured to send a message
     * in response to this message's reply subject.
     *
     * @return a message builder with the reply subject pre-set
     */
    default Builder responseBuilder() {
        return builder().subject(replyTo());
    }

    /**
     * Creates a new message builder.
     *
     * @return a fresh {@link Builder} instance
     */
    static Builder builder() {
        return MessageBus.get().createMessageBuilder();
    }

    /**
     * Builder interface for constructing {@link Message} instances.
     */
    interface Builder {

        /**
         * Sets the raw byte content of the message.
         *
         * @param data the message content
         * @return this builder instance
         */
        Builder content(byte[] data);

        /**
         * Sets the string content of the message.
         * Automatically encoded using UTF-8.
         *
         * @param text the message content as a string
         * @return this builder instance
         */
        Builder content(String text);

        /**
         * Sets the subject the message will be sent to.
         *
         * @param subject the target subject
         * @return this builder instance
         */
        Builder subject(String subject);

        /**
         * Sets the reply subject for the message.
         *
         * @param replyTo the reply subject
         * @return this builder instance
         */
        Builder replyTo(String replyTo);

        /**
         * Builds the {@link Message} instance.
         *
         * @return the constructed message
         */
        Message build();
    }
}
