package me.coconan.bytebuddy;

import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import net.bytebuddy.implementation.bind.annotation.This;
import org.springframework.util.ReflectionUtils;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.concurrent.Callable;

public class Interceptor {
    @RuntimeType
    public static Object intercept(@This Object thisObject, @SuperCall Callable<?> callable) {
        Object call = null;
        try {
            call = callable.call();
            System.out.println("--拦截器打印开始--");
            System.out.println("这是拦截器打印的：" + thisObject);
            System.out.println("开始获取sql");
            Class<?> aClass = thisObject.getClass();
            System.out.println(aClass);
            System.out.println("*******解析出来的sql为：" + ((com.mysql.jdbc.PreparedStatement) thisObject).asSql());

            System.out.println("这是拦截器拦截的结果集：" + call);
            if (call instanceof ResultSet) {
                ResultSet resultSet = (ResultSet) call;
                updatable(resultSet);
                System.out.println("修改后的类型:" + resultSet.getType());


                while (resultSet.next()) {
                    System.out.println("--拦截器--");
                    System.out.println("**********id:" + resultSet.getInt("id"));
                    System.out.println("**********number:" + resultSet.getInt("number"));
                    System.out.println("**********balance:" + resultSet.getBigDecimal("balance"));

                    //获取对应的字段索引
                    int waitUpdateColumnIndex = resultSet.findColumn("balance");
                    //计算对应数据索引
                    int waitUpdateColumnIndexUpdate = waitUpdateColumnIndex - 1;
                    //internalRowData
                    ReflectionUtils.doWithFields(resultSet.getClass(), field -> {
                        field.setAccessible(true);
                        Object thisRowDataObject = field.get(resultSet);
                        //开始正式读取数据
                        ReflectionUtils.doWithFields(thisRowDataObject.getClass(), internalRowDataFieldChild -> {
                            internalRowDataFieldChild.setAccessible(true);
                            //获取internalRowData的值
                            Object internalRowDataByteArray = internalRowDataFieldChild.get(thisRowDataObject);
                            if (internalRowDataByteArray != null) {
                                //获取字段的数据
                                byte[][] rowData = (byte[][]) internalRowDataByteArray;
                                //获取对应字段的数据
                                byte[] rowDatum = rowData[waitUpdateColumnIndexUpdate];
                                System.out.println("初始值：" + new String(rowDatum, StandardCharsets.UTF_8));
                                System.out.println("修改值为：" + new BigDecimal("10000.89"));
                                //修改数据
                                rowData[waitUpdateColumnIndexUpdate] = "10000.89".getBytes(StandardCharsets.UTF_8);
                                internalRowDataFieldChild.set(thisRowDataObject, rowData);
                                System.out.println("--修改数据成功--");
                            }
                        }, internalRowDataFieldChild -> "internalRowData".equals(internalRowDataFieldChild.getName()));
                    }, field -> "thisRow".equals(field.getName()));

                    System.out.println("--拦截器--");
                }
                //回归对应的光标
                resultSet.absolute(0);

            }

            System.out.println("--拦截器打印结束--");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return call;
    }

    /**
     * 解决ResultSet封禁问题
     *
     * @param resultSet 结果集
     */
    protected static void updatable(ResultSet resultSet) {
        //修改可翻滚
        ReflectionUtils.doWithFields(resultSet.getClass(), field -> {
            field.setAccessible(true);
            Object o = field.get(resultSet);
            System.out.println("原始的值为：" + o);
            field.set(resultSet, ResultSet.TYPE_SCROLL_INSENSITIVE);
        }, field -> "resultSetType".equals(field.getName()));

        //修改只读
        ReflectionUtils.doWithFields(resultSet.getClass(), field -> {
            field.setAccessible(true);
            Object o = field.get(resultSet);
            System.out.println("原始的值为：" + o);
            field.set(resultSet, ResultSet.CONCUR_UPDATABLE);
        }, field -> "resultSetConcurrency".equals(field.getName()));

    }
}