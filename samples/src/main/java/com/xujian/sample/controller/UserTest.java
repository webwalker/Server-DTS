package com.xujian.sample.controller;

import com.xujian.sample.model.User;
import com.xujian.sample.service.AutoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * 事务默认回滚的是unchecked异常,如RuntimeException; 不会处理checked异常,如Exception
 * Created by xujian on 2019-06-18
 */
public class UserTest {
    @Autowired
    AutoUserService autoUserService;

    //1.抛出一个Exception
    //结果是不会回滚
    @Transactional
    public void test1(User user) throws Exception {
        autoUserService.add(user);
        throw new Exception("抛出异常");
    }

    //2.抛出一个RuntimeException
    //结果是回滚
    @Transactional
    public void test2(User user) throws Exception {
        autoUserService.add(user);
        throw new RuntimeException("抛出异常");
    }

    //3.抛出一个Exception,注解@Transactional(rollbackFor=Exception.class)
    //结果是回滚
    @Transactional(rollbackFor = Exception.class)
    public void test3(User user) throws Exception {
        autoUserService.add(user);
        throw new Exception("抛出异常");
    }

    //4.使用try catch捕获异常,但catch内不抛出
    //结果是不会回滚
    @Transactional(rollbackFor = Exception.class)
    public void test4(User user) throws Exception {
        try {
            autoUserService.add(user);
            throw new Exception("抛出异常");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //5.使用try catch捕获异常,catch内抛出
    //结果是回滚
    @Transactional(rollbackFor = Exception.class)
    public void test5(User user) throws Exception {
        try {
            autoUserService.add(user);
            throw new Exception("抛出异常");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    //6.try catch捕获异常,不抛出,进行手动回滚,不指定回滚异常类型
    //结果是回滚
    @Transactional
    public void test6(User user) {
        try {
            autoUserService.add(user);
            throw new Exception("抛出异常");
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
    }

    //7.try catch捕获异常,不抛出,进行手动回滚,指定回滚异常类型
    //结果是回滚
    @Transactional(rollbackFor = Exception.class)
    public void test7(User user) {
        try {
            autoUserService.add(user);
            throw new Exception("抛出异常");
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
    }
}
