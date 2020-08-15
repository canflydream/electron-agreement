package org.cfd.electron_agreement.auth.controller;

import org.cfd.electron_agreement.auth.service.IElementDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/data")
public class ElementDataApi {
    private final IElementDataService elementDataService;

    @Autowired
    public ElementDataApi(IElementDataService elementDataService) {
        this.elementDataService = elementDataService;
    }

    @PostMapping("/add")
    public Object add(@RequestBody String body) {

        return true;
    }
}
