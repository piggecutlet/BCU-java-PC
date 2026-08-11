package page.info.filter;

import common.util.unit.Form;
import common.util.unit.Unit;
import page.*;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * ユニット条件と名前検索の結果をソート可能な形態一覧として表示し、呼び出し元へ選択を公開するページ。
 */
public class UnitFindPage extends Page implements SupPage<Unit> {

	private static final long serialVersionUID = 1L;

	private final JBTN back = new JBTN(0, "back");
	private final JTG show = new JTG(0, "showf");
	private final UnitListTable ult = new UnitListTable(this);
	private final JScrollPane jsp = new JScrollPane(ult);
	private final UnitFilterBox ufb;
	private final JTF seatf = new JTF();

	public UnitFindPage(Page p) {
		super(p);

		ufb = UnitFilterBox.getNew(this, null, 0);
		ini();
	}

	public UnitFindPage(Page p, String pack, List<String> parents) {
		super(p);

		ufb = UnitFilterBox.getNew(this, pack, parents);
		ini();
	}

	@Override
	@SuppressWarnings("unchecked")
	public void callBack(Object o) {
		ult.setList((List<Form>) o);
	}

	@Override
	protected JButton getBackButton() {
		return back;
	}

	public Form getForm() {
		if (ult.getSelectedRow() == -1 || ult.getSelectedRow() > ult.list.size() - 1)
			return null;
		return ult.list.get(ult.getSelectedRow());
	}

	public List<Form> getList() {
		return ult.list;
	}

	@Override
	public Unit getSelected() {
		Form f = getForm();
		return f == null ? null : f.unit;
	}

	@Override
	protected void mouseClicked(MouseEvent e) {
		if (e.getSource() != ult)
			return;
		ult.clicked(e.getPoint());
	}

	@Override
	protected void resized(int x, int y) {
		setBounds(0, 0, x, y);

		set(back, x, y, 0, 0, 200, 50);
		set(show, x, y, 250, 0, 200, 50);
		set(seatf, x, y, 550, 0, 1000, 50);
		if (show.isSelected()) {
			int[] siz = ufb.getSizer();
			set(ufb, x, y, 50, 100, siz[0], siz[1]);
			int mx = 0, my = 0;
			if (siz[2] == 0)
				mx = siz[3];
			else
				my = siz[3];
			set(jsp, x, y, 50 + mx, 100 + my, 2200 - mx, 1150 - my);
		} else
			set(jsp, x, y, 50, 100, 2200, 1150);

		ult.setRowHeight(size(x, y, 50));
	}

	private void addListeners() {
		back.addActionListener(arg0 -> changePanel(getFront()));

		show.addActionListener(arg0 -> {
			if (show.isSelected())
				add(ufb);
			else
				remove(ufb);
		});

		seatf.setTypeLnr(x -> setSearch(seatf.getText()));
	}

	private void ini() {
		add(back);
		add(show);
		add(ufb);
		add(jsp);
		add(seatf);
		show.setSelected(true);
		seatf.setHint("Search cat");
		addListeners();
	}

	/**
	 * 名前条件だけを再評価し、型条件で保持済みの候補集合は作り直さない。
	 */
	public void setSearch(String t) {
		if (ufb != null) {
			ufb.name = t;
			ufb.callBack(1);
		}
	}
}
