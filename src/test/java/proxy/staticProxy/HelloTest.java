package proxy.staticProxy;

import junit.framework.TestCase;
import proxy.dynamicProxy.jdk.ProxyHandler;

import java.lang.reflect.Proxy;

/**
 * @Description
 * @Author CP
 * @Date 2020/12/4
 */
public class HelloTest extends TestCase {

    public void testSayHello() {
        Hello hello = new Hello();
        Bye bye = new Bye();
        ProxyHandler proxyHandler1 = new ProxyHandler(hello);
        ProxyHandler proxyHandler2 = new ProxyHandler(bye);
        HelloInterface helloProxy = (HelloInterface) Proxy.newProxyInstance(proxyHandler1.getClass().getClassLoader(), hello.getClass().getInterfaces(), proxyHandler1);
        ByeInterface byeProxy = (ByeInterface) Proxy.newProxyInstance(proxyHandler2.getClass().getClassLoader(), bye.getClass().getInterfaces(), proxyHandler2);
        helloProxy.sayHello();
        byeProxy.sayBye();
    }
}