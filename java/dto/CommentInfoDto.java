package dto;

public class CommentInfoDto {

	private String userName;
	private String pw;
	private String content;
	
	public CommentInfoDto(String userName, String pw, String content) {
		super();
		this.userName = userName;
		this.pw = pw;
		this.content = content;
	}
	
	
	public String getName() {
		return userName;
	}
	public void setName(String userName) {
		this.userName = userName;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
} // CommentDto END
