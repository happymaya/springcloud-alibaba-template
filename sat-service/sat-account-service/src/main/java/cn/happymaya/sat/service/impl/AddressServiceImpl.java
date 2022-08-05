package cn.happymaya.sat.service.impl;

import cn.happymaya.sat.account.AddressInfo;
import cn.happymaya.sat.common.TableId;
import cn.happymaya.sat.dao.SatAddressDao;
import cn.happymaya.sat.entity.SatAddress;
import cn.happymaya.sat.filter.AccessContext;
import cn.happymaya.sat.service.IAddressService;
import cn.happymaya.sat.vo.LoginUserInfo;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <h1>用户地址相关服务接口实现</h1>
 *
 * @author superhsc
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class AddressServiceImpl implements IAddressService {

    private final SatAddressDao addressDao;

    public AddressServiceImpl(SatAddressDao addressDao) {
        this.addressDao = addressDao;
    }

    /**
     * <h2>存储多个地址信息</h2>
     */
    @Override
    public TableId createAddressInfo(AddressInfo addressInfo) {

        // 不能直接从参数中获取用户的 id 信息
        LoginUserInfo loginUserInfo = AccessContext.getLoginUserInfo();

        // 将传递的参数转换成实体对象
        List<SatAddress> ecommerceAddresses = addressInfo.getAddressItems().stream().map(a -> SatAddress.to(loginUserInfo.getId(), a)).collect(Collectors.toList());

        // 保存到数据表并把返回记录的 id 给调用方
        List<SatAddress> savedRecords = addressDao.saveAll(ecommerceAddresses);
        List<Long> ids = savedRecords.stream().map(SatAddress::getId).collect(Collectors.toList());
        log.info("create address info: [{}], [{}]", loginUserInfo.getId(), JSON.toJSONString(ids));

        return new TableId(ids.stream().map(TableId.Id::new).collect(Collectors.toList()));
    }

    @Override
    public AddressInfo getCurrentAddressInfo() {

        LoginUserInfo loginUserInfo = AccessContext.getLoginUserInfo();

        // 根据 userId 查询到用户的地址信息, 再实现转换
        List<SatAddress> ecommerceAddresses = addressDao.findAllByUserId(loginUserInfo.getId());
        List<AddressInfo.AddressItem> addressItems = ecommerceAddresses.stream().map(SatAddress::toAddressItem).collect(Collectors.toList());

        return new AddressInfo(loginUserInfo.getId(), addressItems);
    }

    @Override
    public AddressInfo getAddressInfoById(Long id) {

        SatAddress ecommerceAddress = addressDao.findById(id).orElse(null);
        if (null == ecommerceAddress) {
            throw new RuntimeException("address is not exist");
        }

        return new AddressInfo(ecommerceAddress.getUserId(), Collections.singletonList(ecommerceAddress.toAddressItem()));
    }

    @Override
    public AddressInfo getAddressInfoByTableId(TableId tableId) {

        List<Long> ids = tableId.getIds().stream().map(TableId.Id::getId).collect(Collectors.toList());
        log.info("get address info by table id: [{}]", JSON.toJSONString(ids));

        List<SatAddress> ecommerceAddresses = addressDao.findAllById(ids);
        if (CollectionUtils.isEmpty(ecommerceAddresses)) {
            return new AddressInfo(-1L, Collections.emptyList());
        }

        List<AddressInfo.AddressItem> addressItems = ecommerceAddresses.stream().map(SatAddress::toAddressItem).collect(Collectors.toList());

        return new AddressInfo(ecommerceAddresses.get(0).getUserId(), addressItems);
    }
}
