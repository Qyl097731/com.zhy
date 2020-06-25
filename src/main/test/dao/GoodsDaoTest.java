package dao;

import bean.Goods;
import dao.GoodsDao;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.ArrayList;

/**
 * GoodsDao Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>6月 24, 2020</pre>
 */
public class GoodsDaoTest {
    GoodsDao goodsDao = new GoodsDao();

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getConnection()
     */
    @Test
    public void testGetConnection() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: queryAllGoods()
     */
    @Test
    public void testQueryAllGoods() throws Exception {
//TODO: Test goes here...
        ArrayList<Goods> goodsList = goodsDao.queryAllGoods();
        for (Goods goods : goodsList
        ) {
            System.out.println(goods.toString());
        }
    }

    /**
     * Method: queryGoodsById(Integer id)
     */
    @Test
    public void testQueryGoodsById() throws Exception {
//TODO: Test goes here...
        Goods goods=goodsDao.queryGoodsById(17);
        System.out.println(goods.toString());
    }

    /**
     * Method: insertGoods(String goodsName, Double goodsPrice, Integer goodsNum)
     */
    @Test
    public void testInsertGoods() throws Exception {
//TODO: Test goes here...
        goodsDao.insertGoods("卫龙",2.9,1);
    }

    /**
     * Method: batchInsertGoods()
     */
    @Test
    public void testBatchInsertGoods() throws Exception {
//TODO: Test goes here...
        goodsDao.batchInsertGoods();
    }

    /**
     * Method: updateGoodsPriceById(Integer id, Double goodsPrice)
     */
    @Test
    public void testUpdateGoodsPriceById() throws Exception {
//TODO: Test goes here...
        goodsDao.updateGoodsPriceById(17,3.0);
    }

    /**
     * Method: deleteGoodsById(Integer id)
     */
    @Test
    public void testDeleteGoodsById() throws Exception {
//TODO: Test goes here...
        goodsDao.deleteGoodsById(22);
    }

    /**
     * Method: releaseDB(ResultSet rs, PreparedStatement pst, Connection conn)
     */
    @Test
    public void testReleaseDB() throws Exception {
//TODO: Test goes here... 
    }


} 
