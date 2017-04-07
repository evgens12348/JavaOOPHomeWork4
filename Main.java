package com.gmail.s12348.evgen;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		Group group = new Group();
		group.enterStudent();

		int s = JOptionPane.showConfirmDialog(null, "Do I need to remove a student from the group?");
		if (s == 0) {
			group.deleteStudent();
		}
		s = JOptionPane.showConfirmDialog(null, "Need to find a student on the list?");
		if (s == 0) {
			group.poiskStudent(group.zaprosPoiskStudent());
		}
		group.sortArray();

		System.out.println(group.toString());
		System.out.println("The military commissar chose:");
		group.prizivnik();

	}

}
