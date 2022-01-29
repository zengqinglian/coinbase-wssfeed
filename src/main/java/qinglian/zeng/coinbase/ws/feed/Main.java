package qinglian.zeng.coinbase.ws.feed;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.java_websocket.client.DefaultSSLWebSocketClientFactory;
import org.java_websocket.drafts.Draft_17;
import qinglian.zeng.coinbase.ws.feed.websocket.*;

import javax.net.ssl.SSLContext;
import java.net.URI;
import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) {
        WebsocketFeed feed = null;
        try {
            //Object mapper for json
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            OrderBookManager orderBookManager = new OrderBookManager();
            MessageHandler messageHandler = new MessageHandlerImpl(objectMapper, orderBookManager);

            //Feed init
            System.out.println("Feed init ....");
            feed = new WebsocketFeed(new URI("wss://ws-feed.exchange.coinbase.com:443"),
                    new Draft_17(), new ObjectMapper());
            SSLContext sslContext = SSLContext.getDefault();
            feed.setWebSocketFactory( new DefaultSSLWebSocketClientFactory( sslContext ) );
            feed.addMessageHandler(messageHandler);

            System.out.println("start connect ....");
            feed.connect();
            while(!feed.isConnectioned()){
                System.out.println("Connecting ....");
                Thread.sleep(1000);
            }

        } catch (Exception e) {
            System.out.println(e.getCause());
            if(feed!=null && feed.getConnection()!=null && feed.getConnection().isOpen()) {
                feed.close();
            }
        }

        CountDownLatch doneSignal = new CountDownLatch(1);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> doneSignal.countDown()));

        try {
            doneSignal.await();
        } catch (InterruptedException e) {
            System.out.println("Handle control c failed." + e.getCause());
        }finally {
            if (feed.getConnection().isOpen()) {
                feed.close();
            }
            System.out.println("Connection closed ....");
        }
    }
}
