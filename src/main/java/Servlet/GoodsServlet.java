package Servlet;

import bean.Goods;
import dao.GoodsDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name = "GoodsServlet", urlPatterns = "/GoodsServlet")
public class GoodsServlet extends HttpServlet {
    GoodsDao goodsDao = new GoodsDao();
    Goods goods = null;
    Integer id = null;
    String op;
    Integer pageNum;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        op = request.getParameter("op");
        String authority = session.getAttribute("authority").toString();
        ArrayList<Goods> goodsList = new ArrayList<>();
        if (op.equals("search")) {
            goodsList = goodsDao.queryAllGoods();
            request.setAttribute("goodsList", goodsList);
            if (authority.equals("1")){
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }else {
                request.getRequestDispatcher("seller.jsp").forward(request, response);
            }

        }else if (op.equals("search-id")) {
            id = Integer.parseInt(request.getParameter("id"));
            goods = goodsDao.queryGoodsById(id);
            if (goods.getGoodsName() == null) {
            } else {
                goodsList.add(goods);
                request.setAttribute("goodsList", goodsList);
            }
            request.getRequestDispatcher("seller.jsp").forward(request, response);
        } else if (op.equals("delete")) {
            id = Integer.parseInt(request.getParameter("id"));
            goodsDao.deleteGoodsById(id);
            goodsList = goodsDao.queryAllGoods();
            request.setAttribute("goodsList", goodsList);
            request.getRequestDispatcher("seller.jsp").forward(request, response);
        } else if (op.equals("insert-b")) {
            goodsDao.batchInsertGoods();
            goodsList = goodsDao.queryAllGoods();
            request.setAttribute("goodsList", goodsList);
            request.getRequestDispatcher("seller.jsp").forward(request, response);
        } else {
            Integer goodsNum = Integer.parseInt(request.getParameter("goodsNum"));
            Double goodsPrice = Double.parseDouble(request.getParameter("goodsPrice"));
            String goodsName = request.getParameter("goodsName");
            goodsDao.insertGoods(goodsName,goodsPrice,goodsNum);
            goodsList = goodsDao.queryAllGoods();
            request.setAttribute("goodsList", goodsList);
            request.getRequestDispatcher("seller.jsp").forward(request, response);
        }
    }
}
