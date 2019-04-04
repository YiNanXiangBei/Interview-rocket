package org.yinan.mysql;

import org.yinan.mysql.entity.Amount;

import java.sql.*;

/**
 * @author yinan
 * @date created in 下午2:01 19-4-4
 */
public class AmountExec {

    private static final String JDBC_SERVER = "com.mysql.jdbc.Driver";

    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/study";

    private static final String USER = "root";

    private static final String PASS = "root1234";

    Connection connection;
    PreparedStatement statement;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        AmountExec exec = new AmountExec();
        exec.init();
        Amount amount = new Amount();
//        amount.setSerialNo("12");
//        amount.setMoney(1000);
        amount.setId(2);
//        System.out.println(exec.add(amount));
        exec.delete(amount);
    }

    /**
     * 添加数据
     * @param amount
     * @return
     */
    private int add(Amount amount) throws SQLException {
        String sql = "insert into amount(serial_no, money) values(?,?)";
        statement = connection.prepareStatement(sql);
        statement.setString(1, amount.getSerialNo());
        statement.setInt(2, amount.getMoney());
        int result = statement.executeUpdate();
        connection.commit();
        return result;
    }

    /**
     * 修改数据
     * @param amount
     * @return
     */
    private int update(Amount amount) throws SQLException {
        String sql = "update amount set serial_no=?, money=? where id=?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, amount.getSerialNo());
        statement.setInt(2, amount.getMoney());
        statement.setInt(3, amount.getId());
        int result = statement.executeUpdate();
        connection.commit();
        return result;
    }

    /**
     * 删除数据
     * @param amount
     * @return
     */
    private int delete(Amount amount) throws SQLException {
        String sql = "delete from amount where id=?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, amount.getId());
        int result = statement.executeUpdate();
        connection.commit();
        return result;
    }

    /**
     * 查询数据
     * @param amount
     * @return
     */
    private void search(Amount amount) throws SQLException {
        String sql = "select id, money, serial_no from amount where id=?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, amount.getId());

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            System.out.println("id : " + resultSet.getInt("id"));
            System.out.println("serialNo : " + resultSet.getString("serial_no"));
            System.out.println("amount : " + resultSet.getInt("money"));
        }
    }

    /**
     * 数据库相关信息初始化
     */
    private void init() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_SERVER);
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
        connection.setAutoCommit(false);
    }

}
