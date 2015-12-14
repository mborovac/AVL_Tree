package prviLabos;

public class CvorAVLStabla {
	
	public int vrijednost;
	public CvorAVLStabla lijevoDijete = null;
	public CvorAVLStabla desnoDijete = null;
	public CvorAVLStabla roditelj;
	public int dubinaLijevogPodstabla;
	public int dubinaDesnogPodstabla;
	public int faktorRavnoteze;
	public int razina;

	public CvorAVLStabla(int vrijednost) {
		
		this.roditelj = null;
		this.vrijednost = vrijednost;
		this.dubinaLijevogPodstabla = 0;
		this.dubinaDesnogPodstabla = 0;
		this.razina = 0;
	}
	
	public CvorAVLStabla(int vrijednost, CvorAVLStabla roditelj, int razinaRoditelja) {
		
		this.roditelj = roditelj;
		this.vrijednost = vrijednost;
		this.dubinaLijevogPodstabla = 0;
		this.dubinaDesnogPodstabla = 0;
		this.razina = razinaRoditelja + 1;
	}
	
	public int DubinaPodstabla(CvorAVLStabla dijete) {
		
		if (dijete == null) {
			return -1;
		} else {
			return Math.max(DubinaPodstabla(dijete.lijevoDijete), DubinaPodstabla(dijete.desnoDijete)) + 1;
		}	
	}

	public void IzracunFaktoraRavnoteze() {
		
		this.dubinaLijevogPodstabla = this.DubinaPodstabla(this.lijevoDijete);
		this.dubinaDesnogPodstabla = this.DubinaPodstabla(this.desnoDijete);
		this.faktorRavnoteze = dubinaDesnogPodstabla - dubinaLijevogPodstabla;
		if(this.faktorRavnoteze < -2 || this.faktorRavnoteze > 2) {
			System.out.println("Faktor Ravnoteze cvora " + this.vrijednost + " nije dobar: " + this.faktorRavnoteze);
		}
		if(this.roditelj != null) {
			this.roditelj.IzracunFaktoraRavnoteze();
		}
	}
}
