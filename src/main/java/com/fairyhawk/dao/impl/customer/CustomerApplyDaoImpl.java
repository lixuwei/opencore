package com.fairyhawk.dao.impl.customer;

import java.util.List;

import com.fairyhawk.common.dao.GenericDaoImpl;
import com.fairyhawk.common.entity.PageEntity;
import com.fairyhawk.dao.customer.CustomerApplyDao;
import com.fairyhawk.entity.customer.CustomerApply;

/**
 * @ClassName  CustomerApplyDaoImpl
 * @package com.fairyhawk.dao.impl.customer
 * @description
 * @author  liuqinggang
 * @Create Date: 2013-3-5 下午04:42:41
 * 
 */
public class CustomerApplyDaoImpl extends GenericDaoImpl implements CustomerApplyDao {

    /* (non-Javadoc)
     * @see com.fairyhawk.dao.customer.CustomerApplyDao#addCustomerApply(com.fairyhawk.entity.customer.CustomerApply)
     */
    @Override
    public void addCustomerApply(CustomerApply apply) {
        this.getMyBatisDao().insert("CustomerApplyMapper.addOneCustomerApply", apply);
    }

    /* (non-Javadoc)
     * @see com.fairyhawk.dao.customer.CustomerApplyDao#getCustomerApplyList(com.fairyhawk.common.entity.PageEntity)
     */
    @Override
    public List<CustomerApply> getCustomerApplyList(PageEntity pageEntity) {
       return this.queryForListPage("CustomerApplyMapper.getCustomerApplylit", null, pageEntity);
    }

}
