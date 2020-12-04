package proxy.staticProxy;

/**
 * @Description
 * @Author CP
 * @Date 2020/12/4
 */
public class HelloProxy implements HelloInterface {
    private final HelloInterface helloInterface;

    public HelloProxy(HelloInterface helloInterface) {
        this.helloInterface = helloInterface;
    }

    @Override
    public void sayHello() {
        System.out.println("Before invoke sayHello");
        helloInterface.sayHello();
        System.out.println("After invoke sayHello");
    }
}
