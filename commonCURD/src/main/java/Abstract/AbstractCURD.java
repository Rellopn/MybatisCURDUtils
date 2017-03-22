package Abstract;

import Factory.SetCURDFactory;
import org.apache.ibatis.jdbc.SQL;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**劳苦功高的抽象类，实现了接口所有的方法，接下来分类继承的话，只实现需要的方法久可以了。
 * Created by 然 on 2017/3/20.
 */
public  class AbstractCURD implements SetCURDFactory {

    /**
     * 用的mybatis的SQL类辅助生成sql语句
     */
    protected static final SQL sql=new SQL();



    /**
     *从左向右参数 orderby groupby distince
     */
    protected static final List<String> specialFileds= Arrays.asList(null,null,null);

    /**
     * 把属性和值分开放
     * @param fileds
     */
    public AbstractCURD(Map<String, Object> fileds){

    }

    /**
     * 将要重写的方法
     * @param fileds
     * @return
     */
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


    public String setOrderBy(String orderBy) {

        return orderBy;
    }

    public String setGroupBy(String groupBy) {

        return groupBy;
    }

    public String setDistinct(String distinct) {

        return distinct;
    }
}
