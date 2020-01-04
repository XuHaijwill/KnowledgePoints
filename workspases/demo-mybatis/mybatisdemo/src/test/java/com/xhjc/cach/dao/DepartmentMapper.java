package com.xhjc.cach.dao;

import com.xhjc.bean.Department;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2019/12/26 20:09
 **/
public interface DepartmentMapper {

    public Department getDeptById(Integer id );
    public Department getDepByIdPlus(Integer id );
    public Department getDeptByIdStep(Integer id );
}
