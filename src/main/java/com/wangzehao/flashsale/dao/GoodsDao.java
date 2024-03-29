package com.wangzehao.flashsale.dao;

import com.wangzehao.flashsale.domain.Goods;
import com.wangzehao.flashsale.domain.SaleGoods;
import com.wangzehao.flashsale.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface GoodsDao {
    @Select("select sg.sale_price, sg.stock_count, sg.start_date, sg.end_date, g.* from sale_goods sg left join goods g on sg.goods_id = g.id")
    public List<GoodsVo> listGoodsVo();

    @Select("select sg.sale_price, sg.stock_count, sg.start_date, sg.end_date, g.* from sale_goods sg left join goods g on sg.goods_id = g.id where g.id = #{goodsId}")
    public GoodsVo getGoodsVoByGoodsId(@Param("goodsId") long goodsId);

    @Update("update sale_goods set stock_count = stock_count - 1 where goods_id = #{goodsId} and stock_count > 0")
    public int reduceStock(SaleGoods g);

    @Select("select sg.stock_count from sale_goods sg where goods_id = #{goodsId}")
    public Integer getStockByGoodsId(@Param("goodsId") long goodsId);

    @Select("select sg.original_stock from sale_goods sg where goods_id = #{goodsId}")
    public Integer getOriginalStockByGoodsId(@Param("goodsId") long goodsId);

    @Select("update sale_goods set original_stock = #{count}  where goods_id = #{goodsId}")
    public Boolean setOriginalStockByGoodsId(@Param("goodsId") long goodsId, @Param("count") int count);
}
