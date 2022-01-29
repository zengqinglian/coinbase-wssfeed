package qinglian.zeng.coinbase.ws.feed.websocket;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MessageHandlerImplTest {
    private ObjectMapper objectMapper = new ObjectMapper();
    private OrderBookManager orderBookManager = Mockito.mock(OrderBookManager.class);
    private MessageHandler messageHandler = new MessageHandlerImpl(objectMapper, orderBookManager);

    @Before
    public void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
    }
    @Test
    public void testHandleSubscriptionMessage() throws JsonProcessingException {
        String message = "{\"type\":\"subscriptions\",\"channels\":[{\"name\":\"ticker\",\"product_ids\":[\"ETH-USD\"]},{\"name\":\"level2\",\"product_ids\":[\"ETH-USD\"]},{\"name\":\"heartbeat\",\"product_ids\":[\"ETH-USD\"]}]}";
        messageHandler.handleMessage(message);
        Mockito.verify(orderBookManager,Mockito.times(1))
                .newOrderBook(ArgumentMatchers.eq("ETH-USD"));
    }
    @Test
    public void testHandleTickerMessage(){
    }

    @Test
    public void testHandleSnapshotMessage() {

    }
    @Test
    public void testHandleL2UpdateMessage() {

    }
}
