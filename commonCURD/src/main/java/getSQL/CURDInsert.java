package getSQL;

import Abstract.AbstractCURD;
import SQLBuilder.CommonCURDBuilder;

import javax.management.AttributeList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 然 on 2017/3/20.
 */
public  class CURDInsert extends AbstractCURD {

    private String tableName;
    /**
     * 字段名称
     */
    private List<String> filedsList=new ArrayList<String>();

    /**
     * 对应的值
     */
    private List<Object> valueList=new AttributeList();
    /**
     * 构造方法
     * @param fileds
     */
    public CURDInsert(Map<String, Object> fileds) {
        super(fileds);
        this.tableName= (String) fileds.get("tableName");
        for (Map.Entry<String, Object> entry : fileds.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            this.filedsList.add(key);
            this.valueList.add(value);
        }
    }

    /**
     * 语句生产的类，生成各种对应关系的语句段落
     * @return
     */
    public CommonCURDBuilder commonCURDBuilder(){
        return new CommonCURDBuilder(filedsList);
    }

    /**
     * 返回insert语句
     * @param fileds
     * @return
     */
    public String getInsert(Map<String, Object> fileds) {
        StringBuilder filedsSQl=commonCURDBuilder().filedsSQl();
        StringBuilder andSQL=commonCURDBuilder().insertValuesSQL();
        return sql
                .INSERT_INTO(tableName+" ("+filedsSQl.toString()+") VALUES("+andSQL.toString()+")").toString();
    }
}
