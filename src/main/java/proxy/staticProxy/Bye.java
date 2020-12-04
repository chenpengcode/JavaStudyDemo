package proxy.staticProxy;

/**
 * @Description
 * @Author CP
 * @Date 2020/12/4
 */
public class Bye implements ByeInterface{
    @Override
    public void sayBye() {
        System.out.println("Bye Bye!!!");
    }
}
