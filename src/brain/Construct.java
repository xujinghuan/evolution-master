package brain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Construct 
{
	ArrayList<Node> list=new ArrayList<>();
	
	public Construct() 
	{
	
	}
	
	ArrayList<Node> sort()
	{
		
		Collections.sort(list, new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {

				if (o1.relationship.contains(o2.self)
						&& o2.relationship.contains(o1.self)) {
					throw new RuntimeException("");
				} else {
					if (o1.relationship.contains(o2.self)) {
						return 1;
					} else {
						if (o2.relationship.contains(o1.self))
							return -1;
						else
							return 0;
					}
				}
			}
		});
		return list;
	}
	
	                 
	
	

}
