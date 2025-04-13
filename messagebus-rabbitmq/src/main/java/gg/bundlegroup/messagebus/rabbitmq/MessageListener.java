package gg.bundlegroup.messagebus.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

/**
 * An abstract listener that subscribes to a RabbitMQ queue and handles incoming messages.
 * <p>
 * Extend this class to define custom behavior when messages are received from a queue.
 * </p>
 *
 * <pre>{@code
 * public class MyListener extends MessageListener {
 *     @Override
 *     public String queue() {
 *         return "logs";
 *     }
 *
 *     @Override
 *     public void onMessage(String message) {
 *         System.out.println("Received: " + message);
 *     }
 * }
 * }</pre>
 *
 * <p>
 * To start listening, call {@link #register(Channel)} with an active RabbitMQ channel.
 * </p>
 */
public abstract class MessageListener {
    public abstract String queue();
    public abstract void onMessage(String message);

    public void register(Channel channel) throws Exception {
        channel.queueDeclare(queue(), true, false, false, null);

        DeliverCallback callback = (tag, delivery) -> {
            String msg = new String(delivery.getBody(), StandardCharsets.UTF_8);
            onMessage(msg);
        };

        channel.basicConsume(queue(), true, callback, tag -> {});
    }
}
