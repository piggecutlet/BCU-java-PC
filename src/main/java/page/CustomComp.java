package page;

/**
 * {@link Page#add(java.awt.Component)} への追加時に所有画面を受け取る部品の契約。
 * 通常の Swing コンテナ追加では通知されず、再追加時は複数回呼ばれ得る。
 */
public interface CustomComp {

	default void added(Page page) {
	}

}
