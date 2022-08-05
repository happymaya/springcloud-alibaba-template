package cn.happymaya.sat.dao;

import cn.happymaya.sat.entity.SatUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <h1>EcommerceUser Dao 接口定义</h1>
 * */
public interface EcommerceUserDao extends JpaRepository<SatUser, Long> {

    /**
     * <h2>根据用户名查询 EcommerceUser 对象</h2>
     * select * from t_ecommerce_user where username = ?
     * */
    SatUser findByUsername(String username);

    /**
     * <h2>根据用户名和密码查询实体对象</h2>
     * select * from t_ecommerce_user where username = ? and password = ?
     * */
    SatUser findByUsernameAndPassword(String username, String password);
}
