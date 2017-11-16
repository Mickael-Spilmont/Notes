import java.util.Scanner;
import java.util.Random;

class Notes{
    private int[] tabNotes;
    private String[][] tabBox = {{"\u2554\u2550\u2550\u2566", "\u2550\u2550\u2566", "\u2550\u2550\u2557\n\u2551"},
				 {"\u2560\u2550\u2550\u256c", "\u2550\u2550\u256c", "\u2550\u2550\u2563\n\u2551"},
				 {"\u255a\u2550\u2550\u2569", "\u2550\u2550\u2569", "\u2550\u2550\u255d\n"}
    };

    Notes(){
	// Constructeur, génere un tableau vide de 21 cases
	tabNotes = new int [21];
    }

    void remplirTableau(){
	// Permet de remplir le tableau à la main
	int note;
	Scanner sc = new Scanner(System.in);
	
	note = sc.nextInt();
	while (note >= 0 && note <= 20){
	    tabNotes[note] ++;
	    note = sc.nextInt();
	}
    }

    void remplirTableauAleatoire(){
	// Remplis le tableau généré dans le constructeur de manière aléatoire,
	// il genere un nombre compris entre 0 et 10 dans chaque case
	Random generateur;

	generateur = new Random();

	for (int i = 0 ; i < tabNotes.length ; i++){
	    tabNotes[i] = generateur.nextInt(11);
	}
    }

    String afficherTableau(){
	// Affiche le contenu du tableau
	String tableau;

	tableau = "";
	for (int i = 0 ; i < 5 ; i++){
	    for (int j = 0 ; j < tabNotes.length ; j++){

		if (i == 0){
		    if (j == 0)
			System.out.print(tabBox[0][0]);
		    else if (j == 20)
			System.out.print(tabBox[0][2]);
		    else
			System.out.print(tabBox[0][1]);
		}
		else if (i == 2){
		    if (j == 0)
			System.out.print(tabBox[1][0]);
		    else if (j == 20)
			System.out.print(tabBox[1][2]);
		    else
			System.out.print(tabBox[1][1]);
		}
		else if (i == 4){
		    if (j == 0)
			System.out.print(tabBox[2][0]);
		    else if (j == 20)
			System.out.print(tabBox[2][2]);
		    else
			System.out.print(tabBox[2][1]);
		}
		else if (i == 1){
		    if (j < 10)
		    	System.out.print("0" + j + "\u2551");
		    else if (j == 20)
			System.out.print(j + "\u2551\n");
		    else
		    	System.out.print(j + "\u2551");
		}
		else{
		    if (tabNotes[j] < 10 && j != 20)
		    	System.out.print("0" + tabNotes[j] + "\u2551");
		    else if (tabNotes[j] < 10 && j == 20)
			System.out.print("0" + tabNotes[j] + "\u2551\n");
		    else if (j == 20)
			System.out.print(j + "\u2551\n");
		    else
		    	System.out.print(tabNotes[j] + "\u2551");
		}
	    }
	}
	return tableau;
    }

    int plusHauteNote(){
	// Trouve la plus haute note du tableau
	int noteMax;

	noteMax = -1;
	for (int i = 0 ; i < tabNotes.length ; i++){
	    if (tabNotes[i] > noteMax){
		noteMax = tabNotes[i];
	    }
	}
	return noteMax;
    }

    String[] historigrammeBas(){
	// Genere un tableau permetant de créer le bas de l'historigramme horizontale
	String[] bas;

	bas = new String [21];
	for (int i = 0 ; i < 21 ; i++){
	    if (i < 10)
		bas[i] = "0" + i;
	    else
		bas[i] = "" + i;
	}
	return bas;
    }

    void historigrammeV(){
	// Affiche un historigrame vertical
	for (int i = 0 ; i < tabNotes.length ; i++){
	    System.out.print(i + " | ");
	    for (int j = 0 ; j < tabNotes[i] ; j++){
		System.out.print("#");
	    }
	    System.out.print("\n");
	}
    }

    void historigrammeH(){
	// Affiche un historigramme horizontale
	String[] bas;

	bas = historigrammeBas();
	
	for (int i = plusHauteNote() ; i >= -1 ; i--){
	    for (int j = 0 ; j < tabNotes.length ; j ++){

		// ces lignes affiche le bas de l'historigramme
		if (i < 1){
		    if (i == 0)
			System.out.print(bas[j].charAt(0) + " ");
		    else
			System.out.print(bas[j].charAt(1) + " ");
		}

		// Ces ligne permettent de définir si il faut mettre des espaces
		// ou un caractere
		else if (tabNotes[j] >= i)
		    System.out.print("\u2588 ");
		else
		    System.out.print("  ");
	    }
	    System.out.print("\n");
	}
    }
}

class Main{
    public static void main(String [] args){
	Notes mesNotes;
	Scanner sc = new Scanner(System.in);

	mesNotes = new Notes();

	System.out.println("Veuillez entrez vos notes :");
	// mesNotes.remplirTableau();
	mesNotes.remplirTableauAleatoire();
	System.out.print("\n");
	System.out.print(mesNotes.afficherTableau());
	mesNotes.historigrammeH();
    }
}
