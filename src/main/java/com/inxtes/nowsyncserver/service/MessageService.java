package com.inxtes.nowsyncserver.service;

import com.inxtes.nowsyncserver.model.Message;

import java.util.List;

public interface MessageService {
    List<Message> getAllMsg();

    List<Message> setMsg(List<Message> list);

}
