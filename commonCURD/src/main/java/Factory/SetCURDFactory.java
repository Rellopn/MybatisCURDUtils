package Factory;

/**
 * Created by 然 on 2017/3/20.
 */
public interface SetCURDFactory extends CURDFactory {
    /**
     * 设置特殊字段
     * @param orderBy
     */
    String setOrderBy(String orderBy);

    String setGroupBy(String groupBy);

    String setDistinct(String distinct);
}
