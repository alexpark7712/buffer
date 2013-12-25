import java.nio.ByteBuffer;

public class SliceTest {
	public static void main(String[] args) {
		ByteBuffer buf = ByteBuffer.allocate(10);
		for (int i = 0; i < buf.limit(); i++) {
			buf.put(i, (byte)i);
		}

		for (int i = 0; i < buf.limit(); i++) {
			System.out.println("buf[" + i + "]= " + buf.get(i));
		}


		buf.position(3);
		buf.limit(9);

		System.out.println("position: " + buf.position() + ", limit: " + buf.limit());

		ByteBuffer buf2 = buf.slice();

		System.out.println("position: " + buf2.position() + ", limit: " + buf2.limit());

		for (int i = 0; i < buf2.limit(); i++) {
			System.out.println("buf2[" + i + "]= " + buf2.get(i));
		}


	}
}
