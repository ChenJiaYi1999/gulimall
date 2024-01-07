package com.atguigu.gulimall.coupon.dao;

import com.atguigu.gulimall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author cjy
 * @email cjy@qq.com
 * @date 2024-01-07 17:55:10
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
