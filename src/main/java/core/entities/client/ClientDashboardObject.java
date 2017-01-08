package core.entities.client;

import core.util.CheckData;

import java.io.Serializable;
import java.util.HashMap;

public class ClientDashboardObject implements Serializable {
    private long accountId = 0;
    private String firstLast = "";
    private int revToday = 0;
    private String revTodayFormatted = "0.00";
    private int revMonth = 0;
    private String revMonthFormatted = "0.00";
    private int revYear = 0;
    private String revYearFormatted = "0.00";
    private int tranToday = 0;
    private int tranMonth = 0;
    private int tranYear = 0;
    private int uptakeCountToday = 0;
    private int uptakeCountMonth = 0;
    private int uptakeCountYear = 0;
    private int averageDeliveryDaysToday = 0;
    private int averageDeliveryDaysMonth = 0;
    private int averageDeliveryDaysYear = 0;
    private int totalShips = 0;
    private int totalDelivered = 0;
    private int percentDelivered = 0;
    private int totalInTransit = 0;
    private int percentInTransit = 0;
    private int totalReady = 0;
    private int percentReady = 0;
    private int totalFailed = 0;
    private int percentFailed = 0;
    private String month12Name = "";
    private int month12Value = 0;

    private String month11Name = "";
    private int month11Value = 0;

    private String month10Name = "";
    private int month10Value = 0;

    private String month9Name = "";
    private int month9Value = 0;

    private String month8Name = "";
    private int month8Value = 0;

    private String month7Name = "";
    private int month7Value = 0;

    private String month6Name = "";
    private int month6Value = 0;

    private String month5Name = "";
    private int month5Value = 0;

    private String month4Name = "";
    private int month4Value = 0;

    private String month3Name = "";
    private int month3Value = 0;

    private String month2Name = "";
    private int month2Value = 0;

    private String month1Name = "";
    private int month1Value = 0;

    @Override
    public String toString() {
        return "ClientDashboardObject{" +
                "accountId=" + accountId +
                ", firstLast='" + firstLast + '\'' +
                ", revToday=" + revToday +
                ", revTodayFormatted='" + revTodayFormatted + '\'' +
                ", revMonth=" + revMonth +
                ", revMonthFormatted='" + revMonthFormatted + '\'' +
                ", revYear=" + revYear +
                ", revYearFormatted='" + revYearFormatted + '\'' +
                ", tranToday=" + tranToday +
                ", tranMonth=" + tranMonth +
                ", tranYear=" + tranYear +
                ", uptakeCountToday=" + uptakeCountToday +
                ", uptakeCountMonth=" + uptakeCountMonth +
                ", uptakeCountYear=" + uptakeCountYear +
                ", averageDeliveryDaysToday=" + averageDeliveryDaysToday +
                ", averageDeliveryDaysMonth=" + averageDeliveryDaysMonth +
                ", averageDeliveryDaysYear=" + averageDeliveryDaysYear +
                ", totalShips=" + totalShips +
                ", totalDelivered=" + totalDelivered +
                ", percentDelivered=" + percentDelivered +
                ", totalInTransit=" + totalInTransit +
                ", percentInTransit=" + percentInTransit +
                ", totalReady=" + totalReady +
                ", percentReady=" + percentReady +
                ", totalFailed=" + totalFailed +
                ", percentFailed=" + percentFailed +
                ", month12Name='" + month12Name + '\'' +
                ", month12Value=" + month12Value +
                ", month11Name='" + month11Name + '\'' +
                ", month11Value=" + month11Value +
                ", month10Name='" + month10Name + '\'' +
                ", month10Value=" + month10Value +
                ", month9Name='" + month9Name + '\'' +
                ", month9Value=" + month9Value +
                ", month8Name='" + month8Name + '\'' +
                ", month8Value=" + month8Value +
                ", month7Name='" + month7Name + '\'' +
                ", month7Value=" + month7Value +
                ", month6Name='" + month6Name + '\'' +
                ", month6Value=" + month6Value +
                ", month5Name='" + month5Name + '\'' +
                ", month5Value=" + month5Value +
                ", month4Name='" + month4Name + '\'' +
                ", month4Value=" + month4Value +
                ", month3Name='" + month3Name + '\'' +
                ", month3Value=" + month3Value +
                ", month2Name='" + month2Name + '\'' +
                ", month2Value=" + month2Value +
                ", month1Name='" + month1Name + '\'' +
                ", month1Value=" + month1Value +
                ", shipmentsPerCountry=" + shipmentsPerCountry +
                '}';
    }

