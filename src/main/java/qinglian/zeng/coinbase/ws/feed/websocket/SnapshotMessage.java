package qinglian.zeng.coinbase.ws.feed.websocket;

public class SnapshotMessage extends FeedBaseMessage{
    private String[][] bids;
    private String[][] asks;

    public SnapshotMessage() {
        setType("snapshot");
    }

    public SnapshotMessage(String[][] bids, String[][] asks) {
        this();
        this.bids = bids;
        this.asks = asks;
    }

    public String[][] getBids() {
        return bids;
    }

    public void setBids(String[][] bids) {
        this.bids = bids;
    }

    public String[][] getAsks() {
        return asks;
    }

    public void setAsks(String[][] asks) {
        this.asks = asks;
    }
}
