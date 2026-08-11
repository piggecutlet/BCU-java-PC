package page;

import common.pack.IndexContainer;

/**
 * 識別可能なモデルを選択して呼び出し元へ返す画面の契約。
 * 実装型は同時に {@link Page} であることを前提とする。
 */
public interface SupPage<T extends IndexContainer.Indexable<?, T>> {

	T getSelected();

	default Page getThisPage() {
		return (Page) this;
	}

}
