package proxy.dynamicProxy.cglib;

import junit.framework.TestCase;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @Description
 * @Author CP
 * @Date 2020/12/4
 */
public class ServiceTest extends TestCase {

    public void test01() {
        Enhancer enhancer = new Enhancer();
        //继承被代理类
        enhancer.setSuperclass(Service.class);
        //设置回调
        enhancer.setCallback(new ServiceMethodInterceptor());
        //设置代理类对象
        Service service = (Service) enhancer.create();
        //在调用代理类中方法时会被我们实现的方法拦截器进行拦截
        service.sayHello();
        service.sayBye();
    }
}