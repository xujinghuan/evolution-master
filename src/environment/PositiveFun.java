package environment;

import java.util.Map;

/**
 *这里是主动函数，
 * 需要设定一个类似于主函数一样的东西。
 * 主动函数还存在一些问题，
 */
public  class PositiveFun extends BaseFunction
{
	public PositiveFun(String name)
	{
		super(name);
	}

    /**
     *  启动被动函数的执行
     */
    public  void doIt() {
        for(Address dest:desti) {
            //目的属性集合，如果是属性，计算出对属性应该应该做出的改变。
            if(dest.type==Type.ATTRIBLE) {
                    Map<Attri, Object> t = functionBody.function(this,belonged.world.getAttris(dest));
                    if (world.isRecord) {
                        /**
                         * 计划记录下所执行的变化，
                         */
                    }
                    reallyChange(t);
            }
            //如果是函数的话就直接调用函数，这里有问题，需要再次考虑
            else {

            }}
    }

    /**
     * @param name 主动函数的名称
     * @param functional  主动函数要执行的函数内容
     */
    public PositiveFun(String name,Functional functional) {
        super(name);
       this.functionBody=functional;
    }



    public PositiveFun clone(Thing thing) {
        PositiveFun positiveFun=new PositiveFun(functionName);
        positiveFun.functionBody=this.functionBody;
        thing.attachPositiveFun(positiveFun);
        this.desti.forEach(x->{
            positiveFun.desti.add(x.clone());
        });
        this.src.forEach(x->{
            positiveFun.src.add(x.clone());
        });

        return  positiveFun;
    }

    private void reallyChange(Map<Attri, Object> canshu) {
        for (Attri shuxing : canshu.keySet()) {
            shuxing.liandong(canshu.get(shuxing));}
    }

}
