package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {

    /**
     * 根据菜品id查询对应的套餐id
     * 查询菜品有没有关联套餐用
     * @param dishIds
     * @return
     */
    List<Long> getSetmealIdsByDishIds(List<Long> dishIds);

    /**
     * 插入套餐菜品相关关系的数据
     * @param setmealDishes
     */
    void insert(List<SetmealDish> setmealDishes);

    /**
     * 根据套餐id删除数据
     * @param setmealId
     */
    void deleteBySetmealId(Long setmealId);

    /**
     * 批量删除
     * @param ids
     */
    void delete(List<Long> ids);

    /**
     * 根据套餐id查询关联数据
     * @param id
     * @return
     */
    List<SetmealDish> getBySetmealId(Long id);
}
