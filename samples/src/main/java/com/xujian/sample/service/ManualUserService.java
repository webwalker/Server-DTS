package com.xujian.sample.service;

import com.xujian.sample.config.TransactionHandler;
import com.xujian.sample.mapper.UserMapper;
import com.xujian.sample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

/**
 * Created by xujian on 2019-06-18
 */
@Service
public class ManualUserService {
    @Autowired
    private UserMapper userMapper;

    //    @Resource(name = "txManager2")
    @Autowired
    private PlatformTransactionManager txManager;

    @Autowired
    private TransactionHandler transactionHandler;

    //-----------------------------------------
    @Transactional
    public boolean addUser(User user) {
        addUsers(user);
        return true;
    }

    //此种方式也有有问题, 以下测试示例
    public boolean addUsers0(List<User> users) {
        for (User user : users) {
            addUser(user);
        }
        return true;
    }

    //-----------------------------------------

    //手工控制事务1
    public boolean addUsers1(List<User> users) {
//        TransactionStatus ts = TransactionUtil.newTransaction();
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
        TransactionStatus status = txManager.getTransaction(def); // 获得事务状态

        for (User user : users) {
            addUsers(user);
        }

        //提交事务
//        TransactionUtil.commit(ts);
        txManager.commit(status);
        //回滚事务
//        TransactionUtil.rollback(ts);

        return false;
    }

    public boolean addUsers(User user) {
        try {
            System.out.println("insertUser...");
            this.userMapper.insertUser(user);
            System.out.println("insertUser over.");
            // call DAO layer and adds to database.
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return true;
    }

    //---------------------1 over -----------------------

    //手工控制事务2
    public boolean addUsers2(List<User> users) {
        for (User user : users) {
            transactionHandler.runInTransaction(() -> {
                System.out.println(user.getName() + ", " + user.getPassword());
                addUsers(user);
                return true;
            });
        }
        return false;
    }
}
