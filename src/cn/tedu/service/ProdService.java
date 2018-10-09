package cn.tedu.service;

import java.util.List;

import cn.tedu.domain.Prod;

public interface ProdService {
	/**
	 * �����Ʒ�ķ���
	 * @param prod ��װ����Ʒ��Ϣ��JavaBean
	 * @return true-��ӳɹ� false-���ʧ��
	 */
	boolean addProd(Prod prod);

	/**
	 * ��ѯ������Ʒ���ݵķ���
	 * @return ������Ʒ�����list����
	 */
	List<Prod> listAllProd();

	/**
	 * ������Ʒ������ķ���
	 * @param pid ������Ʒ��id
	 * @param pnum ������Ʒ�Ŀ������ֵ
	 * @return true- ���³ɹ� false- ����ʧ��
	 */
	boolean prodUpdatePnum(int pid, int pnum);

	/**
	 * ͨ����Ʒidɾ��ɾ����Ʒ��Ϣ�ķ���
	 * @param pid ��Ʒid
	 * @return true-ɾ���ɹ� false-ɾ��ʧ��
	 */
	boolean prodDel(int pid);
}
