package com.coding.sales.input;

public class CustomerInfo {
	
	//姓名,等级,卡号,积分
	private String customerName;
	private String customerLeveal;
	private String accountNo;
	private String integral;
	
	String [][] arr= {
		{"6236609999","马丁","普卡","9860"},
		{"6630009999","王立","金卡","48860"},
		{"8230009999","李想","白金卡","98860"},
		{"9230009999","张三","钻石卡","198860"}
	};
	
	
	public String customer() {
		for (int i = 0; i < arr.length; i++) {

			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][0] != null) {
					if (arr[i][0].equals(accountNo)) {
						this.setCustomerName(arr[i][1]);
						this.setCustomerLeveal(arr[i][2]);
						this.setIntegral(arr[i][3]);
					}
				} else {
					return "非会员客户";
				}
			}
		}
		return "";

	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerLeveal() {
		return customerLeveal;
	}
	public void setCustomerLeveal(String customerLeveal) {
		this.customerLeveal = customerLeveal;
	}
	public String getIntegral() {
		return integral;
	}
	public void setIntegral(String integral) {
		this.integral = integral;
	}


}
