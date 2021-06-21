package homework4;

import java.util.Scanner;

public class Game_Management {
	//sorting operation for My SLL variables
	public SingleLinkedList sorting(SingleLinkedList SLL) {
		SingleLinkedList New_SLL = new SingleLinkedList();
		int temp_count = 1;
		while (temp_count != 7) {
			if (SLL.search(temp_count)) {
				SLL.removeOne(temp_count);
				New_SLL.addToEnd(temp_count);
			} else {
				temp_count++;
			}
		}
		return New_SLL;
	}
//The function shows how many numbers in the SLL.
	public int number_of_size(SingleLinkedList SLL, int asked_number) {
		int count = 0;
		int size = SLL.size();
		for (int i = 0; i < size; i++) {
			int deleted_number = SLL.removeFirst();
			SLL.addToEnd(deleted_number);
			if (deleted_number == asked_number) {
				count++;
			}
		}
		return count;
	}
	//Game loop
	public void Game() {
		SingleLinkedList Table_SLL = new SingleLinkedList();
		SingleLinkedList Player_SLL = new SingleLinkedList();
		SingleLinkedList Computer_SLL = new SingleLinkedList();
		//My SLL variables
		Scanner scan = new Scanner(System.in);
		boolean Flag_turn = true;
		int turn = 1;
		int player_book = 0;
		int computer_book = 0;
		int player_space_temp = 5;
		int computer_space_temp = 5;
		int number_1 = 0, number_2 = 0, number_3 = 0, number_4 = 0, number_5 = 0, number_6 = 0;
		int size = 0;
		//Filling the Table_SLL with random numbers
		while (size != 24) {
			int rnd = (int) (Math.random() * 6) + 1;
			if (rnd == 1 && number_1 < 4) {
				Table_SLL.addToEnd(rnd);
				number_1++;
			} else if (rnd == 2 && number_2 < 4) {
				Table_SLL.addToEnd(rnd);
				number_2++;
			} else if (rnd == 3 && number_3 < 4) {
				Table_SLL.addToEnd(rnd);
				number_3++;
			} else if (rnd == 4 && number_4 < 4) {
				Table_SLL.addToEnd(rnd);
				number_4++;
			} else if (rnd == 5 && number_5 < 4) {
				Table_SLL.addToEnd(rnd);
				number_5++;
			} else if (rnd == 6 && number_6 < 4) {
				Table_SLL.addToEnd(rnd);
				number_6++;
			}
			size = Table_SLL.size();
		}
		//Cards are distributed to Player_SLL from Table_SLL.
		for (int i = 0; i < 7; i++) {
			int temp = Table_SLL.removeFirst();
			Player_SLL.addToEnd(temp);
		}
		Player_SLL = sorting(Player_SLL);
		//Cards are distributed to Computer_SLL from Table_SLL.
		for (int i = 0; i < 7; i++) {
			int temp = Table_SLL.removeFirst();
			Computer_SLL.addToEnd(temp);
		}
		Computer_SLL = sorting(Computer_SLL);
		while (true) {
			int input = 0;
			//Space operation
			if (Player_SLL.size() > Computer_SLL.size()) {
				player_space_temp = 4;
				computer_space_temp = 2 * (Player_SLL.size() - Computer_SLL.size()) + 4;
			} else if (Player_SLL.size() < Computer_SLL.size()) {
				player_space_temp = 2 * (Computer_SLL.size() - Player_SLL.size()) + 4;
				computer_space_temp = 4;
			} else {
				player_space_temp = Player_SLL.size() + 4;
				computer_space_temp = Computer_SLL.size() + 4;
			}
			//DÝSPLAY
			System.out.print("Turn: " + turn + "\t\t ");
			for (int i = 0; i < 2 * (player_space_temp + Player_SLL.size()); i++) {
				System.out.print(" ");
			}
			System.out.print("Table\n");
			System.out.print("You:      " + Player_SLL.display());

			for (int i = 0; i < player_space_temp; i++) {
				System.out.print(" ");
			}
			System.out.print("book: " + player_book);
			for (int i = 0; i < player_space_temp; i++) {
				System.out.print(" ");
			}
			System.out.print(Table_SLL.display());
			System.out.print("\nComputer: " + Computer_SLL.display());

			for (int i = 0; i < computer_space_temp; i++) {
				System.out.print(" ");
			}
			System.out.print("book: " + computer_book + "\n");
			//True=Player's turn,False=Computer's turn.
			if (Flag_turn == true) {
				System.out.print("\nYou ask: ");
				input = scan.nextInt();
				if (Player_SLL.search(input)) {
					if (Computer_SLL.search(input)) {
						int amount_of_number = number_of_size(Computer_SLL, input);
						Computer_SLL.removeAll(input);
						for (int i = 0; i < amount_of_number; i++) {
							Player_SLL.addToEnd(input);
						}
						Player_SLL = sorting(Player_SLL);
					} else {
						System.out.println("\nComputer says \"Go Fish\"");
						if (Table_SLL.size() > 0)
							Player_SLL.addToEnd(Table_SLL.removeFirst());
						Player_SLL = sorting(Player_SLL);
						Flag_turn = false;
					}
					for (int i = 1; i < 7; i++) {
						if (number_of_size(Player_SLL, i) == 4) {
							Player_SLL.removeAll(i);
							System.out.println();
							for (int k = 0; k < 4; k++) {
								System.out.print(i + " ");
							}
							player_book++;
						}
					}
				} else {
					System.out.println("\nYou can just ask the cards you have !!");
					turn--;
				}
			} else {
				int computer_ask = 0;
				while (true) {
					int rnd = (int) (Math.random() * 6) + 1;
					if (Computer_SLL.search(rnd)) {
						computer_ask = rnd;
						break;
					}
				}
				System.out.print("\nComputer asks: " + computer_ask + "\n");
				if (Player_SLL.search(computer_ask)) {
					int amount_of_number = number_of_size(Player_SLL, computer_ask);
					Player_SLL.removeAll(computer_ask);
					for (int i = 0; i < amount_of_number; i++) {
						Computer_SLL.addToEnd(computer_ask);
					}
					Computer_SLL = sorting(Computer_SLL);
				} else {
					//
					System.out.println("\nYou say \"Go Fish\"");
					if (Table_SLL.size() > 0)
						Computer_SLL.addToEnd(Table_SLL.removeFirst());
					Computer_SLL = sorting(Computer_SLL);
					Flag_turn = true;
				}
				for (int i = 1; i < 7; i++) {
					if (number_of_size(Computer_SLL, i) == 4) {
						Computer_SLL.removeAll(i);
						System.out.println();
						for (int k = 0; k < 4; k++) {
							System.out.print(i + " ");
						}
						computer_book++;
					}
				}
			}
			System.out.println("\n-----------------------------------------------------------"
					+ "--------------------------------");
			//If both players's cards finish,the game ends.
			if (Computer_SLL.size() == 0 || Player_SLL.size() == 0) {
				System.out.println("Game is over.");
				if (computer_book > player_book)
					System.out.println("\nComputer wins the game !!");
				else if (computer_book < player_book)
					System.out.println("\nYou win the game !!");
				else
					System.out.println("\nThe game drew...");
				break;
			}
			turn++;
		}
		scan.close();
	}
}
