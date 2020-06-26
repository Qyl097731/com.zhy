package Servlet;

import bean.Goods;
import bean.User;
import dao.GoodsDao;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

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
        HttpSession session = request.getSession();
        authority = request.getParameter("autSelect");
        username = request.getParameter("username");
        password = request.getParameter("password");
        op = request.getParameter("op");
        user = userDao.queryUserByUsername(username, authority);
        if (op.equals("0")) {
            if (user.getUsername()!=null && user.getUsername().equals(username) && user.getAuthority().equals(authority) && user.getPassword().equals(password)) {
                session.setAttribute("username",username);
                session.setAttribute("authority",authority);
                if (authority.equals("0")) {
                    request.getRequestDispatcher("seller.jsp").forward(request, response);
                } else{
                    goodsList = goodsDao.queryAllGoods();
                    request.setAttribute("goodsList",goodsList);
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
                session.setAttribute("username",username);
                session.setAttribute("authority",authority);
                request.setAttribute("goodsList",goodsList);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
    }
}
