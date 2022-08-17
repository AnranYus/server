package com.inxtes.nowsyncserver.web.socket;

import com.inxtes.nowsyncserver.service.DatabaseService;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DatabaseHandler {

    static DatabaseService service;

    public DatabaseHandler(DatabaseService service) {
        DatabaseHandler.service = service;
    }

    public static Date getLastUpdateTime() {
        return service.getLastUpdateTime();
    }
}
