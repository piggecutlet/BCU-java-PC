package page.battle;

/**
 * 戦闘表示に録画の完了・中断・進捗取得を追加する契約。
 */
public interface BBRecd extends BattleBox {

	void end();

	String info();

	void quit();

}