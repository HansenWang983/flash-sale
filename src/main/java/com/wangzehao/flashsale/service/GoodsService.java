package com.wangzehao.flashsale.service;

import com.wangzehao.flashsale.dao.GoodsDao;
import com.wangzehao.flashsale.domain.Goods;
import com.wangzehao.flashsale.domain.SaleGoods;
import com.wangzehao.flashsale.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {

    @Autowired
    GoodsDao goodsDao;

    public List<GoodsVo> listGoodsVo(){
        return goodsDao.listGoodsVo();
    }

    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
        return goodsDao.getGoodsVoByGoodsId(goodsId);
    }

    public Integer getStockByGoodsId(long goodsId) {return goodsDao.getStockByGoodsId(goodsId); }

    public Integer getOriginalStockByGoodsId(long goodsId) {return goodsDao.getOriginalStockByGoodsId(goodsId); }

    public Boolean setOriginalStockByGoodsId(long goodsId, int count) {
        return goodsDao.setOriginalStockByGoodsId(goodsId, count);
    }

    public boolean reduceStock(GoodsVo goods) {
        SaleGoods saleGoods = new SaleGoods();
        saleGoods.setGoodsId(goods.getId());
        int ret = goodsDao.reduceStock(saleGoods);
        return ret > 0;
    }
}
