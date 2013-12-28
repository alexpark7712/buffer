import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class TCPEchoServer {
	private int port;

	public TCPEchoServer(int port) {
		this.port = port;
	}

	private void execute() throws IOException, InterruptedException {
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.socket().bind(new InetSocketAddress("localhost", 8000));
		ssc.configureBlocking(false);

		ByteBuffer buff = ByteBuffer.allocateDirect(1024);

		while (true) {
			System.out.println("waiting...");

			SocketChannel sc = ssc.accept();

			if (sc == null) {
				System.out.println("도착한 패킷이 없습니다.");
				Thread.sleep(5000);
			} else {
				System.out.println("패킷이 도착 했습니다.");

				buff.clear();
				sc.read(buff);

				buff.flip();
				sc.write(buff);
			}
		}
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		new TCPEchoServer(8000).execute();
	}
}
