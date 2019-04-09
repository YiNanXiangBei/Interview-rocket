package org.yinan.spring.service;

/**
 * @author yinan
 * @date created in 下午4:19 19-4-8
 */
public interface AmountService {

    void transfer(String out, String in, int money);

    void insert(String serialNo, int money);

}
