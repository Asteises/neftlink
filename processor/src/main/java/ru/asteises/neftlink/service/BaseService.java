package ru.asteises.neftlink.service;

import org.springframework.stereotype.Service;
import ru.asteises.neftlink.repositoryes.BaseRepository;

@Service
public class BaseService {
    private final BaseRepository baseRepository;

    public BaseService(BaseRepository baseRepository) {
        this.baseRepository = baseRepository;
    }
}
