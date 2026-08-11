package page.basis;

import page.Page;
import page.info.UnitInfoPage;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * {@link LineUpBox} へのグローバル入力配送と周期再描画を共通化する画面基底。
 * 数字キーとマウス操作は編成モデルを変更し得るため、入力欄を持つ派生画面は必要に応じて配送を抑止する。
 */
public abstract class LubCont extends Page {

	private static final long serialVersionUID = 1L;

	protected LubCont(Page p) {
		super(p);
	}

	protected abstract LineUpBox getLub();

	@Override
	protected void keyTyped(KeyEvent e) {
		if (Character.isDigit(e.getKeyChar())) {
			int i = Integer.parseInt(String.valueOf(e.getKeyChar()));
			if (i == 0)
				i = 9;
			else
				i--;
			getLub().setPos(i);
		} else if (e.getKeyChar() == 'd') {
			getLub().setPos(10);
		}
	}

	@Override
	protected void mouseClicked(MouseEvent e) {
		if (e.getSource() == getLub())
			if (e.getButton() == MouseEvent.BUTTON1)
				getLub().click(e.getPoint());
			else if (getLub().sf != null)
				changePanel(new UnitInfoPage(this, getLub().sf.unit, getLub().blu.lu.getLv(getLub().sf)));
	}

	@Override
	protected void mouseDragged(MouseEvent e) {
		if (e.getSource() == getLub())
			getLub().drag(e.getPoint());
	}

	@Override
	protected void mousePressed(MouseEvent e) {
		if (e.getSource() == getLub())
			getLub().press(e.getPoint());
	}

	@Override
	protected void mouseReleased(MouseEvent e) {
		if (e.getSource() == getLub())
			getLub().release();
	}

	@Override
    public synchronized void onTimer(int t) {
		super.onTimer(t);

		getLub().paint(getLub().getGraphics());
	}

}
