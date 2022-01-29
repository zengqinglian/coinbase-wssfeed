package qinglian.zeng.coinbase.ws.feed.websocket;

public class HeartBeat extends FeedBaseMessage{
    private Long last_trade_id;

    public HeartBeat() {
        setType("heartbeat");
    }

    public HeartBeat(Long last_trade_id) {
        this();
        this.last_trade_id = last_trade_id;
    }

    public Long getLast_trade_id() {
        return last_trade_id;
    }

    public void setLast_trade_id(Long last_trade_id) {
        this.last_trade_id = last_trade_id;
    }
}
