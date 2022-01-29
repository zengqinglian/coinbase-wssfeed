package qinglian.zeng.coinbase.ws.feed.websocket;

public class L2UpdateMessage extends FeedBaseMessage{
    private String[][] changes;

    public L2UpdateMessage() {
        setType("l2update");
    }

    public L2UpdateMessage(String[][] changes) {
        this();
        this.changes = changes;
    }

    public String[][] getChanges() {
        return changes;
    }

    public void setChanges(String[][] changes) {
        this.changes = changes;
    }
}
