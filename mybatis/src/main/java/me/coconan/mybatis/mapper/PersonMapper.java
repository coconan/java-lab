package me.coconan.mybatis.mapper;

import me.coconan.mybatis.model.Address;
import me.coconan.mybatis.model.Person;
import me.coconan.mybatis.utils.MyBatisUtil;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;
import java.util.Map;

public interface PersonMapper {

	@Insert("Insert into person(name) values (#{name})")
	public Integer save(Person person);

	@Update("Update person set name= #{name} where personId=#{personId}")
	public void updatePerson(Person person);

	@Delete("Delete from person where personId=#{personId}")
	public void deletePersonById(Integer personId);

	@Select("SELECT person.personId, person.name FROM person WHERE person.personId = #{personId}")
	Person getPerson(Integer personId);

	@Select("Select personId,name from person where personId=#{personId}")
	@Results(value = { @Result(property = "personId", column = "personId"), @Result(property = "name", column = "name"),
			@Result(property = "addresses", javaType = List.class, column = "personId", many = @Many(select = "getAddresses"))

	})
	public Person getPersonById(Integer personId);

	@Select("select addressId,streetAddress,personId from address where personId=#{personId}")
	public Address getAddresses(Integer personId);

	@Select("select * from person ")
	@MapKey("personId")
	Map<Integer, Person> getAllPerson();

	@SelectProvider(type = MyBatisUtil.class, method = "getPersonByName")
	public Person getPersonByName(String name);

	@Select(value = "{ CALL getPersonByProc( #{personId, mode=IN, jdbcType=INTEGER})}")
	@Options(statementType = StatementType.CALLABLE)
	public Person getPersonByProc(Integer personId);

}
