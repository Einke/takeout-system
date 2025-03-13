package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/setmeal")
@Tag(name = "套餐相关接口")
@Slf4j
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    /**
     * 新增套餐数据
     * @param setmealDTO
     * @return
     */
    @PostMapping
    @Operation(summary = "新增套餐数据")
    @CacheEvict(cacheNames = "setmealCache",key = "#setmealDTO.categoryId")
    public Result insert(@RequestBody SetmealDTO setmealDTO) {
        log.info("新增套餐数据:{}", setmealDTO);
        setmealService.insertWithSD(setmealDTO);
        return Result.success();
    }

    /**
     * 分页查询套餐
     * @param setmealPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询套餐")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO) {
        log.info("分页查询套餐:{}", setmealPageQueryDTO);
        PageResult pageResult = setmealService.pageQuery(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 根据id批量删除套餐数据
     * @param ids
     * @return
     */
    @DeleteMapping
    @Operation(summary = "根据id批量删除套餐数据")
    @CacheEvict(cacheNames = "setmealCache",allEntries = true)
    public Result delete(@RequestParam List<Long> ids){
        log.info("根据id批量删除套餐数据:{}",ids);
        setmealService.deleteWithSD(ids);
        return Result.success();
    }

    /**
     * 根据id查询套餐数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据id查询套餐数据")
    public Result<SetmealVO> getById(@PathVariable Long id) {
        log.info("根据id查询套餐数据：{}",id);
        SetmealVO setmealVO = setmealService.getByIdWithDish(id);
        return Result.success(setmealVO);
    }

    /**
     * 修改套餐数据
     * @param setmealDTO
     * @return
     */
    @PutMapping
    @Operation(summary = "修改套餐数据")
    @CacheEvict(cacheNames = "setmealCache",allEntries = true)
    public Result update(@RequestBody SetmealDTO setmealDTO) {
        log.info("修改套餐数据：{}", setmealDTO);
        setmealService.updateWithSD(setmealDTO);
        return Result.success();
    }

    /**
     * 套餐起售停售
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @Operation(summary = "套餐起售停售")
    @CacheEvict(cacheNames = "setmealCache",allEntries = true)
    public Result startOrStop(@PathVariable Integer status,Long id){
        log.info("套餐起售停售,id为{}，状态改为{}",id,status);
        setmealService.startOrStop(status,id);
        return Result.success();
    }
}
