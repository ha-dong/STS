package springrest.domain;

import java.sql.Timestamp;

public class RestBoard {
	
	private int rbid;
	private String rbwriter;
	private String rbtitle;
	private String rbcontent;
	private Timestamp rbregdate;
	
	public RestBoard() {
	}

	public RestBoard(int rbid, String rbwriter, String rbtitle, String rbcontent, Timestamp rbregdate) {
		super();
		this.rbid = rbid;
		this.rbwriter = rbwriter;
		this.rbtitle = rbtitle;
		this.rbcontent = rbcontent;
		this.rbregdate = rbregdate;
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

	public Timestamp getRbregdate() {
		return rbregdate;
	}

	public void setRbregdate(Timestamp rbregdate) {
		this.rbregdate = rbregdate;
	}

	@Override
	public String toString() {
		return "RestBoard [rbid=" + rbid + ", rbwriter=" + rbwriter + ", rbtitle=" + rbtitle + ", rbcontent="
				+ rbcontent + ", rbregdate=" + rbregdate + "]";
	}
	
}
