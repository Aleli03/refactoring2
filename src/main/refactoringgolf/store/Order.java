package refactoringgolf.store;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Order {

	private Customer customer;
	private Salesman salesman;
	private Date orderedOn;
	private String deliveryStreet;
	private String deliveryCity;
	private String deliveryCountry;
	private Set<OrderItem> items;

	public Order(Customer customer, Salesman salesman, String deliveryStreet, String deliveryCity, String deliveryCountry, Date orderedOn) {
		this.customer = customer;
		this.salesman = salesman;
		this.deliveryStreet = deliveryStreet;
		this.deliveryCity = deliveryCity;
		this.deliveryCountry = deliveryCountry;
		this.orderedOn = orderedOn;
		this.items = new HashSet<OrderItem>();
	}

	public Customer getCustomer() {
		return customer;
	}

	public Salesman getSalesman() {
		return salesman;
	}

	public Date getOrderedOn() {
		return orderedOn;
	}

	public String getDeliveryStreet() {
		return deliveryStreet;
	}

	public String getDeliveryCity() {
		return deliveryCity;
	}

	public String getDeliveryCountry() {
		return deliveryCountry;
	}

	public Set<OrderItem> getItems() {
		return items;
	}

	public float total() {
		float totalItems = totalItems();
		float shipping = shippingCost();
		float tax= tax(totalItems);
		return totalItems + tax + shipping;
	}

	private float shippingCost() {		
		if (this.deliveryCountry == "USA")
			return 0;
		return 15;	
	}

	private float tax(float totalItems) {
		return totalItems * 5 / 100;
	}

	private float totalItems() {
		float totalItems=0;
		for (OrderItem item : items) {
			float totalItem=0;
			float discount =0;
			float itemAmount = item.getTotalPrice();
			if (item.getProduct().getCategory() == ProductCategory.Accessories) {
				discount = accesoriesDiscount(itemAmount);
			}
			if (item.getProduct().getCategory() == ProductCategory.Bikes) {
				// 20% discount for Bikes
				discount = bikesDiscount(itemAmount);
			}
			if (item.getProduct().getCategory() == ProductCategory.Cloathing) {
				discount = cloathingDiscount(item);
			}
			totalItem = itemAmount - discount;
			totalItems += totalItem;
		}
		return totalItems;
	}

	private float cloathingDiscount(OrderItem item) {
		float cloathingDiscount = 0;
		if (item.getQuantity() > 2) {
			cloathingDiscount = item.getProduct().getUnitPrice();
		}
		return cloathingDiscount;
	}

	private float bikesDiscount(float itemAmount) {
		return itemAmount * 20 / 100;
	}

	private float accesoriesDiscount(float itemAmount) {
		float booksDiscount = 0;
		if (itemAmount >= 100) {
			booksDiscount = itemAmount * 10 / 100;
		}
		return booksDiscount;
	}
}
