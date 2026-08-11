package page.support;

/**
 * DnD元の要素を、移動前モデル上の挿入位置へ移す契約。
 */
public interface Reorderable {

	/**
	 * 要素を指定位置へ移動する。
	 *
	 * @param fromIndex 移動前の要素位置
	 * @param toIndex 移動前モデル上の挿入位置。末尾への移動では要素数と等しい値
	 */
	void reorder(int fromIndex, int toIndex);

}
