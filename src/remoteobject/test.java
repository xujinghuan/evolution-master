package remoteobject;
import java.rmi.*;

/**
 * Created by 付旭东 on 2017/2/28.
 * 提供了客户机提供服务的方法的形式
 */
public interface test extends  Remote {

    public  void sayHello() throws RemoteException;

}
