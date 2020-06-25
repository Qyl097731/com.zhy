package Servlet;

import bean.Goods;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dao.GoodsDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * projectName:  com.zhy
 * packageName: ${PACKAGE_NAME}
 * date: 2020-06-24 01:33
 * copyright(c) 2020 南晓18卓工 邱依良
 */
@WebServlet(name = "GoodsServlet", urlPatterns = "/GoodsServlet")
public class GoodsServlet extends HttpServlet {
    GoodsDao goodsDao = new GoodsDao();
    Goods goods = null;
    Integer id = null;
    String op;
    Integer pageNum;
    PrintWriter out;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    //    search search-id insert-b insert
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        op = request.getParameter("op");
        out = response.getWriter();
        ArrayList<Goods> goodsList = new ArrayList<Goods>();
        if (request.getParameter("pageNum") == null) {
            pageNum = 0;
        } else {
            pageNum = Integer.parseInt(request.getParameter("pageNum"));
        }
        PageHelper.startPage(pageNum, 8);
        if (op.equals("search-")) {
            goodsList = goodsDao.queryAllGoods();
            PageInfo<Goods> info = new PageInfo<Goods>(goodsList, 5);
            int[] nums = info.getNavigatepageNums();
            request.setAttribute("info", info);
            request.setAttribute("nums", nums);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else if (op.equals("search-id")) {
            id = Integer.parseInt(request.getParameter("id"));
            goods = goodsDao.queryGoodsById(id);
            System.out.println(goods.getGoodsName() == null);
            if (goods.getGoodsName() == null) {
            } else {
                goodsList.add(goods);
                PageInfo<Goods> info = new PageInfo<Goods>(goodsList, 5);
                int[] nums = info.getNavigatepageNums();
                request.setAttribute("info", info);
                request.setAttribute("nums", nums);
                request.setAttribute("goods", goods);
            }
            request.getRequestDispatcher("seller.jsp").forward(request, response);
        } else if (op.equals("delete")) {
            id = Integer.parseInt(request.getParameter("id"));
            goodsDao.deleteGoodsById(id);
            goodsList = goodsDao.queryAllGoods();
            PageInfo<Goods> info = new PageInfo<Goods>(goodsList, 5);
            int[] nums = info.getNavigatepageNums();
            request.setAttribute("info", info);
            request.setAttribute("nums", nums);
            request.getRequestDispatcher("seller.jsp").forward(request, response);
        } else if (op.equals("insert-b")) {
            goodsDao.batchInsertGoods();
            goodsList = goodsDao.queryAllGoods();
            PageInfo<Goods> info = new PageInfo<Goods>(goodsList, 5);
            int[] nums = info.getNavigatepageNums();
            request.setAttribute("info", info);
            request.setAttribute("nums", nums);
            request.getRequestDispatcher("seller.jsp").forward(request, response);
        } else {
            Integer goodsNum = Integer.parseInt(request.getParameter("goodsNum"));
            Double goodsPrice = Double.parseDouble(request.getParameter("goodsPrice"));
            String goodsName = request.getParameter("goodsName");
            goodsDao.batchInsertGoods();
            goodsList = goodsDao.queryAllGoods();
            PageInfo<Goods> info = new PageInfo<Goods>(goodsList, 5);
            int[] nums = info.getNavigatepageNums();
            request.setAttribute("info", info);
            request.setAttribute("nums", nums);
            request.getRequestDispatcher("seller.jsp").forward(request, response);
        }
        out.flush();
        out.close();
    }
}
