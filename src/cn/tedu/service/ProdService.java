package cn.tedu.service;

import cn.tedu.domain.Prod;

public interface ProdService {
	/**
	 * �����Ʒ�ķ���
	 * @param prod ��װ����Ʒ��Ϣ��JavaBean
	 * @return true-��ӳɹ� false-���ʧ��
	 */
	boolean addProd(Prod prod);
}
