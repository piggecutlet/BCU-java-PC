package page.support;

import javax.annotation.Nonnull;

/**
 * アニメーション編集表の選択行を、同一表内の移動または別操作からの挿入へ渡すための基底型。
 *
 * @param <T> DnDで受け渡す行データの型
 */
public abstract class AnimTable<T> extends AbJTable {
	public AnimTable(@Nonnull String[] title) {
		super(title);
	}

	private static final long serialVersionUID = 1L;

	/**
	 * 現在選択されている行を、選択行番号と同じ順序で返す。
	 */
	public abstract T[] getSelected();

	/**
	 * データを指定した挿入位置へ追加する。
	 *
	 * @param dst 挿入前の行境界を表す位置
	 * @param data 挿入するデータ
	 * @param rows 転送元で選択されていた行番号
	 * @return 挿入を反映できた場合は {@code true}
	 */
	public abstract boolean insert(int dst, T[] data, int[] rows);

	/**
	 * 選択行を指定した挿入位置へ移動する。
	 *
	 * @param dst 移動前の行境界を表す位置
	 * @param ori 移動元の行番号
	 * @return 移動を反映できた場合は {@code true}
	 */
	public abstract boolean reorder(int dst, int[] ori);

}
