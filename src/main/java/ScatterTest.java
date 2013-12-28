import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ScatteringByteChannel;

public class ScatterTest {
	public static void main(String[] args) throws Exception {
		FileInputStream fin = new FileInputStream("//Users/fratboy/Downloads/news.txt");
		ScatteringByteChannel channel = fin.getChannel();

		ByteBuffer header = ByteBuffer.allocateDirect(100);
		ByteBuffer body = ByteBuffer.allocateDirect(200);
		ByteBuffer[] buffers = {header, body};

		int readCount = (int) channel.read(buffers);
		channel.close();

		System.out.println("read count: " + readCount);

		System.out.println("\n//----------------------------------------------------------------------------//\n");

		header.flip();
		body.flip();

		byte[] b = new byte[100];
		header.get(b);

		System.out.println("header: " + new String(b));

		System.out.println("\n//----------------------------------------------------------------------------//\n");

		byte[] bb = new byte[200];
		body.get(bb);
		System.out.println("body: " + new String(bb));
	}
}
