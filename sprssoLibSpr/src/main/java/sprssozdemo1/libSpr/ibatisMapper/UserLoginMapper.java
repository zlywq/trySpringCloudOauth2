package sprssozdemo1.libSpr.ibatisMapper;

import java.util.*;
import org.apache.ibatis.annotations.*;

import sprssozdemo1.libCommon.domain.UserLogin;




public interface UserLoginMapper {

	@Select("SELECT  `username`,`password`,`enabled`,`createTime`,`updateTime` FROM users"+" WHERE username = #{username}")
	UserLogin getByUsername(@Param("username") String username);
		
	@Select("SELECT  *  FROM users")
	List<UserLogin> getUserLogins();

	@Insert("INSERT INTO users (`username`,`password`,`enabled`,`createTime`,`updateTime`) VALUES (#{username}, #{password}, 1, UNIX_TIMESTAMP(), UNIX_TIMESTAMP())")
	int insertUserLogin(UserLogin user);

	@Insert("INSERT INTO users (username, password, enabled,`createTime`,`updateTime`) VALUES (#{username}, #{password}, 1, UNIX_TIMESTAMP(), UNIX_TIMESTAMP())")
	int insertUserLogin2(@Param("username") String username, @Param("password") String password);
  
	@Insert("INSERT INTO users (username, password, enabled,`createTime`,`updateTime`) VALUES (#{username}, #{password}, 1, UNIX_TIMESTAMP(), UNIX_TIMESTAMP())")
	int insertUserLogin3(Map<String, Object> params);
	
	@Update("UPDATE users SET password = #{password}, `updateTime`=UNIX_TIMESTAMP() WHERE username = #{username}")
	int updateUserLoginPassword(UserLogin user);
	
	@Select("DELETE FROM Users WHERE WHERE username = #{username}")
	int deleteByUsername(@Param("username") String username);

}
