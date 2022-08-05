package cn.happymaya.sat.entity;

import cn.happymaya.sat.constant.BrandCategory;
import cn.happymaya.sat.constant.GoodsCategory;
import cn.happymaya.sat.constant.GoodsStatus;
import cn.happymaya.sat.converter.BrandCategoryConverter;
import cn.happymaya.sat.converter.GoodsCategoryConverter;
import cn.happymaya.sat.converter.GoodsStatusConverter;
import cn.happymaya.sat.goods.GoodsInfo;
import cn.happymaya.sat.goods.SimpleGoodsInfo;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * <h1>商品表实体类定义</h1>
 *
 * @author superhsc
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "sat_goods")
public class SatGoods {

    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 商品类型
     */
    @Column(name = "goods_category", nullable = false)
    @Convert(converter = GoodsCategoryConverter.class)
    private GoodsCategory goodsCategory;

    /**
     * 品牌分类
     */
    @Column(name = "brand_category", nullable = false)
    @Convert(converter = BrandCategoryConverter.class)
    private BrandCategory brandCategory;

    /**
     * 商品名称
     */
    @Column(name = "goods_name", nullable = false)
    private String goodsName;

    /**
     * 商品名称
     */
    @Column(name = "goods_pic", nullable = false)
    private String goodsPic;

    /**
     * 商品描述信息
     */
    @Column(name = "goods_description", nullable = false)
    private String goodsDescription;

    /**
     * 商品状态
     */
    @Column(name = "goods_status", nullable = false)
    @Convert(converter = GoodsStatusConverter.class)
    private GoodsStatus goodsStatus;

    /**
     * 商品价格: 单位: 分、厘
     */
    @Column(name = "price", nullable = false)
    private Integer price;

    /**
     * 总供应量
     */
    @Column(name = "supply", nullable = false)
    private Long supply;

    /**
     * 库存
     */
    @Column(name = "inventory", nullable = false)
    private Long inventory;

    /**
     * 商品属性, json 字符串存储
     */
    @Column(name = "goods_property", nullable = false)
    private String goodsProperty;

    /**
     * 创建时间
     */
    @CreatedDate
    @Column(name = "create_time", nullable = false)
    private Date createTime;

    /**
     * 更新时间
     */
    @LastModifiedDate
    @Column(name = "update_time", nullable = false)
    private Date updateTime;

    /**
     * <h2>将 GoodsInfo 转成实体对象</h2>
     */
    public static SatGoods to(GoodsInfo goodsInfo) {

        SatGoods satGoods = new SatGoods();

        satGoods.setGoodsCategory(GoodsCategory.of(goodsInfo.getGoodsCategory()));
        satGoods.setBrandCategory(BrandCategory.of(goodsInfo.getBrandCategory()));
        satGoods.setGoodsName(goodsInfo.getGoodsName());
        satGoods.setGoodsPic(goodsInfo.getGoodsPic());
        satGoods.setGoodsDescription(goodsInfo.getGoodsDescription());
        satGoods.setGoodsStatus(GoodsStatus.ONLINE);  // 可以增加一个审核的过程
        satGoods.setPrice(goodsInfo.getPrice());
        satGoods.setSupply(goodsInfo.getSupply());
        satGoods.setInventory(goodsInfo.getSupply());
        satGoods.setGoodsProperty(
                JSON.toJSONString(goodsInfo.getGoodsProperty())
        );

        return satGoods;
    }

    /**
     * <h2>将实体对象转成 GoodsInfo 对象</h2>
     */
    public GoodsInfo toGoodsInfo() {

        GoodsInfo goodsInfo = new GoodsInfo();

        goodsInfo.setId(this.id);
        goodsInfo.setGoodsCategory(this.goodsCategory.getCode());
        goodsInfo.setBrandCategory(this.brandCategory.getCode());
        goodsInfo.setGoodsName(this.goodsName);
        goodsInfo.setGoodsPic(this.goodsPic);
        goodsInfo.setGoodsDescription(this.goodsDescription);
        goodsInfo.setGoodsStatus(this.goodsStatus.getStatus());
        goodsInfo.setPrice(this.price);
        goodsInfo.setGoodsProperty(
                JSON.parseObject(this.goodsProperty, GoodsInfo.GoodsProperty.class)
        );
        goodsInfo.setSupply(this.supply);
        goodsInfo.setInventory(this.inventory);
        goodsInfo.setCreateTime(this.createTime);
        goodsInfo.setUpdateTime(this.updateTime);

        return goodsInfo;
    }

    /**
     * <h2>将实体对象转成 SimpleGoodsInfo 对象</h2>
     */
    public SimpleGoodsInfo toSimple() {

        SimpleGoodsInfo goodsInfo = new SimpleGoodsInfo();

        goodsInfo.setId(this.id);
        goodsInfo.setGoodsName(this.goodsName);
        goodsInfo.setGoodsPic(this.goodsPic);
        goodsInfo.setPrice(this.price);

        return goodsInfo;
    }
}
