package qinglian.zeng.coinbase.ws.feed.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class FeedDecoder implements Decoder.Text<FeedBaseMessage> {
    private ObjectMapper objectMapper;

    public FeedDecoder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    @Override
    public FeedBaseMessage decode(String s) throws DecodeException {
        try {
            return objectMapper.readValue(s, FeedBaseMessage.class);
        } catch (JsonProcessingException e) {
            throw new DecodeException("Json exception","Json exception",e);
        }
    }

    @Override
    public boolean willDecode(String s) {
        return true;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
