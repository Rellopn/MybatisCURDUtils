package Factory;

import java.util.Map;

/**
 * Created by 然 on 2017/3/20.
 */
public interface CURDFactory {
    /**
     * 返回查询语句
     * @return
     */
    String getSelect(Map<String,Object> fileds);

    /**
     * 返回更新语句
     * @return
     */
    String getUpdate(Map<String,Object> fileds);

    /**
     * 返回删除语句
     * @return
     */
    String getDelete(Map<String,Object> fileds);

    /**
     * 返回插入语句
     * @return
     */
    String getInsert(Map<String,Object> fileds);
}
