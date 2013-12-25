import java.nio.Buffer;
import java.nio.ByteBuffer;

public class OtherDataTest {
	public static void main(String[] args) {
		ByteBuffer buff = ByteBuffer.allocate(10);

		print(buff);
		buff.putInt(100);
		print(buff);
	}

	public static void print(Buffer buf) {
		System.out.println("position=" + buf.position() + ", limit=" + buf.limit() + ", capacity=" + buf.capacity());
	}
}
