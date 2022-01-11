package com.vincent.swagger2.demo.notifier;

import com.vincent.swagger2.demo.moudule.LineProperties;
import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.domain.events.InstanceRegisteredEvent;
import de.codecentric.boot.admin.server.domain.events.InstanceStatusChangedEvent;
import de.codecentric.boot.admin.server.notify.AbstractEventNotifier;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class LineNotifier extends AbstractEventNotifier {

    /**
     * 繼承 AbstractEventNotifier 可測試事件發生處理
     * 繼承 AbstractStatusChangeNotifier 針對狀態改變事件發生處理
     */

    @Autowired
    private LineProperties lineProperties;

    protected LineNotifier(InstanceRepository repository) {
        super(repository);
    }

    @Override
    protected Mono<Void> doNotify(InstanceEvent event, Instance instance) {
        if (lineProperties.isEnabled() == false) {
            return Mono.empty();
        }

        // 註冊事件發生
        if (event instanceof InstanceRegisteredEvent) {
            InstanceRegisteredEvent registeredEvent = (InstanceRegisteredEvent) event;
/*
            String serviceName = registeredEvent.getRegistration().getName();
            String serviceUrl = registeredEvent.getRegistration().getServiceUrl();
            String status = instance.getStatusInfo().getStatus();
            String msg = JSONObject.toJSONString(instance.getStatusInfo().getDetails());

            StringBuilder str = new StringBuilder();
            str.append("監控告警 : 【" + serviceName + "】\n");
            str.append("【服務位置】" + serviceUrl + "\n");
            str.append("【狀態】" + status + "\n");
            str.append("【詳情】" + msg);
            return Mono.fromRunnable(() -> {
                new LinePushMessage(lineProperties).sendTextMessage(str.toString());
            });
*/
        }

        // 狀態改變事件
        if (event instanceof InstanceStatusChangedEvent) {
            InstanceStatusChangedEvent statusChangedEvent = (InstanceStatusChangedEvent) event;

            String serviceName = instance.getRegistration().getName();
            String serviceUrl = instance.getRegistration().getServiceUrl();
            String status = statusChangedEvent.getStatusInfo().getStatus();
            String msg = JSONObject.toJSONString(statusChangedEvent.getStatusInfo().getDetails());

            StringBuilder str = new StringBuilder();
            str.append("監控告警 : 【" + serviceName + "】\n");
            str.append("【服務位置】" + serviceUrl + "\n");
            str.append("【狀態】" + status + "\n");
            str.append("【詳情】" + msg);
            return Mono.fromRunnable(() -> {
                new LinePushMessage(lineProperties).sendTextMessage(str.toString());
            });
        }
        return Mono.empty();
    }
}
