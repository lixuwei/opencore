package com.fairyhawk.service.impl.user;

import java.util.List;

import com.fairyhawk.dao.user.FunctionDao;
import com.fairyhawk.entity.user.Function;
import com.fairyhawk.entity.user.QueryFunctionCondition;
import com.fairyhawk.service.user.FunctionService;

/**
 * @ClassName FunctionServiceImpl
 * @package com.fairyhawk.service.impl.user
 * @description
 * @author liuqinggang
 * @Create Date: 2013-3-3 上午12:11:13
 * 
 */
public class FunctionServiceImpl implements FunctionService {

    private FunctionDao functionDao;
    
    public  List<Function> getFunctionListByParentId(int parentId){
        return functionDao.getFunctionListByParentId(parentId);
    }
    @Override
    public java.lang.Integer addFunction(Function function) {
        return functionDao.addFunction(function);
    }

    @Override
    public void delFunctionById(int functionId) {
        functionDao.delFunctionById(functionId);
    }

    @Override
    public void updateFunction(Function function) {
        functionDao.updateFunction(function);
    }

    @Override
    public Function getFunctionById(int functionId) {
        return functionDao.getFunctionById(functionId);
    }

    @Override
    public List<Function> getFunctionList(QueryFunctionCondition queryFunctionCondition) {
        return functionDao.getFunctionList(queryFunctionCondition);
    }

    @Override
    public List<Function> getFirstLevelFunctionList() {
        QueryFunctionCondition queryFunctionCondition = new QueryFunctionCondition();
        queryFunctionCondition.setParentFunctionId(1);
        return getFunctionList(queryFunctionCondition);
    }

    @Override
    public List<Function> getUsableFunctionList() {
        QueryFunctionCondition queryFunctionCondition = new QueryFunctionCondition();
        queryFunctionCondition.setStatus(1);
        return getFunctionList(queryFunctionCondition);
    }

    @Override
    public List<Function> getFirFunction(QueryFunctionCondition queryFunctionCondition) {
        return functionDao.getFirFunction(queryFunctionCondition);
    }

    @Override
    public List<Function> getChildFunctionById(int functionId) {
        return functionDao.getChildFunctionById(functionId);
    }

    //根据多个ParentId查询
    public  List<Function> getFunctionListByParentIdSet(List<Integer> parentList){
        return functionDao.getFunctionListByParentIdSet(parentList);
    }
    
    @Override
    public void delFunctions(List<Integer> list) {
        if (list != null) {
            functionDao.delFunctions(list);
//            for (int i = 0; i < list.size(); i++) {
//                delFunction(list.get(i));
//            }
        }
    }

    public void delFunction(int id) {
        functionDao.delFunctionById(id);
        List<Function> list = getChildFunctionById(id);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                delFunction(list.get(i).getFunctionId());
            }
        }
        delFunctionById(id);
    }

    public FunctionDao getFunctionDao() {
        return functionDao;
    }

    public void setFunctionDao(FunctionDao functionDao) {
        this.functionDao = functionDao;
    }

}
