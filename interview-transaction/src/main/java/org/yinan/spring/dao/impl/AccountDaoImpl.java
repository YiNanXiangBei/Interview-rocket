package org.yinan.spring.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.yinan.spring.dao.AccountDao;

/**
 * @author yinan
 * @date 19-4-8
 */
@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AccountDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void in(String serialNo, int money) {
        String sql = "update amount set money = money + ? where serial_no = ?";
        jdbcTemplate.update(sql, money, serialNo);
    }

    @Override
    public void out(String serialNo, int money) {
        String sql = "update amount set money = money - ? where serial_no = ?";
        jdbcTemplate.update(sql, money, serialNo);
    }

    @Override
    public void insert(String serialNo, int money) {
        String sql = "insert into amount(serial_no, money) values(?, ?)";
        jdbcTemplate.update(sql, serialNo, money);
    }
}
