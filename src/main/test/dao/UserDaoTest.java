package test.dao; 

import bean.User;
import dao.UserDao;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

/** 
* UserDao Tester. 
* 
* @author <Authors name> 
* @since <pre>6月 24, 2020</pre> 
* @version 1.0 
*/ 
public class UserDaoTest { 
    UserDao userDao = new UserDao();
@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getConnection() 
* 
*/ 
@Test
public void testGetConnection() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: queryUserByUsername(String username, String authority) 
* 
*/ 
@Test
public void testQueryUserByUsername() throws Exception { 
//TODO: Test goes here...
    User user = userDao.queryUserByUsername("11111","1");
    System.out.println(user==null);
} 

/** 
* 
* Method: releaseDB(ResultSet rs, PreparedStatement pst, Connection conn) 
* 
*/ 
@Test
public void testReleaseDB() throws Exception { 
//TODO: Test goes here... 
} 


} 
