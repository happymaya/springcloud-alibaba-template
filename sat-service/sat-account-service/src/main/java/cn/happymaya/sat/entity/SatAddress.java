package cn.happymaya.sat.entity;

import cn.happymaya.sat.account.AddressInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * <h1>用户地址表实体类定义</h1>
 *
 * @author superhsc
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "sat_address")
public class SatAddress {

    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 用户 id
     */
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /**
     * 用户名
     */
    @Column(name = "username", nullable = false)
    private String username;

    /**
     * 电话
     */
    @Column(name = "phone", nullable = false)
    private String phone;

    /**
     * 省
     */
    @Column(name = "province", nullable = false)
    private String province;

    /**
     * 市
     */
    @Column(name = "city", nullable = false)
    private String city;

    /**
     * 详细地址
     */
    @Column(name = "address_detail", nullable = false)
    private String addressDetail;

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
     * <h2>根据 userId + AddressItem 得到 EcommerceAddress</h2>
     */
    public static SatAddress to(Long userId, AddressInfo.AddressItem addressItem) {

        SatAddress satAddress = new SatAddress();

        satAddress.setUserId(userId);
        satAddress.setUsername(addressItem.getUsername());
        satAddress.setPhone(addressItem.getPhone());
        satAddress.setProvince(addressItem.getProvince());
        satAddress.setCity(addressItem.getCity());
        satAddress.setAddressDetail(addressItem.getAddressDetail());

        return satAddress;
    }

    /**
     * <h2>将 SatAddress 对象转成 AddressInfo</h2>
     */
    public AddressInfo.AddressItem toAddressItem() {

        AddressInfo.AddressItem addressItem = new AddressInfo.AddressItem();

        addressItem.setId(this.id);
        addressItem.setUsername(this.username);
        addressItem.setPhone(this.phone);
        addressItem.setProvince(this.province);
        addressItem.setCity(this.city);
        addressItem.setAddressDetail(this.addressDetail);
        addressItem.setCreateTime(this.createTime);
        addressItem.setUpdateTime(this.updateTime);

        return addressItem;
    }
}
