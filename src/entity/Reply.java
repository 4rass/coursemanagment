package entity;

public class Reply {

	public static final Reply DEFAULT_REPLY = new Reply();
	
	public static final String OK_STR = "OK";
	public static final String FAIL_STR = "fail ";
	public static final int FAIL_ID = -1; // if the id '-1' it will fail
	public static final int OK_ID = 0; //if the id '0' its ok
	public static final Reply OK_DELETE = new Reply();
	
	
	private int id = OK_ID;
	private String msg = OK_STR;
	
	
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
