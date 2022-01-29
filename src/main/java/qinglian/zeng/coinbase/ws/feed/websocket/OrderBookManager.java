package qinglian.zeng.coinbase.ws.feed.websocket;

import java.util.HashMap;
import java.util.Map;

public class OrderBookManager {
    private Map<String, OrderBook> orderBookMap = new HashMap<>();

    public void newOrderBook (String productId) {
        if(!orderBookMap.containsKey(productId)) {
            OrderBook orderBook = new OrderBook(productId);
            orderBookMap.put(productId, orderBook);
        }
    }

    public void addBid (String productId, String[] bid) {
        OrderBook orderBook = orderBookMap.get(productId);
        if(orderBook == null) {
            System.out.println("Not subscribed product : " + productId);
        }
        orderBook.addBid(bid);
    }

    public void addAsk (String productId, String[] ask) {
        OrderBook orderBook = orderBookMap.get(productId);
        if(orderBook == null) {
            System.out.println("Not subscribed product : " + productId);
        }else {
            orderBook.addAsk(ask);
        }
    }

    public void printOrderBook(String productId, String timeStamp) {
        OrderBook orderBook = orderBookMap.get(productId);
        if(orderBook == null) {
            System.out.println("Not yet subscribe this product : " + productId);
            return;
        }
        if(!orderBook.isEmpty()){
            System.out.println("-------------- " + timeStamp + " ----------------");
            System.out.println(System.lineSeparator());
            orderBook.print();
        }
    }

    public void printAllProductOrderBook(String timeStamp){
        orderBookMap.keySet().forEach(key -> printOrderBook(key, timeStamp));
    }

    public void update(String productId, String[] update) {
        if("buy".equals(update[0])){
            addBid(productId, update);
        }else{
            addAsk(productId, update);
        }
    }
}
