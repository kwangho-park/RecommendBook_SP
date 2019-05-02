package dto;

public class SearchStateDto {

	private String bookType;
	private String favorite;
	private String bookLevel;
	
	
	public SearchStateDto() {}
	
	public SearchStateDto(String bookType, String favorite, String bookLevel) {
		super();
		this.bookType = bookType;
		this.favorite = favorite;
		this.bookLevel = bookLevel;
	}
	
	
	public String getBookType() {
		return bookType;
	}
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
	public String getFavorite() {
		return favorite;
	}
	public void setFavorite(String favorite) {
		this.favorite = favorite;
	}
	public String getBookLevel() {
		return bookLevel;
	}
	public void setBookLevel(String bookLevel) {
		this.bookLevel = bookLevel;
	}
	
	
}
