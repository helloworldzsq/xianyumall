package com.dz.mapper;

import com.dz.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dz
 * @since 2021-09-10
 */
@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
