import java.util.Scanner;
import java.util.Random;

class Notes{
    private int[] tabNotes;
    private String[] tabNotesChaine;
    // tableau contetant les caracteres unicodes necessaire à l'affichage du tableau de note
    private String[][] tabBox = {{"\u2554\u2550", "\u2550\u2566\u2550", "\u2550\u2557\n"},
				 {"\u2551", "\u2551\n"},
				 {"\u2560\u2550", "\u2550\u256c\u2550", "\u2550\u2563\n"},
				 {"\u2551", "\u2551\n"},
				 {"\u255a\u2550", "\u2550\u2569\u2550", "\u2550\u255d\n"}
    };

    Notes(){
	// Constructeur, génere un tableau vide de 21 cases
	tabNotes = new int [21];
	tabNotesChaine = new String[21];
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
	remplirTableauChaine();
    }

    void remplirTableauAleatoire(){
	// Remplis le tableau généré dans le constructeur de manière aléatoire,
	// il genere un nombre compris entre 0 et 10 dans chaque case
	Random generateur;

	generateur = new Random();

	for (int i = 0 ; i < tabNotes.length ; i++){
	    tabNotes[i] = generateur.nextInt(11);
	}
	remplirTableauChaine();
    }

    String afficherTableau(){
	// Affiche le contenu du tableau, en construisant une chaine à partir des tableaux ()
	String tableau, donnees;

	tableau = "";
	for (int i = 0 ; i < 5 ; i++){
	    for (int j = 0 ; j < tabNotes.length ; j++){
		// Construction des lignes horizontales
		if (i % 2 == 0){
		    // Permet de detecter le début de ligne
		    if (j == 0)
			tableau += tabBox[i][0];
		    // Permet de detecter la fin de ligne
		    else if (j == 20)
			tableau += tabBox[i][1] + tabBox[i][2];
		    else
			tableau += tabBox[i][1];
		}
	    
		// Construction des cases contenant les données
		else{
		    // Selection des donnees à afficher, selon que l'on soit à l'entête ou à la
		    // première ligne du tableau
		    if (i == 1)
			donnees = tabNotesChaine[j].substring(0,2);
		    else
			donnees = tabNotesChaine[j].substring(2);
		    // Permet de detecter quant on arrive à la fin du tableau
		    if (j == 20)
			tableau += tabBox[i][0] + donnees + tabBox[i][0] + "\n";
		    else
			tableau += tabBox[i][0] + donnees;
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

    void remplirTableauChaine(){
	// Genere un tableau contenant une chaine par case, cette chaine correspond au numéro de
	// case de tabNotes suivi de la valeur de la case, un zero est ajouté si nécessaire
	for (int i = 0 ; i < tabNotesChaine.length ; i++){
	    if (i < 10)
		tabNotesChaine[i] = "0" + i;
	    else
		tabNotesChaine[i] = "" + i;

	    if (tabNotes[i] < 10)
		tabNotesChaine[i] += "0" + tabNotes[i];
	    else
		tabNotesChaine[i] += "" + tabNotes[i];
	}
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
	for (int i = plusHauteNote() ; i >= -1 ; i--){
	    for (int j = 0 ; j < tabNotes.length ; j ++){
		// ces lignes affiche le bas de l'historigramme
		if (i < 1){
		    if (i == 0)
			System.out.print(tabNotesChaine[j].charAt(0) + " ");
		    else
			System.out.print(tabNotesChaine[j].charAt(1) + " ");
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
