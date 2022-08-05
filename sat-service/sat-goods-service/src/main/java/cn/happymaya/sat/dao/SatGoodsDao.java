package cn.happymaya.sat.dao;


import cn.happymaya.sat.constant.BrandCategory;
import cn.happymaya.sat.constant.GoodsCategory;
import cn.happymaya.sat.entity.SatGoods;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

/**
 * <h1>SatGoodsDao Dao 接口定义</h1>
 *
 * @author superhsc
 */
public interface SatGoodsDao extends PagingAndSortingRepository<SatGoods, Long> {

    /**
     * <h2>根据查询条件查询商品表, 并限制返回结果</h2>
     * select * from t_ecommerce_goods where goods_category = ? and brand_category = ?
     * and goods_name = ? limit 1;
     */
    Optional<SatGoods> findFirst1ByGoodsCategoryAndBrandCategoryAndGoodsName(GoodsCategory goodsCategory, BrandCategory brandCategory, String goodsName);
}
