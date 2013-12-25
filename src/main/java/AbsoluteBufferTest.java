import java.nio.ByteBuffer;

public class AbsoluteBufferTest {
	public static void main(String[] args) {
		ByteBuffer buf = ByteBuffer.allocate(10);
		System.out.println("init position: " + buf.position());
		System.out.println("init limit: " + buf.limit());
		System.out.println("init capacity: " + buf.capacity());

		buf.put(3, (byte)3);
		buf.put(4, (byte)4);
		buf.put(5, (byte)5);

		System.out.println("position: " + buf.position());
		System.out.println("value: " + buf.get(3) + ", position: " + buf.position());
		System.out.println("value: " + buf.get(4) + ", position: " + buf.position());
		System.out.println("value: " + buf.get(5) + ", position: " + buf.position());
		System.out.println("value: " + buf.get(9) + ", position: " + buf.position());

	}
}
