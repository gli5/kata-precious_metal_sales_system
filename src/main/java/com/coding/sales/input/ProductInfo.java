package com.coding.sales.input;

public class ProductInfo {
	  String id;//商品编号
	  String name;//商品名称
	  String price;//商品价格
	  String[][] product = {
	    		{"001001","世园会五十国钱币册","998.00"},
	    		{"001002","2019北京世园会纪念银章大全40g","1380.00"},
	    		{"003001","招财进宝","1580.00"},
	    		{"003002","水晶之恋","980.00"},
	    		{"002002","中国经典钱币套装","998.00"},
	    		{"002001","守扩之羽比翼双飞4.8g","1080.00"},
	    		{"002003","中国银象棋12g","698.00"},
	    };
	    
	    public void handleProduct(){
	    	for(int i=0;i<product.length;i++){
	    		for(int j=0;j<product[i].length;j++){
	    			if(id.equals(product[i][0])){
	    				setName(product[i][1]);
	    				setPrice(product[i][2]);
	    			}
	    		}
	    	}
	    }

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
		}
}
