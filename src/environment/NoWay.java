package environment;


/**
 * Created by 付旭东 on 2017/5/6.
 */
public class NoWay extends Exception {
    String cause;
    public NoWay(String cause) {
        this.cause=cause;
    }

}
