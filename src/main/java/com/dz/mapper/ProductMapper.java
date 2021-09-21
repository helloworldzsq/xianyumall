package com.dz.mapper;

import com.dz.pojo.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dz
 * @since 2021-09-12
 */
@Mapper
@Repository
public interface ProductMapper extends BaseMapper<Product> {

}
