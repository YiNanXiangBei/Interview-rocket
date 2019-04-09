package org.yinan.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yinan.spring.dao.AccountDao;
import org.yinan.spring.service.AmountService;

/**
 * @author yinan
 * @date 19-4-8
 */
@Service("amountService")
public class AmountServiceImpl implements AmountService {

    private final AccountDao accountDao;

    @Autowired
    public AmountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    @Transactional(propagation = Propagation.NESTED, noRollbackFor = ArithmeticException.class, rollbackFor = Exception.class)
//    @Transactional(propagation = Propagation.NESTED)
    public void transfer(String out, String in, int money) {
        accountDao.out(out, money);
        int i = 1 / 0;
        accountDao.in(in, money);
    }

    @Override
    @Transactional
    public void insert(String serialNo, int money) {
        accountDao.insert(serialNo, money);
    }
}
