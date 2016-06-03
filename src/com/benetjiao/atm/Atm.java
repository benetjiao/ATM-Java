package com.benetjiao.atm;

import java.util.Scanner;

public class Atm {

	Scanner input = new Scanner(System.in);
	Bank bank = new Bank();
	/**
	 * ����ATM��
	 */
	public void start() {
		// TODO ��ʾӦ�ò˵�
		System.out.println("------------------------");
		System.out.println("    ��ӭʹ��ATM��������");
		System.out.println("------------------------");
		System.out.println("    1.��  ��");
		System.out.println("    2.��  ��");
		System.out.println("    3.ϵͳ����");
		System.out.println("    4.�˳�ϵͳ");
		System.out.println("------------------------");
		System.out.println("��ѡ����Ҫ���еĲ�����");
		
		String num = input.next();
		
		switch(num){
		case "1":
			//���뿪���˵�
			openAccountMenu();
			break;
		case "2":
			//����忨�˵�
			insertCardMenu();
			break;
		case "3":
			//����ϵͳ����˵�
			systemManagerMenu();
			break;
		case "4":
			//�˳�ϵͳ
			exit();
			default:
				System.err.println("��������ȷ��ѡ��!");
				start();
			
		}
	}
	
	/**
	 * �˳�ϵͳ
	 */
	private void exit() {
		// TODO ��������˳�ϵͳ
		System.out.println("*******************");
		System.out.println("    ��л����ʹ�ã�");
		System.out.println("*******************");
		System.exit(0);
	}

	/**
	 * ϵͳ����˵�
	 */
	private void systemManagerMenu() {
		// TODO ��ʾϵͳ����˵�
		System.out.println("ϵͳ����˵�~~~~~");
		start();
	}

	/**
	 * �忨����
	 */
	private void insertCardMenu() {
		// TODO ���в忨����
		System.out.println("�������û�����");
		String name = input.next();
		if(!"".equals(name) && name != null){
			System.out.println("���������룺");
			String pwd = input.next();
			
			Person p = bank.vaildatePerson(name,pwd);
			if(p != null){
				accountMenu(p);
			} else {
				System.out.println("�û��������벻��ȷ��");
				insertCardMenu();
			}
		} else {
			System.out.println("��������ȷ���û�����");
			insertCardMenu();
		}
		
	}
	
