package getSQL;

import Abstract.AbstractCURD;
import SQLBuilder.CommonCURDBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 然 on 2017/3/23.
 */
public class CURDUpdate extends AbstractCURD {
    private String tableName;
    /**
     * 字段名称
     */
    private List<String> filedsList=new ArrayList<String>();

    /**
     * 对应的值
     */
    private List<Object> valueList=new ArrayList<Object>();
    /**
     * 把属性和值分开放
     *
     * @param fileds
     */
    public CURDUpdate(Map<String, Object> fileds) {
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
    public String getUpdate(Map<String,Object> map){
        StringBuilder correspondingSQRelationSQL=commonCURDBuilder().correspondingSQRelationSQL();

        return sql.UPDATE(tableName).SET(correspondingSQRelationSQL.toString()).WHERE("1=1").toString();
    }
}
