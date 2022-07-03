package com.inxtes.nowsyncserver.web;

import com.google.gson.Gson;
import com.inxtes.nowsyncserver.bean.ReturnMessage;
import com.inxtes.nowsyncserver.model.Contacts;
import com.inxtes.nowsyncserver.service.ContactsService;
import com.inxtes.nowsyncserver.service.MessageService;
import com.inxtes.nowsyncserver.utils.DataProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ApiController {

    private final ContactsService contactsService;
    private final MessageService messageService;
    private final DataProcess dataProcess;
    private final Gson gson = new Gson();
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public ApiController(ContactsService contactsService, MessageService messageService, DataProcess dataProcess) {
        this.contactsService = contactsService;
        this.messageService = messageService;
        this.dataProcess = dataProcess;
    }

    /**
     * 接收json化的phones列表，处理后储存存入数据库
     *
     * @param contacts 客户端推送的数据
     */
    @RequestMapping(value = "/api/pushContacts", method = RequestMethod.POST)
    public void pushContacts(@RequestParam("contacts") String contacts, HttpServletResponse response) throws IOException {


        logger.error(contacts);
        // 未插入的数据
        List<Contacts> unInsert = new ArrayList<>();

        try {
            unInsert = contactsService.setContacts(DataProcess.JsonToObj(contacts, Contacts.class));

        } catch (IOException e) {
            try {
                formatReturnMsg(406, e.getMessage(), "contacts", response);
                return;
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setStatus(500);
            try {
                response.getWriter().println("数据库储存发生错误");
                return;
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        // 存在未储存的内容
        logger.info("Uninsert list size: " + unInsert.size());
        if (unInsert.size() != 0) {
            formatReturnMsg(202, new Gson().toJson(unInsert), "contacts", response);
            return;
        }
        response.setStatus(200);
        try {
            formatReturnMsg(200, null, "contacts", response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * @return 以HashMap形式返回数据库所有联系人信息   Key:电话号码    Value:Contacts对象
     */
    @RequestMapping(value = "/api/pullContacts", method = RequestMethod.GET)
    @ResponseBody
    public String pullAllContacts() {
        Map<String, Contacts> map = contactsService.getAllContacts();

        return gson.toJson(map);
    }

    /**
     * 获取所有短信
     *
     * @return 未插入的短信
     */
    @RequestMapping(value = "/api/pullMessage", method = RequestMethod.GET)
    @ResponseBody
    public String pullAllMsg() {
        return gson.toJson(messageService.getAllMsg());
    }

    // 格式化返回信息
    private void formatReturnMsg(Integer code, Object obj, String type, HttpServletResponse response) throws IOException {
        Gson gson = new Gson();
        response.setStatus(code);
        response.getWriter().println(new ReturnMessage(code, gson.toJson(obj), type));
    }
}
