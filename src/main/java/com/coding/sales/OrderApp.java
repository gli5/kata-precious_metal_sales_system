package com.coding.sales;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.coding.sales.input.CustomerInfo;
import com.coding.sales.input.OrderCommand;
import com.coding.sales.input.OrderItemCommand;
import com.coding.sales.input.ProductInfo;
import com.coding.sales.input.Purchase;
import com.coding.sales.output.DiscountItemRepresentation;
import com.coding.sales.output.OrderItemRepresentation;
import com.coding.sales.output.OrderRepresentation;
import com.coding.sales.output.PaymentRepresentation;

/**
 * 销售系统的主入口
 * 用于打印销售凭证
 */
public class OrderApp {

    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("参数不正确。参数1为销售订单的JSON文件名，参数2为待打印销售凭证的文本文件名.");
        }

        String jsonFileName = args[0];
        String txtFileName = args[1];

        String orderCommand = FileUtils.readFromFile(jsonFileName);
        OrderApp app = new OrderApp();
        String result = app.checkout(orderCommand);
        FileUtils.writeToFile(result, txtFileName);
        
    }

    public String checkout(String orderCommand) {
        OrderCommand command = OrderCommand.from(orderCommand);
        OrderRepresentation result = checkout(command);
        System.out.println(result.toString());
        return result.toString();
    }

    OrderRepresentation checkout(OrderCommand command) {
        OrderRepresentation result = null;
        double sum=0;//购买金额
        int newIntegral =0;//新增积分
        double Discountsum=0;//优惠合计
        double normalSum =0;//正常合计金额
        //购物清单
        List<OrderItemRepresentation> orderItems =new ArrayList<OrderItemRepresentation>();
        //优惠清单
        List<DiscountItemRepresentation> discounts = new ArrayList<DiscountItemRepresentation>();
        //支付方式
        List<PaymentRepresentation> payments =new ArrayList<PaymentRepresentation>();
        
        CustomerInfo cust = new CustomerInfo();
        cust.setAccountNo(command.getMemberId());
        cust.customer();
        String customerLeveal ="";
        int integral=Integer.parseInt(cust.getIntegral());
    	
    	
    	String discountCards = "";
    	List<String> Discounts=command.getDiscounts();
    	for(String Discount:Discounts){
    		discountCards = Discount;
    	}
    	
    	List<OrderItemCommand> items=command.getItems();
    	for(OrderItemCommand oic:items){
    		//产品信息
    		ProductInfo productInfo = new ProductInfo();
    		productInfo.setId(oic.getProduct());//商品编号
    		productInfo.handleProduct();
    		productInfo.getName();//商品名称
    		productInfo.getPrice();//商品价格
    		//购买信息
    		Purchase purchase = new Purchase();
    		purchase.setId(oic.getProduct());
    		purchase.setCount(String.valueOf(oic.getAmount()));
    		purchase.setDiscountCards(discountCards);
    		sum += purchase.Buy();
    		normalSum += purchase.getNormalSum();
    		OrderItemRepresentation OrderItem = new OrderItemRepresentation(oic.getProduct(), productInfo.getName(), new BigDecimal(productInfo.getPrice()), oic.getAmount(), new BigDecimal(purchase.getNormalSum()));
    		orderItems.add(OrderItem);
    		//产品清单
    	    //获取优惠列表
    		if(purchase.getDiscountSum()>0){
    			DiscountItemRepresentation Discount = new DiscountItemRepresentation(oic.getProduct(), productInfo.getName(), new BigDecimal(purchase.getDiscountSum()));
    			discounts.add(Discount);
    	    }
    		Discountsum+=purchase.getDiscountSum();
    	}
    	if(integral<10000){
    		integral =integral+(int)sum;
    		newIntegral =(int)sum;
    	}else if(integral<5000){
    		integral =integral+(int)(sum*1.5);
    		newIntegral =(int)(sum*1.5);
    	}else if(integral<100000){
    		integral =integral+(int)(sum*1.8);
    		newIntegral =(int)(sum*1.8);
    	}else{
    		integral=integral+(int)(sum*2);
    		newIntegral =(int)(sum*2);
    	}
    	if(integral>=100000){
    		customerLeveal="钻石卡";
    	}else if(integral >= 50000){
    		customerLeveal="白金卡";
    	}else if(integral >= 10000){
    		customerLeveal="金卡";
    	}else {
    		customerLeveal="普卡";
    	}
    	
    	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
    	PaymentRepresentation payment = new PaymentRepresentation("余额支付", new BigDecimal(sum));
    	payments.add(payment);
    	 
    	result = new OrderRepresentation(df.format(new Date()), new Date(), command.getMemberId(), cust.getCustomerName(), cust.getCustomerLeveal(), customerLeveal, 
    			newIntegral, integral, orderItems, new BigDecimal(normalSum), discounts, new BigDecimal(Discountsum), new BigDecimal(sum), payments, Discounts);
        
        return result;
    }

}
