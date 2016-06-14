package mongo;

import com.mongodb.BasicDBObject;
import net.sf.cglib.beans.BeanMap;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
   
public class MongoUtils {

    /**
     * java 对象转换成 BasicDBObject
     * @param target
     * @return
     */
    public static BasicDBObject toBasicDBObject(Object target){
        return new BasicDBObject(beanToMap(target));
    }

    /**
     * java 对象转换成 BasicDBObject
     * @param target
     * @param ignoreFields  忽略的字段列表
     * @return
     */
    public static BasicDBObject toBasicDBObject(Object target, String... ignoreFields){
        BasicDBObject result = toBasicDBObject(target);
        if(ignoreFields != null && ignoreFields.length != 0){
            for(String ignoreField : ignoreFields){
                result.remove(ignoreField);
            }
        }
        return result;
    }

    private static Map<String, Object> beanToMap(Object object) {
        BeanMap beanMap = getBeanMap(object);
        beanMap.setBean(object);
        Map<String, Object> toMap = beanMap;
        for (Entry<String, Object> entry : toMap.entrySet()) {
            if (entry.getValue() != null) {
                toMap.put(entry.getKey(), entry.getValue());
            }
        }
        return toMap;
    }

    private static BeanMap getBeanMap(Object object) {
        BeanMap beanMap = beanMapCache.get(object.getClass().getName());
        if (beanMap == null) {
            generator.setBean(object);
            beanMap = generator.create();
            beanMapCache.put(object.getClass().getName(), beanMap);
        }
        return beanMap;
    }

    private static ConcurrentMap<String, BeanMap> beanMapCache = new ConcurrentHashMap<String, BeanMap>();
    private static BeanMap.Generator generator = new BeanMap.Generator();

}