package me.coconan.mybatis.utils;

import me.coconan.mybatis.mapper.AddressMapper;
import me.coconan.mybatis.mapper.PersonMapper;
import me.coconan.mybatis.model.Address;
import me.coconan.mybatis.model.Person;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.util.Map;
import javax.sql.DataSource;

public class MyBatisUtil {
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/test?useSSL=false";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "hello123";
	private static SqlSessionFactory sqlSessionFactory;

	public static SqlSessionFactory buildqlSessionFactory() {
		DataSource dataSource = new PooledDataSource(DRIVER, URL, USERNAME, PASSWORD);
		Environment environment = new Environment("Development", new JdbcTransactionFactory(), dataSource);
		Configuration configuration = new Configuration(environment);
		configuration.addMapper(PersonMapper.class);
		configuration.addMapper(AddressMapper.class);
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(configuration);
		return factory;

	}

	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public static String getPersonByName() {
		return new SQL() {
			{
				SELECT("*");
				FROM("person");
				WHERE("name like #{name} || '%'");
			}
		}.toString();
	}

	public static void main(String[] args) {
		System.out.println("hello, mybatis");
		sqlSessionFactory = buildqlSessionFactory();
		System.out.println(sqlSessionFactory);

		try (SqlSession session = sqlSessionFactory.openSession()) {
			PersonMapper pm = session.getMapper(PersonMapper.class);

			System.out.println("\ngetAllPerson:");
			Map<Integer, Person> personMap = pm.getAllPerson();
			if (personMap.size() >= 2) {
				System.out.println(personMap.get(1).getName());
				System.out.println(personMap.get(2).getName());
			}

			System.out.println("\ndynamic sql:");
			System.out.println(getPersonByName());
			Person person = pm.getPersonByName("brian");
			System.out.println(person.getName());

			System.out.println("\ngetPersonById:");
			Person brian = pm.getPersonById(1);
			System.out.println(brian.getAddresses());
			for (Address address : brian.getAddresses()) {
				System.out.println(address.getStreetAddress());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
