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
				System.err.println("请输入正确的选项!");
				start();
			
		}
	}
	
	/**
	 * 退出系统
	 */
	private void exit() {
		// TODO 操作完成退出系统
		System.out.println("*******************");
		System.out.println("    感谢您的使用！");
		System.out.println("*******************");
		System.exit(0);
	}

	/**
	 * 系统管理菜单
	 */
	private void systemManagerMenu() {
		// TODO 显示系统管理菜单
		System.out.println("系统管理菜单~~~~~");
		start();
	}

	/**
	 * 插卡操作
	 */
	private void insertCardMenu() {
		// TODO 进行插卡操作
		System.out.println("请输入用户名：");
		String name = input.next();
		if(!"".equals(name) && name != null){
			System.out.println("请输入密码：");
			String pwd = input.next();
			
			Person p = bank.vaildatePerson(name,pwd);
			if(p != null){
				accountMenu(p);
			} else {
				System.out.println("用户名或密码不正确！");
				insertCardMenu();
			}
		} else {
			System.out.println("请输入正确的用户名！");
			insertCardMenu();
		}
		
	}
	
	/**
	 * 个人账户
	 * @param p
	 */
	private void accountMenu(Person p) {
		// TODO 个人账户操作
		System.out.println("************************");
		System.out.println("   欢迎您  " + p.getName() + " !");
		System.out.println("************************");
		System.out.println("  1.查询余额");
		System.out.println("  2.存    款");
		System.out.println("  3.转    账");
		System.out.println("  4.取    款");
		System.out.println("  5.修改密码");
		System.out.println("  6.退    出");
		System.out.println("************************");
		System.out.println("请选择您要进行的操作：");
		
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
					System.out.println("请输入正确的操作！");
					accountMenu(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 用户修改密码操作
	 * @param p 要修改密码的用户
	 */
	private void modifyPwd(Person p) {
		// TODO 进行密码修改过操作
		System.out.println("请输入旧密码：");
		String pwd = input.next();
		
		if(p.getPwd().equals(pwd)){
			System.out.println("请输入新密码：");
			String newPwd = input.next();
			
			if(!"".equals(newPwd) && newPwd != null){
				System.out.println("请再次输入新密码：");
				String newPwd2 = input.next();
				
				if(newPwd.equals(newPwd2)){
					p.setPwd(newPwd2);
					System.out.println("密码修改成功！");
					accountMenu(p);
				} else {
					System.out.println("两次输入的密码不一样，请重新输入！");
					modifyPwd(p);
				}
			} else {
				System.out.println("请输入正确的新密码！");
				modifyPwd(p);
			}
		} else {
			System.out.println("旧密码错误，请重新输入!");
			start();
		}
	}

	/**
	 * 用户取款操作	
	 * @param p 要取款的用户
	 */
	private void getMoney(Person p) {
		// TODO 用户取款操作
		System.out.println("请输入您要取款的金额：");
		try {
			float money = input.nextFloat();
			
			if(money > 0){
				if(money <= p.getMoney()){
					p.setMoney(p.getMoney() - money);
					System.out.println("取款成功！ 您卡上的余额为：" + p.getMoney() + " 元！");
					accountMenu(p);
				} else {
					System.out.println("您的余额不足！请充值！");
					accountMenu(p);
				}
			} else {
				System.out.println("请输入正确的取款金额！");
				getMoney(p);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 用户转账操作
	 * @param p 要转账的用户
	 */
	private void transferMoney(Person p) {
		// TODO 用户转账
		System.out.println("请输入您要转账的用户名：");
		try {
			String transPerName = input.next();
			
			if(!"".equals(transPerName) && transPerName != null){
				Person transPer = bank.vaildatePerson(transPerName);
				
				if(transPer != null){
					if(transPer.isEnable()){
						System.out.println("请输入要转账的金额：");
						float money = input.nextFloat();
						
						if(money > 0){
							if(money <= p.getMoney()){
								p.setMoney(p.getMoney() - money);
								transPer.setMoney(transPer.getMoney() + money);
								System.out.println("转账成功，您卡上的余额为： " + p.getMoney() + " 元！");
								accountMenu(p);
							} else {
								System.out.println("您的余额不足，请充值！");
								accountMenu(p);
							}
						} else {
							System.out.println("请输入正确的转账金额！");
							transferMoney(p);
						}
					} else {
						System.out.println("该用户已被冻结，请联系管理与！");
						accountMenu(p);
					}
				} else {
					System.out.println("该用户不存在！");
					accountMenu(p);
				}
			} else {
				System.out.println("请输入正确的转账用户名！");
				transferMoney(p);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 用户存储操作
	 * @param 要存款的用户
	 */
	private void saveMoney(Person p) {
		// TODO 用户存款操作
		System.out.println("请输入您要存款的金额：");
		try {
			float money = input.nextFloat();
			
			if(money > 0){
				p.setMoney(money + p.getMoney());
				System.out.println("存款成功！您卡上的余额为：" + p.getMoney() + " 元！");
				accountMenu(p);
			} else {
				System.out.println("请输入正确的存款金额！");
				saveMoney(p);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 用户查询余额操作
	 * @param p
	 */
	private void balanceInquiry(Person p) {
		// TODO 用户查询余额操作	
		System.out.println("您卡上的余额为：" + p.getMoney() + " 元！");
		accountMenu(p);
	}

	/**
	 * 开户操作
	 */
	private void openAccountMenu() {
		// TODO 进行开户操作
		System.out.println("请输入用户名：");
		String name = input.next();
		
		if(!"".equals(name) && name != null){
			//对新用户进行验证，用户名不能重复
			Person p = bank.vaildatePerson(name);
			if(p == null){
				System.out.println("请输入密码：");
				String pwd = input.next();
				
				if(!"".equals(pwd) && pwd != null){
					System.out.println("请再次输入密码:");
					String pwd2 = input.next();
					
					if(pwd.equals(pwd2)){
						try {
							System.out.println("请输入开户金额：");
							float money = input.nextFloat();
							
							if(money >= 0){
								Person per = new Person();
								per.setEnable(true);
								per.setName(name);
								per.setPwd(pwd2);
								
								boolean isSaved = bank.savePerson(per);
								if(isSaved){
									System.out.println("开户成功！");
									start();
								} else {
									System.out.println("开户失败，请联系管理员！");
									start();
								}
							} else {
								System.out.println("请输入正确的开户金额！");
								openAccountMenu();
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
					} else {
						System.out.println("两次输入的密码 不相同，请重新输入！");
						openAccountMenu();
					}
				} else {
					System.out.println("密码不能为空，请输入正确的密码！");
					openAccountMenu();
				}
			} else {
				System.out.println("该用户已存在！");
				start();
			}
		} else {
			System.out.println("请输入正确的用户名！");
			openAccountMenu();
		}
		
	}

	
}