package com.fairyhawk.action.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fairyhawk.common.action.CommonAction;
import com.fairyhawk.common.entity.JsonEntity;
import com.fairyhawk.entity.user.Function;
import com.fairyhawk.entity.user.KeyValueDTO;
import com.fairyhawk.entity.user.QueryFunctionCondition;
import com.fairyhawk.service.user.FunctionService;

/**
 * @ClassName  FunctionAction
 * @package com.fairyhawk.action.user
 * @description 权限管理
 * @author  liuqinggang
 * @Create Date: 2013-3-3 下午11:12:21
 * 
 */
public class FunctionAction extends CommonAction {

    /**
     * 
     */
    private static final long serialVersionUID = 1251015098824187276L;

    private static Logger logger = LoggerFactory.getLogger(FunctionAction.class);
  
    private FunctionService functionService;
    private List<Function> functionList = new ArrayList<Function>();
    private QueryFunctionCondition queryFunctionCondition = new QueryFunctionCondition();
    private Function function;
    private List<Integer> ids;
    private Function parentFunction;

    public String toAddFunction() {
        //一级组
        functionList = functionService.getUsableFunctionList();
        return "toAddFunction";
    }

    /**
     * 获取子权限列表
     * @return
     * @throws Exception
     * @author fairyhawk
     */
    public String getChildFunctionById() {
        try{
            functionList =functionService.getChildFunctionById(function.getFunctionId());
            List<KeyValueDTO> myList = new ArrayList<KeyValueDTO>();
            
            KeyValueDTO keyvalue = null;

            for(int i=0; i < functionList.size(); i++) {
                keyvalue = new KeyValueDTO();
                Function tempFunction = functionList.get(i); 
                keyvalue.setKey(tempFunction.getFunctionId());
                keyvalue.setValue(tempFunction.getFunctionName());
                myList.add(keyvalue);
            }
            
            this.setJson(new JsonEntity<List<KeyValueDTO>>(true, "", myList));
            
        }catch(Exception e){
            logger.error("FunctionAction.getChildFunctionById", e);
            return ERROR;
        }
        return "json";
    }
    
    /**
     * 添加权限
     * @return
     */
    public String addFunction() {
        try{
            function.setStatus(1);
            function.setCreateTime(new Date());
            function.setFunctionApplyScopeId(2);
            functionService.addFunction(function);
        }catch(Exception e){
            logger.error("FunctionAction.addFunction", e);
            return ERROR;
        }
        return "reshow";
    }
    
    /**
     * 权限管理，显示权限树
     * @return String
     */
    public String showFunctionList() {
        try{
            logger.info(" +++ showFunctionList functionList opt");
            functionList = functionService.getUsableFunctionList();
            if(functionList!=null && functionList.size()>0){
                logger.info(" +++ showFunctionList functionList size:"+functionList.size());
                /*for(Function function:functionList){
                  function.setSubFunctionList(functionService.getFunctionListByParentId(function.getFunctionId()));
                }*/
            }
        }catch(Exception e){
            logger.error("FunctionAction.showFunctionList error", e);
            return ERROR;
        }
        return "showFunctionList";
    }
    
    /**
     * 初始化修改权限
     * @return
     */
    public String toUpdateFunction() {
        try{
            function = functionService.getFunctionById(function.getFunctionId());
            functionList = functionService.getUsableFunctionList();
        }catch(Exception e){
            logger.error("FunctionAction.toUpdateFunction", e);
            return ERROR;
        }
        return "toUpdateFunction";
    }
    
    /**
     * 修改权限
     * @return
     */
    public String updateFunction() {
        try{
            functionService.updateFunction(function);
        }catch(Exception e){
            logger.error("FunctionAction.updateFunction", e);
            return ERROR;
        }
        return "reshow";
    }
    
    /**
     * 删除权限及子权限
     * @return
     */
    public String delFunctions() {
        try {
            functionService.delFunctions(ids);
        } catch (Exception e) {
            logger.error("FunctionAction.delFunctions", e);
        }
        return "reshow";
    }

    

    public List<Function> getFunctionList() {
        return functionList;
    }

    public void setFunctionList(List<Function> functionList) {
        this.functionList = functionList;
    }

    public QueryFunctionCondition getQueryFunctionCondition() {
        if(queryFunctionCondition == null) {
            queryFunctionCondition = new QueryFunctionCondition();
        }
        return queryFunctionCondition;
    }

    public void setQueryFunctionCondition(
            QueryFunctionCondition queryFunctionCondition) {
        this.queryFunctionCondition = queryFunctionCondition;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public Function getParentFunction() {
        return parentFunction;
    }

    public void setParentFunction(Function parentFunction) {
        this.parentFunction = parentFunction;
    }

    public FunctionService getFunctionService() {
        return functionService;
    }

    public void setFunctionService(FunctionService functionService) {
        this.functionService = functionService;
    }

}
