package com.example.Assignment3.Model.Service.Implementation;

import com.example.Assignment3.Model.Model.Writer;
import com.example.Assignment3.Model.Repository.WriterRepository;
import com.example.Assignment3.Model.Service.WriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WriterServiceImpl implements WriterService {

    @Autowired
    private WriterRepository repository;


    @Override
    public void addNewWriter(Writer writer) {
        repository.save(writer);
    }

    @Override
    public List<Writer> getAllWRiters() {
        return repository.findAll();
    }

    @Override
    public void deleteWriter(String username) {
        Writer writer = repository.findByUsername(username);
        repository.deleteById(writer.getId());
    }

    @Override
    public void updateUser(String username) {

    }

    @Override
    public Writer findWriter(String username, String password) {
        return repository.findByUP(username, password);
    }

    @Override
    public Writer findWriter(String username) {
        return repository.findByUsername(username);
    }
}
