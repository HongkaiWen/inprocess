package reflect;

import java.lang.reflect.Method;

/**
 * Created by hongkai on 2016/3/17.
 */
public class GetMethodParamType {
    public static void main(String args[]){
        for(Method method : GetMethodParamType.class.getMethods()){
            System.out.println(getParameterType(method));
        }
    }

    private static Class getParameterType(Method method){
        Class<?>[] parameterTypes = method.getParameterTypes();
        if(parameterTypes.length > 0){
            return parameterTypes[0];
        }
        return null;
    }

    public void set(String abc){

    }
}
