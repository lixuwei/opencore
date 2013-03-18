package com.fairyhawk.service.customer;

import java.util.List;

import com.fairyhawk.common.entity.PageEntity;
import com.fairyhawk.entity.customer.CustomerApply;

/**
 * @ClassName CustomerApplyService
 * @package com.fairyhawk.service.customer
 * @description
 * @author liuqinggang
 * @Create Date: 2013-3-5 下午04:43:06
 * 
 */
public interface CustomerApplyService {

    /** 添加 */
    public void addCustomerApply(CustomerApply apply);

    /** 获得所有 */
    public List<CustomerApply> getCustomerApplyList(PageEntity pageEntity);

}
