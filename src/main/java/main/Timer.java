package main;

import page.MainFrame;

import javax.swing.*;

/**
 * 一定周期で画面全体の更新をSwing EDTへ投入するフレームタイマー。
 * 通常は次の周期へ進む前にEDT側の更新完了を待つが、{@link #manualTick()}は完了前でも待機を解除する。
 * manualTickを使わない状態でEDTが停止すると、このスレッドも待機し続ける。
 */
public strictfp class Timer extends Thread {

	public static int p = 33;
	public static int inter = 0;
	protected static boolean state;

	public static void manualTick() {
		state = true; // 後続ポップアップ用。待機解除が不可欠な場合以外は使用しない
	}

	@Override
	public void run() {
		while (true) {
			long m = System.currentTimeMillis();

			state = false;

			Inv thr;

			SwingUtilities.invokeLater(thr = new Inv());

			try {
				boolean end = false;

				while (!end) {
					synchronized (thr) {
						end = state;
					}
					if (!end)
						sleep(1);
				}

				thr.join();

				int delay = (int) (System.currentTimeMillis() - m);

				inter = (inter * 9 + 100 * delay / p) / 10;

				int sle = delay >= p ? 1 : p - delay;
				sleep(sle);
			} catch (InterruptedException e) {
				return;
			}
		}
	}

}

/**
 * {@link SwingUtilities#invokeLater(Runnable)} に渡され、EDT上で1回分の画面更新と完了通知を行う。
 * Threadとして開始する型ではない。
 */
strictfp class Inv extends Thread {

	@Override
	public void run() {
		MainFrame.timer(-1);
		synchronized (this) {
			Timer.state = true;
		}

	}

}