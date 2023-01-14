package com.driver.RequestDTO;

public class BlogRequestDto {

    private int userId;

    private String title;
    private String contents;

    public BlogRequestDto(int userId, String title, String contents) {
        this.userId = userId;
        this.title = title;
        this.contents = contents;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
