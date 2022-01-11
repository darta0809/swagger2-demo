package com.vincent.swagger2.demo.notifier;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import com.vincent.swagger2.demo.moudule.LineProperties;
import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Component;

@Component
public class LinePushMessage {

    private LineProperties lineProperties;

    public LinePushMessage(LineProperties lineProperties) {
        this.lineProperties = lineProperties;
    }

    public void sendTextMessage(String message) {
        LineMessagingClient client = LineMessagingClient.builder(lineProperties.getChannelToken()).build();
        TextMessage textMessage = new TextMessage(message);
        PushMessage pushMessage = new PushMessage(lineProperties.getTo(), textMessage);
        BotApiResponse botApiResponse;

        try {
            botApiResponse = client.pushMessage(pushMessage).get();
            System.out.println(botApiResponse.getMessage());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
