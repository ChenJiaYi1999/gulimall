package com.atguigu.gulimall.order.dao;

import com.atguigu.gulimall.order.entity.OrderItemEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单项信息
 * 
 * @author cjy
 * @email cjy@qq.com
 * @date 2024-01-07 22:51:10
 */
@Mapper
public interface OrderItemDao extends BaseMapper<OrderItemEntity> {
	
}
