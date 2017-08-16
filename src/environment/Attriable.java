package environment;

/**
 * 属性接口
 */
public  interface Attriable 
{
	//这个初始化用于在{@code Attri} 中初始化需要的变量
	void inite(Attri attri);
	
	 void setValue(Attri attri,Object change);
	   
	Object getValue(Attri attri);
}
