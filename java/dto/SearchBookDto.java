package dto;

// 검색 게시글 dto // 

public class SearchBookDto {

	private String bookName;
	private String writer;
	private int averageScore;
	
	// Overloading
	public SearchBookDto() {}
	
	// Overloading
	public SearchBookDto(String bookName, String writer, int averageScore) {
		super();
		this.bookName = bookName;
		this.writer = writer;
		this.averageScore = averageScore;
	}
	
	
	
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getAverageScore() {
		return averageScore;
	}
	public void setAverageScore(int averageScore) {
		this.averageScore = averageScore;
	}
	
	
	
} // SearchBookDto END
