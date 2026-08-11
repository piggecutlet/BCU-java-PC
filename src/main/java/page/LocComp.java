package page;

import java.awt.event.MouseListener;

/**
 * {@link Page} への追加時にローカライズ情報を結び付ける UI 部品の契約。
 * 文言の再解決と編集用ポップアップの状態は {@link LocSubComp} が所有する。
 */
interface LocComp extends CustomComp {

	@Override
	default void added(Page p) {
		getLSC().added(p);
	}

	void addMouseListener(MouseListener ml);

	LocSubComp getLSC();

	String getText();

	String getToolTipText();

	default void reLoc() {
		getLSC().reLoc();
	}

	default void setText(int i, String str) {
		getLSC().init(i, str);
	}

	void setText(String t);

	void setToolTipText(String ttt);

}
