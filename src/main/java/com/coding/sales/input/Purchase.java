package com.coding.sales.input;

import java.util.Arrays;

public class Purchase {
       String id;//产品编号
       String count;//产品购买数量
       String discountCards;//打折券
       double sum = 0;//优惠后购买金额
       String price;//单价
	   double discountSum=0;//优惠金额
	   double normalSum =0;//正常金额
	
		//普通商品
		String[] ordinaryGoods = {"001001"};
		//9折商品
		String[] reduced9 ={"001002","002003"};
		//95折商品
		String[] reduced95 ={"003001","002001"};
		//满减商品:第3件半价，满3送1
		String[] fullReduction1 = {"003002","002001"};
		//满减商品:每满2000减30，每满1000减10
		String[] fullReduction2 = {"002002","002003"};
		//满减商品:每满3000元减350,
		String[] fullReduction3 = {"002003"};
		
	/**
	 * 	购买商品
	 * @return
	 */
	public double  Buy(){
		ProductInfo productInfo = new ProductInfo();
		   productInfo.setId(id);
		   productInfo.handleProduct();
		   price = productInfo.getPrice();
		   normalSum = Double.parseDouble(price)*Integer.parseInt(count);//正常不优惠金额
		if(!"".equals(discountCards) && discountCards!= null){
			if("95折券".equals(discountCards)&& Arrays.asList(reduced95).contains(id)){
				sum =Double.parseDouble(price)*Integer.parseInt(count)*0.95;
				discountSum = Double.parseDouble(price)*Integer.parseInt(count)*0.05;
			}else if("9折券".equals(discountCards)){
				double reduced9Sum=0,fullReduction3Sum=0;
				//9折券时金额计算
				if(Arrays.asList(reduced9).contains(id)){
					reduced9Sum = Double.parseDouble(price)*Integer.parseInt(count)*0.9;
				}else{
					reduced9Sum = Double.parseDouble(price)*Integer.parseInt(count);
				}
				//满减金额计算:参与满减：每满3000元减350, 每满2000减30，每满1000减10
				if(Arrays.asList(fullReduction3).contains(id)){
					if(Double.parseDouble(price)*Integer.parseInt(count)>3000){
						int Multiple =(int)(Double.parseDouble(price)*Integer.parseInt(count)/3000);
						fullReduction3Sum = Double.parseDouble(price)*Integer.parseInt(count)-350*Multiple;
					}else if(Double.parseDouble(price)*Integer.parseInt(count)>2000 ){
						int Multiple =(int)(Double.parseDouble(price)*Integer.parseInt(count)/2000);
						fullReduction3Sum = Double.parseDouble(price)*Integer.parseInt(count)-30*Multiple;
					}else if(Double.parseDouble(price)*Integer.parseInt(count)>1000){
						int Multiple =(int)(Double.parseDouble(price)*Integer.parseInt(count)/1000);
						fullReduction3Sum = Double.parseDouble(price)*Integer.parseInt(count)-10*Multiple;
					}else{
						fullReduction3Sum = Double.parseDouble(price)*Integer.parseInt(count);
					}
				}else{
					fullReduction3Sum = Double.parseDouble(price)*Integer.parseInt(count);
				}
				
				//两者相比较取最小值
				if(reduced9Sum>fullReduction3Sum){
					sum =fullReduction3Sum;
				}else{
					sum =reduced9Sum;
				}
				discountSum = Double.parseDouble(price)*Integer.parseInt(count)-sum;
			}else{
				sum =No_discountCards_Buy();
			}
		}else{
			sum =No_discountCards_Buy();
		}
		return sum;
	}
	
		
		/**
		 * 无打折券购买
		 */
   private double No_discountCards_Buy(){
	   double buySum = 0;
	   
	   //如果商品是满减商品:第3件半价，满3送1
		if(Arrays.asList(fullReduction1).contains(id)){
			   if(Integer.parseInt(count)==3){
				   buySum = Double.parseDouble(price)*2.5;
				   discountSum = Double.parseDouble(price)*0.5;
			   }
			   if(Integer.parseInt(count)<3){
				   buySum = Double.parseDouble(price)*Integer.parseInt(count);
			   }
			   if(Integer.parseInt(count)>3){
				   buySum = Double.parseDouble(price)*(Integer.parseInt(count)-1);
				   discountSum = Double.parseDouble(price);
			   }
		 }
		if(Double.parseDouble(price)*Integer.parseInt(count)>3000 && Arrays.asList(fullReduction3).contains(id)){
			int Multiple =(int)(Double.parseDouble(price)*Integer.parseInt(count)/3000);
			buySum = Double.parseDouble(price)*Integer.parseInt(count)-350*Multiple;
			discountSum = 350*Multiple;
		}else if(Double.parseDouble(price)*Integer.parseInt(count)>2000 && Arrays.asList(fullReduction2).contains(id)){
			int Multiple =(int)(Double.parseDouble(price)*Integer.parseInt(count)/2000);
			buySum = Double.parseDouble(price)*Integer.parseInt(count)-30*Multiple;
			discountSum = 30*Multiple;
		}else if(Double.parseDouble(price)*Integer.parseInt(count)>1000 && Arrays.asList(fullReduction2).contains(id)){
			int Multiple =(int)(Double.parseDouble(price)*Integer.parseInt(count)/1000);
			buySum = Double.parseDouble(price)*Integer.parseInt(count)-10*Multiple;
			discountSum = 10*Multiple;
		}
		//普通商品
		if(Arrays.asList(fullReduction1).contains(id)){
			buySum = Double.parseDouble(price)*Integer.parseInt(count);
		}
		return buySum;
   }

		
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getDiscountCards() {
		return discountCards;
	}
	public void setDiscountCards(String discountCards) {
		this.discountCards = discountCards;
	}
	public double getDiscountSum() {
		return discountSum;
	}
	public void setDiscountSum(double discountSum) {
		this.discountSum = discountSum;
	}
	public double getNormalSum() {
		return normalSum;
	}
	public void setNormalSum(double normalSum) {
		this.normalSum = normalSum;
	}
}
