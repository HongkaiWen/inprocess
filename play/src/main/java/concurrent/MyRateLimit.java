package concurrent;

import org.apache.commons.collections.Buffer;
import org.apache.commons.collections.BufferUtils;
import org.apache.commons.collections.buffer.CircularFifoBuffer;

/**
 * Created by hongkai on 2016/7/13.
 */
public class MyRateLimit {

    private Buffer requestHistory;

    public MyRateLimit(int requestCount){
        requestHistory = BufferUtils.synchronizedBuffer(new CircularFifoBuffer(requestCount));
    }

    public boolean acquire(){
        Long current = System.currentTimeMillis();
        Long oldestRequest = (Long) requestHistory.get();
        if((current - oldestRequest) >= 1000L){
            return false;
        } else {
            requestHistory.add(current);
            return true;
        }
    }
}
