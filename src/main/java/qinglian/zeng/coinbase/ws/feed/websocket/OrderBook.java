package qinglian.zeng.coinbase.ws.feed.websocket;

import it.unimi.dsi.fastutil.doubles.Double2DoubleAVLTreeMap;
import it.unimi.dsi.fastutil.doubles.Double2DoubleMap;

import java.util.Comparator;

public class OrderBook {
    private String productId;
    private Double2DoubleAVLTreeMap bids;
    private Double2DoubleAVLTreeMap asks;
    private StringBuilder sb = new StringBuilder();

    public OrderBook (String productId){
        bids = new Double2DoubleAVLTreeMap(Comparator.reverseOrder());
        asks = new Double2DoubleAVLTreeMap();
        this.productId = productId;
    }
    public void addBid(String[] bid) {
        updateOrderBook(bid, bids);
    }
    public void addAsk(String[] ask) {
        updateOrderBook(ask, asks);
    }

    private void updateOrderBook(String[] newValue , Double2DoubleAVLTreeMap map) {
        double price;
        double size;
        if(newValue.length == 2) {
            price = Double.parseDouble(newValue[0]);
            size = Double.parseDouble(newValue[1]);
        }else{
            price = Double.parseDouble(newValue[1]);
            size = Double.parseDouble(newValue[2]);
        }
        if(size == 0.0d) {
            map.remove(price);
        }else{
            map.put(price, size);
        }
    }

    public void print() {
        print("Bids Top 10", Math.min(bids.size(), 10), bids);
        print("Asks Top 10", Math.min(asks.size(), 10), asks);
    }

    private void print(String title, int depth, Double2DoubleAVLTreeMap book) {
        sb.append("--------------  ");
        sb.append(productId);
        sb.append("  ").append(title).append("  --------------");
        sb.append(System.lineSeparator()).append(System.lineSeparator());
        String header = "Price ------------------------ Size";
        sb.append(header);
        sb.append(System.lineSeparator());
        int loop = 0;
        for(Double2DoubleMap.Entry entry : book.double2DoubleEntrySet()) {
            int spaces = header.length() - String.valueOf(entry.getDoubleKey()).length() - 4 ;
            sb.append(entry.getDoubleKey());
            for(int i=0; i< spaces; i++) {
                sb.append(" ");
            }
            sb.append(entry.getDoubleValue());
            sb.append(System.lineSeparator());
            loop++;
            if(loop == depth) {
                break;
            }
        }
        sb.append(System.lineSeparator());
        sb.append("---------------PRINT END -------------------");
        sb.append(System.lineSeparator());
        System.out.println(sb.toString());
        sb.setLength(0);
    }

    public boolean isEmpty() {
        return bids.isEmpty() && asks.isEmpty();
    }

    Double2DoubleAVLTreeMap getBids(){
        return bids;
    }
    Double2DoubleAVLTreeMap getAsks(){
        return asks;
    }
}
