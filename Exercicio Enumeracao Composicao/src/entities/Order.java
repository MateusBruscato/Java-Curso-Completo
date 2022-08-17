package entities;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import enumerate.OrderStatus;

public class Order {

	private LocalDateTime moment;
	private OrderStatus status;
	private List<OrderItem> items = new ArrayList<>();
	private Client client;
	
	DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Order() {
		
	}
	
	public Order(OrderStatus status, Client client) {
		this.status = status;
		this.client = client;
		this.moment = LocalDateTime.now();
	}

	
	public LocalDateTime getMoment() {
		return moment;
	}

	public void setMoment(LocalDateTime moment) {
		this.moment = moment;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void addItem(OrderItem item) {
		items.add(item);
	}
	
	public void removeItem(OrderItem item) {
		items.remove(item);
	}

	public double total() {
		double sum = 0;
		for(OrderItem item : items) {
			sum += item.subTotal();
		}
		return sum;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ORDER SUMMARY: \n");
		sb.append("Order moment: ");
		sb.append(fmt1.format(moment) + "\n");
		sb.append("Order status: " + status + "\n");
		sb.append("Client: " + client.getName() + ", (" + sdf.format(client.getBirthDate()) + ") - " + client.getEmail() + "\n");
		sb.append("Order items: \n");
		for(OrderItem item : items) {
			sb.append(item.getProduct().getName() +", ");
			sb.append("$" + item.getPrice() + ", ");
			sb.append("Quantity: " + item.getQuantity() + ", ");
			sb.append("Subtotal: " + String.format("$%.2f", item.subTotal()) + "\n");
		}
		sb.append("Total price: " + String.format("$%.2f", total()));
		return sb.toString();
		
	}
}
