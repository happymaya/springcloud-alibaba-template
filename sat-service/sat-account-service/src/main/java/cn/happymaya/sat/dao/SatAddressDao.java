package cn.happymaya.sat.dao;

import cn.happymaya.sat.entity.SatAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * <h1>EcommerceAddress Dao 接口定义</h1>
 *
 * @author superhsc
 */
public interface SatAddressDao extends JpaRepository<SatAddress, Long> {

    /**
     * <h2>根据 用户 id 查询地址信息</h2>
     */
    List<SatAddress> findAllByUserId(Long userId);
}
