import getSQL.CURDInsert;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 然 on 2017/3/22.
 */

public class TestClass {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tableName", "sys_user");
        map.put("id", "1233");
        map.put("name", "你好");
        CURDInsert curdInsert = new CURDInsert(map);
        System.out.println(curdInsert.getInsert(map));
        ;
    }
}
