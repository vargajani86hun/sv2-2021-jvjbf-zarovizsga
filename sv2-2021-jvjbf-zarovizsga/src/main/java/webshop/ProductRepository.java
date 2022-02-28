package webshop;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class ProductRepository {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public ProductRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    public Product findProductById(long id) {//language=sql
        return jdbcTemplate.queryForObject("SELECT * FROM products WHERE id = ?;",
                (rs, rowNum) -> new Product(rs.getLong("id"),
                                            rs.getString("product_name"),
                                            rs.getInt("price"),
                                            rs.getInt("stock")
                ), id);
    }

    public long insertProduct(String productName, int price, int stock) {//language=sql
        KeyHolder keyHolder = new GeneratedKeyHolder();
        return jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO products (product_name, price, stock) VALUES (?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, productName);
            ps.setLong(2, price);
            ps.setLong(3, stock);
            return ps;
        }, keyHolder);
    }

    public void updateProductStock(long id, int amount) {//language=sql
        jdbcTemplate.update("UPDATE products SET stock = stock - ? WHERE id = ?;", amount, id);
    }
}
