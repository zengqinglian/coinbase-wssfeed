package qinglian.zeng.coinbase.ws.feed.websocket;

public class SubscriptionsMessage extends FeedBaseMessage{
    private Channel[] channels;

    public Channel[] getChannels() {
        return channels;
    }

    public void setChannels(Channel[] channels) {
        this.channels = channels;
    }
}
