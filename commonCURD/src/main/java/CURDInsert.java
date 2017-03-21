import java.util.Map;

/**
 * Created by 然 on 2017/3/20.
 */
public  class CURDInsert extends AbstractCURD {

    private String tableName;

    public CURDInsert(Map<String, Object> fileds) {
        super(fileds);
        this.tableName= (String) fileds.get("tableName");
    }

    public void setSpecialFiled() {

    }

    public String getInsert(Map<String, Object> fileds) {


        StringBuilder filedsSQl=commonCURDBuilder().filedsSQl();
        StringBuilder andSQL=commonCURDBuilder().insertValuesSQL();
        return sql
                .INSERT_INTO(tableName+" ("+filedsSQl.toString()+") VALUES("+andSQL.toString()+")").toString();
    }
}
