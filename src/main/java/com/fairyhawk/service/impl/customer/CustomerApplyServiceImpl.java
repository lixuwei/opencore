package com.fairyhawk.service.impl.customer;

import java.util.List;

import com.fairyhawk.common.entity.PageEntity;
import com.fairyhawk.dao.customer.CustomerApplyDao;
import com.fairyhawk.entity.customer.CustomerApply;
import com.fairyhawk.service.customer.CustomerApplyService;

/**
 * @ClassName  CustomerApplyServiceImpl
 * @package com.fairyhawk.service.impl.customer
 * @description
 * @author  liuqinggang
 * @Create Date: 2013-3-5 下午04:43:24
 * 
 */
public class CustomerApplyServiceImpl implements CustomerApplyService {
    
    private CustomerApplyDao customerApplyDao;

    /* (non-Javadoc)
     * @see com.fairyhawk.service.customer.CustomerApplyService#addCustomerApply(com.fairyhawk.entity.customer.CustomerApply)
     */
    @Override
    public void addCustomerApply(CustomerApply apply) {
        customerApplyDao.addCustomerApply(apply);
        
    }

    /* (non-Javadoc)
     * @see com.fairyhawk.service.customer.CustomerApplyService#getCustomerApplyList(com.fairyhawk.common.entity.PageEntity)
     */
    @Override
    public List<CustomerApply> getCustomerApplyList(PageEntity pageEntity) {
        return customerApplyDao.getCustomerApplyList(pageEntity);
    }

    public CustomerApplyDao getCustomerApplyDao() {
        return customerApplyDao;
    }

    public void setCustomerApplyDao(CustomerApplyDao customerApplyDao) {
        this.customerApplyDao = customerApplyDao;
    }
    
    

}
