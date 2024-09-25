// Choice.java
package com.example.storycraft.model;

public class Choice {
    private Long id; // 선택지 ID
    private String name; // 선택지 이름
    private String content; // 선택지 내용
    private Long moneyEffect; // 선택 시 돈에 미치는 영향
    private Long hpEffect; // 선택 시 체력에 미치는 영향

    public Choice() {
    }

	public Choice(Long id, String name, String content, Long moneyEffect, Long hpEffect) {
		super();
		this.id = id;
		this.name = name;
		this.content = content;
		this.moneyEffect = moneyEffect;
		this.hpEffect = hpEffect;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getMoneyEffect() {
		return moneyEffect;
	}

	public void setMoneyEffect(Long moneyEffect) {
		this.moneyEffect = moneyEffect;
	}

	public Long getHpEffect() {
		return hpEffect;
	}

	public void setHpEffect(Long hpEffect) {
		this.hpEffect = hpEffect;
	}

	@Override
	public String toString() {
		return "Choice [id=" + id + ", name=" + name + ", content=" + content + ", moneyEffect=" + moneyEffect
				+ ", hpEffect=" + hpEffect + "]";
	}
    
}
