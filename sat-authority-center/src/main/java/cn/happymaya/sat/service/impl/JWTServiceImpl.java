package cn.happymaya.sat.service.impl;

import cn.happymaya.sat.constant.AuthorityConstant;
import cn.happymaya.sat.constant.CommonConstant;
import cn.happymaya.sat.dao.SatUserDao;
import cn.happymaya.sat.entity.SatUser;
import cn.happymaya.sat.service.IJWTService;
import cn.happymaya.sat.vo.LoginUserInfo;
import cn.happymaya.sat.vo.UsernameAndPassword;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Decoder;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

/**
 * <h1>JWT 相关服务接口实现</h1>
 *
 * @author superhsc
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class JWTServiceImpl implements IJWTService {

    private final SatUserDao satUserDao;

    public JWTServiceImpl(SatUserDao satUserDao) {
        this.satUserDao = satUserDao;
    }

    @Override
    public String generateToken(String username, String password) throws Exception {

        return generateToken(username, password, 0);
    }

    @Override
    public String generateToken(String username, String password, int expire) throws Exception {

        // 首先需要验证用户是否能够通过授权校验, 即输入的用户名和密码能否匹配数据表记录
        SatUser satUser = satUserDao.findByUsernameAndPassword(username, password);
        if (null == satUser) {
            log.error("can not find user: [{}], [{}]", username, password);
            return null;
        }

        // Token 中塞入对象, 即 JWT 中存储的信息, 后端拿到这些信息就可以知道是哪个用户在操作
        LoginUserInfo loginUserInfo = new LoginUserInfo(satUser.getId(), satUser.getUsername());

        if (expire <= 0) {
            expire = AuthorityConstant.DEFAULT_EXPIRE_DAY;
        }

        // 计算超时时间
        ZonedDateTime zdt = LocalDate.now().plus(expire, ChronoUnit.DAYS).atStartOfDay(ZoneId.systemDefault());
        Date expireDate = Date.from(zdt.toInstant());

        return Jwts.builder()
                // jwt payload --> KV
                .claim(CommonConstant.JWT_USER_INFO_KEY, JSON.toJSONString(loginUserInfo))
                // jwt id
                .setId(UUID.randomUUID().toString())
                // jwt 过期时间
                .setExpiration(expireDate)
                // jwt 签名 --> 加密
                .signWith(getPrivateKey(), SignatureAlgorithm.RS256).compact();
    }

    @Override
    public String registerUserAndGenerateToken(UsernameAndPassword usernameAndPassword) throws Exception {

        // 先去校验用户名是否存在, 如果存在, 不能重复注册
        SatUser oldUser = satUserDao.findByUsername(usernameAndPassword.getUsername());
        if (null != oldUser) {
            log.error("username is registered: [{}]", oldUser.getUsername());
            return null;
        }

        SatUser satUser = new SatUser();
        satUser.setUsername(usernameAndPassword.getUsername());
        satUser.setPassword(usernameAndPassword.getPassword());   // MD5 编码以后
        satUser.setExtraInfo("{}");

        // 注册一个新用户, 写一条记录到数据表中
        satUser = satUserDao.save(satUser);
        log.info("register user success: [{}], [{}]", satUser.getUsername(), satUser.getId());

        // 生成 token 并返回
        return generateToken(satUser.getUsername(), satUser.getPassword());
    }

    /**
     * <h2>根据本地存储的私钥获取到 PrivateKey 对象</h2>
     */
    private PrivateKey getPrivateKey() throws Exception {

        PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(new BASE64Decoder().decodeBuffer(AuthorityConstant.PRIVATE_KEY));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(priPKCS8);
    }
}
