import org.apache.ibatis.jdbc.SQL;


import java.util.List;
import java.util.Map;

/**劳苦功高的抽象类，实现了接口所有的方法，接下来分类继承的话，只实现需要的方法久可以了。
 * Created by 然 on 2017/3/20.
 */
public  class AbstractCURD implements SetCURDFactory {

    protected SQL sql=new SQL();

    protected List<String> filedsList;


    public AbstractCURD(Map<String, Object> fileds){
        for (Map.Entry<String, Object> entry : fileds.entrySet()) {
           this.filedsList.add(entry.getKey());
        }
    }

    public CommonCURDBuilder commonCURDBuilder(){
        return new CommonCURDBuilder(filedsList);
    }

    public void setSpecialFiled() {

    }

    public String getSelect(Map<String, Object> fileds) {
        return null;
    }

    public String getUpdate(Map<String, Object> fileds) {
        return null;
    }

    public String getDelete(Map<String, Object> fileds) {
        return null;
    }

    public String getInsert(Map<String, Object> fileds) {
        return null;
    }
}
