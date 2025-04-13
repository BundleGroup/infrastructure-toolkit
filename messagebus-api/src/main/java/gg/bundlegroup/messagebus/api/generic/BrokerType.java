package gg.bundlegroup.messagebus.api.generic;

public enum BrokerType {
    RABBIT_MQ("rabbitmq"),
    REDIS("redis"),
    NATS("nats"),
    KAKFA("kakfa");


    private String friendlyName;

    private BrokerType(String friendlyName) {
        this.friendlyName = friendlyName;
    }

}
