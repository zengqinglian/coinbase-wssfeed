package qinglian.zeng.coinbase.ws.feed.websocket;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class FeedEncoder implements Encoder.Text<FeedBaseMessage> {

    @Override
    public String encode(FeedBaseMessage feedBaseMessage) throws EncodeException {
        return null;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
