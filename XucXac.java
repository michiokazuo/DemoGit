package baitapxx;

import java.util.Random;

public class XucXac {
	private int mat20;

	public XucXac(int mat20) {
		this.mat20 = mat20;
	}

	public int gieo() {
		int rs = 0;
		Random random = new Random();
		int rd = random.nextInt(25);

		rs = mat20;
		for (int i = 0; i < 5; i++) {
			if (rd >= 4 * i && rd < 4 * i + 4) {
				rs = i + 1;
				if (rs >= mat20)
					rs++;
			}
		}

		return rs;
	}

}
