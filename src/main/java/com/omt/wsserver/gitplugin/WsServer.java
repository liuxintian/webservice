package com.omt.wsserver.gitplugin;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;


/**
 * Java_websocket server First for voting test
 * @author tonyliu
 *
 */
public class WsServer extends WebSocketServer {
		private static Logger omtlogger = Logger.getLogger(WsServer.class);
		
		private static List<WebSocket> connList = new ArrayList<WebSocket>();
		
	    public WsServer(InetSocketAddress address) {
	        super(address);
	    }
	    
	    public static void broadcast(String msg) {
	        for (WebSocket client : connList) {
	            try {
	                synchronized (client) {
	                	omtlogger.info("broadcast message: "+ msg);
	                    client.send(msg);
	                }
	            } catch (Exception e) {
	            	connList.remove(client);
	                try {
	                    client.close();
	                } catch (Exception e1) {
	                    // Ignore
	                }
	                String message = String.format("* %s %s", client.getRemoteSocketAddress(), "has been disconnected.");
	                omtlogger.info(message);
	            }
	        }
	    }
	    
	    @Override
	    public void onOpen(WebSocket conn, ClientHandshake handshake) {
	        omtlogger.info("new connection to " + conn.getRemoteSocketAddress());
	        handshake.getResourceDescriptor();
	        handshake.getFieldValue("Username");
	        connList.add(conn);
	        //conn.send("server sent this message...");
	    }

	    @Override
	    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
	        omtlogger.info("closed " + conn.getRemoteSocketAddress() + " with exit code " + code + " additional info: " + reason);
	        connList.remove(conn);
	    }

	    @Override
	    public void onMessage(WebSocket conn, String message) {
	        omtlogger.info("received message from " + conn.getRemoteSocketAddress() + ": " + message);
	    }

	    @Override
	    public void onError(WebSocket conn, Exception ex) {
	        System.err.println("an error occured on connection " + conn.getRemoteSocketAddress()  + ":" + ex);
	    }

	    private static WebSocketServer server;
	    private static String host = "localhost";
	    private static int port = 9091;
	    public static void getWssServerInstance(){
	    	if(server == null){
		        server = new WsServer(new InetSocketAddress(host, port));
		        server.start();
	    	}
	    }
	    
	    public static void main(String[] args) {
	        WebSocketServer server = new WsServer(new InetSocketAddress(host, port));
	        server.run();
	    }
}
