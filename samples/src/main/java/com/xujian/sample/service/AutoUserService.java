package com.xujian.sample.service;

import com.xujian.sample.model.User;
import org.springframework.stereotype.Service;

/**
 * Created by xujian on 2019-06-18
 */
@Service
public class AutoUserService {
    //通过注解自动完成事务
    public boolean add(User user) {
        return false;
    }
}
