package com.moonlight035.udfsbackend.constants;

import lombok.Data;

public enum  FilePartitionEnum {
    GAME(1,"游戏","game"),
    ANIME(2,"动漫","anime"),
    GHOST(3,"鬼畜","ghost");

    private FilePartitionEnum(int code ,String content,String path){
        this.code=code;
        this.content=content;
        this.path=path;
    }

    public static FilePartitionEnum getEnumByCode(int code){
        for (FilePartitionEnum value : FilePartitionEnum.values()) {
            if(value.code==code)
                return value;
        }
        return null;
    }

    private int code;
    private String content;
    private String path;

    public int getCode() {
        return code;
    }

    public String getContent() {
        return content;
    }

    public String getPath() {
        return path;
    }
}
