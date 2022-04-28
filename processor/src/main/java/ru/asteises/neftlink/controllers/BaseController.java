package ru.asteises.neftlink.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.asteises.neftlink.repositoryes.BaseRepository;
import ru.asteises.neftlink.service.BaseService;

@RestController
@RequestMapping("/base")
public class BaseController {

    private BaseService baseService;

    public BaseController(BaseService baseService) {
        this.baseService = baseService;
    }
}
