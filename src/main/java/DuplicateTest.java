import java.nio.ByteBuffer;

public class DuplicateTest {
	public static void main(String[] args) {
		ByteBuffer buf = ByteBuffer.allocate(10);
		for (int i = 0; i < buf.limit(); i++) {
			buf.put((byte) i);
		}

		buf.position(3);
		buf.limit(9);
		buf.mark();

		ByteBuffer buf2 = buf.duplicate();

		System.out.println("1) position: " + buf2.position() + ", limit: " + buf2.limit() + ", capacity: " + buf2.capacity());

		buf2.position(7);
		buf2.reset();
		System.out.println("reset() 호출 후 position: " + buf2.position());

		buf2.clear();

		System.out.println("2) position: " + buf2.position() + ", limit: " + buf2.limit() + ", capacity: " + buf2.capacity());

		while(buf2.hasRemaining()) {
			System.out.println(buf2.get() + " ");
		}

		buf.put(0, (byte)10);
		System.out.println("=> buf의 0값을 10으로 바꿈");

		System.out.println("buf value: " + buf.get(0));
		System.out.println("buf2 value: " + buf2.get(0));


	}
}
