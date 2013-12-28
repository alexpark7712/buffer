import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class FileLockTest {
	public static void main(String[] args) {
		FileChannel channel = null;

		try {
			File file = new File("//Users/fratboy/Downloads/text.txt");
			channel = new RandomAccessFile(file, "rw").getChannel();

			FileLock lock = channel.lock(0, Long.MAX_VALUE, false);

			try {
				boolean shared = lock.isShared();
				System.out.println("is shared lock? : " + shared);

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
			} finally {
				lock.release();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(channel != null) {
				try {
					channel.close();
				} catch (IOException ex) {
					//ignore
				}
			}
		}

	}
}
