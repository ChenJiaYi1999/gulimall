package com.atguigu.gulimall.order.dao;

import com.atguigu.gulimall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author cjy
 * @email cjy@qq.com
 * @date 2024-01-07 22:51:10
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
