package cn.happymaya.sat.dao;


import cn.happymaya.sat.entity.SatLogistics;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <h1>EcommerceLogistics Dao 接口定义</h1>
 *
 * @author superhsc
 */
public interface SatLogisticsDao extends JpaRepository<SatLogistics, Long> {
}
