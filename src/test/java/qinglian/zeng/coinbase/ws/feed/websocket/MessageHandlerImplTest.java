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
        String message = "{\"type\":\"ticker\",\"sequence\":33600429870,\"product_id\":\"BTC-USD\",\"price\":\"37613.15\",\"open_24h\":\"36855.34\",\"volume_24h\":\"14244.35387754\",\"low_24h\":\"36772.98\",\"high_24h\":\"38226.62\",\"volume_30d\":\"562384.81333581\",\"best_bid\":\"37613.15\",\"best_ask\":\"37613.16\",\"side\":\"sell\",\"time\":\"2022-01-29T17:04:40.144722Z\",\"trade_id\":272796563,\"last_size\":\"0.00058247\"}";
        messageHandler.handleMessage(message);
        Mockito.verify(orderBookManager,Mockito.times(1))
                .printOrderBook(ArgumentMatchers.eq("BTC-USD"), ArgumentMatchers.eq("2022-01-29T17:04:40.144722Z"));
    }

    @Test
    public void testHandleSnapshotMessage() {
        String message = "{\"type\":\"ticker\",\"sequence\":33600429870,\"product_id\":\"BTC-USD\",\"price\":\"37613.15\",\"open_24h\":\"36855.34\",\"volume_24h\":\"14244.35387754\",\"low_24h\":\"36772.98\",\"high_24h\":\"38226.62\",\"volume_30d\":\"562384.81333581\",\"best_bid\":\"37613.15\",\"best_ask\":\"37613.16\",\"side\":\"sell\",\"time\":\"2022-01-29T17:04:40.144722Z\",\"trade_id\":272796563,\"last_size\":\"0.00058247\"}";
        messageHandler.handleMessage(message);
        Mockito.verify(orderBookManager,Mockito.times(1))
                .printOrderBook(ArgumentMatchers.eq("BTC-USD"), ArgumentMatchers.eq("2022-01-29T17:04:40.144722Z"));
    }
    @Test
    public void testHandleL2UpdateMessage() {
        String message = "{\"type\":\"snapshot\",\"product_id\":\"ETH-USD\",\"asks\":[[\"2567.99\",\"1.71998230\"],[\"2568.00\",\"0.48943066\"]],\"bids\":[[\"2567.98\",\"0.00100000\"],[\"2567.54\",\"0.63612784\"]]}";
        messageHandler.handleMessage(message);

        Mockito.verify(orderBookManager,Mockito.times(2))
                .addAsk(ArgumentMatchers.eq("ETH-USD"), ArgumentMatchers.any());

        Mockito.verify(orderBookManager,Mockito.times(2))
                .addBid(ArgumentMatchers.eq("ETH-USD"), ArgumentMatchers.any());

    }
}
