package environment;

import java.util.Map;

/**
 * @author 付旭东
 *被动函数
 */
public  class Negetivefun  extends BaseFunction 
{

	/**
	 * 检查函数是否启动
	 */
	 Checkable check;

	public Negetivefun(String name) {
        super(name);
    }

    public Negetivefun(String name,Functional functional) {
        super(name);
        this.functionBody=functional;
    }

	/**
	 * 可能不需要check函数，之后可能会被删除
	 * @param check
	 */
	public void setCheck(Checkable check)
	{
		this.check=check;
	}

	/**
	 * 对函数变动可能会引发自己启动的属性注册自己
	 * 只能监听自身的属性变化，不能监听其他事物的属性变化，进行响应，
	 * 那函数调用的监听范围又是什么
     * check检查被动函数是否启动，那么src存在的目的是什么，
     * 怎么进行路径构建我也没有搞清楚呢
     *
	 * 这里注册的是源啊，怎么会出现问题呢？并没有出现问题，是check的问题，是世界的start函数造成的问题，
	 *
	 */
	void registerSelf() {
		for (Address dizhi : src) {
			if(dizhi.type==Type.ATTRIBLE) {
				for (Attri shuxing : belonged.attris) {
					if (dizhi.equals(shuxing.getAddress())) {
						shuxing.linkedFun.add(this);
					}
				}
			}
			if(dizhi.type==Type.FUNCTION) {
				for(BaseFunction function : belonged.negetiveFunMap) {
					if(function.getAddress().equals(dizhi))
					{
						function.linkedFunction.add(this);
					}
				}
			}}
	}

    /**
     * 这个克隆函数有问题，被动函数应该可以做为单体移出，暂时不管这个了
     * @param shiwu
     * @return
     */
	public Negetivefun clone(Thing shiwu) {
		Negetivefun p=new Negetivefun(functionName);
		p.functionBody =this.functionBody;
		this.desti.forEach((r)->p.desti.add(r));
		this.src.forEach((r)->p.src.add(r));
		p.check=this.check;
		shiwu.attachNegtiveFun(p);
        return p;
	}
	
	public void doIt() {
		for(Address dest:desti) {
			//目的属性集合，如果是属性，计算出对属性应该应该做出的改变。
			if(dest.type==Type.ATTRIBLE) {
			    try {
                    Map<Attri, Object> t = functionBody.function(this, getAttris(dest));
                    if (world.isRecord) {
                        /**
                         * 计划记录下所执行的变化，
                         */
                    }
                    reallyChange(t);
                }
				catch (BaseException e)
                {
                    //Way path= belonged.thinker.fightOutWay(e);
                    //belonged.executeWay(path);
                }
			}
			//如果是函数的话就直接调用函数，这里有问题，需要再次考虑
			else {
			}}
	}
	private void reallyChange(Map<Attri, Object> canshu) {
		for (Attri shuxing : canshu.keySet()) {
			shuxing.liandong(canshu.get(shuxing));}
	}

}
