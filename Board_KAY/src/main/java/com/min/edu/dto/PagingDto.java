package com.min.edu.dto;

// 게시글의 전체 갯수를 입력받아 게시글의 갯수(page)와 그룹(group)
public class PagingDto {
	
	private int page; // 현재 페이지 번호
	private int countList; // 페이지당 몇 개(row) 게시글 보여주는지
	private int totalCount; // 전체 글 갯수
	
	private int countPage; // 화면의 몇 개의 page group 
	private int totalPage; // 페이지당 보여줄 게시글의 갯수
	
	private int stagePage; // 현재 그룹의 시작 페이지 번호
	private int endPage; // 현재 그룹의 마지막 페이지 번호
	
	public PagingDto() {
	}

	public PagingDto(int page, int countList, int totalCount, int countPage, int totalPage, int stagePage,int endPage) {
		super();
		this.page = page;
		this.countList = countList;
		this.totalCount = totalCount;
		this.countPage = countPage;
		this.totalPage = totalPage;
		this.stagePage = stagePage;
		this.endPage = endPage;
	}

	public int getPage() {
		return page;
	}
	// 화면에서 입력 받은 page 번호
	// page 번호가 전체 page 숫자보다 크다면 무조건 마지막 page로 세팅되게 함
	public void setPage(int page) {
		if(totalPage < page) {
			page = totalPage;
		}
		this.page = page;
	}

	// countList 페이지에 소속된 row 갯수
	public int getCountList() {
		return countList;
	}

	public void setCountList(int countList) {
		this.countList = countList;
	}

	// 화면에 보여질 페이지 그룹
	public int getCountPage() {
		return countPage;
	}

	public void setCountPage(int countPage) {
		this.countPage = countPage;
	}

	// 페이지의 총 갯수
	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		int totalPageResult = totalCount / countList;
		if(totalCount % countList > 0) {
			totalPageResult ++;
		}
		this.totalPage = totalPageResult;
	}

	// group 시작 번호
	public int getStagePage() {
		return stagePage;
	}

	public void setStagePage(int stagePage) {
		int stagePageResult = ((page-1) / countPage) * countPage + 1;
		this.stagePage = stagePageResult;
	}

	// 화면에 보이는 그룹의 끝 번호
	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		int endPageResult = stagePage + countPage - 1;
		if(endPageResult > totalPage) {
			endPageResult = totalPage;
		}
		this.endPage = endPageResult;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}
