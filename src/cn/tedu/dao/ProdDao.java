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
}
