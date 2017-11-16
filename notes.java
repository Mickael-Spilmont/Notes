import java.util.Scanner;

class Notes{
    private int[] tabNotes;

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

    String afficherTableau(){
	// Affiche le contenu du tableau
	String tableau;

	tableau = "";
	for (int i = 0 ; i < tabNotes.length ; i++){
	    if (i < 10)
		tableau += "0" + i + " -> " + tabNotes[i] + "\n";
	    else
		tableau += i + " -> " + tabNotes[i] + "\n";
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
	mesNotes.remplirTableau();
	System.out.print("\n");
	System.out.print(mesNotes.afficherTableau());
	mesNotes.historigrammeH();
    }
}
