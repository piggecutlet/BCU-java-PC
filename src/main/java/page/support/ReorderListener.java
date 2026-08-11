package page.support;

/**
 * {@link ReorderList} の表示モデル変更を外部データへ同期するコールバック。
 * 並べ替えでは {@link #reordering()} の後に表示モデルが変わり、その後で {@link #reordered(int, int)} が呼ばれる。
 */
public interface ReorderListener<T> {

	/**
	 * 複製された要素を外部データへ追加する。
	 *
	 * @return 表示モデルにも追加してよい場合は {@code true}
	 */
	default boolean add(T t) {
		return false;
	}

	/**
	 * 並べ替え後の位置を外部データへ反映する。
	 *
	 * @param ori 移動前の要素位置
	 * @param fin 元要素の削除後に補正された移動先位置
	 */
	void reordered(int ori, int fin);

	/**
	 * 表示モデルを変更する直前に呼ばれる。
	 */
	void reordering();

}
