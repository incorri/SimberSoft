package com.example.chat.model;

import com.example.chat.domain.User;
import lombok.Data;

@Data
public class UserModel {

    private Long id;
    private String name;

    public static UserModel toModel(User user){
        UserModel model = new UserModel();
        model.setId(user.getId());
        model.setName(user.getName());
        return model;
    }

}
