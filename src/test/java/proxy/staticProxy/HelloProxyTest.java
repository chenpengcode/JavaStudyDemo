package proxy.staticProxy;

import org.junit.Test;

/**
 * @Description
 * @Author CP
 * @Date 2020/12/4
 */
public class HelloProxyTest {
    @Test
    public void test01() {
        Hello hello = new Hello();
        HelloProxy helloProxy = new HelloProxy(hello);
        helloProxy.sayHello();
    }


}