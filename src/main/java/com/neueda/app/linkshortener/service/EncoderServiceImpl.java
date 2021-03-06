package com.neueda.app.linkshortener.service;

import com.neueda.app.linkshortener.util.Uuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EncoderServiceImpl implements EncoderService {
    private Uuid uuid;

    @Autowired
    public void setUuid(Uuid uuid) {
        this.uuid = uuid;
    }

    public String encode(String url) {
        return uuid.randomId();
    }
}
