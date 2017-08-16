package environment;


/**
 * 对一个事物的属性进行标识
 * @author 付旭东
 *
 */
public class Address {
	/**
	 *作用于事物，为一类事物的统称，
	 */
	public final String thingName;
	/**
	 * 作用于事物，区分单个事物
	 */
	public final String id;
	/**
	 * 如果 type 为ATTRIABLE,则此属性为属性的名称
	 */
	public final String attriName;

	/**
	 * 标识元素的类型
	 */
	public final Type type;

	/**
	 * 如果 type 为FUNCTION,则此属性为函数的名称
	 * */
	public final String  functionName;

    /**
     *
     * @param thingName 事物的名称
     * @param Name 函数或者属性的名称
     * @param id 事物的id
     * @param type 指明该地址指向属性还是函数
     */
	public Address(String thingName, String Name, String id, Type type) {
		this.thingName = thingName;
		this.id=id;
		this.type=type;
		switch (type) {
            case ATTRIBLE:
                this.attriName = Name;
                this.functionName="";
                break;
            case FUNCTION:
                this.functionName=Name;
                this.attriName = "";
                break;
            default:
                this.attriName = "";
                this.functionName="";
                break;
        }
	}

	/**
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(Object obj) {
        Address address = (Address) obj;
		if(this.id==null || address.id==null || this.id.equals("")|| address.id.equals("")) {
            return this.thingName.equals(address.thingName) && this.attriName.equals(address.attriName);
        }
        else {
            return this.thingName.equals(address.thingName) && this.attriName.equals(address.attriName) && this.id.equals(address.id);
        }
	}

    /**
     *
     * @return 我自己设定address是不可更改的，是固定的，
     */
	@Override
	public Address clone()
    {
        return this;
    }


}

