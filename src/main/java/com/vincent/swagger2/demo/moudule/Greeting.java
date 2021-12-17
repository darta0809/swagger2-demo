package com.vincent.swagger2.demo.moudule;

import lombok.Data;

@Data
public class Greeting {

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    private final long id;
    private final String content;
}
