import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class SharedFilechannelInstanceTest {
	public static void main(String[] args) throws Exception {
		RandomAccessFile raf = new RandomAccessFile("/Users/fratboy/Downloads/test.txt", "rw");

		raf.seek(10);

		FileChannel fc = raf.getChannel();
		System.out.println("file position: " + fc.position());

		raf.seek(20);
		System.out.println("file position: " + fc.position());

		fc.position(10);
		System.out.println("file position: " + raf.getFilePointer());
	}
}
