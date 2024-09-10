package com.example.storycraft.model;

import java.util.Date;

public class Notice {
    private Long id;
    private String title;
    private String content;
    private Date createDate;

    // 생성자, getter, setter
    public Notice() {
        this.createDate = new Date();  // 생성 시 자동으로 현재 날짜 설정
    }
	public Notice(Long id, String title, String content, Date createDate) {
			super();
			this.id = id;
			this.title = title;
			this.content = content;
			this.createDate = createDate;
		}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
	@Override
	public String toString() {
		return "Notice [id=" + id + ", title=" + title + ", content=" + content + ", createDate=" + createDate + "]";
	}
    
}
