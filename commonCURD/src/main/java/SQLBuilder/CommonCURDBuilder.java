package SQLBuilder;

import java.util.List;

/**
 * 通用的c
 * Created by 然 on 2017/3/5.
 */
public class CommonCURDBuilder {

    private  List<String> filedsList;
    /**
     * 允许空构造，自己去引用方法
     */
    public CommonCURDBuilder(List<String> filedsList) {
        this.filedsList=filedsList;
    }

    /**
     * 构建一一对应的and sql语句 如 user=#{user} AND pwd=#{pwd}
     * @return
     */
    public StringBuilder correspondingANDRelationSQL() {
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < filedsList.size(); i++) {
            if (i==filedsList.size()-1 || filedsList.size()==1) {
                sb.append(filedsList.get(i) + "=#{" + filedsList.get(i) + "}");
            }
            else {
                sb.append(filedsList.get(i) + "=#{" + filedsList.get(i) + "} AND ");
            }
        }
        return sb;
    }

    /**
     * 构建一一对应的and sql语句 如 user=#{user} , pwd=#{pwd}
     * @return
     */
    public StringBuilder correspondingSQRelationSQL() {
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < filedsList.size(); i++) {
            if (i==filedsList.size()-1 || filedsList.size()==1) {
                sb.append(filedsList.get(i) + "=#{" + filedsList.get(i) + "}");
            }
            else {
                sb.append(filedsList.get(i) + "=#{" + filedsList.get(i) + "} , ");
            }
        }
        return sb;
    }

    /**
     * 构建sql语句如 #{user} AND #{user}
     * @return
     */
    public StringBuilder andSQl(){
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < filedsList.size(); i++) {
            if (i==filedsList.size()-1 || filedsList.size()==1) {
                sb.append("#{" + filedsList.get(i) + "}");
            }
            else {
                sb.append("#{" + filedsList.get(i) + "} AND ");
            }
        }
        return sb;
    }

    /**
     * 构建sql语句 如 user,pwd
     * @return
     */
    public StringBuilder filedsSQl(){
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < filedsList.size(); i++) {
            if (i==filedsList.size()-1 || filedsList.size()==1) {
                sb.append(filedsList.get(i));
            }
            else {
                sb.append(filedsList.get(i) + ",");
            }
        }
        return sb;
    }

    /**
     * 构建sql语句如 #{user},#{pwd}
     * @return
     */
    public StringBuilder insertValuesSQL(){
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < filedsList.size(); i++) {
            if (i==filedsList.size()-1 || filedsList.size()==1)
                sb.append("#{"+filedsList.get(i)+"}");
            else
                sb.append("#{"+filedsList.get(i)+"},");
        }
        return sb;
    }
}
