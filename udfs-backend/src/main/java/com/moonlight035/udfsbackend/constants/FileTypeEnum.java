package com.moonlight035.udfsbackend.constants;

public enum FileTypeEnum {
    VIDEO(1,"视频",new String[]{".mp4",".avi"}),
    AUDIO(2,"音频",new String[]{".mp3"}),
    PHOTO(3,"图片",new String[]{".jpg",".png",".jpg",".jpeg",".gif",".png",".bmp"}),
    DOCUMENT(4,"文档",new String[]{".pdf",".doc",".docx",".txt"}),
    ARCHIVE(5,"压缩包",new String[]{".zip",".rar"}),
    OTHER(6,"其它",new String[]{});

    private FileTypeEnum(int code, String content,String[] suffixs){
        this.code=code;
        this.content=content;
        this.suffixs=suffixs;
    }

    private int code;
    private String content;
    private String[] suffixs;

    public String[] getSuffixs() {
        return suffixs;
    }

    public int getCode() {
        return code;
    }

    public String getContent() {
        return content;
    }
}
