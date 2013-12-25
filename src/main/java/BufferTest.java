import java.nio.ByteBuffer;

public class BufferTest {
	public static void main(String[] args) {
		ByteBuffer buf = ByteBuffer.allocate(10);
		for (int i = 0; i < buf.limit(); i++) {
			buf.put(i, (byte)i);
		}

		//clear(buf)
		//rewind(buf);
		//flip(buf);
		compact(buf);

		for (int i = 0; i < buf.limit(); i++) {
			System.out.println("buf[" + i + "]= " + buf.get(i));
		}
	}

	private static void clear(ByteBuffer buf) {
		buf.limit(5);
		buf.put((byte)0).put((byte)1);

		System.out.println("position: " + buf.position() + ", limit: " + buf.limit());

		buf.clear();

		System.out.println("position: " + buf.position() + ", limit: " + buf.limit());
	}

	private static void rewind(ByteBuffer buf) {
		buf.limit(5);
		buf.put((byte)0).put((byte)1);

		System.out.println("position: " + buf.position() + ", limit: " + buf.limit());

		buf.clear();

		System.out.println("position: " + buf.position() + ", limit: " + buf.limit());
	}

	private static void flip(ByteBuffer buf) {
		buf.limit(5);
		buf.put((byte)0).put((byte)1);

		System.out.println("position: " + buf.position() + ", limit: " + buf.limit());

		buf.flip();

		System.out.println("position: " + buf.position() + ", limit: " + buf.limit());
	}

	private static void compact(ByteBuffer buf) {
		buf.limit(5);
		buf.put((byte)0).put((byte)1);

		System.out.println("position: " + buf.position() + ", limit: " + buf.limit());

		buf.compact();

		System.out.println("position: " + buf.position() + ", limit: " + buf.limit());
	}
}
