package cn.happymaya.sat.dao;

import cn.happymaya.sat.entity.SatBalance;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <h1>EcommerceBalance Dao 接口定义</h1>
 *
 * @author superhsc
 */
public interface SatBalanceDao extends JpaRepository<SatBalance, Long> {

    /**
     * 根据 userId 查询 EcommerceBalance 对象
     */
    SatBalance findByUserId(Long userId);
}
