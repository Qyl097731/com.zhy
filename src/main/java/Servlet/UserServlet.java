package Servlet;

import bean.Goods;
import bean.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dao.GoodsDao;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * projectName:  com.zhy
 * packageName: ${PACKAGE_NAME}
 * date: 2020-06-24 17:39
 * copyright(c) 2020 南晓18卓工 邱依良
 */
@WebServlet(name = "UserServlet", urlPatterns = "/UserServlet")
public class UserServlet extends HttpServlet {
    UserDao userDao = new UserDao();
    GoodsDao goodsDao = new GoodsDao();
    ArrayList<Goods>goodsList = null;
    User user;
    String op;
    String authority, username, password;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        authority = request.getParameter("autSelect");
        username = request.getParameter("username");
        password = request.getParameter("password");
        op = request.getParameter("op");
        user = userDao.queryUserByUsername(username, authority);
        if (op.equals("0")) {
            if (user.getUsername()!=null && user.getUsername().equals(username) && user.getAuthority().equals(authority) && user.getPassword().equals(password)) {
                if (authority.equals("0")) {
                    request.getRequestDispatcher("seller.jsp").forward(request, response);
                } else{
                    PageHelper.startPage(1, 8);
                    System.out.println(PageHelper.getLocalPage());
                    goodsList = goodsDao.queryAllGoods();
                    PageInfo<Goods> info = new PageInfo<>(goodsList, 5);
                    int[] nums = info.getNavigatepageNums();
                    request.setAttribute("info",info);
                    request.setAttribute("nums",nums);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            }else {
                request.getRequestDispatcher("userLogin.jsp").forward(request, response);
            }
        }else {
            if(user.getUsername()!=null){
                request.getRequestDispatcher("userLogin.jsp").forward(request, response);
            }else {
                userDao.insertUser(username,password,authority);
                goodsList = goodsDao.queryAllGoods();
                PageInfo<Goods> info = new PageInfo<Goods>(goodsList, 5);
                int[] nums = info.getNavigatepageNums();
                request.setAttribute("info",info);
                request.setAttribute("nums",nums);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
    }
}
