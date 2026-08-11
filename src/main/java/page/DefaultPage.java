package page;

import javax.swing.*;

/**
 * 戻る操作だけを備えた最小の画面実装。
 * 戻る操作では前画面を再生成せず、保持している {@link #front} へ遷移する。
 */
public class DefaultPage extends Page {

	private static final long serialVersionUID = 1L;

	private final JBTN back = new JBTN(0, "back");

	protected DefaultPage(Page p) {
		super(p);

		ini();
	}

	@Override
	protected JButton getBackButton() {
		return back;
	}

	@Override
	protected void resized(int x, int y) {
		setBounds(0, 0, x, y);
		set(back, x, y, 0, 0, 200, 50);
	}

	private void addListeners() {
		back.setLnr(e -> changePanel(getFront()));
	}

	private void ini() {
		add(back);
		addListeners();
	}

}
