package com.example.Blog.Application.repositories.readOnlyRepo.implClass;

import com.example.Blog.Application.repositories.readOnlyRepo.PostRepo;
import lombok.AllArgsConstructor;


public class GetDataImpl implements PostRepo.GetData {

    public GetDataImpl(int Id,String about, String email, String userName,String password, String content,String date){

    }
    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getAbout() {
        return null;
    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public String getUserName() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getContent() {
        return null;
    }

    @Override
    public String getDate() {
        return null;
    }
}
