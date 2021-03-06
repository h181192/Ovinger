package no.hib.dat100.prosjekt.utsyn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import no.hib.dat100.prosjekt.kontroll.Kontroll;
import no.hib.dat100.prosjekt.modell.Kort;

public class SydButtonListener implements ActionListener {

	private Utsyn utsyn;

	public static String TREKK_FORBI_STR = "Du m� trekke kort eller melde forbi.";

	public SydButtonListener(Utsyn utsyn) {
		this.utsyn = utsyn;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		utsyn.getLogger().info("");

		Kontroll kontroll = utsyn.getKontroll();

		if (kontroll.erSydTur()) {
			Kort kort = kontroll.foreslaaSyd();

			if (kort != null) {
				utsyn.fremhevKort(kort);
			} else {
				utsyn.visMelding(TREKK_FORBI_STR);
			}
		} else {
			utsyn.spillLyd();
		}
	}

}
