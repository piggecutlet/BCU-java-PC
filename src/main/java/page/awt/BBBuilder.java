package page.awt;

import common.battle.BattleField;
import common.battle.SBCtrl;
import page.anim.IconBox;
import page.battle.BattleBox;
import page.battle.BattleBox.OuterBox;
import page.view.ViewBox;

/**
 * 描画バックエンドごとの戦闘・アニメーション表示部品を生成する抽象ファクトリ。
 * 起動時に {@link #def} へ選択した実装を設定し、以後の画面はその共有実装を参照する。
 */
public abstract class BBBuilder {

	public static BBBuilder def;

	public abstract BattleBox getCtrl(OuterBox bip, SBCtrl bf);

	public abstract BattleBox getDef(OuterBox bip, BattleField bf);

	public abstract IconBox getIconBox();

	public abstract BattleBox getRply(OuterBox bip, BattleField bf, String str, boolean t);

	public abstract ViewBox getViewBox();

}
