package com.fairyhawk.dao.impl.customer;

import java.util.List;
import java.util.Map;

import com.fairyhawk.common.dao.GenericDaoImpl;
import com.fairyhawk.dao.customer.CustomerDao;
import com.fairyhawk.entity.customer.Customer;

/**
 * 
 * @ClassName CustomerDaoImpl
 * @package com.fairyhawk.dao.impl
 * @description
 * @author liuqinggang
 * @Create Date: 2013-2-27 上午10:58:51
 * 
 */
public class CustomerDaoImpl extends GenericDaoImpl implements CustomerDao {
    /** ①单条增加 返回主键(可以不返回) */
    @Override
    public Long addOneUser(Customer customer) {
        return new Long(this.getMyBatisDao().insert("CustomerMapper.addOneUser", customer));
    }

    /** ② 批量增加 返回响应行数(可以不返回) */
    @Override
    public Long addBatchUser(List<Customer> list) {
        return new Long(this.getMyBatisDao().insert("CustomerMapper.addBatchUser", list));
    }

    /**
     * ③ .1删除 返回响应行数(可以不返回), 条件多的可放在Map中， 或者定义存储条件的实体类
     */
    @Override
    public Long deleteUser(Map<String, Object> sourceMap) {
        return new Long(this.getMyBatisDao().delete("CustomerMapper.deleteUser", sourceMap));
    }

    /** ③.2 删除 返回响应行数(可以不返回),根据id */
    @Override
    public Long deleteUserById(Long id) {
    	return new Long(this.getMyBatisDao().delete("CustomerMapper.deleteUserById", id));
    }

    /** ④.1修改 返回响应行数(可以不返回) */
    @Override
    public Long updateUserByCondition(Map<String, Object> sourceMap) {
        return null;
    }

    /** ④.2修改 返回响应行数(可以不返回) */
    @Override
    public Long updateUser(Customer customer) {
    	return new Long(this.getMyBatisDao().update("CustomerMapper.updateUser", customer));
    };

    /** ⑤查询单个实体 */
    @Override
    public Customer queryUserByid(Long userId) {
        return this.getMyBatisDao().selectOne("CustomerMapper.queryUserById", userId);
    }

    /** ⑥查询实体List */
    @Override
    public List<Customer> queryUserByCondition(Map<String, Object> sourceMap) {
        
        return this.getMyBatisDao().selectList("CustomerMapper.queryUserByCondition", sourceMap);
        
    }

    /** ⑦ 查询返回List<Map<String,Object>> */
    @Override
    public List<Map<String, Object>> queryUserMapsByCondition(
    		Map<String, Object> sourceMap) {
        return this.getMyBatisDao().selectList("CustomerMapper.queryUserMapsByCondition", sourceMap);
        
    }

}
