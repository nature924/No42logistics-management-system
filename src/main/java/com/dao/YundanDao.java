package com.dao;

import com.entity.YundanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.YundanView;

/**
 * 运单信息 Dao 接口
 *
 * @author 
 */
public interface YundanDao extends BaseMapper<YundanEntity> {

   List<YundanView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
