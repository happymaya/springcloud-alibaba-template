package cn.happymaya.sat.service;

import cn.happymaya.sat.filter.AccessContext;
import cn.happymaya.sat.vo.LoginUserInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <h1>测试用例基类, 填充登录用户信息</h1>
 *
 * @author superhsc
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public abstract class BaseTest {

    protected final LoginUserInfo loginUserInfo = new LoginUserInfo(10L, "superhsc@happymaya.cn");

    @Before
    public void init() {
        AccessContext.setLoginUserInfo(loginUserInfo);
    }

    @After
    public void destroy() {
        AccessContext.clearLoginUserInfo();
    }
}
