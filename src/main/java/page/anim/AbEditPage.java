package page.anim;

import common.util.anim.AnimCE;

/**
 * 編集画面間で現在のアニメーション選択を引き継ぐための契約。
 */
public interface AbEditPage {

	void setSelection(AnimCE ac);

}
