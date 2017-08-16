package environment;

import java.util.ArrayList;
import java.util.Map;

public interface Functional 
{
	  /**
     * 
     * @param desit 需要更改的目标属性，
     * @return 针对每个目标函数，需要进行的更改
     */
     Map<Attri, Object> function(BaseFunction fun,ArrayList<Attri> desit);

     Tentacle getTentacle(BaseFunction fun);


}
