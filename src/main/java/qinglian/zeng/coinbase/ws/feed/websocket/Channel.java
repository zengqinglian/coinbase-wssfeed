package qinglian.zeng.coinbase.ws.feed.websocket;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Channel {
    @JsonProperty("name")
    private String name;

    @JsonProperty("product_ids")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String[] productIds;

    public Channel() {
    }

    public Channel(String name, String[] productIds){
        this.name = name;
        this.productIds = productIds;
    }

    public String getName() {
        return name;
    }

    public String[] getProductIds() {
        return productIds;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProductIds(String[] productIds) {
        this.productIds = productIds;
    }
}
