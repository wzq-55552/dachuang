package com.wzq.da.chuang.security.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BCryptTest {

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //$2a$10$X9dkRlCYHf/ukex/WkWDou1xCBf256CWeoQ7qPEjBVhWgsqLUgmb2
    @Test
    public void testBCrypt(){
        String s = "dachuang";
        String encode = bCryptPasswordEncoder.encode(s);
        System.out.println(encode);
    }

}
