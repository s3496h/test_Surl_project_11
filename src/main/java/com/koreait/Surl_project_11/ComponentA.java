package com.koreait.Surl_project_11;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
@RequiredArgsConstructor
public class ComponentA {
    private final ComponentB componentB;
    private final ComponentC componentC;
    @Autowired
    private ComponentD componentD;
    @Autowired
    private ComponentE componentE;
    public String action() {
        return "ComponentA action / " + componentB.getAction();
    }
}