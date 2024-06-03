package id.co.bjj.library.general.persistence.dtos;

import java.util.List;

/**
 * Template for variable paging
 * 
 * @author Steve Sentosa, 20 May 2024
 * @param <M> -> model or dto or template
 */
public class Paging<M> {
	private Integer page;
	private Integer pageSize;
	private Long recordTotal;
	private Integer pageTotal;
	private List<M> data;

	public Paging() {
		super();
	}

	public Paging(Integer page, Integer pageSize, Long recordTotal, Integer pageTotal, List<M> data) {
		super();
		this.page = page;
		this.pageSize = pageSize;
		this.recordTotal = recordTotal;
		this.pageTotal = pageTotal;
		this.data = data;
	}

	public Integer getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(Integer pageTotal) {
		this.pageTotal = pageTotal;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getRecordTotal() {
		return recordTotal;
	}

	public void setRecordTotal(Long recordTotal) {
		this.recordTotal = recordTotal;
	}

	public List<M> getData() {
		return data;
	}

	public void setData(List<M> data) {
		this.data = data;
	}
}
