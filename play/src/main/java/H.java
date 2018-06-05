import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * Created by hongkai on 2017/5/18.
 */
public class H {

    public static void main(String args[]) throws IOException {
        System.out.println(SelectionKey.OP_ACCEPT);
        System.out.println(SelectionKey.OP_CONNECT);
        System.out.println(SelectionKey.OP_READ);
        System.out.println(SelectionKey.OP_WRITE);

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("jenkov.com", 80));

        while(! socketChannel.finishConnect() ){
            //wait, or do something else...
        }

        ByteBuffer allocate = ByteBuffer.allocate(1024);

        socketChannel.read(allocate);

        System.out.println(new String(allocate.array()));

    }


}
