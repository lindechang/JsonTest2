package com.lindec.json.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.codehaus.jackson.JsonGenerationException;
//import org.codehaus.jackson.JsonParseException;
//import org.codehaus.jackson.map.JsonMappingException;
//import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSONObject;

/**
 * 
 * JSON 、 Fastjson 、 Jackson 性能测试
 * @author lindec
 *
 */
public class TestMain {

	static ObjectMapper mapper = new ObjectMapper();

	/**
	 * 测试三组数据：10000次 ，50000次，100000次
	 * 
	 */
	@Test
	public void test() {

		Utils.timeBegin();
		List<Corp> corps = new ArrayList<Corp>();
		int amount = 50000;
		for (int i = 0; i < amount; i++) {
			corps.add(Utils.fullObject(Corp.class));
		}
		Utils.timeEnd("装配" + amount + "组数据");

		// json
		Utils.timeBegin();
		for (Corp corp : corps) {
			JSONObject jsonObject = JSONObject.fromObject(corp);

		}
		Utils.timeEnd("Json");

		// fastjson
		Utils.timeBegin();
		for (Corp corp : corps) {
			String string = JSON.toJSONString(corp);
		}
		Utils.timeEnd("fastjson");

		// Jackson
		Utils.timeBegin();
		for (Corp corp : corps) {
			try {
				String string = mapper.writeValueAsString(corp);
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Utils.timeEnd("jackson");

	}

	/**
	 * 
	 */
	@Test
	public void test2() {
		String Car1 = "{\"brand\":\"BMW\",\"value\":\"500000\"}";
		String Car2 = "{\"brand\":\"AUDI\",\"value\":\"300000\"}";
		String Car3 = "{\"brand\":\"FORD\",\"value\":\"400000\"}";

		JSONObject jsonObject = JSONObject.fromObject(Car1);
		Car car1 = (Car) JSONObject.toBean(jsonObject, Car.class);

		System.out.println(car1.getBrand());

		Car car2 = JSON.parseObject(Car2, Car.class);
		System.out.println(car2.getBrand());

		try {
			Car car3 = mapper.readValue(Car3, Car.class);
			System.out.println(car3.getBrand());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 
	 */

	@Test
	public void test3() {

		String Person1 = "{\"name\":\"lindec\",\"age\":26,\"cars\":[{\"brand\":\"BMW\",\"value\":\"500000\"},{\"brand\":\"AUDI\",\"value\":\"300000\"}]}";
		String Person2 = "{\"name\":\"miki\",\"age\":27,\"cars\":[{\"brand\":\"FORD\",\"value\":\"200000\"},{\"brand\":\"BYD\",\"value\":\"150000\"}]}";
		String Person3 = "{\"name\":\"TOM\",\"age\":28,\"cars\":[{\"brand\":\"BENZ\",\"value\":\"400000\"},{\"brand\":\"BYD\",\"value\":\"150000\"}]}";

		int amount = 100000;

		JSONObject jsonObject = JSONObject.fromObject(Person1);
		Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
		// persons 名称需对应各个list变量名
		classMap.put("cars", Car.class);
		Person person1 = null;
		Utils.timeBegin();
		for (int i = 0; i < amount; i++) {
			person1 = (Person) JSONObject.toBean(jsonObject, Person.class, classMap);
		}
		Utils.timeEnd("JSON");
		System.out.println(person1.getCars().get(0).getBrand());

		Person person2 = null;
		Utils.timeBegin();
		for (int i = 0; i < amount; i++) {
			 person2 = JSON.parseObject(Person2, Person.class);
		}
		Utils.timeEnd("Fastjson");
		System.out.println(person2.getCars().get(0).getBrand());

		try {
			Person person3 = null;
			Utils.timeBegin();
			for (int i = 0; i < amount; i++) {
				 person3 = (Person) mapper.readValue(Person3, Person.class);
			}
			Utils.timeEnd("Jackson");
			System.out.println(person3.getCars().get(0).getBrand());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
