package com.dz.service.impl;

import com.dz.mapper.ProductMapper;
import com.dz.pojo.Cart;
import com.dz.mapper.CartMapper;
import com.dz.pojo.Product;
import com.dz.service.CartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dz.util.MybatisRedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dz
 * @since 2021-09-14
 */
@Service
@CacheNamespace(implementation= MybatisRedisCache.class,eviction= MybatisRedisCache.class)
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;
    // 获取登录用户的购物车
    public List<Product> getCarts(String username){
        List<Cart> carts = cartMapper.selectList(null);
        List<Product> list = new ArrayList<>();
        for (Cart cart : carts) {
            if (cart.getUsername().equals(username)){
                Product product = productMapper.selectById(cart.getPid());
                list.add(product);
            }
        }
       return list;
    }
    //将产品从购物车中删除
    public void delectProduct(String username,Integer pid){
        List<Cart> carts = cartMapper.selectList(null);
        for (Cart cart : carts) {
            if (cart.getUsername().equals(username) && cart.getPid()==pid){
                cartMapper.delectProduct(cart.getUsername(),cart.getPid());
                break;
            }
        }
    }
}
