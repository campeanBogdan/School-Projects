package com.example.Assignment3.Model.Service;

import com.example.Assignment3.Model.Model.Writer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WriterService {

    void addNewWriter(Writer writer);
    List<Writer> getAllWRiters();
    void deleteWriter(String username);
    void updateUser(String username);
    Writer findWriter(String username, String password);
    Writer findWriter(String username);

}
