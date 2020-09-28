package com.example.client.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Chat {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)

    private Long chat_id;
    private String chat_pertanyaan;
    private String chat_jawaban;
    private Long user_id;

    public Chat() {
	}

	protected Chat(String chat_pertanyaan,String chat_jawaban,Long user_id) {
		super();
		this.chat_pertanyaan = chat_pertanyaan;
		this.chat_jawaban = chat_jawaban;
		this.user_id = user_id;
    }

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getChat_id() {
		return chat_id;
	}
	public void setChat_id(Long chat_id) {
		this.chat_id = chat_id;
    }


    public String getChat_pertanyaan() {
		return chat_pertanyaan;
	}
	public void setChat_pertanyaan(String chat_pertanyaan) {
		this.chat_pertanyaan = chat_pertanyaan;
    }



    public String getChat_jawaban() {
		return chat_jawaban;
	}
	public void setChat_jawaban(String chat_jawaban) {
		this.chat_jawaban = chat_jawaban;
    }

    public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
    }
}