package gg.bundlegroup.messagebus.rabbitmq;

import com.rabbitmq.client.Channel;
import gg.bundlegroup.messagebus.api.messages.Message;

import java.io.IOException;

/**
 * Represents a RabbitMQ message that can be published to a RabbitMQ exchange.
 * <p>
 * This class implements the {@link Message} interface and provides methods for accessing the
 * message data, text content, subject, and reply-to information, as well as publishing the message.
 * </p>
 */
public class RabbitMQMessage implements Message {
    private final Channel channel;
    private final byte[] data;
    private final String text;
    private final String subject;
    private final String replyTo;
    private final String exchangeType;

    /**
     * Constructs a RabbitMQMessage with the specified data, text, subject, and reply-to fields.
     *
     * @param channel the channel we want to publish to.
     * @param data    the byte array representing the message content.
     * @param text    the human-readable text content of the message.
     * @param subject the subject or routing key of the message.
     * @param replyTo the reply-to address for the message.
     */
    public RabbitMQMessage(Channel channel, byte[] data, String text, String subject, String replyTo, String exchangeType) {
        this.channel = channel;
        this.data = data;
        this.text = text;
        this.subject = subject;
        this.replyTo = replyTo;
        this.exchangeType = exchangeType;
    }

    /**
     * Returns the raw data of the message as a byte array.
     * This could be used for transmitting binary data in the message body.
     *
     * @return the byte array containing the message data.
     */
    @Override
    public byte[] data() {
        return data;
    }

    /**
     * Returns the text representation of the message.
     * This is often a human-readable string or the actual message content.
     *
     * @return the message content as a string.
     */
    @Override
    public String text() {
        return text;
    }

    /**
     * Returns the subject or routing key of the message.
     * This can be used for routing the message to the appropriate consumer in RabbitMQ.
     *
     * @return the subject of the message.
     */
    @Override
    public String subject() {
        return subject;
    }

    /**
     * Returns the reply-to address for the message.
     * This is used to direct any response messages to the appropriate address.
     *
     * @return the reply-to address of the message.
     */
    @Override
    public String replyTo() {
        return replyTo;
    }

    /**
     * Publishes the message to RabbitMQ.
     * This method should implement the logic for sending the message to the RabbitMQ exchange.
     */
    @Override
    public void publish() throws IOException {
        channel.exchangeDeclare(subject, exchangeType, true);
        channel.basicPublish("", subject, null, this.data());
    }
}
