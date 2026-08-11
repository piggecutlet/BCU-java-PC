package page;

/**
 * 子画面や非同期処理から所有元へ結果または状態変化を通知するコールバック契約。
 * 引数の型と実行スレッドは呼び出し側ごとに異なるため、実装側で対応する必要がある。
 */
public interface RetFunc {

	void callBack(Object o);

}
