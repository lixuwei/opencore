package com.fairyhawk.common.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fairyhawk.common.dao.MyBatisDao;
import com.fairyhawk.common.entity.PageEntity;
import com.fairyhawk.common.entity.PageOL;

/**
 * 
 * @ClassName GenericDaoImpl
 * @package com.fairyhawk.common.dao
 * @description 数据库基类 供其他dao继承
 * @author liuqinggang
 * @Create Date: 2013-2-27 上午11:05:17
 * 
 */
public abstract class GenericDaoImpl {

    private MyBatisDao myBatisDao;

    public MyBatisDao getMyBatisDao() {
        return myBatisDao;
    }

    public void setMyBatisDao(MyBatisDao myBatisDao) {
        this.myBatisDao = myBatisDao;
    }

    /**
     * 分页查询时使用
     * 
     * @return
     */
    public <T> List<T> queryForListPage(String sqlKey, Object params, PageEntity page) {

        /**
         * 分页时需要2个sql。在正常sql后面加pageCount为计算count的sql
         * 如：customre.getcustomreByTime必须命名为customre.getcustomreByTimeCount
         */

        // 查询总行数
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageCondition", params);
        PageOL pageOL = new PageOL();
        pageOL.setOffsetPara((page.getCurrentPage() - 1) * page.getPageSize());
        pageOL.setLimitPara(page.getPageSize());
        map.put("page", pageOL);

        Integer objectscount = this.getMyBatisDao().selectOne(sqlKey + "Count", map);

        if (objectscount == null || objectscount == 0) {
            page.setTotalResultSize(0);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return null;
        } else {
            page.setTotalResultSize(objectscount);
            int totalPageSize = (page.getTotalResultSize() - 1) / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
            return this.getMyBatisDao().selectList(sqlKey, map);
        }

    }

}
