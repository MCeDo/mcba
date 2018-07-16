package cn.maiba.model;

public class PageBean {

	private int totalPage;		//总页数
	private int currentPage;	//当前页
	private int size = 5; 		//每页记录
	private int count;			//总记录数
	private int start;			//第几条开始查询
	
	public PageBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PageBean(int count, int currentPage, int size) {
		super();
		this.count = count;
		this.currentPage = currentPage;
		this.size = size;
	}

	public int getStart() {
		return this.currentPage*this.size;
	}

	public int getTotalPage() {
		return count%size==0?count/size:count/size+1;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
