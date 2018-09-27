package cn.tedu.dao;

import cn.tedu.domain.User;
import cn.tedu.exception.MsgException;

public interface UserDao {
	//dao�еķ�����������Դ��CRUD
	//����ǲ�1�� getXXXByXXX
	//�������listXXXByXXX() listAllXXX()
	//����� ��   insert/save insertXXX() saveXXX()
	//�����ɾ   delete/remove insertXXX() saveXXX()
	//����� ��   insert/save insertXXX() saveXXX()
	
	/**
	 * �����û�����ѯ�û��Ƿ���ڵķ���
	 * @param username ��ѯ���û���
	 * @return true-�����û�    false-�������û�
	 */
	boolean getUserByUsername(String username);
	
	
	/**
	 * �����û���Ϣ
	 * @param user ��װ�û���Ϣ��Javabean
	 * @return true- �����û���Ϣ�ɹ� false- �����û���Ϣʧ��
	 */
	boolean saveUser(User user);
	
	/**
	 * �����û�����������е�¼��������
	 * @param username �û����û���
	 * @param password �û�������
	 * @return  �ɹ�--���� User����  ʧ��--����null 
	 * @throws ��װ����ʾ��Ϣ���Զ����쳣����
	 */
	User getUserByUAP(String username, String password) throws MsgException;
	 
}
