package com.fairyhawk.dao.impl.customer;

import java.util.List;
import java.util.Map;

import com.fairyhawk.common.dao.GenericCrmDaoImpl;
import com.fairyhawk.dao.customer.CrmCustomerDao;
import com.fairyhawk.entity.customer.Customer;
import com.fairyhawk.entity.customer.CustomerQueryCondition;

/**
 * 
 * @ClassName CustomerDaoImpl
 * @package com.fairyhawk.dao.impl
 * @description
 * @author liuqinggang
 * @Create Date: 2013-2-27 上午10:58:51
 * 
 */
public class CrmCustomerDaoImpl extends GenericCrmDaoImpl implements CrmCustomerDao {
    /** ①单条增加 返回主键(可以不返回) */
    @Override
    public Long addOneUser(Customer customer) {
        return new Long(this.getMyBatisDao().insert("CustomerMapper.addOneUser", customer));
    }

    /** ② 批量增加 返回响应行数(可以不返回) */
    @Override
    public Long addBatchUser(List<Customer> list) {
        return null;
    }

    /**
     * ③ .1删除 返回响应行数(可以不返回), 条件多的可放在Map中， 或者定义存储条件的实体类
     */
    @Override
    public Long deleteUser(Map<String, Object> sourceMap) {
        return null;
    }

    /** ③.2 删除 返回响应行数(可以不返回),根据id */
    @Override
    public Long deleteUserById(Long id) {
        return null;
    }

    /** ④.1修改 返回响应行数(可以不返回) */
    @Override
    public Long updateUserByCondition(Map<String, Object> sourceMap) {
        return null;
    }

    /** ④.2修改 返回响应行数(可以不返回) */
    @Override
    public Long updateUserById(Customer customer) {
        return null;
    };

    /** ⑤查询单个实体 */
    @Override
    public Customer queryUserByid(Long userId) {
        return null;
    }

    /** ⑥查询实体List */
    @Override
    public List<Customer> queryUserByCondition(Map<String, Object> sourceMap) {
        return null;
    }

    /** ⑦ 查询返回List<Map<String,Object>> */
    @Override
    public List<Map<String, Object>> queryUserMapsByCondition(
            CustomerQueryCondition condition) {
        return null;
    }

}
