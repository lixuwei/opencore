package com.fairyhawk.service.impl.customer;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fairyhawk.common.entity.PageEntity;
import com.fairyhawk.dao.customer.CustomerDao;
import com.fairyhawk.entity.customer.Customer;
import com.fairyhawk.entity.customer.CustomerQueryCondition;
import com.fairyhawk.service.customer.ICustomerService;

public class ICustomerServiceImpl  implements ICustomerService {

    
    private static Log logger = LogFactory.getLog(ICustomerServiceImpl.class);
    private CustomerDao customerDao;
   

    /** ①单条增加 返回主键(可以不返回) */
    @Override
    public Long addOneUser(Customer customer) {
        logger.info("+++++ ");
        return customerDao.addOneUser(customer);
    }

    /** ② 批量增加 返回响应行数(可以不返回) */
    @Override
    public Long addBatchUser(List<Customer> list) {
        return customerDao.addBatchUser(list);
    }

    /**
     * ③ .1删除 返回响应行数(可以不返回), 条件多的可放在Map中， 或者定义存储条件的实体类
     */
    @Override
    public Long deleteUser(Map<String, Object> sourceMap) {
        return customerDao.deleteUser(sourceMap);
    }

    /** ③.2 删除 返回响应行数(可以不返回),根据id */
    @Override
    public Long deleteUserById(Long id) {
        return customerDao.deleteUserById(id);
    }

    /** ④.1修改 返回响应行数(可以不返回) */
    @Override
    public Long updateUserByCondition(Map<String, Object> sourceMap) {
        return null;
    }

    /** ④.2修改 返回响应行数(可以不返回) */
    @Override
    public  Long updateCustomer(Customer customer) {
        return customerDao.updateUser(customer);
    };

    /** ⑤查询单个实体 */
    @Override
    public Customer queryUserByid(Long userId) {
        return customerDao.queryUserByid(userId);
    }

    /** ⑥查询实体List */
    @Override
    public List<Customer> queryUserByCondition(Map<String, Object> sourceMap) {
        return customerDao.queryUserByCondition(sourceMap);
    }

    /** ⑦ 查询返回List<Map<String,Object>> */
    @Override
    public List<Map<String, Object>> queryUserMapsByCondition(
    		Map<String,Object> sourceMap) {
        return customerDao.queryUserMapsByCondition(sourceMap);
    }
    
    public List<Customer> getCustomerName(CustomerQueryCondition queryCondition,
            PageEntity page) {
        return  null;//this.queryForListPage("CustomerMapper.getCustest", queryCondition, page);
    };

    public List<Customer> getCustomerName(CustomerQueryCondition queryCondition) {
        return  null;//this.getMyBatisDao().selectList("CustomerMapper.getCustomerListJson",
                //queryCondition);
    }

    public boolean checkEmail(Customer customer) {
        /*Integer result = this.getMyBatisDao().selectOne("CustomerMapper.checkEmail",
                customer);
        if (result != null && result.intValue() > 0) {
            return false;
        }*/
        return true;
    }


    @Override
    public List<Map<String, Object>> getCustomerListMap(
            CustomerQueryCondition queryCondition) {
        return null;//this.getMyBatisDao().selectList("CustomerMapper.getCustestList", null);
    }

    
    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    
}
