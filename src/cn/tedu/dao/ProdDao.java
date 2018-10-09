package cn.tedu.dao;

import java.util.List;

import cn.tedu.domain.Prod;
import cn.tedu.domain.ProdCategory;
import cn.tedu.exception.MsgException;

public interface ProdDao {

	/**
	 * ������Ʒ�������Ʋ�ѯ��Ʒ����id�ķ���
	 * @param cname ��Ʒ��������
	 * @return ��Ʒ�����id �� -1
	 * @throws ��װ���Զ�����Ϣ���쳣����
	 */
	int getCidByCname(String cname) throws MsgException;
	
	/**
	 * �����Ʒ����ķ���
	 * @param pc ��װ����Ʒ������Ϣ��JavaBean
	 * @return true-��ӳɹ� false-���ʧ��
	 */
	boolean insertPC(ProdCategory pc);
	
	/**
	 * �����Ʒ��Ϣ�ķ���
	 * @param prod ��װ����Ʒ��Ϣ��JavaBean
	 * @return true-��ӳɹ� false-���ʧ��
	 */
	boolean insertProd(Prod prod);

	/**
	 * ��ѯ������Ʒ��Ϣ�ķ���
	 * @return
	 */
	List<Prod> listAllProd();

	/**
	 * ͨ����Ʒid������Ʒ�������ֵ
	 * @param pid ��Ʒid
	 * @param pnum ��Ʒ�������ֵ
	 * @return true-���³ɹ�  false-����ʧ��
	 */
	boolean updatePnumById(int pid, int pnum);

	/**
	 * ������Ʒ��id��ѯ����Ʒ��cid
	 * @param pid ��Ʒid
	 * @return ��Ʒ��cid
	 */
	int getCidByPid(int pid);

	/**
	 * ������Ʒ�����cid,�õ�����Ʒ�����ж�������Ʒ
	 * @param cid ��Ʒ������id
	 * @return ������Ʒ�ĸ���
	 */
	int getCountProdByCid(int cid);

	/**
	 * ͨ����Ʒidɾ����Ʒ��Ϣ
	 * @param pid ��Ʒid
	 * @return true -ɾ���ɹ�  false-ɾ��ʧ��
	 */
	boolean delProdById(int pid);

	/**
	 * ͨ��cidɾ����Ʒ����
	 * @param cid ��Ʒ����id
	 * @return true-ɾ���ɹ� false-ɾ��ʧ��
	 */
	boolean delProdCategoryByCid(int cid);
}
