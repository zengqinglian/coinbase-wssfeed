package qinglian.zeng.coinbase.ws.feed.websocket;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.management.RuntimeErrorException;
import javax.net.ssl.SSLContext;
import javax.websocket.*;
import java.net.URI;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

@ClientEndpoint
public class WebsocketFeed extends WebSocketClient {

    private static final Logger log = LoggerFactory.getLogger(WebsocketFeed.class);

    private final ObjectMapper objectMapper;
    private MessageHandler messageHandler;
    private boolean connectioned;
    private Subscribe subscribe;

    public WebsocketFeed(URI serverUri, Draft draft, ObjectMapper objectMapper) {
        super(serverUri, draft);
        this.objectMapper = objectMapper;
        this.subscribe = new Subscribe();

    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        System.out.println("Connection Open...");
        connectioned = true;
        System.out.println("Send Subscribe ...");
        try {
            send(objectMapper.writeValueAsString(subscribe));
        } catch (JsonProcessingException e) {
            System.out.println("failed to convert subscribe object to string");
        }
    }

    @Override
    public void onMessage(String s) {
        log.debug(s);
        messageHandler.handleMessage(s);
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        log.info("onClose" + s);
        super.close();
    }

    @Override
    public void onError(Exception e) {
        log.error("Error: " , e);
        e.printStackTrace();
    }

    public boolean isConnectioned() {
        return connectioned;
    }

    public void addMessageHandler(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }
}
