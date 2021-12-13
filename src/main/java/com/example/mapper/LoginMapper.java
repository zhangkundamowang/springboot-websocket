package com.example.mapper;

import com.example.po.Staff;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
    Staff getpwdbyname(String name);

    Staff getnamebyid(long id);
}
