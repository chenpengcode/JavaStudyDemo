package proxy.staticProxy;

/**
 * @Description
 * @Author CP
 * @Date 2020/12/4
 */
public class Hello implements HelloInterface {
    @Override
    public void sayHello() {
        System.out.println("Hello World!");
    }
}