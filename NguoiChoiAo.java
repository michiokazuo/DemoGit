package baitapxx;

public class NguoiChoiAo extends NguoiChoi {
	private String trangThai;

	public NguoiChoiAo(String ten, String trangThai) {
		super(ten);
		this.trangThai = trangThai;
		// TODO Auto-generated constructor stub
	}

	public void bieuLoTrangThai() {
		System.out.println(getTen() + " " + trangThai);
	}

}
