package com.fratboy.study.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleChatServer {
	private static final String HOST = "localhost";
	private static final int PORT = 9090;
	private static final Logger logger = Logger.getLogger("com.fratboy.study");
	private static FileHandler fileHandler;
	private Selector selector;
	private ServerSocketChannel serverSocketChannel;
	private ServerSocket serverSocket;
	private Vector<SocketChannel> room = new Vector<SocketChannel>();

	public void initServer() {
		try {
			selector = Selector.open();

			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(false);

			serverSocket = serverSocketChannel.socket();

			serverSocket.bind(new InetSocketAddress(HOST, PORT));

			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		} catch (IOException e) {
			log(Level.WARNING, "SimpleChatServer.initServer()", e);
		}

	}

	public void startServer() {
		info("Server is started..");
		try {
			while (true) {
				info("요청을 기다리는중..");

				selector.select();

				Iterator<SelectionKey> it = selector.selectedKeys().iterator();
				while (it.hasNext()) {
					SelectionKey key = it.next();
					if (key.isAcceptable()) {
						accept(key);
					} else if (key.isReadable()) {
						read(key);
					}
				}

				it.remove();
			}
		} catch (Exception e) {
			log(Level.WARNING, "SimpleChatServer.startServer()", e);
		}
	}

	private void accept(SelectionKey key) {
		ServerSocketChannel server = (ServerSocketChannel)key.channel();
		SocketChannel sc;
		try {
			sc = server.accept();

			registerChannel(selector, sc, SelectionKey.OP_READ);
			info(sc.toString() + " 클라이언트가 접속했습니다.");
		} catch (ClosedChannelException e) {
			log(Level.WARNING, "SimpleChatServer.accept()", e);
		} catch (IOException e) {
			log(Level.WARNING, "SimpleChatServer.accept()", e);
		}

	}

	private void registerChannel(Selector selector, SocketChannel sc, int ops) throws IOException {
		if (sc == null) {
			info("Invalid Connection");
			return;
		}

		sc.configureBlocking(false);
		sc.register(selector, ops);

		addUser(sc);
	}

	private void read(SelectionKey key) {
		SocketChannel sc = (SocketChannel)key.channel();

		ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

		try {
			int read = sc.read(buffer);
			info(read + " byte를 읽었습니다.");
		} catch (IOException e) {
			try {
				sc.close();
			} catch (IOException e1) {
			}

			removeUser(sc);

			info(sc.toString() + " 클라이언트가 접속을 해제했습니다.");
		}

		try {
			broadcast(buffer);
		} catch (IOException e) {
			log(Level.WARNING, "SimpleChatServer.broadcast()", e);
		}

		clearBuffer(buffer);

	}

	private void broadcast(ByteBuffer buffer) throws IOException {
		buffer.flip();

		Iterator<SocketChannel> iter = room.iterator();

		while (iter.hasNext()) {
			SocketChannel sc = iter.next();
			if (sc != null) {
				sc.write(buffer);
				buffer.rewind();
			}
		}
	}

	private void clearBuffer(ByteBuffer buffer) {
		if(buffer != null) {
			buffer.clear();
			buffer = null;
		}

	}

	private void addUser(SocketChannel sc) {
		room.add(sc);
	}

	private void removeUser(SocketChannel sc) {
		room.remove(sc);
	}

	public void initLog() {
		logger.addHandler(new ConsoleHandler());
		logger.setLevel(Level.ALL);
	}

	private void log(Level level, String message, Exception e) {
		logger.log(level, message, e);
	}

	private void info(String message) {
		logger.info(message);
	}

	public static void main(String[] args) {
		SimpleChatServer scs = new SimpleChatServer();

		scs.initLog();
		scs.initServer();
		scs.startServer();
	}

}
