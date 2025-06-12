package secao_4_3.serializationBinary.reflection;

import java.util.Map;

import secao_4_3.serializationBinary.entidades.Person;
import secao_4_3.serializationBinary.entidades.Product;

public class ReflectionTest {
 
	public static void main(String[] args) {
 
		Person person = new Person();
		person.setId(1);
		person.setName("Gabriel");
		person.setLastName("Amorim da silva");
		person.setAge(25);
 
		Product product = new Product();
		product.setId(1);
		product.setDescription("Oxford Dictionary");
		product.setPrice(11.90);
		product.setQuantity(1);
 
		Map<String, Object> attributes = ReflectionMapper.getAttributesMap(person);
 
		for(String key : attributes.keySet()) {
			System.out.println(key + ": " + attributes.get(key));
		}
 
		attributes = ReflectionMapper.getAttributesMap(product);
 
		for(String key : attributes.keySet()) {
			System.out.println(key + ": " + attributes.get(key));
		}
	}
}