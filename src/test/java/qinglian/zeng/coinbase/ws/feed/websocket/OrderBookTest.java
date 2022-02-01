package qinglian.zeng.coinbase.ws.feed.websocket;

import org.junit.Assert;
import org.junit.Test;

public class OrderBookTest {
    OrderBook orderBook = new OrderBook("ABC");
    @Test
    public void testAddBid() {
        String[] test = {"1", "1"};
        orderBook.addBid(test);
        Assert.assertEquals(1,orderBook.getBids().size());
    }
    @Test
    public void testAddAsk() {
        String[] test = {"1", "1"};
        orderBook.addAsk(test);
        Assert.assertEquals(1,orderBook.getAsks().size());
    }
}
