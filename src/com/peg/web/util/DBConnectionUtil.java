package com.peg.web.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @createTime 2019-06-06 14:52
 */
public class DBConnectionUtil {
    private static String CONFIG_PATH = "config.properties";
    private static String driver = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;

    public synchronized static Connection getConnection() throws IOException, SQLException, FileNotFoundException,
            ClassNotFoundException {
        if (url == null || username == null || password == null) {
            Properties dbproperties = new Properties();
            try {
                String configPath = DBConnectionUtil.class.getResource("/").getFile() + CONFIG_PATH;
                dbproperties.load(new FileInputStream(configPath));
                driver = dbproperties.getProperty("jdbc_driver2");
                url = dbproperties.getProperty("jdbc_url2");
                username = dbproperties.getProperty("jdbc_username2");
                password = dbproperties.getProperty("jdbc_password2");
            } catch (FileNotFoundException e) {
                throw new FileNotFoundException("不能读取属性文件,请确保config.properties在classpath指定的路径中");
            }
        }

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            throw new ClassNotFoundException("请检查sqlserver提供的JDBC jar包");
        }

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new SQLException("请检查数据库连接地址、用户、密码是否正确");
        }
        return connection;
    }

    /**
     * 执行返回泛型集合的SQL语句
     * @param clazz 泛型类型
     * @param sql 查询SQL语句
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> List<T> executeList(Class<T> clazz, String sql) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<T> list = new ArrayList<T>();
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                T obj = executeResultSet(clazz, rs);
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        } finally {
            close(conn, ps, rs);
        }
        return list;
    }

    /**
     * 将一条记录转成一个对象
     * @param clazz 泛型类型
     * @param rs ResultSet对象
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws SQLException
     */
    public static <T> T executeResultSet(Class<T> clazz, ResultSet rs) throws IllegalAccessException,
            InstantiationException, SQLException {
        T obj = clazz.newInstance();
        ResultSetMetaData rsm = rs.getMetaData();
        int columnCount = rsm.getColumnCount();
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String fieldName = field.getName();
            for (int j = 1; j <= columnCount; j++) {
                String columnName = rsm.getColumnName(j);
                if (fieldName.equalsIgnoreCase(columnName)) {
                    Object value = rs.getObject(j);
                    field.setAccessible(true);
                    field.set(obj, value);
                    break;
                }
            }
        }
        return obj;
    }

    /**
     * 关闭连接，释放资源
     * @param conn
     * @param ps
     * @param rs
     */
    private static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
