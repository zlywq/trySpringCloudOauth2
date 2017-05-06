package sprssozdemo1.sprssoLibDs2Spr.ibatisMapper;



import java.util.*;

import org.apache.ibatis.annotations.*;

import sprssozdemo1.libCommon.domain.Message;





public interface MessageMapper {
	@Select("SELECT  *  FROM Message WHERE messageId = #{messageId} AND isDeleted=0")
	Message getById(@Param("messageId") long messageId);
	@Select("SELECT  *  FROM Message WHERE messageId = #{messageId}")
	Message getByIdWithDeleted(@Param("messageId") long messageId);
	
	@Select("SELECT  *  FROM Message WHERE messageId = #{messageId} FOR UPDATE")
	Message getByIdForUpdate(@Param("messageId") long messageId);
	

	
	@Select("SELECT  *  FROM Message WHERE userId = #{userId} AND isDeleted=0 ORDER BY createTime DESC")
	List<Message> getByUserId(@Param("userId") long userId);
	
	List<Message> getOnlyDeleted();
	
	@Select("SELECT  *  FROM Message WHERE isDeleted=0 ORDER BY createTime DESC")
	List<Message> getMessages();
	
	
	@Select("SELECT  *  FROM Message WHERE messageId < #{upperMessageIdExclude} AND isDeleted=0 ORDER BY messageId DESC LIMIT #{pageSize} ")
	List<Message> getMessagesPaged(@Param("upperMessageIdExclude") long upperMessageIdExclude, @Param("pageSize") int pageSize);


		

	@Insert("INSERT INTO Message (`messageId`,`userId`,`groupId`,`title`,`content`,`isDeleted`,`createTime`,`updateTime`)"
			+" VALUES (#{messageId},#{userId},#{groupId},#{title},#{content},#{isDeleted},UNIX_TIMESTAMP(),UNIX_TIMESTAMP())"
			)
	int insert(Message message);
	@Insert("INSERT INTO Message (`messageId`,`userId`,`groupId`,,`title`,`content`,`isDeleted`,`createTime`,`updateTime`)"
			+" VALUES (#{messageId},#{userId},#{groupId},#{title},#{content},#{isDeleted},UNIX_TIMESTAMP(),UNIX_TIMESTAMP())"
			)
	int insert2(Map<String, Object> params);//这里看来必须使用一个不同的名字，不然会报错
	

	@Update("UPDATE Message SET `title`=#{title},`content`=#{content},`isDeleted`=#{isDeleted}, `updateTime`=UNIX_TIMESTAMP() WHERE `messageId`=#{messageId}")
	int update(Message message);
	
	@Delete("DELETE FROM Message WHERE messageId = #{messageId}")
	int delete(@Param("messageId") long messageId);
	
	
	@Delete("UPDATE Message SET `isDeleted`=1 WHERE messageId = #{messageId}")
	int deleteLogic(@Param("messageId") long messageId);


}
