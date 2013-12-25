import java.nio.ByteBuffer;

public class RelativeBufferTest {
	public static void main(String[] args) {
		ByteBuffer buf = ByteBuffer.allocate(10);
		System.out.println("init position: " + buf.position());
		System.out.println("init limit: " + buf.limit());
		System.out.println("init capacity: " + buf.capacity());

		buf.mark();

		buf.put((byte) 10).put((byte) 11).put((byte) 12);
		buf.reset();

		System.out.println("value: " + buf.get() + ", position: " + buf.position());
		System.out.println("value: " + buf.get() + ", position: " + buf.position());
		System.out.println("value: " + buf.get() + ", position: " + buf.position());
		System.out.println("value: " + buf.get() + ", position: " + buf.position());
	}
}
