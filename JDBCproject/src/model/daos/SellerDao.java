package model.daos;

import java.util.List;
import model.entities.Seller;

public interface SellerDao {

	void insert(Seller seller);

	void update(Seller seller);

	void deleteById(int id);

	Seller findById(int id);

	List<Seller> findAll();

}
