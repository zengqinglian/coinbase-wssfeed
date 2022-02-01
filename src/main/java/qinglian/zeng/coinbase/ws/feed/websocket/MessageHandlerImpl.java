package qinglian.zeng.coinbase.ws.feed.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class MessageHandlerImpl implements MessageHandler{

    private ObjectMapper objectMapper;
    private Map<String, Consumer<FeedBaseMessage>> map;
    private OrderBookManager orderBookManager;

    public MessageHandlerImpl(ObjectMapper objectMapper, OrderBookManager orderBookManager) {
        this.objectMapper = objectMapper;
        map = new HashMap<>(){{
            put("subscriptions", subscriptionsMessageConsumer());
            put("snapshot", snapshotMessageConsumer());
            put("ticker", tickerMessageConsumer());
            put("l2update", l2updateMessageConsumer());
        }};
        this.orderBookManager = orderBookManager;
    };

    @Override
    public void handleMessage(String message) {
        try {
            FeedBaseMessage feedBaseMessage = objectMapper.readValue(message, FeedBaseMessage.class);
            String type = feedBaseMessage.getType();
            if(map.containsKey(type)) {
                map.get(type).accept(feedBaseMessage);
            }
        }catch (JsonMappingException e) {
            System.out.println("JsonProcessingException" );
        } catch (JsonProcessingException e) {
            System.out.println("JsonProcessingException" );
        } catch (Exception e) {
            System.out.println("Errors!" );
        }
    }

    private Consumer<FeedBaseMessage> subscriptionsMessageConsumer(){
        return (message) -> {
            SubscriptionsMessage subscriptionsMessage = (SubscriptionsMessage)message;
            for(Channel channel : subscriptionsMessage.getChannels()) {
                for(String productId : channel.getProductIds())
                    orderBookManager.newOrderBook(productId);
            }
        };
    }
    private Consumer<FeedBaseMessage> snapshotMessageConsumer() {
        return (message) -> {
            SnapshotMessage snapshotMessage = (SnapshotMessage)message;
            String productId = snapshotMessage.getProduct_id();
            for(String[] ask : snapshotMessage.getAsks()){
                orderBookManager.addAsk(productId, ask);
            }
            for(String[] bid : snapshotMessage.getBids()){
                orderBookManager.addBid(productId, bid);
            }
        };
    }

    private Consumer<FeedBaseMessage> tickerMessageConsumer(){
        return message-> {
            TickerMessage tickerMessage = (TickerMessage)message;
            Instant instant = tickerMessage.getTime();
            String productId = tickerMessage.getProduct_id();
            tickerMessage.printBestBidAndAsk();
            orderBookManager.printOrderBook(productId, instant.toString());
        };
    }

    private Consumer<FeedBaseMessage> l2updateMessageConsumer(){

        return (message) -> {
            L2UpdateMessage l2UpdateMessage = (L2UpdateMessage) message;
            String productId = l2UpdateMessage.getProduct_id();
            for(String[] update : l2UpdateMessage.getChanges()) {
                orderBookManager.update(productId, update);
            }
        };
    }
}
