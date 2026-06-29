package Controller;
import java.sql.SQLException;
import java.util.List;

import Model.Product;
import dao.ProductDao;
public class WareHouseControl {

	public static List<Product> UrunleriAl(int depo_id) throws SQLException{
		return ProductDao.DaoPro.GetProducts(depo_id);
	}
}
