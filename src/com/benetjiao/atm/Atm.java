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
				start();
			
		}
	}
	
	/**
	 * �˳�ϵͳ
	 */
	private void exit() {
		// TODO ��������˳�ϵͳ
		
	}

	/**
	 * ϵͳ����˵�
	 */
	private void systemManagerMenu() {
		// TODO ��ʾϵͳ����˵�
		
	}

	/**
	 * �忨�˵�
	 */
	private void insertCardMenu() {
		// TODO ��ʾ�忨�˵�
		
	}
	
	/**
	 * �����˵�
	 */
	private void openAccountMenu() {
		// TODO ��ʾ�����˵�
		
	}

	
}
