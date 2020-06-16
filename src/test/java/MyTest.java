import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.dao.UserDao;
import com.qf.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class MyTest {

    @Test
    public void selectAll() throws Exception{
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = factory.openSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        List<User> users = mapper.selectAll();
        System.out.println(users);
    }

    @Test
    public void slectByNameAndPassword() throws Exception{
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = factory.openSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User user = mapper.slectByNameAndPassword("admin", "1234");
        System.out.println(user);
    }

    @Test
    public void updateById() throws Exception{
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = factory.openSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        mapper.updateById(new User(1,"admin","1234"));
        sqlSession.commit();
    }

    //此为根据多个id修改多个选项，综合考虑来看，此方法并不能实现，故弃之。
    /*@Test
    public void updateManyId() throws Exception{
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = factory.openSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        List<User> list = new ArrayList<>();
        list.add(new User(1,"admin","123"));
        list.add(new User(2,"zhangsan","123"));
        list.add(new User(3,"lisi","123"));
        mapper.updateManyId(list);
        sqlSession.commit();
    }*/

    @Test
    public void selectByIds() throws Exception{
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = factory.openSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        List<User> users = mapper.selectByIds(list);
        System.out.println(users);
        sqlSession.commit();
    }

    //分页之PageHelper
    //先调用startPage方法对页数进行设置，再查所有，最后把查所有的集合通过PageInto类打印输出。
    @Test
    public void selectAllByPage() throws Exception{
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = factory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage(1, 2);
        List<User> users = userDao.selectAll();
        PageInfo pageInfo = new PageInfo(users);
        System.out.println(pageInfo);
    }

    //注解开发之根据id查询
    @Test
    public void selectById() throws Exception{
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = factory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = userDao.selectById(1);
        System.out.println(user);
    }

    //注解开发之插入
    @Test
    public void insertUser() throws Exception{
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = factory.openSession();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = new User();
        user.setUsername("wangliu");
        user.setPassword("123");
        userDao.insertUser(user);
        sqlSession.commit();
    }

    @Test
    public void a(){
        Integer a = 10,b = 10;
        System.out.println(a == b);
    }
}
