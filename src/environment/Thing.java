package environment;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * 事物是被动函数{@code Negetivefun}和属性{@code Attri}的集合
 * @author 付旭东
 *
 */
public class Thing implements Cloneable {

	/**
	 *事物所依附的时候的世界
	 */
	World world;
    /**
     * 需要将这个该为键值对，以后再改
     */
	ArrayList<Attri> attris = new ArrayList<>();
	/**
	 * 默认的用于路径构造的类
	 */
	Thinker thinker;
    /**
     * 需要将这个该为键值对，以后再改
     * 被动函数集
     */
	ArrayList<Negetivefun> negetiveFunMap = new ArrayList<>();

    /**
     * 主动函数集合，
     */
	Map<String,PositiveFun> positiveFunMap=new TreeMap<>();

	/**
	 * 事物的名字，代表一类事物
	 */
	private String name;

	/**
	 * 事物的id，代表一个事物
	 */
	private String id;

	/**
	 * 指示该事物处于实例事物还是模型事物状态
	 */
	boolean isIntance = false;

	/**
	 * 返回对象是否是实例状态
	 * 
	 * @return
	 */
	public boolean isIntance() {
		return isIntance;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	/**
	 * 设置对象是实例状态还是模型状态
	 * 
	 * @param isIntance
	 */
	//这个函数在包外应该被限制使用，特此加上private
	private void setIntance(boolean isIntance) {
		this.isIntance = isIntance;
	}

	/**
	 * 设置某种属性的值，供非正常渠道使用
	 * @param type
	 * @param value
	 */
	public void setTypeValue(String type, Object value) {
	    for(Attri attri :attris) {
            if(attri.getName().equals(type)) {
                attri.setValue(value);
            }}
	}


    /**
     * 获取事物本身某个属性的值
     * @param AttriName 属性的名称
     * @return 属性的值
     */
	public Object getAttriValue(String AttriName)
    {
        for(Attri attri:attris) {
            if(attri.getName().equals(AttriName)) {
                return attri.getValue();
            }
        }
        return null;
    }



    //这个函数写的存在问题，没有捕捉BaseException，
    //主动函数的启动应该放在世界中才行，对于异常的处理同意放在一个地方，
    //整个架构还存在的一个问题是，架构的事物现在都是死的，没有获得动力，
    //表面执行好像就会存在问题。

    /**
     * 激活事物包含的某个主动函数
     * @param positiveFunName 要启动的主动函数的名称
     */
	public void invokePositiveFun(String positiveFunName) {
	    if(this.isIntance) {
            world.invokeThingFunction(this.getId(),positiveFunName);
        }
        else {
            throw  new RuntimeException("事物本身为非实例事物，不能启动主动函数");
        }
    }

	/**
	 * 向事物添加被动函数
	 */
	public void attachNegtiveFun(Negetivefun fun){
		negetiveFunMap.add(fun);
		fun.belonged=this;
		fun.world=this.world;
	}

    /**
     * 附加主动函数
     * @param fun
     */
    //是否需要设定事物此时必须处于非实例状态呢，应该需要
	public void attachPositiveFun(PositiveFun fun) {
        positiveFunMap.put(fun.functionName,fun);
        fun.belonged=this;
        fun.world=this.world;
    }
	
	public void attachAttri(Attri attri) {
		attris.add(attri);
		attri.belonged=this;
		attri.world=this.world;
	}

    /**
     * 构造函数
     * @param name
     */
    public Thing(String name){
		this.name=name;
	}
	
	
	/**
	 * 
	 * @return 该事物此时刻的状态
	 */
	public Condition getCondition() {
		return new Condition(this);
	}

	/**
	 * 
	 * @param id  新的事物的id，没有检验事物id的唯一性，为了得到全局唯一id的效果，从world获取，防止冲突
	 * @return
	 */
	public Thing clone(String id) {
		Thing temp=new Thing( this.name);
		temp.world=this.world;
		temp.id=id;
		for(Attri shuxing:this.attris) {
			shuxing.clone(temp,shuxing.getName());
		}
		for(Negetivefun fun: negetiveFunMap) {
			fun.clone(temp);
		}
		positiveFunMap.forEach((x,y)->y.clone(temp));
		return temp;
	}

	/**
	 * 将事物的所有的函数进行注册
	 */
       void registerFunToAttri() {
		for(Negetivefun fun: negetiveFunMap) {
			fun.registerSelf();
		}
	}


	public  Address getAttriAddress(String AttriName) {

        for (Attri x : attris) {
            if (x.getName().equals(AttriName))
                return new Address(Thing.this.getName(), AttriName, Thing.this.getId(), Type.ATTRIBLE);
        }
        throw  new RuntimeException("事物不包含名为"+AttriName+"的属性");
    }



}
