package srcan;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author dongxiaohong
 * @date 2019/2/18 14:22
 */
public class ArrayListOp {

    public void Op1(List list) {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            String str = iterator.next();
            System.out.println(str);
        }
    }
    public void Op2(List list){
        /**相当于
         * new Iter();
         * Iter为内部类*/
        Iterator<String> iterator = list.iterator();
    }
}
