package helper;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Utils {
	// Conjunto de funções para ser utilizado no programa todo

	static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	static NumberFormat numberFormat = new DecimalFormat("R$ #,##0.00");
	static DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(new Locale("pt", "BR"));

	public static String dateToString(Date date) {
		// retorna a data no formato pt-BR
		return Utils.simpleDateFormat.format(date);
	}

	public static String doubleToString(Double value) {
		// retorna o valor no formato pt-BR
		return Utils.numberFormat.format(value);
	}

	public static Double stringToDouble(String valor) {
		// recebe o valor no formato pt-BR e retorna pós parse || null
		try {
			return (Double) Utils.numberFormat.parse(valor);
		} catch (ParseException e) {
			return null;
		}
	}

	public static void pause(int seconds) {
		// pausa no programa para visualizar a mensagem em tela || mensagem de erro
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			System.out.println("Erro ao pausar por " + seconds + " segundos");
		}
	}

}
