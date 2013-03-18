package com.fairyhawk.dao.impl.user;

import java.util.ArrayList;
import java.util.List;

import com.fairyhawk.common.dao.GenericDaoImpl;
import com.fairyhawk.dao.user.FunctionDao;
import com.fairyhawk.entity.user.Function;
import com.fairyhawk.entity.user.QueryFunctionCondition;

/**
 * @ClassName  FunctionDaoImpl
 * @package com.fairyhawk.dao.impl.user
 * @description
 * @author  liuqinggang
 * @Create Date: 2013-3-3 上午12:14:10
 * 
 */
public class FunctionDaoImpl extends GenericDaoImpl implements FunctionDao {

    @Override
    public List<Function> getFunctionListByRoleId(int roleId) {
        List<Function> list = this.getMyBatisDao().selectList("FunctionMapper.getFunctionListByRoleId", roleId);
        if(list!=null && list.size()>0){
            return list;
        }
        return null;
    }

    private FunctionDao functionDao;

    
    public java.lang.Integer addFunction(Function function) {
        return this.getMyBatisDao().insert("FunctionMapper.createFunction", function);
    }

    public void delFunctionById(int functionId) {
        this.getMyBatisDao().delete("FunctionMapper.deleteFunctionById", functionId);
    }

    public void updateFunction(Function function) {
        this.getMyBatisDao().update("FunctionMapper.updateFunction", function);
    }

    public Function getFunctionById(int functionId) {
        return this.getMyBatisDao().selectOne("FunctionMapper.getFunctionById", functionId);
    }

    public List<Function> getFunctionList(QueryFunctionCondition queryFunctionCondition) {
        return this.getMyBatisDao()
                .selectList("FunctionMapper.getFunctionList", queryFunctionCondition);
    }

    public List<Function> getFirstLevelFunctionList() {
        QueryFunctionCondition queryFunctionCondition = new QueryFunctionCondition();
        queryFunctionCondition.setParentFunctionId(1);
        return getFunctionList(queryFunctionCondition);
    }

    public List<Function> getUsableFunctionList() {
        QueryFunctionCondition queryFunctionCondition = new QueryFunctionCondition();
        queryFunctionCondition.setStatus(1);
        return getFunctionList(queryFunctionCondition);
    }

    public List<Function> getFirFunction(QueryFunctionCondition queryFunctionCondition) {
        return this.getMyBatisDao().selectList("FunctionMapper.getFirFunction", queryFunctionCondition);
    }

    public List<Function> getChildFunctionById(int functionId) {
        return this.getMyBatisDao().selectList("FunctionMapper.getChildFunctionById", functionId);
    }
    /**
     * 根据多个parentId 查询
     * @param parentList
     * @return
     */
    public  List<Function>  getFunctionListByParentIdSet(List<Integer> parentList){
        return this.getMyBatisDao().selectList("FunctionMapper.getFunctionListByParentIdSet", parentList);
    }

    @Override
    public void delFunctions(List<Integer> list) {
        if (list == null || list.size()==0) {
            return ;
        }
        for (Integer id:list) {
            
            //删除role角色中的对应关系
            this.getMyBatisDao().delete("RoleFunctionMapper.deleteRoleFunctionByFunctionId", id);
            //删除function
            delFunctionById(id);
            //循环查询节点
            List<Integer> childListParam= new ArrayList<Integer>();
            childListParam.add(id);
            List<Function> functionlist=this.getMyBatisDao().selectList("FunctionMapper.getFunctionListByParentIdSet",childListParam);
            while(functionlist!=null && functionlist.size()>0){
                System.out.println("+++ functionlist.size():"+functionlist.size());
                //批量删除role角色中的对应关系
                this.getMyBatisDao().delete("RoleFunctionMapper.deleteRoleFunctionByFunctionIdBatch", functionlist);
                //批量删除function
                this.getMyBatisDao().delete("FunctionMapper.deleteFunctionByIdBatch", functionlist);
                //查询参数
                childListParam= new ArrayList<Integer>();
                for(Function function: functionlist){
                    System.out.println("function.getFunctionId():"+function.getFunctionId());
                    childListParam.add(function.getFunctionId());
                }
                //继续查询，为空时代表删除完成
                functionlist=this.getMyBatisDao().selectList("FunctionMapper.getFunctionListByParentIdSet", childListParam);
            }
        }
        
    }

    @Override
    public List<Function> getFunctionListByParentId(int parentId) {
        return this.getMyBatisDao().selectList("FunctionMapper.getFunctionListByParentId", parentId);
    }
    public FunctionDao getFunctionDao() {
        return functionDao;
    }

    public void setFunctionDao(FunctionDao functionDao) {
        this.functionDao = functionDao;
    }

    


}
