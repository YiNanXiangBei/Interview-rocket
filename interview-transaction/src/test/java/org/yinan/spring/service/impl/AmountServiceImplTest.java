package org.yinan.spring.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.yinan.spring.service.AmountService;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * @author yinan
 * @date created in 上午9:52 19-4-9
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
//@Transactional(transactionManager="trans")
//@Transactional
public class AmountServiceImplTest {

    @Autowired
    private AmountService amountService;

    @Test
    public void transfer() {
        amountService.transfer("34499f5b-e627-4064-9f95-10edc6b9e9dd",
                "19057eb3-211b-414a-a8ef-c62732aee4d3",
                10);
    }


    @Test
    public void insert() {
        String serialNo = UUID.randomUUID().toString();
        int money = 100;
        amountService.insert(serialNo, money);
    }
}