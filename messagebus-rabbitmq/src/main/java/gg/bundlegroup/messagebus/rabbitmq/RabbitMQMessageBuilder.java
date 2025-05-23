package gg.bundlegroup.messagebus.rabbitmq;

import com.rabbitmq.client.Channel;
import gg.bundlegroup.messagebus.api.messages.Message;

public class RabbitMQMessageBuilder implements Message.Builder {
    byte[] data;
    String content;
    String subject;
    String replyTo;
    Channel channel;

    public RabbitMQMessageBuilder(Channel connection) {
        channel = connection;
    }

    @Override
    public Message.Builder content(byte[] data) {
        this.data = data;
        return this;
    }

    @Override
    public Message.Builder content(String text) {
        this.content = text;
        return this;
    }

    @Override
    public Message.Builder subject(String subject) {
        this.subject = subject;
        return this;
    }

    @Override
    public Message.Builder replyTo(String replyTo) {
        this.replyTo = replyTo;
        return this;
    }

    @Override
    public Message build() {
        return new RabbitMQMessage(
                channel,
                data,
                content,
                subject,
                replyTo,
                "fanout"
        );
    }
}
