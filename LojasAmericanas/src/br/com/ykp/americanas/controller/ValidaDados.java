package br.com.ykp.americanas.controller;

import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class ValidaDados {

	public boolean validEmail(String email) {
		System.out.println("Metodo de validacao de email");
		Pattern p = Pattern.compile("[a-zA-Z]*@[a-zA-Z]");
		// completo "^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$"
		Matcher m = p.matcher(email);
		if (m.find()) {
			System.out.println("O email " + email + " e valido");
			return true;
		} else {
			System.out.println("O E-mail " + email + "Invalido");
			return false;
		}
	}

	public boolean validTelefone(String tel) {

		String formato = "[a-zA-Z]";
		String formato2 = "\\((10)|([1-9][1-9])\\)\\s9?[6-9][0-9]{3}-[0-9]{4}";

		// if((tel == null) || (tel.length()!=13) || (!tel.matches(formato))){
		if ((tel == null) || (tel.length() > 15) || (tel.length() < 7) || (tel.matches(formato))) {
			System.out.println("O telefone " + tel + " é inválido");
			return false;

		} else {
			System.out.println("O telefone " + tel + " é valido");
			return true;
		}
	}

	public static boolean validCPF(String CPF) {
		System.out.println("Metodo de validacao de CPF");
		if (CPF == null || CPF.length() != 11
				|| CPF.matches("^(0{11}|1{11}|2{11}|3{11}|4{11}|5{11}|6{11}|7{11}|9{11}|9{11})$")) {
			System.out.println("O CPF " + CPF + " é inválido");
			return false;
			// if (CPF.equals("00000000000") || CPF.equals("11111111111") ||
			// CPF.equals("22222222222") || CPF.equals("33333333333") ||
			// CPF.equals("44444444444") || CPF.equals("55555555555") ||
			// CPF.equals("66666666666") || CPF.equals("77777777777") ||
			// CPF.equals("88888888888") || CPF.equals("99999999999") ||
			// (CPF.length() != 11))

		}
		char dig10, dig11;
		int sm, i, r, num, peso;

		try {

			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig10 = '0';
			else
				dig10 = (char) (r + 48);

			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else
				dig11 = (char) (r + 48);

			// Verifica se os digitos calculados conferem com os digitos
			// informados.
			if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
				System.out.println("O CPF " + CPF + " é válido");
				return (true);
			}

			else {
				System.out.println("O CPF " + CPF + " é inválido");
				return (false);
			}
		} catch (InputMismatchException erro) {
			return (false);
		}
	}

	public static String imprimeCPF(String CPF) {
		return (CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." + CPF.substring(6, 9) + "-"
				+ CPF.substring(9, 11));
	}

	// public static void validCPF(String CPF) {
	// System.out.println("Metodo de validacao de CPF");
	// if (CPF == null || CPF.length() != 11 ||
	// CPF.matches("^(0{11}|1{11}|2{11}|3{11}|4{11}|5{11}|6{11}|7{11}|9{11}|9{11})$"))
	//// if (CPF.equals("00000000000") || CPF.equals("11111111111") ||
	//// CPF.equals("22222222222") || CPF.equals("33333333333") ||
	//// CPF.equals("44444444444") || CPF.equals("55555555555") ||
	//// CPF.equals("66666666666") || CPF.equals("77777777777") ||
	//// CPF.equals("88888888888") || CPF.equals("99999999999") ||
	//// (CPF.length() != 11))
	// System.out.println("O CPF " + CPF + " é inválido");
	//
	// char dig10, dig11;
	// int sm, i, r, num, peso;
	//
	// try {
	//
	// sm = 0;
	// peso = 10;
	// for (i=0; i<9; i++) {
	// num = (int)(CPF.charAt(i) - 48);
	// sm = sm + (num * peso);
	// peso = peso - 1;
	// }
	//
	// r = 11 - (sm % 11);
	// if ((r == 10) || (r == 11))
	// dig10 = '0';
	// else dig10 = (char)(r + 48);
	//
	// // Calculo do 2o. Digito Verificador
	// sm = 0;
	// peso = 11;
	// for(i=0; i<10; i++) {
	// num = (int)(CPF.charAt(i) - 48);
	// sm = sm + (num * peso);
	// peso = peso - 1;
	// }
	//
	// r = 11 - (sm % 11);
	// if ((r == 10) || (r == 11))
	// dig11 = '0';
	// else dig11 = (char)(r + 48);
	//
	//
	// if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
	// System.out.println("O CPF " + CPF + " é válido");
	// // return("Valido");
	//
	// else { System.out.println("O CPF " + CPF + " é inválido");}
	// } catch (InputMismatchException erro) {
	// System.out.println("Erro");
	// }
	// }
	//
	// public static String imprimeCPF(String CPF) {
	// return(CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." +
	// CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
	//
	// }

}