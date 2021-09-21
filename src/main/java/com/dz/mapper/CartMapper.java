package com.dz.mapper;

import com.dz.pojo.Cart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dz
 * @since 2021-09-14
 */
@Mapper
@Repository
public interface CartMapper extends BaseMapper<Cart> {
      int delectProduct(String username,Integer pid);
}
