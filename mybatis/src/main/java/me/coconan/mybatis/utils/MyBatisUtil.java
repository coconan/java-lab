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
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import java.sql.*;
import java.util.Map;
import javax.sql.DataSource;

public class MyBatisUtil {
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static SqlSessionFactory sqlSessionFactory;

	public static SqlSessionFactory buildqlSessionFactory() {
		MySQLContainer mysql = new MySQLContainer("mysql:5.7");
		mysql.start();
		try (Connection conn = DriverManager.getConnection(mysql.getJdbcUrl(), mysql.getUsername(), mysql.getPassword())) {
            try (Statement stmt = conn.createStatement()) {
				stmt.execute("drop table if exists person");
				stmt.execute("create table if not exists person (" +
						"personId int primary key auto_increment," +
						"name varchar(30)" +
						")");
				
				stmt.execute("drop table if exists adddress");
				stmt.execute("create table if not exists address (" +
						"addressId int primary key auto_increment," +
						"personId int," +
						"streetAddress varchar(30)" +
						")");
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		DataSource dataSource = new PooledDataSource(DRIVER, mysql.getJdbcUrl(), mysql.getUsername(), mysql.getPassword());
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

			System.out.println("\nsavePerson:");
			pm.save(new Person(1, "alex"));
			pm.save(new Person(2, "brian"));

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
			System.out.println(brian.getName());
			for (Address address : brian.getAddresses()) {
				System.out.println(address.getStreetAddress());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
