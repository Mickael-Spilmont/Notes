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

	System.out.println("Entrez vos notes : ");
	note = sc.nextInt();
	while (note >= 0 && note <= 20){
	    tabNotes[note] ++;
	    note = sc.nextInt();
	}
	remplirTableauChaine();
	System.out.println("\n" + separateur() + "\n");
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

    String separateur(){
	String separateur;

	separateur = "";
	for (int i = 0 ; i <= 65 ; i++){
	    separateur += "\u2501";
	}
	return separateur;
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
	tableau += "\n" + separateur() + "\n";
	return tableau;
    }
    
    String historigrammeV(){
	// Affiche un historigramme vertical
	String historigramme;

	historigramme = "";
	for (int i = 0 ; i < tabNotes.length ; i++){
	    historigramme += tabNotesChaine[i].substring(0,2) + " ";
	    for (int j = 0 ; j < tabNotes[i] ; j++){
		historigramme += "\u25ac\u25ac";
	    }
	    historigramme += "\n";
	}
	historigramme += "\n" + separateur() + "\n";
	return historigramme;
    }

    String historigrammeH(){
	// Affiche un historigramme horizontale
	String historigramme;

	historigramme = "";
	for (int i = plusHauteNote() ; i >= -1 ; i--){
	    for (int j = 0 ; j < tabNotes.length ; j ++){
		// ces lignes affiche le bas de l'historigramme
		if (i < 1){
		    if (i == 0)
			historigramme += tabNotesChaine[j].charAt(0) + " ";
		    else
			historigramme +=tabNotesChaine[j].charAt(1) + " ";
		}
		// Ces ligne permettent de définir si il faut mettre des espaces
		// ou un caractere
		else if (tabNotes[j] >= i)
		    historigramme += "\u2588 ";
		else
		    historigramme += "  ";
	    }
	    historigramme += "\n";
	}
	historigramme += "\n" + separateur() + "\n";
	return historigramme;
    }
}

class Main{
    public static void main(String [] args){
	int reponse;
	String[] phrases = {
	    "Bienvenu comment souhaitez vous saisir les notes :\n",
	    "1 - Manuellement\n2 - Remplir le tableau avec des notes aléatoires\n3 - Quitter le programme\n",
	    "Reponse incorrecte\n",
	    "Que souhaitez vous faire\n",
	    "1 - Afficher le tableau\n2 - Afficher un historigramme vertical\n3 - Afficher un historigramme horizontale\n4 - Quitter le programme\n",
	    "Fin du programme"
	};
	Notes mesNotes;
	Scanner sc = new Scanner(System.in);

	mesNotes = new Notes();

	System.out.print(phrases[0] + phrases[1]);
	reponse = sc.nextInt();
	System.out.print("\n");
	while (reponse < 1 || reponse > 3){
	    System.out.print(phrases[2] + phrases[1]);
	    reponse = sc.nextInt();
	    System.out.print("\n");
	}

	switch(reponse){
	case 1:
	    mesNotes.remplirTableau();
	    break;
	case 2:
	    mesNotes.remplirTableauAleatoire();
	    break;
	default:
	    System.out.println(phrases[5]);
	    System.exit(0);
	}

	System.out.print(phrases[3] + phrases[4]);
	reponse = sc.nextInt();
	System.out.print("\n");
	while (reponse != 4){
	    if (reponse == 1)
		System.out.println(mesNotes.afficherTableau());
	    else if (reponse == 2)
		System.out.println(mesNotes.historigrammeV());
	    else if (reponse == 3)
		System.out.println(mesNotes.historigrammeH());
	    else
		System.out.println(phrases[2]);

	    System.out.print(phrases[4]);
	    reponse = sc.nextInt();
	    System.out.print("\n");
	}
	System.out.println(phrases[5]);
	System.exit(0);
    }
}
