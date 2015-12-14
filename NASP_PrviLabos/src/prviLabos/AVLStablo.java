package prviLabos;

public class AVLStablo {
	
	public CvorAVLStabla korijen;
	
	public AVLStablo(CvorAVLStabla korijen) {
		
		this.korijen = korijen;
		
	}
	
	public void DodajCvor(CvorAVLStabla cvor, CvorAVLStabla roditeljskiCvor) {
		
		if(cvor.vrijednost < roditeljskiCvor.vrijednost) {
			if(roditeljskiCvor.lijevoDijete == null) {
				cvor.roditelj = roditeljskiCvor;
				cvor.razina = roditeljskiCvor.razina + 1;
				roditeljskiCvor.lijevoDijete = cvor;
			} else {
				DodajCvor(cvor, roditeljskiCvor.lijevoDijete);
			}
		} else if (cvor.vrijednost > roditeljskiCvor.vrijednost) {
			if(roditeljskiCvor.desnoDijete == null) {
				cvor.roditelj = roditeljskiCvor;
				cvor.razina = roditeljskiCvor.razina + 1;
				roditeljskiCvor.desnoDijete = cvor;
			} else {
				DodajCvor(cvor, roditeljskiCvor.desnoDijete);
			}
		} else {
			System.out.println("Zadani cvor vec postoji!");	
		}
		if(cvor.roditelj != null) {
			BalansirajStabloNakonDodavanja(cvor.roditelj);
		}
		
	}
	
	public void BalansirajStabloNakonDodavanja(CvorAVLStabla cvor) {
		
		if(!cvor.equals(null)) {
			cvor.IzracunFaktoraRavnoteze();
			if(cvor.faktorRavnoteze == 0) {
				return;
			} else if(cvor.faktorRavnoteze == 1 || cvor.faktorRavnoteze == -1) { 
				if(cvor.roditelj != null) {
					BalansirajStabloNakonDodavanja(cvor.roditelj);
				}
			} else if(cvor.faktorRavnoteze == 2) {
				if(cvor.desnoDijete.faktorRavnoteze == 1) {
					System.out.println("Balansiram1 " + cvor.vrijednost + "!");
					CvorAVLStabla P = cvor;
					CvorAVLStabla Q = cvor.desnoDijete;						///rotacija Q oko P///
					P.desnoDijete = Q.lijevoDijete;							
					if(Q.lijevoDijete != null) {
						Q.lijevoDijete.roditelj = P;
					}
					Q.lijevoDijete = P;
					if(P.roditelj != null) {
						if(P.roditelj.lijevoDijete.equals(P)) {
							P.roditelj.lijevoDijete = Q;
							Q.roditelj = P.roditelj;
						} else {
							P.roditelj.desnoDijete = Q;
							Q.roditelj = P.roditelj;
						}
					} else {
						Q.roditelj = null;
						this.korijen = Q;
					}
					if(P != null) {
						P.roditelj = Q;
					}
					return;
				} else if(cvor.desnoDijete.faktorRavnoteze == -1) {	
					System.out.println("Balansiram2 " + cvor.vrijednost + "!");
					CvorAVLStabla P = cvor;
					CvorAVLStabla Q = cvor.desnoDijete;
					CvorAVLStabla R = Q.lijevoDijete;
					Q.lijevoDijete = R.desnoDijete;							// rotacija R oko Q //
					if(R.desnoDijete != null) {
						R.desnoDijete.roditelj = Q;
					}
					R.desnoDijete = Q;
					if(Q != null) {
						Q.roditelj = R;
					}
					P.desnoDijete = R;
					if(R != null) {
						R.roditelj = P;
					}
					P.desnoDijete = R.lijevoDijete;							// rotacija R oko P
					if(R.lijevoDijete != null) {
						R.lijevoDijete.roditelj = P;
					}
					R.lijevoDijete = P;
					if(P.roditelj != null) {
						if(P.roditelj.lijevoDijete.equals(P)) {
							P.roditelj.lijevoDijete = R;
							R.roditelj = P.roditelj;
						} else {
							P.roditelj.desnoDijete = R;
							R.roditelj = P.roditelj;
						}
					} else {
						R.roditelj = null;
						this.korijen = R;
					}
					if(P != null) {
						P.roditelj = R;
					}
					return;
				} else {
					System.out.println("Desilo se nesto nepredvidjeno!");
				}
			} else if(cvor.faktorRavnoteze == -2) {
				if(cvor.lijevoDijete.faktorRavnoteze == -1) {
					System.out.println("Balansiram3 " + cvor.vrijednost + "!");
					CvorAVLStabla P = cvor;
					CvorAVLStabla Q = cvor.lijevoDijete;					
					P.lijevoDijete = Q.desnoDijete;							///rotacija Q oko P
					if(Q.desnoDijete != null) {
						Q.desnoDijete.roditelj = P;	
					}
					Q.desnoDijete = P;
					if(P.roditelj != null) {
						if(P.roditelj.lijevoDijete.equals(P)) {
							P.roditelj.lijevoDijete = Q;
							Q.roditelj = P.roditelj;
						} else {
							P.roditelj.desnoDijete = Q;
							Q.roditelj = P.roditelj;
						}
					} else {
						Q.roditelj = null;
						this.korijen = Q;
					}
					if(P != null) {
						P.roditelj = Q;
					}
					return;
				} else if(cvor.lijevoDijete.faktorRavnoteze == 1) {
					System.out.println("Balansiram4 " + cvor.vrijednost + "!");
					CvorAVLStabla P = cvor;
					CvorAVLStabla Q = cvor.lijevoDijete;
					CvorAVLStabla R = Q.desnoDijete;
					Q.desnoDijete = R.lijevoDijete;
					if(R.lijevoDijete != null) {
						R.lijevoDijete.roditelj = Q;
					}
					R.lijevoDijete = Q;										// rotacija R oko Q //
					if(Q != null)  {
						Q.roditelj = R;
					}
					P.lijevoDijete = R;
					if(R != null) {
						R.roditelj = P;
					}
					P.lijevoDijete = R.desnoDijete;							// rotacija R oko P //
					R.desnoDijete = P;
					if(P.roditelj != null) {
						if(P.roditelj.lijevoDijete.equals(P)) {
							P.roditelj.lijevoDijete = R;
							R.roditelj = P.roditelj;
						} else {
							P.roditelj.desnoDijete = R;
							R.roditelj = P.roditelj;
						}
					} else {
						R.roditelj = null;
						this.korijen = R;
					}
					if(P != null) {
						P.roditelj = R;
					}
					return;
				} else {
					System.out.println("Desilo se nesto nepredvidjeno!");
				}
			} else {
				System.out.println("Faktor ravnoteze cvora " + cvor.vrijednost + " je pogresan!");
			}
		}
	}
}
