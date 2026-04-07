package com.example.retail_pos_system.billing;

import java.util.List;

public class BillingRequest {
	public List<Item> items;
	public double discount;
	
	public static class Item {
		public Long productId;
        public int quantity;
	}

}
