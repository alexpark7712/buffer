import java.nio.ByteBuffer;

public class BulkWriteTest {
	public static void main(String[] args) {
		ByteBuffer buf = ByteBuffer.allocate(10);
		buf.position(5);
		buf.mark();
		System.out.println("position: " + buf.position() + ", limit: " + buf.limit());

		byte[] b = new byte[15];
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte)i;
		}

		int size = buf.remaining();
		if (b.length < size) {
			size = b.length;
		}

		buf.put(b, 0, size);
		System.out.println("position: " + buf.position() + ", limit: " + buf.limit());

		for (int i = 0; i < buf.limit(); i++) {
			System.out.println("buf[" + i + "]= " + buf.get(i));
		}

	}
}
