package id.co.bjj.library.general.core.exceptions;

public class MultipleError extends Error {
	private static final long serialVersionUID = 3458547219249874348L;
	
	private Integer row;
	
	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}
}
