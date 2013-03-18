package com.fairyhawk.dao.customer;

import java.util.List;
import java.util.Map;

import com.fairyhawk.entity.customer.Customer;
import com.fairyhawk.entity.customer.CustomerQueryCondition;

public interface CrmCustomerDao {
    /**
     * ①单条增加 返回主键(可以不返回) ② 批量增加 返回响应行数(可以不返回) ③ 删除 返回响应行数(可以不返回) ④修改
     * 返回响应行数(可以不返回) ⑤查询单个实体 ⑥查询实体List ⑦ 查询返回List<Map<String,Object>>
     */

    /** ①单条增加 返回主键(可以不返回) */
    public Long addOneUser(Customer customer);

    /** ② 批量增加 返回响应行数(可以不返回) */
    public Long addBatchUser(List<Customer> list);

    /**
     * ③ .1删除 返回响应行数(可以不返回), 条件多的可放在Map中， 或者定义存储条件的实体类
     */
    public Long deleteUser(Map<String, Object> sourceMap);

    /** ③.2 删除 返回响应行数(可以不返回),根据id */
    public Long deleteUserById(Long id);

    /** ④.1修改 返回响应行数(可以不返回) */
    public Long updateUserByCondition(Map<String, Object> sourceMap);

    /** ④.2修改 返回响应行数(可以不返回) */
    public Long updateUserById(Customer customer);

    /** ⑤查询单个实体 */
    public Customer queryUserByid(Long userId);

    /** ⑥查询实体List */
    public List<Customer> queryUserByCondition(Map<String, Object> sourceMap);

    /** ⑦ 查询返回List<Map<String,Object>> */
    public List<Map<String, Object>> queryUserMapsByCondition(
            CustomerQueryCondition condition);

}
