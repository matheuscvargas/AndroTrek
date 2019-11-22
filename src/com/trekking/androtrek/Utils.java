package com.trekking.androtrek;

public final class Utils {
	public static String formatNumber(long NumToFormat, int res){
		String NumFormatado = String.valueOf(NumToFormat);
		for (int aux = 0; aux < res; aux++){
			if (NumToFormat < Math.pow(10, aux) && !((NumToFormat == 0) && (aux == 0))){
				NumFormatado = "0" + NumFormatado;
			}
		}
		return NumFormatado;
	}
	public static String ConvertSecsToHours(double NumToFormat){
		String NumFormatado;
		double hr, min, sec;
		hr = 0;
		min = 0;
		sec = NumToFormat;
		while (sec >= 60){
			sec = sec - 60;
			min = min + 1;
		}
		while (min >= 60){
			min = min - 60;
			hr = hr + 1;
		}
		NumFormatado = formatNumber((long) hr, 2) + ":" + formatNumber((long) min, 2) + ":" + formatNumber((long) sec, 2);
		return NumFormatado;
	}
	public static String ConvertSecsToMinutes(double NumToFormat){
		String NumFormatado;
		double min, sec;
		min = 0;
		sec = NumToFormat;
		while (sec >= 60){
			sec = sec - 60;
			min = min + 1;
		}
		NumFormatado = formatNumber((long) min, 2) + "." + formatNumber((long) sec, 2);
		return NumFormatado;
	}
	
}
