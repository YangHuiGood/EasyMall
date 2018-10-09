package cn.tedu.service;

import java.util.List;

import cn.tedu.dao.ProdDao;
import cn.tedu.domain.Prod;
import cn.tedu.domain.ProdCategory;
import cn.tedu.exception.MsgException;
import cn.tedu.factory.BaseFactory;
import cn.tedu.util.TransactionManager;

public class ProdServiceImpl implements ProdService {
	private ProdDao dao=BaseFactory.getFactory().getInstance(ProdDao.class);
	@Override
	public boolean addProd(Prod prod) {
		boolean flag = false;
		try {
			//开启事务
			TransactionManager.startTran();
			// 查询数据库，确认是否已经存在该商品种类
			int cid = -1;
			try {
				cid = dao.getCidByCname(prod.getCname());
			} catch (MsgException e) {
				e.printStackTrace();
				return false;
			}
			// 如果没有这个商品种类
			if (cid == -1) {
				// 创建ProdCategory对象，封装要添加的数据
				ProdCategory pc = new ProdCategory();
				pc.setCname(prod.getCname());
				// 1) 先添加这个商品种类
				boolean flag1 = dao.insertPC(pc);
				if (flag1 == false) {
					return false;
				}
				// 2) 再次查询cid，获取数据库生成的最新的cid
				try {
					cid = dao.getCidByCname(prod.getCname());
				} catch (MsgException e) {
					e.printStackTrace();
					return false;
				}
			}
			// 将查询到的cid添加到prod对象中
			prod.setCid(cid);
			// 添加商品
			flag = dao.insertProd(prod);
			//提交事务
			TransactionManager.commit();
		} catch (Exception e) {
			e.printStackTrace();
			//回滚事务
			TransactionManager.rollback();
		}finally{
			//关闭连接对象
			TransactionManager.colseConn();
		}
		return flag;
	}
	@Override
	public List<Prod> listAllProd() {
		return dao.listAllProd();
	}
	@Override
	public boolean prodUpdatePnum(int pid, int pnum) {
		return dao.updatePnumById(pid,pnum);
	}
	@Override
	public boolean prodDel(int pid) {
		//需求：如果商品是唯一的一个商品则在删除商品的同时也要删除该商品的商品种类，反之，则不需要删除商品种类
		boolean flag = false;
		try {
			//开启事务
			TransactionManager.startTran();
			//根据pid查询该商品的种类cid
			int cid = dao.getCidByPid(pid); 
			if(cid == 0){
				//查询失败
				return false;
			}
			// 根据商品种类的cid,得到该商品种类有多少种商品
			int countProd = dao.getCountProdByCid(cid);
			if(countProd > 1){
				// 如果该商品种类包含多于1种商品，则仅删除该商品
				flag = dao.delProdById(pid);
			}else if(countProd == 1){
				// 如果该商品种类只有1种商品了，则删除商品和商品种类
				flag = dao.delProdById(pid);
				flag = dao.delProdCategoryByCid(cid) && flag;
			}
			//提交事务
			TransactionManager.commit();
		} catch (Exception e) {
			e.printStackTrace();
			//回滚事务
			TransactionManager.rollback();
		}finally{
			TransactionManager.colseConn();
		}
		
		return flag;
	}

}
