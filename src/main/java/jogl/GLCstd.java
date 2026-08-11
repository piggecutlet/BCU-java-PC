package jogl;

import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import jogl.util.ResManager;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * BCUのOpenGL Canvasに共通する初期設定と資源解放を定義する基底型。
 * GLコンテキスト破棄時に対応する{@link ResManager}を解放する。
 * 通常の画面取得はGL読戻しではなく、AWT {@link Robot}によるCanvas領域のスクリーンキャプチャ。
 */
public abstract class GLCstd extends GLCanvas implements GLEventListener {

	private static final long serialVersionUID = 1L;

	protected GLCstd() {
		super(GLStatic.GLC);
		addGLEventListener(this);
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		ResManager.get(drawable.getGL().getGL2()).dispose();
	}

	public BufferedImage getScreen() {
		try {
			Rectangle r = getBounds();
			Point p = getLocationOnScreen();
			r.x = p.x;
			r.y = p.y;
			return new Robot().createScreenCapture(r);
		} catch (AWTException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void init(GLAutoDrawable drawable) {
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
	}

}
