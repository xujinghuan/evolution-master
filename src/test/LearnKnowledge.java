package test;

import environment.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 付旭东 on 2017/3/16.
 */

//开来我需要加入主动函数才行，待会再加
public class LearnKnowledge
{

    /*
    *   小明启动主动函数进行学习， 自己变暗了，然后学习失败，
        然后学习函数需要提交自己学习失败的原因（灯变暗了），以及想要的某些状态
        （自己的亮度为亮），（开灯也是一个主动函数，主动函数才是路径搜索过程中，可以
        调用的函数，被动函数不能被调用）。小明这个时候可以启动自己的路径搜索工具，建立到达
        学习提出的目的状态的路径并且执行。
        代码好像写的差不多了，
        我可以写一写代码来测试一下了，试一下构建代码的能力
    * */

    public static void main(String[] args)
    {
        LearnKnowledge l=new LearnKnowledge();

        World home=new World("homeTest");

        Thing people=new Thing("xiaoming");

        // 灯需要增加一个开关属性，人可以改变开关属性，开关属性的改变会引发被动函数（灯发光）的启动，
        // 被动函数启动后，会引发书本的亮度改变，亮度改变人才可以进行学习，
        // 学习中不需要自身去实现打开灯光这个目的，而是需要系统进行学习，自己认识到他们之间存在的联系才可以。

        Thing book=new Thing("book");

        Attri bright=new Attri("bright", new Attriable() {
            @Override
            public void inite(Attri attri) {
                attri.booleanMap.put("bright",true);
            }

            @Override
            public void setValue(Attri attri, Object change) {
                if((Boolean) change) {
                    if( attri.booleanMap.get("bright"))
                        attri.booleanMap.put("bright", false);
                    else
                        attri.booleanMap.put("bright", true);
                }
            }
            @Override
            public Object getValue(Attri attri) {
              return   attri.booleanMap.get("bright");
            }
        });

        people.attachAttri(bright);

        Attri grade=new Attri("grade", new Attriable() {
            @Override
            public void inite(Attri attri) {
                attri.integerMap.put("grade",60);
            }
            @Override
            public void setValue(Attri attri, Object change) {
                int grade=attri.integerMap.get("grade");
                attri.integerMap.put("grade",(int)change+grade);
                System.out.println("更改成绩，目前成绩为"+attri.integerMap.get("grade"));
            }
            @Override
            public Object getValue(Attri attri) {
                return attri.integerMap.get("grade");
            }
        });

        people.attachAttri(grade);


        // 需要先检查源属性，自己的亮度，如果亮度为暗，那么就返回一个错误， 继承自baseException，
        PositiveFun learn=new PositiveFun("learn", new Functional() {
            @Override
            public Map<Attri, Object> function(BaseFunction fun, ArrayList<Attri> desit) {
                //需要检查源属性的
                System.out.println("learn 函数体运行");
                Map<Attri,Object> change=new HashMap<>();
                Boolean bright=(Boolean) fun.belonged.getAttriValue("bright");
                if(bright) {
                    for(Attri attri:desit) {
                     switch (attri.getName()) {
                         case"grade":
                             change.put(attri,10);
                             System.out.println("小明成绩加10");
                         break;
                     }}}
                else {
                    //自己之前的有一些想法是错误的，主动函数的调用必须局限于提出的事物，不得调用其他事物的主动函数
                    //除非 （除非的内容的还没有想好，）
                    //我现在必须修正 Condition的比较和

                    System.out.println("灯光亮度为暗，提交基础错误");

                    Condition current=fun.belonged.getCondition();
                    Condition ambition=current.getDesitCondition("bright",true);

                    //代码到这里可能是结束了，还有就是代码的异常的捕捉，
                    throw  new BaseException(current,ambition,null);
                }
                return change;
            }
            @Override
            public Tentacle getTentacle(BaseFunction fun) {
                return null;
            }
        });


        learn.src.add(people.getAttriAddress("bright"));

        learn.desti.add(grade.getAddress());
        people.attachPositiveFun(learn);

        Thing lamp=new Thing("lamp");

        home.attachModleThing(people);
        Thing xiaoming = home.addIntanceThingFromModel("xiaoming", "xiaoming");

        PositiveFun turnLampOn=new PositiveFun("turnLampOn", new Functional() {
            @Override
            public Map<Attri, Object> function(BaseFunction fun, ArrayList<Attri> desit) {
                System.out.println("改变灯的亮度变化函数被调用");
                Map<Attri,Object> change=new HashMap<>();
                for(Attri attri:desit) {
                    switch (attri.getName()) {
                        case "bright":
                            change.put(attri,true);
                            break;
                    }}
                return change;
            }
            public Tentacle getTentacle(BaseFunction fun) {
                Tentacle tentacle =new Tentacle(fun);
                Condition off= xiaoming.getCondition();
                Condition on =off.getDesitCondition("bright",true);
                tentacle.record(off,on);
                tentacle.record(on,off);
                return  tentacle;
            }
        });
        turnLampOn.desti.add(people.getAttriAddress("bright"));

        lamp.attachPositiveFun(turnLampOn);

        people.attachPositiveFun(turnLampOn);

        home.attachModleThing(lamp);
        home.attachModleThing(book);
        Thing deng=  home.addIntanceThingFromModel("lamp","lamp");
        Thing shu= home.addIntanceThingFromModel("book","book");
        home.start();

        xiaoming.invokePositiveFun("learn");
        xiaoming.setTypeValue("bright",true);
        xiaoming.invokePositiveFun("learn");
        xiaoming.invokePositiveFun("learn");
        xiaoming.invokePositiveFun("learn");


    }

}