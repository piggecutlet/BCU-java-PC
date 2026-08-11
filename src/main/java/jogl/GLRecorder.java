package jogl;

import page.RetFunc;
import page.awt.RecdThread;

import java.awt.image.BufferedImage;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * JOGL Canvasの連続キャプチャをエンコード用キューへ渡す録画抽象。
 * {@link #end()}は入力終了、{@link #quit()}は中断を通知する別契約であり、
 * 実際のエンコードと完了通知は{@link RecdThread}が非同期に行う。
 */
public abstract class GLRecorder {

	public static GLRecorder getIns(GLCstd scr, String path, int type, RetFunc ob) {
		return new GLRecdBImg(scr, path, type, ob);
	}

	protected final GLCstd screen;

	protected GLRecorder(GLCstd scr) {
		screen = scr;
	}

	public abstract void end();

	public abstract void quit();

	public abstract int remain();

	public abstract void start();

	public abstract void update();

}

/**
 * AWTスクリーンキャプチャをフレームキューへ積み、RecdThreadへ供給する録画実装。
 */
class GLRecdBImg extends GLRecorder {

	private final Queue<BufferedImage> qb;
	private final RecdThread th;

	public GLRecdBImg(GLCstd scr, Queue<BufferedImage> lb, RecdThread rt) {
		super(scr);
		qb = lb;
		th = rt;
	}

	protected GLRecdBImg(GLCstd scr, String path, int type, RetFunc ob) {
		super(scr);
		qb = new ArrayDeque<>();
		th = RecdThread.getIns(ob, qb, path, type);
	}

	@Override
	public void end() {
		synchronized (th) {
			th.end = true;
		}
	}

	@Override
	public void quit() {
		synchronized (th) {
			th.quit = true;
		}
	}

	@Override
	public int remain() {
		int size;
		synchronized (qb) {
			size = qb.size();
		}
		return size;
	}

	@Override
	public void start() {
		th.start();
	}

	@Override
	public void update() {
		synchronized (qb) {
			qb.add(screen.getScreen());
		}
	}

}