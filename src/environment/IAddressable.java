package environment;

/**
 * Created by 付旭东 on 2017/3/17.
 */

/**
 * 属性和函数都需要提供地址，所以将这个函数抽提为一个接口
 */
public interface IAddressable {
    Address getAddress();
}
