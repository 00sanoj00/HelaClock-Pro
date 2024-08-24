package lk.sanoj.helaclok.pro.HelaClockPro.Controller;

import lk.sanoj.helaclok.pro.HelaClockPro.Models.Time;

public class SinhalaTimeConverter {
    private final Time time;

    public SinhalaTimeConverter(Time time) {
        this.time = time;
    }

    public String getHour() {
        switch (time.getHours()) {
            case "01":
            case "13":
                return "එක පසුවී";
            case "02":
            case "14":
                return "දෙක පසුවී";
            case "03":
            case "15":
                return "තුන පසුවී";
            case "04":
            case "16":
                return "හතර පසුවී";
            case "05":
            case "17":
                return "පහ පසුවී";
            case "06":
            case "18":
                return "හය පසුවී";
            case "07":
            case "19":
                return "හත පසුවී";
            case "08":
            case "20":
                return "අට පසුවී";
            case "09":
            case "21":
            case "22":
                return "නවය පසුවී";
            case "10":
                return "දහය පසුවී";
            case "11":
            case "23":
                return "එකොළහයි";
            case "12":
            case "24":
            case "0":
            case "00":
                return "දොළහ පසුවී";
        }
        return "";
    }

    public String getMinutes() {
        switch (time.getMinutes()) {
            case "0":
            case "00":
                return "තත්පර කීපයයි";
            case "01":
                return "විනාඩි එකයි";
            case "02":
                return "විනාඩි දෙකයි";
            case "03":
                return "විනාඩි තුනයි";
            case "04":
                return "විනාඩි හතරයි";
            case "05":
                return "විනාඩි පහයි";
            case "06":
                return "විනාඩි හයයි";
            case "07":
                return "විනාඩි හතයි";
            case "08":
                return "විනාඩි අටයි";
            case "09":
                return "විනාඩි නවයයි";
            case "10":
                return "විනාඩි දහයයි";
            case "11":
                return "විනාඩි එකොළහයි";
            case "12":
                return "විනාඩි දොළහයි";
            case "13":
                return "විනාඩි දහ තුනයි";
            case "14":
                return "විනාඩි දහ හතරයි";
            case "15":
                return "විනාඩි පහ ලොවයි";
            case "16":
                return "විනාඩි දහ සයයි";
            case "17":
                return "විනාඩි දහ හතයි";
            case "18":
                return "විනාඩි දහ අටයි";
            case "19":
                return "විනාඩි දහ නවයයි";
            case "20":
                return "විනාඩි විස්සයි";
            case "21":
                return "විනාඩි විසීඑකයි";
            case "22":
                return "විනාඩි විසිදෙකයි";
            case "23":
                return "විනාඩි විසිතුනයී";
            case "24":
                return "විනාඩි විසිහතරයි";
            case "25":
                return "විනාඩි විසිපහයි";
            case "26":
                return "විනාඩි විසිහයයි";
            case "27":
                return "විනාඩි විසිහතයි";
            case "28":
                return "විනාඩි විසිඅටයි";
            case "29":
                return "විනාඩි විසිනවයයි";
            case "30":
                return "විනාඩි තිහයි";
            case "31":
                return "විනාඩි තිස්එකයි";
            case "32":
                return "විනාඩි තිස්දෙකයි";
            case "33":
                return "විනාඩි තිස්තුනයි";
            case "34":
                return "විනාඩි තිස්හතරයි";
            case "35":
                return "විනාඩි තිස්පහයි";
            case "36":
                return "විනාඩි තිස්හයයි";
            case "37":
                return "විනාඩි තිස්හතයි";
            case "38":
                return "විනාඩි තිස්අටයි";
            case "39":
                return "විනාඩි තිස්නවයයි";
            case "40":
                return "විනාඩි හතලිහයි";
            case "41":
                return "විනාඩි හතලිස් එකයි";
            case "42":
                return "විනාඩි හතලිස් දෙකයි";
            case "43":
                return "විනාඩි හතලිස් තුනයි";
            case "44":
                return "විනාඩි හතලිස් හතරයි";
            case "45":
                return "විනාඩි හතලිස් පහයි";
            case "46":
                return "විනාඩි හතලිස් හයයි";
            case "47":
                return "විනාඩි හතලිස් හතයි";
            case "48":
                return "විනාඩි හතලිස් අටයි";
            case "49":
                return "විනාඩි හතලිස් නවයය";
            case "50":
                return "විනාඩි පනහයි";
            case "51":
                return "විනාඩි පනස් එකයි";
            case "52":
                return "විනාඩි පනස් දෙකයි";
            case "53":
                return "විනාඩි පනස් තුනයි";
            case "54":
                return "විනාඩි පනස් හතරයි";
            case "55":
                return "විනාඩි පනස් පහයි";
            case "56":
                return "විනාඩි පනස් හයයි";
            case "57":
                return "විනාඩි පනස් හතයි";
            case "58":
                return "විනාඩි පනස් අටයි";
            case "59":
                return "විනාඩි පනස් නවයයි";
            default:
                return "N/A";
        }
    }

    public String getMeridiem() {
        switch (time.getMeridiem()) {
            case "am":
            case "AM":
                return "වේලාව පෙරවරු";
            case "pm":
            case "PM":
                return "වේලාව පස්වරු";
        }
        return "";
    }
}
