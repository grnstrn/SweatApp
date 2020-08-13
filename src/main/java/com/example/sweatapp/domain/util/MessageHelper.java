package com.example.sweatapp.domain.util;

import com.example.sweatapp.domain.User;

public abstract class MessageHelper {
    public static String getAuthorName(User author){
        return author != null ? author.getUsername() : "<none>";
    }
}
