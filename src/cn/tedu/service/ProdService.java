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
}