	/**
	 * �����˻�
	 * @param p
	 */
	private void accountMenu(Person p) {
		// TODO �����˻�����
		System.out.println("************************");
		System.out.println("   ��ӭ��  " + p.getName() + " !");
		System.out.println("************************");
		System.out.println("  1.��ѯ���");
		System.out.println("  2.��    ��");
		System.out.println("  3.ת    ��");
		System.out.println("  4.ȡ    ��");
		System.out.println("  5.�޸�����");
		System.out.println("  6.��    ��");
		System.out.println("************************");
		System.out.println("��ѡ����Ҫ���еĲ�����");
		
		try {
			String num = input.next();
			switch(num){
			case "1":
				balanceInquiry(p);
				break;
			case "2":
				saveMoney(p);
				break;
			case "3":
				transferMoney(p);
				break;
			case "4":
				getMoney(p);
				break;
			case "5":
				modifyPwd(p);
				break;
			case "6":
				start();
				break;
				default:
					System.out.println("��������ȷ�Ĳ�����");
					accountMenu(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * �û��޸��������
	 * @param p Ҫ�޸�������û�
	 */
	private void modifyPwd(Person p) {
		// TODO ���������޸Ĺ�����
		System.out.println("����������룺");
		String pwd = input.next();
		
		if(p.getPwd().equals(pwd)){
			System.out.println("�����������룺");
			String newPwd = input.next();
			
			if(!"".equals(newPwd) && newPwd != null){
				System.out.println("���ٴ����������룺");
				String newPwd2 = input.next();
				
				if(newPwd.equals(newPwd2)){
					p.setPwd(newPwd2);
					System.out.println("�����޸ĳɹ���");
					accountMenu(p);
				} else {
					System.out.println("������������벻һ�������������룡");
					modifyPwd(p);
				}
			} else {
				System.out.println("��������ȷ�������룡");
				modifyPwd(p);
			}
		} else {
			System.out.println("�������������������!");
			start();
		}
	}

	/**
	 * �û�ȡ�����	
	 * @param p Ҫȡ����û�
	 */
	private void getMoney(Person p) {
		// TODO �û�ȡ�����
		System.out.println("��������Ҫȡ��Ľ�");
		try {
			float money = input.nextFloat();
			
			if(money > 0){
				if(money <= p.getMoney()){
					p.setMoney(p.getMoney() - money);
					System.out.println("ȡ��ɹ��� �����ϵ����Ϊ��" + p.getMoney() + " Ԫ��");
					accountMenu(p);
				} else {
					System.out.println("�������㣡���ֵ��");
					accountMenu(p);
				}
			} else {
				System.out.println("��������ȷ��ȡ���");
				getMoney(p);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * �û�ת�˲���
	 * @param p Ҫת�˵��û�
	 */
	private void transferMoney(Person p) {
		// TODO �û�ת��
		System.out.println("��������Ҫת�˵��û�����");
		try {
			String transPerName = input.next();
			
			if(!"".equals(transPerName) && transPerName != null){
				Person transPer = bank.vaildatePerson(transPerName);
				
				if(transPer != null){
					if(transPer.isEnable()){
						System.out.println("������Ҫת�˵Ľ�");
						float money = input.nextFloat();
						
						if(money > 0){
							if(money <= p.getMoney()){
								p.setMoney(p.getMoney() - money);
								transPer.setMoney(transPer.getMoney() + money);
								System.out.println("ת�˳ɹ��������ϵ����Ϊ�� " + p.getMoney() + " Ԫ��");
								accountMenu(p);
							} else {
								System.out.println("�������㣬���ֵ��");
								accountMenu(p);
							}
						} else {
							System.out.println("��������ȷ��ת�˽�");
							transferMoney(p);
						}
					} else {
						System.out.println("���û��ѱ����ᣬ����ϵ�����룡");
						accountMenu(p);
					}
				} else {
					System.out.println("���û������ڣ�");
					accountMenu(p);
				}
			} else {
				System.out.println("��������ȷ��ת���û�����");
				transferMoney(p);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * �û��洢����
	 * @param Ҫ�����û�
	 */
	private void saveMoney(Person p) {
		// TODO �û�������
		System.out.println("��������Ҫ���Ľ�");
		try {
			float money = input.nextFloat();
			
			if(money > 0){
				p.setMoney(money + p.getMoney());
				System.out.println("���ɹ��������ϵ����Ϊ��" + p.getMoney() + " Ԫ��");
				accountMenu(p);
			} else {
				System.out.println("��������ȷ�Ĵ���");
				saveMoney(p);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * �û���ѯ������
	 * @param p
	 */
	private void balanceInquiry(Person p) {
		// TODO �û���ѯ������	
		System.out.println("�����ϵ����Ϊ��" + p.getMoney() + " Ԫ��");
		accountMenu(p);
	}

	/**
	 * ��������
	 */
	private void openAccountMenu() {
		// TODO ���п�������
		System.out.println("�������û�����");
		String name = input.next();
		
		if(!"".equals(name) && name != null){
			//�����û�������֤���û��������ظ�
			Person p = bank.vaildatePerson(name);
			if(p == null){
				System.out.println("���������룺");
				String pwd = input.next();
				
				if(!"".equals(pwd) && pwd != null){
					System.out.println("���ٴ���������:");
					String pwd2 = input.next();
					
					if(pwd.equals(pwd2)){
						try {
							System.out.println("�����뿪����");
							float money = input.nextFloat();
							
							if(money >= 0){
								Person per = new Person();
								per.setEnable(true);
								per.setName(name);
								per.setPwd(pwd2);
								
								boolean isSaved = bank.savePerson(per);
								if(isSaved){
									System.out.println("�����ɹ���");
									start();
								} else {
									System.out.println("����ʧ�ܣ�����ϵ����Ա��");
									start();
								}
							} else {
								System.out.println("��������ȷ�Ŀ�����");
								openAccountMenu();
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
					} else {
						System.out.println("������������� ����ͬ�����������룡");
						openAccountMenu();
					}
				} else {
					System.out.println("���벻��Ϊ�գ���������ȷ�����룡");
					openAccountMenu();
				}
			} else {
				System.out.println("���û��Ѵ��ڣ�");
				start();
			}
		} else {
			System.out.println("��������ȷ���û�����");
			openAccountMenu();
		}
		
	}

	
}