package com.fairyhawk.action.customer;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fairyhawk.common.action.CommonAction;
import com.fairyhawk.entity.customer.CustomerApply;
import com.fairyhawk.service.customer.CustomerApplyService;

/**
 * @ClassName  CustomerApplyAction
 * @package com.fairyhawk.action.customer
 * @description
 * @author  liuqinggang
 * @Create Date: 2013-3-5 下午04:40:50
 * 
 */
public class CustomerApplyAction extends CommonAction{

    private static Logger logger = LoggerFactory.getLogger(CustomerApplyAction.class);
    private CustomerApply user;
    
    private CustomerApplyService customerApplyService;
    
    private List<CustomerApply> list;
    
    /**
     * 
     */
    private static final long serialVersionUID = 706203865775926310L;

    //json 
    public String addApplyInfo(){
        logger.info(" +++ addApplyInfo ");
        try {
            if (user!=null){
                /*if(!StringUtils.isMobileNo(user.getMobile())){
                    this.sendMessage("error");
                    return null;
                }
                if(!StringUtils.validEmail(user.getEmail())){
                    this.sendMessage("error");
                    return null;
                }*/
                user.setCreateTime(new Date());
                customerApplyService.addCustomerApply(user);
            }
            this.sendMessage("success");
        } catch (Exception e) {
            logger.error("+++ addApplyInfo error",e);
        }
        return "json";
        
    }
    //查询list分页 所有的
    public String getApplyList(){
        logger.info(" +++ getApplyList ");
        try {
            this.getPage().setPageSize(20);
            list= customerApplyService.getCustomerApplyList(this.getPage());
        } catch (Exception e) {
            logger.error("+++ getApplyList error",e);
        }
        return "getApplyList";
    }
    

    public CustomerApply getUser() {
        return user;
    }


    public void setUser(CustomerApply user) {
        this.user = user;
    }
    public CustomerApplyService getCustomerApplyService() {
        return customerApplyService;
    }
    public void setCustomerApplyService(CustomerApplyService customerApplyService) {
        this.customerApplyService = customerApplyService;
    }
    public List<CustomerApply> getList() {
        return list;
    }
    public void setList(List<CustomerApply> list) {
        this.list = list;
    }
    
    
}
