package com.qf.dao;

import com.qf.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao {
    //查所有
    List<User> selectAll() throws Exception;
    //根据姓名密码查询
    User slectByNameAndPassword(@Param("username") String username , @Param("password") String password) throws Exception;
    //根据id修改
    void updateById(User user) throws Exception;
    //多个id修改多个用户-----暂不能实现
    void updateManyId(List<User> list) throws Exception;
    //根据多个id查询多个值
    List<User> selectByIds(List<Integer> list) throws Exception;
    //注解开发之根据id查询
    @Select("select * from t_user where id = #{id}")
    User selectById(int id);
    //注解开发之插入
    @Insert("insert into t_user (username,password) values (#{username},#{password})")
    void insertUser(User user);
}
