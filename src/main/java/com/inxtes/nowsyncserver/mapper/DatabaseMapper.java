package com.inxtes.nowsyncserver.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

@Mapper
public interface DatabaseMapper {
    Date selectLastUpdateTime();
}