    private HashMap<String,Integer> shipmentsPerCountry = new HashMap<>();

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getFirstLast() {
        return firstLast;
    }

    public void setFirstLast(String firstLast) {
        this.firstLast = firstLast;
    }

    public int getRevToday() {
        return revToday;
    }

    public void setRevToday(int revToday) {
        this.revToday = revToday;
    }

    public void addRevToday(int revToday) {
        this.revToday += revToday;
    }

    public int getRevMonth() {
        return revMonth;
    }

    public void setRevMonth(int revMonth) {
        this.revMonth = revMonth;
    }

    public void addRevMonth(int revMonth) {
        this.revMonth += revMonth;
    }

    public int getRevYear() {
        return revYear;
    }

    public void setRevYear(int revYear) {
        this.revYear = revYear;
    }

    public void addRevYear(int revYear) {
        this.revYear += revYear;
    }

    public int getTranToday() {
        return tranToday;
    }

    public void setTranToday(int tranToday) {
        this.tranToday = tranToday;
    }

    public void addTranToday() {
        this.tranToday++;
    }

    public int getTranMonth() {
        return tranMonth;
    }

    public void setTranMonth(int tranMonth) {
        this.tranMonth = tranMonth;
    }

    public void addTranMonth() {
        this.tranMonth++;
    }

    public int getTranYear() {
        return tranYear;
    }

    public void setTranYear(int tranYear) {
        this.tranYear = tranYear;
    }

    public void addTranYear() {
        this.tranYear++;
    }

    public int getUptakeCountToday() {
        return uptakeCountToday;
    }

    public void setUptakeCountToday(int uptakeCountToday) {
        this.uptakeCountToday = uptakeCountToday;
    }

    public void addUptakeCountToday() {
        this.uptakeCountToday++;
    }

    public int getUptakeCountMonth() {
        return uptakeCountMonth;
    }

    public void setUptakeCountMonth(int uptakeCountMonth) {
        this.uptakeCountMonth = uptakeCountMonth;
    }

    public void addUptakeCountMonth() {
        this.uptakeCountMonth++;
    }

    public int getUptakeCountYear() {
        return uptakeCountYear;
    }

    public void setUptakeCountYear(int uptakeCountYear) {
        this.uptakeCountYear = uptakeCountYear;
    }

    public void addUptakeCountYear() {
        this.uptakeCountYear++;
    }

    public String getRevTodayFormatted() {
        return CheckData.addDecimal(this.revToday);
    }

    public String getRevMonthFormatted() {
        return CheckData.addDecimal(this.revMonth);
    }

    public String getRevYearFormatted() {
        return CheckData.addDecimal(this.revYear);
    }

    public int getAverageDeliveryDaysToday() {
        return averageDeliveryDaysToday;
    }

    public void setAverageDeliveryDaysToday(int averageDeliveryDaysToday) {
        this.averageDeliveryDaysToday = averageDeliveryDaysToday;
    }

    public int getAverageDeliveryDaysMonth() {
        return averageDeliveryDaysMonth;
    }

    public void setAverageDeliveryDaysMonth(int averageDeliveryDaysMonth) {
        this.averageDeliveryDaysMonth = averageDeliveryDaysMonth;
    }

    public int getAverageDeliveryDaysYear() {
        return averageDeliveryDaysYear;
    }

    public void setAverageDeliveryDaysYear(int averageDeliveryDaysYear) {
        this.averageDeliveryDaysYear = averageDeliveryDaysYear;
    }

    public int getTotalShips() {
        return totalShips;
    }

    public void setTotalShips(int totalShips) {
        this.totalShips = totalShips;
    }

    public void addTotalShips() {
        this.totalShips++;
    }

    public int getTotalDelivered() {
        return totalDelivered;
    }

    public void setTotalDelivered(int totalDelivered) {
        this.totalDelivered = totalDelivered;
    }

    public void addTotalDelivered() {
        this.totalDelivered++;
    }

    public int getTotalInTransit() {
        return totalInTransit;
    }

    public void setTotalInTransit(int totalInTransit) {
        this.totalInTransit = totalInTransit;
    }

    public void addTotalInTransit() {
        this.totalInTransit++;
    }

    public int getTotalReady() {
        return totalReady;
    }

    public void setTotalReady(int totalReady) {
        this.totalReady = totalReady;
    }

    public void addTotalReady() {
        this.totalReady++;
    }

    public int getTotalFailed() {
        return totalFailed;
    }

