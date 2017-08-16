package environment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 付旭东 on 2017/4/9.
 * 我设置触须的目的是什么？  在目前的简单架构前（即没有添加子事物集合之前），
 * 属性值就代表了状态，触须的存在是为了决定如何去调用被动函数，
 */
public class Tentacle {

    /**
     * 被描述的函数，被描述的必须是函数
     */
    Address described;

    String functionName;

    /**
     * 目的属性和源状态的集合
     */
    Map<Condition,ArrayList<Condition>> rows=new HashMap<>();

    /**
     * @param fun 建立该被动函数的触须
     */
    public Tentacle(BaseFunction fun) {
        described=fun.getAddress();
        functionName=fun.getFunctionName();
    }


    /**
     * 添加一条记录
     * @param src
     * @param desiit
     */
    public  void record(Condition desiit,Condition src) {
        if(rows.containsKey(desiit)) {
            rows.get(desiit).add(src);
        }
        else {
            ArrayList<Condition> temp=new ArrayList<>();
            temp.add(src);
            rows.put(desiit,temp);
        }
    }

    /**
     * 为了得到目的状态，对应的源状态的集合。
     * 严格型的状态对应
     * @param desit
     * @return
     */
    public ArrayList<Condition> reach(Condition desit) {
        if(desit.AllFit)
        return rows.get(desit);
        else {
            ArrayList<Condition> result=new ArrayList<>();
            if(desit.IgnoreOtherAttri) {
                rows.forEach((x,y)->{
                    if(x.equals(desit))
                    result.addAll(y);});

                return result;
            }
        }
        return null;
    }

    /**
     * 检验某种状态状态转换的可行性，如果可行返回true，否则返回false。
     * @param src
     * @param desite
     * @return
     */
    public boolean check(Condition src,Condition desite,Address keyAttri) {
        ArrayList<Condition> srcs=reach(desite);
        if(srcs!=null && srcs.contains(src)) {
            return true;
        }
        else
            return  false;
    }


}
