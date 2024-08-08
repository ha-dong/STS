package springrestnew.domain;

public class RestBoard {
	private int rbid;
	private String rbwriter;
	private String rbtitle;
	private String rbcontent;
	private String rbregdate;
	
	

	public RestBoard(int rbid, String rbwriter, String rbtitle, String rbcontent, String rbregdate) {
		super();
		this.rbid = rbid;
		this.rbwriter = rbwriter;
		this.rbtitle = rbtitle;
		this.rbcontent = rbcontent;
		this.rbregdate = rbregdate;
	}

	public RestBoard() {	
	}

	public int getRbid() {
		return rbid;
	}

	public void setRbid(int rbid) {
		this.rbid = rbid;
	}

	public String getRbwriter() {
		return rbwriter;
	}

	public void setRbwriter(String rbwriter) {
		this.rbwriter = rbwriter;
	}

	public String getRbtitle() {
		return rbtitle;
	}

	public void setRbtitle(String rbtitle) {
		this.rbtitle = rbtitle;
	}

	public String getRbcontent() {
		return rbcontent;
	}

	public void setRbcontent(String rbcontent) {
		this.rbcontent = rbcontent;
	}

	public String getRbregdate() {
		return rbregdate;
	}

	public void setRbregdate(String rbregdate) {
		this.rbregdate = rbregdate;
	}

	@Override
	public String toString() {
		return "RestBoard [rbid=" + rbid + ", rbwriter=" + rbwriter + ", rbtitle=" + rbtitle + ", rbcontent="
				+ rbcontent + ", rbregdate=" + rbregdate + "]";
	}

}//class