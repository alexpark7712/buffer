import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Timer;
import java.util.TimerTask;

public class TcpEchoClient {
	private static Timer timer = null;

	public TcpEchoClient(int seconds) throws Exception {
		timer = new Timer();
		timer.schedule(new EchoClientTask(), seconds * 1000);
	}

	public static void main(String[] args) throws Exception {
		new TcpEchoClient(2);

	}

	class EchoClientTask extends TimerTask {
		@Override
		public void run() {
			try {
				SocketChannel sc = SocketChannel.open();
				sc.configureBlocking(false);
				sc.connect(new InetSocketAddress("localhost", 8000));
				sc.finishConnect();

				ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

				while (!Thread.interrupted()) {
					buffer.clear();
					buffer.put(("test " + System.currentTimeMillis()).getBytes());
					buffer.flip();

					sc.write(buffer);
					buffer.clear();

					sc.read(buffer);
					buffer.flip();

					byte[] bb = new byte[buffer.limit()];
					buffer.get(bb, 0, buffer.limit());

					String data = new String(bb);
					System.out.println("receive: " + data);

					Thread.sleep(1000);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}
