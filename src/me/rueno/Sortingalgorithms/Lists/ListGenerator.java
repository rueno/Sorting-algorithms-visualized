package me.rueno.Sortingalgorithms.Lists;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;

public class ListGenerator{
	
	private ThreadLocalRandom random;
	
	public ListGenerator(){
		this.random = ThreadLocalRandom.current();
	}
	
	
	public Integer[] generateIntegerList(int length, ListType type){
		Integer[] list = new Integer[length];
		
		switch(type){
			case RANDOM:
				for(int i = 0; i < list.length; i++){
					list[i] = genIntegerInRange(-9999, 10000);
				}
				
				break;
			case ASCENDING:
				int start = genIntegerInRange(-5000, 10000);
				for(int i = 0; i < list.length; i++){
					list[i] = start++;
				}
				
				break;
			case DESCENDING:
				int end = genIntegerInRange(-5000, 10000);
				for(int i = 0; i < list.length; i++){
					list[i] = end--;
				}
				break;
		}
		return list;
	}
	
	public Long[] generateLongList(int length, ListType type){
		Integer[] raw = generateIntegerList(length, type);
		
		Long[] result = new Long[length];
		
		for(int i = 0; i < result.length; i++){
			result[i] = Long.valueOf(raw[i]);
		}
		return result;
	}
	
	public Double[] generateDoubleList(int length, ListType type){
		Float[] raw = generateFloatList(length, type);
		
		Double[] result = new Double[length];
		
		for(int i = 0; i < length; i++){
			result[i] = new BigDecimal(Float.toString(raw[i])).setScale(2, RoundingMode.HALF_UP).doubleValue();
		}
		return result;
	}
	
	public Float[] generateFloatList(int length, ListType type){
		Float[] list = new Float[length];
		
		switch(type){
			case RANDOM:
				for(int i = 0; i < list.length; i++){
					list[i] = genFloatInRange(-9.99999F, 99.0F);
				}
				
				break;
			case ASCENDING:
				float start = genFloatInRange(-9.99999F, 99.0F);
					for(int i = 0; i < list.length; i++){
						list[i] = new BigDecimal(start + "").setScale(2, RoundingMode.HALF_UP).floatValue();
						start += 0.1F;
					}
				
				break;
			case DESCENDING:
				float start2 = genFloatInRange(-9.99999F, 99.0F);
					for(int i = 0; i < list.length; i++){
						list[i] = new BigDecimal(start2 + "").setScale(2, RoundingMode.HALF_UP).floatValue();
						start2 -= 0.1F;
					}
				break;
		}
		return list;
	}
	
	public String[] generateStringList(int length){
		String[] list = new String[length];
		
		for(int i = 0; i < length; i++){
			list[i] = generateRandomString(5);
		}
		
		return list;
	}
	
	private String generateRandomString(int length){
		int left = 97;
		int right = 122;
		
		StringBuffer buffer = new StringBuffer("");
		
		for(int i = 0; i < length; i++){
			char next = (char) ((int) genIntegerInRange(left, right));
			next = (random.nextBoolean() ? Character.toUpperCase(next) : next);
			buffer.append(next);
		}
		return buffer.toString();
	}
	
	private Integer genIntegerInRange(int min, int max){
		return (Integer) (Integer.valueOf(genLongInRange(min, max) + ""));
	}
	
	
	private Long genLongInRange(int min, int max){
		return random.nextLong((max - min) + 1) + min;
	}
	
	private Float genFloatInRange(float min, float max){
		return roundTo((float) (min + (max - min) * random.nextDouble()), 2);
	}
	
	private Float roundTo(float f, int decals){
		BigDecimal dec = new BigDecimal(Float.toString(f));
		dec = dec.setScale(decals, RoundingMode.HALF_UP);
		return dec.floatValue();
	}
	
}