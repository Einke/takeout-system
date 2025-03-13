package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.GoodsSalesDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {

    /**
     * 插入订单数据
     * @param orders
     */
    void insert(Orders orders);

    /**
     * 历史订单分页查询
     * @param ordersPageQueryDTO
     * @return
     */
    Page<Orders> pageSelect(OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     * 查询订单详情
     * @param id
     * @return
     */
    Orders getDetailById(Long id);

    /**
     * 修改订单
     * @param orders
     */
    void update(Orders orders);

    /**
     * 查询各个状态的订单数量
     * @param toBeConfirmed
     * @return
     */
    Integer countStatus(Integer toBeConfirmed);

    /**
     * 查询是否有超时订单
     * @param status
     * @param time
     * @return
     */
    @Select("select * from orders where status = #{status} and order_time < #{time}")
    List<Orders> getByStatusAndOrderTimeLT(Integer status, LocalDateTime time);

    /**
     * 传时间算营业总和
     * @param map
     * @return
     */
    Double sumByMap(Map map);

    /**
     * 算订单数
     * @param map
     * @return
     */
    Integer countByMap(Map map);

    /**
     * 销量前十的菜品统计
     * @param begin
     * @param end
     * @return
     */
    List<GoodsSalesDTO> getTop10(LocalDateTime begin, LocalDateTime end);
}
