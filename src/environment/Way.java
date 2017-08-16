package environment;

import java.util.*;

/**
 * @date 2016/10/7
 * @author 付旭东
 *用于记录世界的运行，是针对于整个世界来说的。
 *
 * 上面的表述存在问题，我要进行修改，修改的内容是,{@code Way} 表达仅是一种从主动函数开始的执行序列。
 */
public class Way {
    /**
     * 预计执行的路径以及状态转换
     */
   private Stack<Action> _prediction=new Stack<>();

	/**
	 * 世界的初始状态，可能不需要这个变量，以后再删除吧，
     * 添加一个标识使他过期。
	 */
	@Deprecated
	Map<Address, Condition> startCondition=new HashMap<Address, Condition>();
	
	/**
	 * 记录整个世界中函数的执行状况
	 */
	Map<Address, Object> recorder=new TreeMap<>();

	public Way() {}


    /**
     * 添加执行路径中的一个动作，这个需不需要
     * 检验执行过程中事物状态是否符合动作中所描述的。
     *
     *
     * @param current
     * @param fun
     * @param predition
     */
	public  void putAction(Condition current,PositiveFun fun,Condition predition) {
        _prediction.push(new Action(current,fun,predition));
    }

    public  void putAction(Condition current,Negetivefun fun,Condition predition) {
        _prediction.push(new Action(current,fun,predition));
    }

    /**
     *
     * @return
     */
    public  PositiveFun getNextPosiFun() {
        Action action=new Action();
        while (_prediction.size()!= 0 &&!(action= _prediction.pop()).isPositive);
        return action.pfun;

    }

	 static  class  Action{
	    Condition cunrent;
	    Boolean isPositive;
	    Condition predition;
	    PositiveFun pfun;
	    Negetivefun nfun;

	    Action()
        {
            pfun=null;
            nfun=null;
        }

	    Action(Condition current, PositiveFun fun,Condition predition) {
            this.cunrent=current;
            isPositive=true;
            pfun=fun;
            this.predition=predition;
            nfun=null;
        }

        Action(Condition current, Negetivefun fun,Condition predition) {
            this.cunrent=current;
            isPositive=false;
            nfun=fun;
            this.predition=predition;
            pfun=null;
        }
    }
}
