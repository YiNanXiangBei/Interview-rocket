package org.yinan.spring.dao;

/**
 * @author yinan
 * @date 19-4-8
 */
public interface AccountDao {
    void in(String serialNo, int money);

    void out(String serialNo, int money);
}
