package ua.com.ex.reprository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Repository;

@Repository("imageReprository")
public class ImageReprositoryImpl implements ImageReprository {

	@Override
	public String getProductImageById(int productId) {

		File f;
		FileInputStream fis = null;
		String result = "";
		try {
			f = new File("/media/alex/d84bf558-86ff-4f37-906a-f167b938fbae/alexander/www/new.ex.com.ua/media/images/products_images/" + productId + "_thumb.png");
			if (!f.exists()) {
				f = new File("classes/images/ex.png");
			}
			fis = new FileInputStream(f);
			byte byteArray[] = new byte[(int) f.length()];
			fis.read(byteArray);
			result = Base64.encodeBase64String(byteArray);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

    @Override
    public String getCategoryImageById(int categoryId) {
        File f;
        FileInputStream fis = null;
        String result = "";
        try {
            f = new File("classes/images/categories/cat_"+ categoryId + ".png");
            if (!f.exists()) {
                f = new File("classes/images/ex.png");
            }
            fis = new FileInputStream(f);
            byte byteArray[] = new byte[(int) f.length()];
            fis.read(byteArray);
            result = Base64.encodeBase64String(byteArray);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