    public void setTotalFailed(int totalFailed) {
        this.totalFailed = totalFailed;
    }

    public void addTotalFailed() {
        this.totalFailed++;
    }

    private int getPercent(int value) {
        if(value == 0 || totalShips == 0) {
            return 0;
        }
        return (value*100)/totalShips;
    }

    public int getPercentDelivered() {
        return getPercent(totalDelivered);
    }

    public int getPercentInTransit() {
        return getPercent(totalInTransit);
    }

    public int getPercentReady() {
        return getPercent(totalReady);
    }

    public int getPercentFailed() {
        return getPercent(totalFailed);
    }

    public String getMonth12Name() {
        return month12Name;
    }

    public void setMonth12Name(String month12Name) {
        this.month12Name = month12Name;
    }

    public int getMonth12Value() {
        return month12Value;
    }

    public void setMonth12Value(int month12Value) {
        this.month12Value = month12Value;
    }

    public String getMonth11Name() {
        return month11Name;
    }

    public void setMonth11Name(String month11Name) {
        this.month11Name = month11Name;
    }

    public int getMonth11Value() {
        return month11Value;
    }

    public void setMonth11Value(int month11Value) {
        this.month11Value = month11Value;
    }

    public String getMonth10Name() {
        return month10Name;
    }

    public void setMonth10Name(String month10Name) {
        this.month10Name = month10Name;
    }

    public int getMonth10Value() {
        return month10Value;
    }

    public void setMonth10Value(int month10Value) {
        this.month10Value = month10Value;
    }

    public String getMonth9Name() {
        return month9Name;
    }

    public void setMonth9Name(String month9Name) {
        this.month9Name = month9Name;
    }

    public int getMonth9Value() {
        return month9Value;
    }

    public void setMonth9Value(int month9Value) {
        this.month9Value = month9Value;
    }

    public String getMonth8Name() {
        return month8Name;
    }

    public void setMonth8Name(String month8Name) {
        this.month8Name = month8Name;
    }

    public int getMonth8Value() {
        return month8Value;
    }

    public void setMonth8Value(int month8Value) {
        this.month8Value = month8Value;
    }

    public String getMonth7Name() {
        return month7Name;
    }

    public void setMonth7Name(String month7Name) {
        this.month7Name = month7Name;
    }

    public int getMonth7Value() {
        return month7Value;
    }

    public void setMonth7Value(int month7Value) {
        this.month7Value = month7Value;
    }

    public String getMonth6Name() {
        return month6Name;
    }

    public void setMonth6Name(String month6Name) {
        this.month6Name = month6Name;
    }

    public int getMonth6Value() {
        return month6Value;
    }

    public void setMonth6Value(int month6Value) {
        this.month6Value = month6Value;
    }

    public String getMonth5Name() {
        return month5Name;
    }

    public void setMonth5Name(String month5Name) {
        this.month5Name = month5Name;
    }

    public int getMonth5Value() {
        return month5Value;
    }

    public void setMonth5Value(int month5Value) {
        this.month5Value = month5Value;
    }

    public String getMonth4Name() {
        return month4Name;
    }

    public void setMonth4Name(String month4Name) {
        this.month4Name = month4Name;
    }

    public int getMonth4Value() {
        return month4Value;
    }

    public void setMonth4Value(int month4Value) {
        this.month4Value = month4Value;
    }

    public String getMonth3Name() {
        return month3Name;
    }

    public void setMonth3Name(String month3Name) {
        this.month3Name = month3Name;
    }

    public int getMonth3Value() {
        return month3Value;
    }

    public void setMonth3Value(int month3Value) {
        this.month3Value = month3Value;
    }

    public String getMonth2Name() {
        return month2Name;
    }

    public void setMonth2Name(String month2Name) {
        this.month2Name = month2Name;
    }

    public int getMonth2Value() {
        return month2Value;
    }

    public void setMonth2Value(int month2Value) {
        this.month2Value = month2Value;
    }

    public String getMonth1Name() {
        return month1Name;
    }

    public void setMonth1Name(String month1Name) {
        this.month1Name = month1Name;
    }

    public int getMonth1Value() {
        return month1Value;
    }

    public void setMonth1Value(int month1Value) {
        this.month1Value = month1Value;
    }

    public HashMap<String, Integer> getShipmentsPerCountry() {
        return shipmentsPerCountry;
    }

    public void setShipmentsPerCountry(HashMap<String, Integer> shipmentsPerCountry) {
        this.shipmentsPerCountry = shipmentsPerCountry;
    }
}
