package environment;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public  class Attri implements IAddressable
{
    /**
     * 所属的世界和事物
     */
    World world;

	Thing belonged;

	/**
	 * 属性的类型，给属性添加的事物内属性唯一标识符
	 */
	private String name;

	/**
	 * 作为成员变量是为了能够支持克隆
	 */
	Attriable shuxing;
	/**
	 * 链接的函数，属性变动时候可能去调用的函数，这样的设定也不知道好不好
	 */
	ArrayList<Negetivefun> linkedFun=new ArrayList<>();

    /**
     * 这个是为了属性可以克隆所做的工作
     */
	public Map<String, Integer> integerMap=new TreeMap<>();
	public Map<String, String> stringMap=new TreeMap<>();
	public Map<String,Boolean> booleanMap=new TreeMap<>();
	public Map<String,Float> floatMap=new TreeMap<>();
    public Map<String,Double> doubleMap=new TreeMap<>();
    public Map<String,Character> characterMap=new TreeMap<>();
    public Map<String ,Long> longMap=new TreeMap<>();

    /**
     *
     * @param name 属性的名称或者叫做类型
     * @param shu
     */
   	public Attri(String name,Attriable shu)
	{
		this.name =name;
		this.shuxing=shu;
		shu.inite(this);
	}

   public Object getValue() {
	   if(shuxing!=null)
		   return shuxing.getValue(this);
		   throw new RuntimeException("属性的获取值函数没有被设定");
   }
   
   public void setValue(Object change) {
		   shuxing.setValue(this,change);
   }

   /**
    * 
    * @param change
    */
    void liandong(Object change) {
	   shuxing.setValue(this,change);
	   for(Negetivefun beidong:linkedFun) {
		   if(beidong.check.check(beidong)) {
			   beidong.doIt();
		   }
	   }
   }
   
    public String getName()
   {
	return name;
   }

    public Attri clone(Thing shiwu,String type) {
    	Attri shu=new Attri( type,this.shuxing);
    	shiwu.attachAttri(shu);
    	return shu;
    }

	/**
	 *
	 * @return
	 */
	//这个函数有问题，需要改
	public Address getAddress() {
    	if(belonged.isIntance) {
    		Address dizhi=new Address(belonged.getName(),name,belonged.getId(),Type.ATTRIBLE);
        	return dizhi;
    	}
    	else {
            Address dizhi=new Address(belonged.getName(),name,"",Type.ATTRIBLE);
            return  dizhi;
        }
	}

	@Override
	public boolean equals(Object obj )
    {
        Attri attri=(Attri) obj;
        return  this.getValue().equals(attri.getValue());
    }




}
