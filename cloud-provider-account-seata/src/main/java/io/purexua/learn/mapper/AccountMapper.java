package io.purexua.learn.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountMapper {
  void decrease(@Param("userId") Long userId, @Param("money") Long money);
}
