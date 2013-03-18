package com.fairyhawk.action.customer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fairyhawk.common.action.CommonAction;
import com.fairyhawk.common.entity.JsonEntity;
import com.fairyhawk.entity.customer.Customer;
import com.fairyhawk.entity.customer.CustomerQueryCondition;
import com.fairyhawk.service.customer.ICustomerService;
import com.fairyhawk.service.mq.NotifyMessageProducer;
import com.fairyhawk.test.Threads;
/**
 * 
 * @ClassName  CustomerAction
 * @package com.fairyhawk.action.customer
 * @description 操作数据库各种DEMO
 * @author  liuqinggang
 * @Create Date: 2013-2-27 上午10:32:24
 *
 */
public class CustomerAction extends CommonAction {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5437567400651524629L;
  
	private static Logger logger = LoggerFactory.getLogger(CustomerAction.class);
    private ICustomerService customerService;
    private List<Customer> customerLists;
    private CustomerQueryCondition queryCondition;
    private Customer customer;
    
    private NotifyMessageProducer advancedNotifyMessageProducer;
 
  	
    /**
     *  ①单条增加 返回主键
                    ② 批量增加 返回响应行数
                    ③ 删除 返回响应行数
                    ④修改 返回响应行数
                    ⑤查询单个实体
                    ⑥查询实体List
                    ⑦ 查询返回List<Map<String,Object>>
     */
    public String testOperDb(){
        try {
        	
            logger.info("++++ testOperDb go +++ ");
        	Long cnt=0L;
        	
        	//①单条增加 返回主键
        	/*Customer customer = new Customer();
            customer.setEmail("dgdshe@11111.com");
            customer.setName("测完么");
            //cnt =customerService.addOneUser(customer);
            System.out.println(customer.getCusId());//主键
*/        	//①单条增加 返回主键
            
        	
        	//② 批量增加 返回响应行数
            /*List<Customer> list = new ArrayList<Customer>();
            customer = new Customer();
            customer.setEmail("dgdsdd00he@11111.com");
            customer.setName("测dsd");
            list.add(customer);
            
            Customer customer02 = new Customer();
            customer02.setEmail("dgds1@21.com");
            customer02.setName("dfffdg2222112");
            list.add(customer02);
             //cnt =customerService.addBatchUser(list);
             System.out.println("++++ 响应 cnt:"+cnt);*/
            //② 批量增加 返回响应行数
            
        	
        	//③ 。1删除 返回响应行数
            //cnt=customerService.deleteUserById(5L);
            //System.out.println("++++ 响应 cnt:"+cnt);
            //③ .1删除 返回响应行数
        	
        	
        	//③ .2删除 返回响应行数
        	/*Map<String,Object> sourceMap = new HashMap<String,Object>();
        	sourceMap.put("minId", 101533645L);
        	sourceMap.put("maxId", 101523545L);*/
        	//customerService.deleteUser(sourceMap);
        	//③ .2删除 返回响应行数
        	
        	
        	//④修改 返回响应行数
        	/*customer = new Customer();
            customer.setEmail("111@11111.com");
            customer.setName("1111么111");
            customer.setId(101523547L);
            //cnt=customerService.updateCustomer(customer);
            System.out.println("++++ 响应 cnt:"+cnt);*/
            //④修改 返回响应行数
            
            //⑤查询单个实体
           /*Customer customer2df= customerService.queryUserByid(101523550L);
           System.out.println(customer2df.getEmail());
           System.out.println(customer2df.getName());*/
           //⑤查询单个实体
           
           
           //⑥查询实体List
           /*Map<String, Object> qryMap= new HashMap<String,Object>();
           String [] ids="101523547,101523548,101523550".split(",");
           qryMap.put("ids", ids);
           qryMap.put("email", "360_378401427@qq.com");
           qryMap.put("name", "13331937576");
           List<Customer>  custList=  customerService.queryUserByCondition(qryMap);
           for(Customer customer2:custList){
               System.out.println(customer2.getId()+","+customer2.getEmail()+","+customer2.getName());
           }*/
           //⑥查询实体List
           
           
           //⑦ 查询返回List<Map<String,Object>>
           Map<String, Object> qryMap2= new HashMap<String,Object>();
           String [] ids2="101523541,101523542,3,4".split(",");
           qryMap2.put("ids", ids2);
           qryMap2.put("email", "111@11111.com");
           qryMap2.put("name", "测完么2222278");
           List<Map<String, Object>>  custList2=  customerService.queryUserMapsByCondition(qryMap2);
           System.out.println("custList2.size():"+custList2.size());
           for(Map<String, Object> mapt:custList2){
               System.out.println(mapt.get("id")+","+mapt.get("email")+","+mapt.get("name"));
           }
           //⑦ 查询返回List<Map<String,Object>>
           
           logger.info("++++ testOperDb end ++++ ");
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  "testpage";
        
    }
    
    public String hghgd(){
    	System.out.println("hghgd hhh)))");
    	return  "testpage";
    }
    
    /**
     * 分页demo
     * @return String
     */
    public String testpage() {
        try {
            this.getPage().setPageSize(20);//设置每页为20，默认10
            customerLists = customerService.getCustomerName(getQueryCondition(),
                    this.getPage());
            
            //直接返回List<Map<String, Object>> 不用定义对象来接收
            List<Map<String, Object>> list= customerService.getCustomerListMap(getQueryCondition());
             System.out.println("++ list.size():"+list.size());
             for(Map<String, Object> map : list){
                 System.out.println("email:"+map.get("email"));
                 System.out.println("createdate"+map.get("createdate"));
                 System.out.println("customerkey:"+map.get("customerkey"));
             }
             
        } catch (Exception e) {
            logger.error("分页错误:",e);
            return ERROR;
        }
        return "testpage";
    }
    
    /**
     * 分页demo
     * @return String
     */
    public String testmem() {
        try {
            this.getPage().setPageSize(50);//设置每页为50，默认10
            //customerLists=(List<Customer>)memcachedClient.get("testmemm");
            
        } catch (Exception e) {
            return ERROR;
        }
        return "testpage";
    }
    
    
    /**
     * ajax demo
     * @return
     */
    public String tesajax() {
        try {
            customerLists = customerService.getCustomerName(getQueryCondition()
                    );
            //返回json 示例1
            this.setJson(new JsonEntity<List<Customer>>(true,"success",customerLists));
            //返回json 示例2
            JSONObject jsonObject = JSONObject.fromObject(customerLists);
            this.setJson(new JsonEntity<String>(true,"success",jsonObject.toString()));
        } catch (Exception e) {
            this.setJson(new JsonEntity<String>(false,"failed",null));
            logger.error("ajax demo error:",e);
        }
        
        return "json";
    }
    
    /**
     * 检查验证码是否正确
     * @return
     */
    public String checkRandomCode(){
        try {
            String email = customer.getEmail();
            if (email != null) {
                if (customerService.checkEmail(customer)) {
                    sendValidateMessage("true");
                    return null;
                }
            }
            sendValidateMessage("false");
        } catch (Exception e) {
            sendValidateMessage("false");
            return null;
        }
        return null;
    }
    /**
     * 检查email是否存在
     * @return
     */
    
    public String  checkEmail(){
        try {
            String email = customer.getEmail();
            if (email != null) {
                if (customerService.checkEmail(customer)) {
                    sendValidateMessage("true");
                    return null;
                }
            }
            sendValidateMessage("false");
        } catch (Exception e) {
            sendValidateMessage("false");
            logger.error("", e);
            return null;
        }
        return null;
    }
    
    public String testUpdate(){
        try {
            customerService.updateCustomer(new Customer());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "testpage";
    }
    
    public String testActiveMQ(){

		logger.info("+++ test send message start +++ ");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", "liuqinggang");
		map.put("email", "7305@qq.com");
	
		advancedNotifyMessageProducer.sendQueue(map);
		logger.info("+++ send message ok +++ ");
		Threads.sleep(1000);
		logger.info("+++ test message ok:{} +++ ");
		
	
    	 return "testpage";
    }

	public void sendValidateMessage(String message)  {
        try {
            this.getServletResponse().setCharacterEncoding("utf-8");
            this.getServletResponse().getWriter().write(message);
        } catch (Exception e) {
        }
    }
    
	
    public NotifyMessageProducer getAdvancedNotifyMessageProducer() {
		return advancedNotifyMessageProducer;
	}

	public void setAdvancedNotifyMessageProducer(
			NotifyMessageProducer advancedNotifyMessageProducer) {
		this.advancedNotifyMessageProducer = advancedNotifyMessageProducer;
	}

    public ICustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(ICustomerService customerService) {
        this.customerService = customerService;
    }

    public List<Customer> getCustomerLists() {
        return customerLists;
    }

    public void setCustomerLists(List<Customer> customerLists) {
        this.customerLists = customerLists;
    }
    public CustomerQueryCondition getQueryCondition() {
        if(queryCondition==null){
            queryCondition= new CustomerQueryCondition();
        }
        return queryCondition;
    }
    public void setQueryCondition(CustomerQueryCondition queryCondition) {
        this.queryCondition = queryCondition;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
   

}
