package com.omt.wsserver.gitplugin;

import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_10;
import org.java_websocket.handshake.ServerHandshake;

	public class WsClient extends WebSocketClient {

	    public WsClient(URI serverUri, Draft draft) {
	        super(serverUri, draft);
	    }

	    public WsClient(URI serverURI) {
	        super(serverURI);
	    }

	    @Override
	    public void onOpen(ServerHandshake handshakedata) {
	        System.out.println("new connection opened");
	    }

	    @Override
	    public void onClose(int code, String reason, boolean remote) {
	        System.out.println("closed with exit code " + code + " additional info: " + reason);
	    }

	    @Override
	    public void onMessage(String message) {
	        System.out.println("client received message: " + message);
	        while(true){
	        	try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        this.getConnection().send("client send this message...");
	        }
	    }

	    @Override
	    public void onError(Exception ex) {
	        System.err.println("an error occurred:" + ex);
	    }

	    public static void main(String[] args) throws URISyntaxException {      
	        // ------------------------------------------------------------------------------------------------------
	    	
	        WebSocketClient client = new WsClient(new URI("ws://localhost:8887"), new Draft_10());
	        client.connect();
	        // ------------------------------------------------------------------------------------------------------
	        
	        // ------------------------------------------------------------------------------------------------------
//	        WebSocketClient wsClient = new WsClient(new URI("ws://localhost:8887"), new Draft_10());
//	        SSLContext sslContext = null;
//	        try {
//	            sslContext = SSLContext.getDefault();
//	        } catch (NoSuchAlgorithmException e) {
//	            e.printStackTrace();
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
//
//	        wsClient.setWebSocketFactory( new DefaultSSLWebSocketClientFactory( sslContext ) );
//	        wsClient.connect();
	        // ------------------------------------------------------------------------------------------------------
	    }
	}
