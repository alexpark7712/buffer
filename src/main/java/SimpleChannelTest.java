import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class SimpleChannelTest {
	public static void main(String[] args) throws IOException {
		ReadableByteChannel src = Channels.newChannel(System.in);
		WritableByteChannel dst = Channels.newChannel(System.out);

		ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

		while(src.read(buffer) != -1) {
			buffer.flip();
			while(buffer.hasRemaining()) {
				dst.write(buffer);
			}
			buffer.clear();
		}
	}
}
