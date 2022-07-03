package com.inxtes.nowsyncserver.mapper;

import com.inxtes.nowsyncserver.model.Contacts;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Mapper
public interface ContactsMapper {

    Integer insertContacts(Contacts phones);

    Contacts selectContactsByNumber(String number);

    /**
     * @return key为PhoneNumber，value为Contacts对象
     */
    @MapKey("phoneNumber")
    Map<String, Contacts> selectAllContacts();
}
