package prviLabos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * AVL tree implementation as part of a faculty lab assignment. AVL tree is a self-balancing binary search tree.
 * Pre-defined entries are in "ulaz.txt" file, after they are read and the tree is balanced, user can enter additional
 * data to additionaly test the self-balancing tree.
 * 
 * @author Marko Borovac, october 2013
 *
 */
public class NASPPrviLabos {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader("ulaz.txt"));
		String line;
		try {
			line = br.readLine();
		} finally {
				br.close();
		}
		
		String[] ulazniElementi = line.split(" ");
		CvorAVLStabla korijen = new CvorAVLStabla(Integer.parseInt(ulazniElementi[0]));
		AVLStablo stablo = new AVLStablo(korijen);
		for(int i = 1; i < ulazniElementi.length; i++) {
			CvorAVLStabla cvor = new CvorAVLStabla(Integer.parseInt(ulazniElementi[i]));
			stablo.DodajCvor(cvor, stablo.korijen);
		}
		
		IspisiStablo(stablo.korijen);
		Scanner reader = new Scanner(System.in);
		while(true) {
			System.out.println("Unesite broj koji ce biti dodan u stablo: ");
			int a = reader.nextInt();
			if(a == -1) {
				System.out.println("Zavrsavam");
				reader.close();
				break;
			} else {
				CvorAVLStabla cvor = new CvorAVLStabla(a);
				stablo.DodajCvor(cvor, stablo.korijen);
			}
			IspisiStablo(stablo.korijen);
		}
	}
	
	public static void IspisiStablo(CvorAVLStabla korijen) {
		
		boolean praznaLista = false;
		ArrayList<CvorAVLStabla> listaRoditelja = new ArrayList<CvorAVLStabla>();
		ArrayList<CvorAVLStabla> listaDjece = new ArrayList<CvorAVLStabla>();
		listaRoditelja.add(korijen);
		
		while(!praznaLista) {
			praznaLista = true;
			for(CvorAVLStabla i : listaRoditelja) {
				if(i != null) {
					praznaLista = false;
					listaDjece.add(i.lijevoDijete);
					listaDjece.add(i.desnoDijete);
				} else {
					listaDjece.add(null);
					listaDjece.add(null);
				}
			}
			if(!praznaLista) {
				for(CvorAVLStabla j : listaRoditelja) {
					if(j != null) {
						System.out.print("[" + j.vrijednost + "]");
					} else {
						System.out.print("[ ]");
					}
				}
			}
			listaRoditelja.clear();
			listaRoditelja.addAll(listaDjece);
			listaDjece.clear();
			System.out.print("\n");
		}
	}
}
