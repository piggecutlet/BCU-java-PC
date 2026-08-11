package jogl;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import main.MainBCU;

/**
 * JOGL描画で共有するGL2プロファイル、Canvas機能設定、実装切替フラグを保持する。
 * クラス初期化時にGLプロファイルを取得するため、OpenGLを使用しない起動経路では参照しないこと。
 */
public class GLStatic {

	public static final boolean MIP = false;
	public static final GLProfile GLP;
	public static final GLCapabilities GLC;

	public static boolean ALWAYS_GLIMG = true;
	public static boolean GLTEST = !MainBCU.WRITE;
	public static boolean JOGL_SHADER = true;
	public static boolean ALL_BIMG = true;

	static {
		GLP = GLProfile.get(GLProfile.GL2);
		GLC = new GLCapabilities(GLP);

	}

}
