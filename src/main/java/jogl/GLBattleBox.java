package jogl;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import common.battle.BattleField;
import common.battle.SBCtrl;
import common.util.unit.Enemy;
import common.util.unit.Form;
import jogl.util.GLGraphics;
import page.battle.BBCtrl;
import page.battle.BattleBox;

/**
 * 戦闘シミュレーションの描画要求をJOGL Canvas上の{@link GLGraphics}へ接続する。
 * 各display呼出しで描画アダプタを作成・破棄し、画面終了時の{@link #releaseData()}では
 * 描画資源ではなく戦闘シミュレーション側の保持データを解放する。
 */
public class GLBattleBox extends GLCstd implements BattleBox, GLEventListener {

	private static final long serialVersionUID = 1L;

	protected final BBPainter bbp;

	public GLBattleBox(OuterBox bip, BattleField bf, int type) {
		bbp = type == 0 ? new BBPainter(bip, bf, this) : new BBCtrl(bip, (SBCtrl) bf, this);
		for (Form[] fs : bbp.bf.sb.b.lu.fs)
			for (Form f : fs)
				if (f != null)
					f.anim.check();
		for (Enemy e : bbp.bf.sb.st.data.getAllEnemy())
			e.anim.check();
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		GLGraphics g = new GLGraphics(drawable.getGL().getGL2(), getWidth(), getHeight());
		bbp.draw(g);
		g.dispose();
		gl.glFlush();
	}

	@Override
	public BBPainter getPainter() {
		return bbp;
	}

	@Override
	public void paint() {
		display();
	}

	@Override
	public void reset() {
	}

	@Override
	public void releaseData() {
		bbp.bf.sb.release();
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		bbp.reset();
	}

}
