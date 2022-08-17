package com.inxtes.nowsyncserver.service.impl;

import com.inxtes.nowsyncserver.mapper.DatabaseMapper;
import com.inxtes.nowsyncserver.service.DatabaseService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DatabaseImpl implements DatabaseService {

    final
    DatabaseMapper mapper;

    public DatabaseImpl(DatabaseMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Date getLastUpdateTime() {
        return mapper.selectLastUpdateTime();
    }
}
