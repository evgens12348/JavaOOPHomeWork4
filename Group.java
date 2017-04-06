package com.gmail.s12348.evgen;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class Group implements Voencom {
	private Student[] studentGroup = new Student[10];

	public Group() {
	}

	public void enterStudent() {
		int i = 0;
		for (;;) {
			int s = JOptionPane.showConfirmDialog(null, "Want to enter a new student into the group?");
			if (s == 0) {
				try {
					if (i == studentGroup.length) {
						resizeArray();
					}
					studentGroup[i] = new Student();
					studentGroup[i].inputSurname();
					studentGroup[i].inputName();
					studentGroup[i].inputSex();
					studentGroup[i].inputOld();
					studentGroup[i].inputHighSchool();
					studentGroup[i].inputDepartment();
					studentGroup[i].inputKurs();
					studentGroup[i].inputType();
					studentGroup[i].inputAssessment();
					i += 1;
				} catch (MyExeption e) {
					e.nameNull("null");
				}
			} else {
				break;
			}

		}
	}

	public void resizeArray() {
		Student[] temp = new Student[studentGroup.length + 1];
		System.arraycopy(studentGroup, 0, temp, 0, studentGroup.length);
		studentGroup = temp;
	}

	public void deleteStudent() {
		for (;;) {
			try {
				int n = Integer.valueOf(JOptionPane
						.showInputDialog("Enter the number of the record you want to delete (From 1 to 10)."));
				if (n < 1 & n > 10)
					throw new MyNegativeExeption();
				studentGroup[n] = null;
				break;
			} catch (MyNegativeExeption e) {
				e.negativeNumber();
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Error number format");
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, "Canseled set as default");
				break;
			}
		}

	}

	public void poiskStudent() {
		String surname;
		for (;;) {
			try {
				surname = String
						.valueOf(JOptionPane.showInputDialog("Enter the surname of the student you want to find."));
				if (surname == "null")
					throw new MyExeption();
				if (checkString(surname) != true) {
					JOptionPane.showMessageDialog(null, "Input Error");
				} else {
					break;
				}
			} catch (MyExeption e) {
				e.nameNull("null");
			}
		}

		int k = 0;
		for (int i = 0; i < studentGroup.length; i++) {
			if (studentGroup[i] != null) {
				if (studentGroup[i].getSurname().compareToIgnoreCase(surname) == 0) {
					System.out.println(studentGroup[i]);
					k += 1;
				}
			}
		}
		if (k == 0) {
			JOptionPane.showMessageDialog(null, "Нет такого студента");
		}
	}

	public void sortArray() {
		int n = 0;
		for (;;) {
			try {
				n = Integer.valueOf(
						JOptionPane.showInputDialog("Select by what to sort (1-name, 2-surname, 3-sex, 4-age)."));
				if (n < 1 & n > 4)
					throw new MyNegativeExeption();
				break;
			} catch (MyNegativeExeption e) {
				e.negativeNumber();
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Error number format");
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, "Canseled set as default");
				break;
			}
		}
		Student temp = new Student();
		for (int i = 0; i < studentGroup.length; i++) {
			for (int j = studentGroup.length - 1; j > i; j--) {
				if (studentGroup[j] != null & studentGroup[j - 1] != null) {
					switch (n) {
					case 1:
						if (studentGroup[j].getName().compareToIgnoreCase(studentGroup[j - 1].getName()) < 0) {
							temp = studentGroup[j];
							studentGroup[j] = studentGroup[j - 1];
							studentGroup[j - 1] = temp;
						}

						break;
					case 2:
						if (studentGroup[j].getSurname().compareToIgnoreCase(studentGroup[j - 1].getSurname()) < 0) {
							temp = studentGroup[j];
							studentGroup[j] = studentGroup[j - 1];
							studentGroup[j - 1] = temp;
						}
						break;
					case 3:
						if (studentGroup[j].getSex().compareToIgnoreCase(studentGroup[j - 1].getSex()) < 0) {
							temp = studentGroup[j];
							studentGroup[j] = studentGroup[j - 1];
							studentGroup[j - 1] = temp;
						}
						break;
					case 4:
						if (studentGroup[j].getOld() < studentGroup[j - 1].getOld()) {
							temp = studentGroup[j];
							studentGroup[j] = studentGroup[j - 1];
							studentGroup[j - 1] = temp;
						}
						break;

					}

				}
			}
		}
	}

	public boolean checkString(String string) {
		if (string.length() == 0) {
			return false;
		} else {
			Pattern p = Pattern.compile("^([a-zA-Z- №]+)");
			Matcher m = p.matcher(string);

			return m.matches();
		}
	}

	@Override
	public String toString() {
		int k = 0;
		for (int i = 0; i < studentGroup.length; i++) {
			if (studentGroup[i] != null) {
				System.out.println(studentGroup[i] + " ");
				k += 1;
			}
		}

		return "In Group [" + k + "]";
	}

	@Override
	public void prizivnik() {
		Student[] prizivnik = new Student[studentGroup.length];
		for (int i = 0; i < studentGroup.length; i++) {
			if (studentGroup[i] != null) {
				if (studentGroup[i].getSex() == "man" & studentGroup[i].getOld() > 18) {
					for (int j = 0; j < prizivnik.length; j++) {
						if (prizivnik[j] == null) {
							prizivnik[j] = studentGroup[i];
							break;
						}
					}
				}
			}
		}
		for (int i = 0; i < prizivnik.length; i++) {
			if (prizivnik[i] != null) {
				System.out.println(prizivnik[i] + " ");
			}
		}
	}

}
