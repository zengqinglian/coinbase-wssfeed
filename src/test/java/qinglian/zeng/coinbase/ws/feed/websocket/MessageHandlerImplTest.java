package qinglian.zeng.coinbase.ws.feed.websocket;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

public class MessageHandlerImplTest {
    private ObjectMapper objectMapper = new ObjectMapper();
    private OrderBookManager orderBookManager;
    private MessageHandler messageHandler;

    @Before
    public void setUp() {
        orderBookManager = Mockito.mock(OrderBookManager.class);
        objectMapper.registerModule(new JavaTimeModule());
        messageHandler = new MessageHandlerImpl(objectMapper, orderBookManager);
    }

    @Test
    public void testHandleSubscriptionMessage(){
        String message = "{\"type\":\"subscriptions\",\"channels\":[{\"name\":\"ticker\",\"product_ids\":[\"ETH-USD\"]},{\"name\":\"level2\",\"product_ids\":[\"ETH-USD\"]},{\"name\":\"heartbeat\",\"product_ids\":[\"ETH-USD\"]}]}";
        messageHandler.handleMessage(message);
        Mockito.verify(orderBookManager,Mockito.times(3))
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
