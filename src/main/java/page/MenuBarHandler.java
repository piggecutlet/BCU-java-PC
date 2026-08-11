package page;

import io.BCUWriter;
import main.MainBCU;
import main.Opts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * アプリケーション共通のメニューバーを一度構築し、保存と現在画面の戻る操作へ接続する。
 * 項目の有効状態は画面遷移と終了処理から共有インスタンスに対して更新される。
 */
public class MenuBarHandler {
    private static final JMenuBar bar = new JMenuBar();

    private static final List<JMenuItem> fileItems = new ArrayList<>();

    public static JMenuBar getBar() {
        return bar;
    }

    public static void initialize() {
        setFileItems();
        MainFrame.F.setJMenuBar(bar);
    }

    private static void setFileItems() {
        JMenu menu = new JMenu("File");
        JMenu history = new JMenu("History");

        bar.add(menu);
        bar.add(history);

        int shortcut = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

        JMenuItem save = new JMenuItem("Save All");
        save.setAccelerator(KeyStroke.getKeyStroke('S', shortcut));
        save.addActionListener(e -> {
            BCUWriter.writeData();
            Opts.pop("Successfully saved data.", "Save Confirmation");
        });

        JMenuItem back = new JMenuItem("Go Back");
        back.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
        back.addActionListener(e -> {
            if(MainFrame.getPanel().getBackButton() != null) {
                MainFrame.getPanel().getBackButton().doClick();
            }
        });

        save.setEnabled(false);
        back.setEnabled(false);

        menu.add(save);
        history.add(back);

        fileItems.add(save);
        fileItems.add(back);
    }

    public static JMenuItem getFileItem(String n) {
        for (JMenuItem i : fileItems) {
            if (i.getText().equals(n))
                return i;
        }

        if(MainBCU.loaded) {
            System.out.println("Missing menu item: " + n);
        }

        return null;
    }
}
