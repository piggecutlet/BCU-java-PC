package utilpc.awt;

import common.system.fake.FakeTransform;

import java.awt.geom.AffineTransform;

/**
 * AWTの{@link AffineTransform}を{@link FakeTransform}として受け渡すためのラッパー。
 */
public class FTAT implements FakeTransform {

	protected final AffineTransform t;

	protected FTAT(AffineTransform at) {
		t = at;
	}

	@Override
	public AffineTransform getAT() {
		return t;
	}

}
