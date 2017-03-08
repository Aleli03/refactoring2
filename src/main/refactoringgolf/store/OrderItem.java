package refactoringgolf.store;

public class OrderItem {
	
	private Product product;
	private int quantity;

	/*
	 * Order Item Constructor
	 */
	public OrderItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}
	
	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public int getTotalPrice(){
		return (int) (product.getUnitPrice() * quantity);
	}
	public boolean moreThanTwoItems(){
		return quantity>2;
	}
	public boolean isAccesory(){
		return product.getCategory() == ProductCategory.Accessories;
	}
	public boolean isBike(){
		return product.getCategory() == ProductCategory.Bikes;
	}
	public boolean isCloathing(){
		return product.getCategory() == ProductCategory.Cloathing;
	}
}
