package codingtest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.math.BigInteger;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.Test;

public class ComparableTest {
    @Test(expected=ClassCastException.class)
    public void testTreeSet() {
        TreeSet<Object> treeSet = new TreeSet<>();
        treeSet.add(new Object());
        treeSet.add(new Object());
    }

    @Test(expected=ClassCastException.class)
    public void testTreeMap() {
        TreeMap<Object, String> treeSet = new TreeMap<>();
        treeSet.put(new Object(), "AA");
        treeSet.put(new Object(), "BB");
    }

    @Test
    public void testProxy() {
        MyInvocationHandler handler = new MyInvocationHandler();
        A o = (A) Proxy.newProxyInstance(MyInvocationHandler.class.getClassLoader(),
                        new Class[] {A.class}, handler);
        o.f();
    }

    @Test
    public void testBigInteger() {
        BigInteger bi = new BigInteger("11");
        BigInteger bi2 = new BigInteger("12");

        System.out.println(bi.add(bi2));
    }

}

class MyInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        System.out.println(method);
        return new Integer(1);
    }
}

interface A {
    public int f();
}