package com.prisbox.base;

public class NAV {
	private String date;
	private String netAssetValue;
	private String historicalNetValue;
	private String dailyGrowthRate;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNetAssetValue() {
		return netAssetValue;
	}

	public void setNetAssetValue(String netAssetValue) {
		this.netAssetValue = netAssetValue;
	}

	public String getHistoricalNetValue() {
		return historicalNetValue;
	}

	public void setHistoricalNetValue(String historicalNetValue) {
		this.historicalNetValue = historicalNetValue;
	}

	public String getDailyGrowthRate() {
		return dailyGrowthRate;
	}

	public void setDailyGrowthRate(String dailyGrowthRate) {
		this.dailyGrowthRate = dailyGrowthRate;
	}
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append(this.getDate())
		  .append("\t")
		  .append(this.getNetAssetValue())
		  .append("\t")
		  .append(this.getHistoricalNetValue())
		  .append("\t")
		  .append(this.getDailyGrowthRate());
		return sb.toString();
	};
}
