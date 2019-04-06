package cn.iocoder.mall.user.application.controller.users;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.user.application.convert.UserAddressConvert;
import cn.iocoder.mall.user.application.po.UserAddressAddPO;
import cn.iocoder.mall.user.application.po.UserAddressUpdatePO;
import cn.iocoder.mall.user.sdk.context.UserSecurityContextHolder;
import cn.iocoder.mall.user.service.api.UserAddressService;
import cn.iocoder.mall.user.service.api.dto.UserAddressAddDTO;
import cn.iocoder.mall.user.service.api.dto.UserAddressUpdateDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户地址
 *
 * @author Sin
 * @time 2019-04-06 14:11
 */
@RestController
@RequestMapping("user/address")
@Api(description = "用户地址API")
public class UserAddressController {

    @Autowired
    private UserAddressService userAddressService;

    @PostMapping("add")
    @ApiOperation(value = "用户地址-添加")
    public CommonResult addAddress(@RequestBody @Validated UserAddressAddPO userAddressAddPO) {
        Integer userId = UserSecurityContextHolder.getContext().getUserId();
        UserAddressAddDTO userAddressAddDTO = UserAddressConvert.INSTANCE.convert(userAddressAddPO);
        userAddressAddDTO.setUserId(userId);
        return userAddressService.addAddress(userAddressAddDTO);
    }

    @PostMapping("update")
    @ApiOperation(value = "用户地址-更新")
    public CommonResult updateAddress(@RequestBody @Validated UserAddressUpdatePO userAddressUpdatePO) {
        Integer userId = UserSecurityContextHolder.getContext().getUserId();
        UserAddressUpdateDTO userAddressUpdateDTO = UserAddressConvert.INSTANCE.convert(userAddressUpdatePO);
        userAddressUpdateDTO.setUserId(userId);
        return userAddressService.updateAddress(userAddressUpdateDTO);
    }

    @DeleteMapping("remove")
    @ApiOperation(value = "用户地址-删除")
    public CommonResult removeAddress(@RequestParam("id") Integer id) {
        Integer userId = UserSecurityContextHolder.getContext().getUserId();
        return userAddressService.removeAddress(userId, id);
    }

    @GetMapping("list")
    @ApiOperation(value = "用户地址列表")
    public CommonResult addressList() {
        Integer userId = UserSecurityContextHolder.getContext().getUserId();
        return userAddressService.addressList(userId);
    }
}