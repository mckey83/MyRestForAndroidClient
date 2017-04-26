package ua.com.ex.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.tomcat.util.codec.binary.Base64;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
	@Id
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "price")
	private BigDecimal price;

	@Transient
	private String imageBase64;

	// TODO: create file repository and fill imageBase64 in ...
	public void setImage() {
		File f;
		FileInputStream fis = null;
		try {
			f = new File("/media/alex/d84bf558-86ff-4f37-906a-f167b938fbae/alexander/www/new.ex.com.ua/media/images/products_images/" + id + "_thumb.png");
			if (!f.exists()) {
				f = new File("src/main/resources/images/ex.png");
			}
			fis = new FileInputStream(f);
			byte byteArray[] = new byte[(int) f.length()];
			fis.read(byteArray);
			imageBase64 = Base64.encodeBase64String(byteArray);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
