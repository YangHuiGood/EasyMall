package cn.tedu.dao;

import java.util.List;

import cn.tedu.domain.Prod;
import cn.tedu.domain.ProdCategory;
import cn.tedu.exception.MsgException;

public interface ProdDao {

	/**
	 * 根据商品种类名称查询商品种类id的方法
	 * @param cname 商品种类名称
	 * @return 商品种类的id 或 -1
	 * @throws 封装了自定义消息的异常对象
	 */
	int getCidByCname(String cname) throws MsgException;
	
	/**
	 * 添加商品种类的方法
	 * @param pc 封装了商品种类信息的JavaBean
	 * @return true-添加成功 false-添加失败
	 */
	boolean insertPC(ProdCategory pc);
	
	/**
	 * 添加商品信息的方法
	 * @param prod 封装了商品信息的JavaBean
	 * @return true-添加成功 false-添加失败
	 */
	boolean insertProd(Prod prod);

	/**
	 * 查询所有商品信息的方法
	 * @return
	 */
	List<Prod> listAllProd();

	/**
	 * 通过商品id更新商品库存量的值
	 * @param pid 商品id
	 * @param pnum 商品库存量的值
	 * @return true-更新成功  false-更新失败
	 */
	boolean updatePnumById(int pid, int pnum);

	/**
	 * 根据商品的id查询该商品的cid
	 * @param pid 商品id
	 * @return 商品的cid
	 */
	int getCidByPid(int pid);

	/**
	 * 根据商品种类的cid,得到该商品种类有多少种商品
	 * @param cid 商品的种类id
	 * @return 返回商品的个数
	 */
	int getCountProdByCid(int cid);

	/**
	 * 通过商品id删除商品信息
	 * @param pid 商品id
	 * @return true -删除成功  false-删除失败
	 */
	boolean delProdById(int pid);

	/**
	 * 通过cid删除商品种类
	 * @param cid 商品种类id
	 * @return true-删除成功 false-删除失败
	 */
	boolean delProdCategoryByCid(int cid);
}
