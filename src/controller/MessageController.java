package controller;

import model.Message;
import model.User;
import service.MessageService;
import service.MessageServiceImpl;

/**
 * Created by Alican on 2016-11-21.
 */
public class MessageController {
    private MessageService messageService;

    public MessageController(){
        messageService = new MessageServiceImpl();
    }

    public void sendMessage(Message message){
        messageService.sendMessage(message);
    }

    public void getMessagesFromUser(User user){
        messageService.getMessagesFromUser(user);
    }
}//class
