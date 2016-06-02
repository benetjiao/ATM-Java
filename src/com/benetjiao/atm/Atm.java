package com.benetjiao.atm;

import java.util.Scanner;

public class Atm {

	Scanner input = new Scanner(System.in);
	Bank bank = new Bank();
	/**
	 * 启动ATM机
	 */
	public void start() {
		// TODO 显示应用菜单
		System.out.println("------------------------");
		System.out.println("    欢迎使用ATM自助银行");
		System.out.println("------------------------");
		System.out.println("    1.开  户");
		System.out.println("    2.插  卡");
		System.out.println("    3.系统管理");
		System.out.println("    4.退出系统");
		System.out.println("------------------------");
		System.out.println("请选择您要进行的操作：");
		
		String num = input.next();
		
		switch(num){
		case "1":
			//进入开户菜单
			openAccountMenu();
			break;
		case "2":
			//进入插卡菜单
			insertCardMenu();
			break;
		case "3":
			//进入系统管理菜单
			systemManagerMenu();
			break;
		case "4":
			//退出系统
			exit();
			default:
				start();
			
		}
	}
	
	/**
	 * 退出系统
	 */
	private void exit() {
		// TODO 操作完成退出系统
		
	}

	/**
	 * 系统管理菜单
	 */
	private void systemManagerMenu() {
		// TODO 显示系统管理菜单
		
	}

	/**
	 * 插卡菜单
	 */
	private void insertCardMenu() {
		// TODO 显示插卡菜单
		
	}
	
	/**
	 * 开户菜单
	 */
	private void openAccountMenu() {
		// TODO 显示开户菜单
		
	}

	
}
