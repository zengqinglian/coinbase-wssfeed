package qinglian.zeng.coinbase.ws.feed.websocket;

public class Subscribe {
    private final static String SUBSCRIBE_MSG_TYPE = "subscribe";
    private final static String[] CHANNEL_NAMES = {"ticker", "level2", "heartbeat"};
    private final static String[] PRODUCT_IDS = {"ETH-USD", "BTC-USD"};

    private String type = SUBSCRIBE_MSG_TYPE;
    private String[] product_ids;
    private Channel[] channels;

    public Subscribe() {
        this.product_ids = PRODUCT_IDS;
        this.channels = new Channel[CHANNEL_NAMES.length];
        for(int i = 0; i< CHANNEL_NAMES.length; i++) {
            this.channels[i] = new Channel(CHANNEL_NAMES[i], product_ids);
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getProduct_ids() {
        return product_ids;
    }

    public void setProduct_ids(String[] product_ids) {
        this.product_ids = product_ids;
    }

    public void setChannels(Channel[] channels) {
        this.channels = channels;
    }

    public Channel[] getChannels() {
        return channels;
    }
}
