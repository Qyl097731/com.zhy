package dao;

import bean.Goods;
import bean.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

/**
 * projectName:  com.zhy
 * packageName: dao
 * date: 2020-06-24 19:29
 * copyright(c) 2020 南晓18卓工 邱依良
 */
public class UserDao {
    private Connection conn;
    private PreparedStatement pstm;
    private ResultSet rs;
    private String sql;

    // 连接数据库
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动错误！");
        }
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/shop?useUnicode=true&characterEncoding=utf8&useSSL=false", "root",
                    "");

        } catch (SQLException e) {
            System.out.println("数据库连接字符串错误！");
        }
        return conn;
    }


    public User queryUserByUsername(String username, String authority) {
        conn = getConnection();
        User user = new User();
        try {
            sql = "select * from tb_user where tb_user.username=" + username + " and authority=" + authority;
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setAuthority(rs.getString("authority"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            releaseDB(rs, pstm, conn);
        }
        return user;
    }

    public void insertUser(String username, String password, String authority) {
        conn = getConnection();
        User user = new User();
        try {
            sql = "insert into tb_user(username,password,authority) values (?,?,?)";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, username);
            pstm.setString(2, password);
            pstm.setString(3, authority);
            pstm.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            releaseDB(rs, pstm, conn);
        }
    }

    public void releaseDB(ResultSet rs, PreparedStatement pst, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}