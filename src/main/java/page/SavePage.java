package page;

import common.CommonStatic;

import javax.swing.*;

/**
 * 保存をバックグラウンドスレッドで実行し、完了後に画面群とアプリケーションを終了する終端画面。
 * 生成時に終了処理中フラグを立て、例外時もプロセスを終了するため通常画面へは戻らない。
 */
public class SavePage extends Page {
    private static final long serialVersionUID = 1L;

    private final JLabel save = new JLabel(Page.get(MainLocale.PAGE, "savepro"));
    private boolean[] saveOpts = new boolean[]{true, false};

    protected SavePage() {
        super(null);

        MainFrame.closeClicked = true;

        ini();

        new Thread(this::finishJob).start();
    }

    protected SavePage(boolean[] backup) {
        this();
        saveOpts = backup;
        setVisible(saveOpts[0]);
    }

    @Override
    protected JButton getBackButton() {
        return null;
    }

    @Override
    protected void resized(int x, int y) {
        setBounds(0, 0, x, y);
        set(save, x, y,900, 625, 500, 50);
    }

    private void ini() {
        add(save);
    }

    private void finishJob() {
        try {
            CommonStatic.def.save(saveOpts[0], false);

            setVisible(false);

            MainFrame.exitAll();

            changePanel(null);

            MainFrame.F.dispose();

            System.gc();

            Thread.sleep(5000);

            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();

            System.exit(0);
        }
    }
}
