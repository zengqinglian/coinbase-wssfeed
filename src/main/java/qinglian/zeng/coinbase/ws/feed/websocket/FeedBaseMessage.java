package qinglian.zeng.coinbase.ws.feed.websocket;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.Instant;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type",  include = JsonTypeInfo.As.PROPERTY, visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = SubscriptionsMessage.class, name = "subscriptions"),
        @JsonSubTypes.Type(value = HeartBeat.class, name = "heartbeat"),
        @JsonSubTypes.Type(value = TickerMessage.class, name = "ticker"),
        @JsonSubTypes.Type(value = SnapshotMessage.class, name = "snapshot"),
        @JsonSubTypes.Type(value = L2UpdateMessage.class, name = "l2update"),
})
public abstract class FeedBaseMessage {
    private String type;
    private Long sequence;
    private Instant time;
    private String product_id;

    public void setType(String type) {
        this.type = type;
    }

    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getType() {
        return type;
    }

    public Long getSequence() {
        return sequence;
    }

    public Instant getTime() {
        return time;
    }

    public String getProduct_id() {
        return product_id;
    }
}
