package brain;

import java.util.ArrayList;

/**
 * 
 * @author 付旭东
 *这个东西是用来干嘛的，忘记了，
 *节点相当于一个事物，
 * @param <T>
 */
public class Node<T> 
{
	T self;
	ArrayList<T> relationship;
	public Node(T node,ArrayList<T> relation) 
	{
		self=node;
		if(relation.contains(node))
			throw new RuntimeException("节点创建失败，节点的创建以自己为前提条件");
		relationship=relation;
	}
}
