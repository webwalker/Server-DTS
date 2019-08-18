package com.xujian.sample.config;

import com.xujian.sample.SamplesApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * Created by xujian on 2019-08-18
 */
public class TransactionUtil {
    public static DataSourceTransactionManager getTxManager() {
//        DataSourceTransactionManager transactionManager = SpringContextHolder.getBean("transactionManager");
//        return transactionManager;
        return null;
    }

    public static TransactionStatus newTransaction() {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
        TransactionStatus status = getTxManager().getTransaction(def); // 获得事务状态
        return status;
    }

    public static void commit(TransactionStatus status) {
        if (status != null) {
            getTxManager().commit(status);
        }
    }

    public static void rollback(TransactionStatus status) {
        if (status != null) {
            getTxManager().rollback(status);
        }
    }
}
