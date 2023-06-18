package adminBean;

import java.sql.Blob;

public class EditProductBean {
	String productId;
	String productName;
	String stock;
	String price;
	Blob productimage;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Blob getProductimage() {
		return productimage;
	}
	public void setProductimage(Blob productimage) {
		this.productimage = productimage;
	}
}
