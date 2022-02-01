package qinglian.zeng.coinbase.ws.feed.websocket;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

public class OrderBookManagerTest {

    private OrderBookManager orderBookManager = new OrderBookManager();

    @Test
    public void testAddBid() {
        OrderBook orderBook = Mockito.mock(OrderBook.class);
        orderBookManager.getOrderBookMap().put("ABC",orderBook );
        String[] test = {"1","1"};
        orderBookManager.addBid("ABC", test);
        Mockito.verify(orderBook,Mockito.times(1)).addBid(ArgumentMatchers.eq(test));
    }

    @Test
    public void testAddAsk() {
        OrderBook orderBook = Mockito.mock(OrderBook.class);
        orderBookManager.getOrderBookMap().put("ABC",orderBook );
        String[] test = {"1","1"};
        orderBookManager.addAsk("ABC", test);
        Mockito.verify(orderBook,Mockito.times(1)).addAsk(ArgumentMatchers.eq(test));
    }

    @Test
    public void testUpdate(){
        OrderBook orderBook = Mockito.mock(OrderBook.class);
        orderBookManager.getOrderBookMap().put("ABC",orderBook );
        String[] test = {"buy","1","1"};
        orderBookManager.update("ABC", test);
        Mockito.verify(orderBook,Mockito.times(1)).addBid(ArgumentMatchers.eq(test));
    }

    @Test
    public void testPrint(){
        OrderBook orderBook = Mockito.mock(OrderBook.class);
        orderBookManager.getOrderBookMap().put("ABC",orderBook );
        orderBookManager.printOrderBook("ABC", "2022-01-29T17:04:40.144722Z");
        Mockito.verify(orderBook,Mockito.times(1)).print();
    }

    @Test
    public void testCreateNewOrderBook() {
        orderBookManager.newOrderBook("ABC");
        Assert.assertTrue(orderBookManager.getOrderBookMap().containsKey("ABC"));
    }
}
