package cn.tedu.service;

import java.util.List;

import cn.tedu.domain.Prod;

public interface ProdService {
	/**
	 * 添加商品的方法
	 * @param prod 封装了商品信息的JavaBean
	 * @return true-添加成功 false-添加失败
	 */
	boolean addProd(Prod prod);

	/**
	 * 查询所有商品数据的方法
	 * @return 返回商品对象的list集合
	 */
	List<Prod> listAllProd();

	/**
	 * 更新商品库存量的方法
	 * @param pid 更新商品的id
	 * @param pnum 更新商品的库存量的值
	 * @return true- 更新成功 false- 更新失败
	 */
	boolean prodUpdatePnum(int pid, int pnum);

	/**
	 * 通过商品id删除删除商品信息的方法
	 * @param pid 商品id
	 * @return true-删除成功 false-删除失败
	 */
	boolean prodDel(int pid);
}
