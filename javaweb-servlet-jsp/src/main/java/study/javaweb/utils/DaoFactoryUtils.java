package study.javaweb.utils;

/**
 * 创建dao对象工具类
 * @author dhl
 */
public class DaoFactoryUtils {
    /**
     * 获取我们不同的dao对象
     * @param pathName 当前dao的路径
     * @param clazz 当前dao的对象
     * @param <T>
     * @return T
     */
    public static <T> T getDao(String pathName, Class<T> clazz) {
        try {
            return (T) Class.forName(pathName).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
