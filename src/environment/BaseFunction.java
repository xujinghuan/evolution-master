package environment;

import java.util.ArrayList;

public  class BaseFunction implements IAddressable
{
	public Thing belonged;
	public World world;

	/**
	 * 函数也是需要名称的，代表执行动作的名称，为以后构建新的编程语言做准备
	 */
	String functionName;

	/**
	 * 函数要作用的目标{@code Address}的集合，
	 * 也是要去更改的对象。
	 * 每个函数都是为了改变才存在的
	 */
	public ArrayList<Address> desti=new ArrayList<>();

	/**
	 * 关联的被动函数，被动函数的启动也可以启动被动函数，被启动的被动函数需要  可以截断此被动函数的执行，并且可以检测传递的参数
	 * 合法性，更改参数等等所有内容。
	 */
	public ArrayList<BaseFunction> linkedFunction =new ArrayList<>();

    /**
     * 函数体
     */
	public  Functional functionBody;

    /**
     * 自己要监听的属性变化或者是函数调用，
     * 只有监听的函数被调用，或者属性被更改，这个函数就会被启动。
     *
     * 也就是说对与src的限定必须被限定起来，否则就会破坏规则，
     * 暂时就不进行限定了。
     */
    public ArrayList<Address> src=new ArrayList<>();

    /**
     * 每个函数都拥有的Tentacle属性
     */
	public Tentacle Tentacle;

    /**
     *
     * @param funName  函数的名称，也代表所进行动作的名称
     */
    public	BaseFunction(String funName) {
        functionName=funName;
    }



    public void setFunction(Functional hanshu)
    {
    	this.functionBody =hanshu;
    }

    /**
     * functionBody.getTentacle()返回为空的情况下，填充一个tentacle。
     * @param tentacle
     */
    public void attachTentacle(Tentacle tentacle )
    {
        environment.Tentacle tentacle1=functionBody.getTentacle(this);
        if(tentacle1==null) {
            this.Tentacle=tentacle;
        }
        else
            //这里返回 RuntimeException可能不太好，以后可以改一下。
            throw  new RuntimeException("functionBody.getTentacle()不为空，不建议进行替换");
    }

    /**
     *
     * @return 获取函数的 tentacle，每个函数对应一个tentacle.
     */
    public Tentacle getTentacle()
    {
        if(Tentacle!=null)
            return  Tentacle;
        else
            return functionBody.getTentacle(this);
    }

    /**
     *用于远对象过程调用使用
     * 2017/3/16   这个需要重新考虑，这个函数会被抛弃
     * @return 返回需要的参数列表
     */
    @Deprecated
    public ArrayList<String> getParams()
    {
        return  null;
    }

    /**
     * @param change 需要发生的改变，也就是函数的逆函数，用于构建路径使用，
	 *               这也是学习的核心函数，使用神经网络来构建
     * @return
     */
    @Deprecated
    Object predict(Address address,Object change)
    {	
    	return null;
    }

    public	BaseFunction(World world)
   	{
       this.world=world;
   	}
    
    /**
     * 需要记录函数对每个函数进行的改变
     */
    @SuppressWarnings("unused")
	private void registerChangeToWorld()
    {
    	
    }
    /**
     * 需要一种描述达到什么状态执行的的方法。
     */
   
    public  Address getAddress() {
        if(belonged.isIntance) {
            Address address=new Address(belonged.getName(),functionName,belonged.getId(),Type.FUNCTION);
            return  address;
        }
        throw new RuntimeException("非实例事物，无法返回地址");
	}


    public String getFunctionName()
    {
        return  functionName;
    }

    /**
     * 这里必须进行检验，如果不被包含在src中，那么就返回错误
     *
     * 这里存在问题，这里的本意是src必须是事物自身的属性中的一部分，
     * 但是是上，这里的限定失败。
     *
     * 这里存在问题，这里的本意是src必须是事物自身的属性中的一部分，* 但是是上，这里的限定失败。
     *
     * 这个是供外部使用的
     * @param dizhi dizhi必须被被包含在src中
     * @return 获取匹配address的属性
     */
    @Deprecated
    public ArrayList<Attri> getAttris(Address dizhi) {

        if(src.contains(dizhi)) {
            return world.getAttris(dizhi);
        }
     else
         throw  new RuntimeException("地址不被包含在源属性中，获取属性集失败");
    }









}
