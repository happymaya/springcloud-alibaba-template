package cn.happymaya.sat.service;

import cn.happymaya.sat.dao.SatUserDao;
import cn.happymaya.sat.entity.SatUser;
import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <h1>EcommerceUser 相关的测试</h1>
 *
 * @author superhsc
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class EcommerceUserTest {

    @Autowired
    private SatUserDao satUserDao;

    @Test
    public void createUserRecord() {

        SatUser ecommerceUser = new SatUser();
        ecommerceUser.setUsername("happymaya@happymaya.cn");
        ecommerceUser.setPassword(MD5.create().digestHex("12345678"));
        ecommerceUser.setExtraInfo("{}");
        log.info("save user: [{}]", JSON.toJSON(satUserDao.save(ecommerceUser)));
    }
}
