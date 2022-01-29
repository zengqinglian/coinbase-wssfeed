package qinglian.zeng.coinbase.ws.feed.websocket;

import java.math.BigDecimal;
import java.time.Instant;

public class TickerMessage extends FeedBaseMessage{
    private Long trade_id;
    private Long sequence;
    private Instant time;
    private String product_id;
    private BigDecimal price;
    private String side;
    private BigDecimal last_size;
    private BigDecimal best_bid;
    private BigDecimal best_ask;
    private StringBuilder sb = new StringBuilder();

    public TickerMessage() {
        setType("ticker");
    }

    public TickerMessage(Long trade_id,
                         Long sequence,
                         Instant time,
                         String product_id,
                         BigDecimal price,
                         String side,
                         BigDecimal last_size,
                         BigDecimal best_bid,
                         BigDecimal best_ask) {
        this();
        this.trade_id = trade_id;
        this.sequence = sequence;
        this.time = time;
        this.product_id = product_id;
        this.price = price;
        this.side = side;
        this.last_size = last_size;
        this.best_bid = best_bid;
        this.best_ask = best_ask;
    }

    public Long getTrade_id() {
        return trade_id;
    }

    public void setTrade_id(Long trade_id) {
        this.trade_id = trade_id;
    }

    @Override
    public Long getSequence() {
        return sequence;
    }

    @Override
    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }

    @Override
    public Instant getTime() {
        return time;
    }

    @Override
    public void setTime(Instant time) {
        this.time = time;
    }

    @Override
    public String getProduct_id() {
        return product_id;
    }

    @Override
    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public BigDecimal getLast_size() {
        return last_size;
    }

    public void setLast_size(BigDecimal last_size) {
        this.last_size = last_size;
    }

    public BigDecimal getBest_bid() {
        return best_bid;
    }

    public void setBest_bid(BigDecimal best_bid) {
        this.best_bid = best_bid;
    }

    public BigDecimal getBest_ask() {
        return best_ask;
    }

    public void setBest_ask(BigDecimal best_ask) {
        this.best_ask = best_ask;
    }

    public void printBestBidAndAsk (){
        sb.append("Product:  " + product_id);
        sb.append(System.lineSeparator());
        sb.append("Best Bid: " + best_bid);
        sb.append(System.lineSeparator());
        sb.append("Best Ask: " + best_ask);
        sb.append(System.lineSeparator());
        System.out.println(sb.toString());
        sb.setLength(0);
    }
}
