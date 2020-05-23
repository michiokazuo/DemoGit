package baitapxx;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrongTai extends Thread {
	private String[] ten = { "Idol Nguyen", "Idol Tu", "Idol Dat", "Idol Quang", "Idol Tuan", "Idol Minh",
			"Idol Cuong" };
	private String[] tt = { "Hic, thua roi.", "Huhuhuhuhuh....", "Doi buon.", "Cay cho.", "Nupakachi...",
			"I will com back..." };

	private List<NguoiChoi> dsNguoiChoi;
	private List<XucXac> dsXucXac;
	private boolean ketThuc;
	private int vong;

	public TrongTai() {
		Random random = new Random();

		// Khoi tao nguoi choi

		dsNguoiChoi = new ArrayList<NguoiChoi>();

		// Nguoi choi that
		int soNCThat = random.nextInt(TroChoi.MAX_NC);
		for (int i = 0; i < soNCThat; i++) {
			int a = random.nextInt(7);

			List<Integer> integers = new ArrayList<Integer>();

			while (true) {
				if (kiemtraTrungLap(integers, a)) {
					integers.add(a);
					break;
				}
			}

			dsNguoiChoi.add(new NguoiChoi(ten[a]));
		}

		// Nguoi choi ao
		for (int i = 0; i < TroChoi.MAX_NC - soNCThat; i++) {
			int a = random.nextInt(6);

			List<Integer> integers = new ArrayList<Integer>();

			while (true) {
				if (kiemtraTrungLap(integers, a)) {
					integers.add(a);
					break;
				}
			}

			dsNguoiChoi.add(new NguoiChoiAo("Boss" + (i + 1), tt[a]));
		}

		// Khoi tao xucxac
		dsXucXac = new ArrayList<XucXac>();
		for (int i = 0; i < TroChoi.MAX_XX; i++) {
			dsXucXac.add(new XucXac((i + 1)));
		}

		// Khoi tao vong
		vong = 1;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			batDau();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Integer> thuTu() {
		Random random = new Random();
		List<Integer> rs = new ArrayList<Integer>();

		rs.add(random.nextInt(TroChoi.MAX_NC));

		while (rs.size() != TroChoi.MAX_NC) {
			int x = random.nextInt(TroChoi.MAX_NC);

			if (kiemtraTrungLap(rs, x)) {
				rs.add(x);
			}
		}

		return rs;
	}

	public void batDau() throws InterruptedException {
		Random random = new Random();
		List<Integer> list = thuTu();
		ketThuc = true;

		while (ketThuc) {
			Thread.sleep(1000);
			System.out.println("Vong tung xx lan " + vong + ": ");

			for (Integer i : list) {
				Thread.sleep(1000);

				int soXX = dsXucXac.get(random.nextInt(TroChoi.MAX_XX)).gieo();
				System.out.print("	" + dsNguoiChoi.get(i).getTen() + " tung duoc " + soXX);

				int diemHT = dsNguoiChoi.get(i).getDiem();
				dsNguoiChoi.get(i).setDiem((diemHT + soXX) <= TroChoi.DIEM_KT ? (diemHT + soXX) : 0);

				System.out.println(". Tong diem: " + dsNguoiChoi.get(i).getDiem());
				if (dsNguoiChoi.get(i).getDiem() == TroChoi.DIEM_KT) {
					ketThuc = false;

					System.out.println("Chuc mung " + dsNguoiChoi.get(i).getTen() + " da thang.");

					for (int j = 0; j < TroChoi.MAX_NC; j++) {
						if (j != i && (dsNguoiChoi.get(j) instanceof NguoiChoiAo)) {
							((NguoiChoiAo) dsNguoiChoi.get(j)).bieuLoTrangThai();
						}
					}

					break;
				}
			}
			vong++;
		}

	}

	public boolean kiemtraTrungLap(List<Integer> list, int a) {
		for (Integer integer : list) {
			if (a == integer) {
				return false;
			}
		}
		return true;

	}

}
