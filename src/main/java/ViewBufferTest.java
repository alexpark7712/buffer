import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class ViewBufferTest {
	public static void main(String[] args) {
		ByteBuffer buf = ByteBuffer.allocate(10);
		//buf.position(1);

		IntBuffer ib = buf.asIntBuffer();

		System.out.println("position=" + ib.position() + ", limit=" + ib.limit() + ", capacity=" + ib.capacity());

		ib.put(1024).put(2048);

		System.out.println(ib.get(0) + " " + ib.get(1));

		System.out.println("test");

		buf.rewind();
		while (buf.hasRemaining()) {
			System.out.print(buf.get() + " ");
		}
	}
}
