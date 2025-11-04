package ninjawar.mylib;

import ninjawar.input.MyButton;
import ninjawar.message.Config;
import ninjawar.model.FontTemplate;
import ninjawar.model.MissionPaint;
import ninjawar.model.PointChangeMap;
import ninjawar.model.PopupItemInfo;
import ninjawar.object.mNPC;
import ninjawar.screen.dialog.MessageDialog;
import ninjawar.screen.quest.NPCTalkQuestScreen;
import ninjawar.screen.subtab.SubTabKeyWareHouse;
import ninjawar.screen.subtab.SubTabScr;
import ninjawar.screen.tab.TabInventory;
import ninjawar.screen.tab.TabLearnSkill;
import ninjawar.screen.tab.TabListQuest;
import ninjawar.screen.tab.TabScr;
import ninjawar.screen.tab.TabShop;
import ninjawar.supporter.mColorGdxCustom;

public class mFont {
    public static final int[] COLOR_FONT = {-11062273, 872372223, 861405183, -13393409, -2686721, -1976835329, 13554175};
    public static int[] colorJava = {-16777216, -65536, -15175236, -1, -22016, -11327227, -16755931, -16724992, -9390988, -5428, -16777216, -16711681, -16755931, -256, -11184811, -34953, -16744193, -8075479, -1053713, -8777435, -8401, -1815979, -12652317, -12105913, -680904, -65536, -1, -256, -16724992, -680904, -10256897, -8401};
    public static int[] colorJava1 = {0, 16711680, 1601980, 16777215, 16755200, 5449989, 21285, 52224, 7386228, 16771788, 0, 65535, 21285, 16776960, 5592405, 16742263, 33023, 8701737, 15723503, 7999781, 16768815, 14961237, 4124899, 4671303, 16096312, 16711680, 16777215, 16776960, 52224, 16096312, 6520319, 16768815};
    public static mFont fontChatBoxUserName;
    public static mFont[] fontColorServer;
    public static mFont fontPaintAutoAccept;
    public static mFont fontPaintCharInfo;
    public static mFont fontPaintCharInfo2;
    public static mFont fontPaintCharInfoFocus;
    public static mFont fontPaintDisableButotn;
    public static mFont fontPaintKhu;
    public static mFont fontPaintLevelInfoMe;
    public static mFont fontPaintLv;
    public static mFont fontPaintLvFocus;
    public static mFont fontPaintMapNameWhite;
    public static mFont fontPaintMapNameYellow;
    public static mFont fontPaintMoneyInven;
    public static mFont fontPaintMyName;
    public static mFont fontPaintNameParty;
    public static mFont fontPaintNamePartyFocus;
    public static mFont fontPaintNumParty;
    public static mFont fontPaintNumPartyFull;
    public static mFont fontPaintNumberInventory;
    public static mFont fontPaintSelectOption;
    public static mFont fontPaintSkillName;
    public static mFont fontPaintTab;
    public static mFont fontPaintTagName;
    public static mFont fontPaintTagNameFocus;
    public static mFont fontPaintTextUpgrade;
    public static mFont fontPaintTextUpgradeSuccess;
    public static mFont fontPaintTextUpgradeUp;
    public static mFont fontTextAn;
    public static mFont fontTextCam;
    public static mFont fontTextDo;
    public static mFont fontTextNau;
    public static mFont fontTextTrang;
    public static mFont fontTextXam;
    public static mFont fontTextXanh;
    public static mFont fontTmp;
    public static mFont fontTmp2;
    public static mFont font_NPC_name;
    public static boolean isLoadFontDone = false;
    public static mFont nameFontGreen;
    public static mFont nameFontRed;
    public static mFont nameFontYellow;
    public static mFont number_green;
    public static mFont number_orange;
    public static mFont number_red;
    public static mFont number_yellow;
    public static int sizeHardCode = 14;
    public static String staccato = "staccato";
    public static String str = " 0123456789+-*='_?.,<>/[]{}!@#$%^&*():aáàảãạâấầẩẫậăắằẳẵặbcdđeéèẻẽẹêếềểễệfghiíìỉĩịjklmnoóòỏõọôốồổỗộơớờởỡợpqrstuúùủũụưứừửữựvxyýỳỷỹỵzwAÁÀẢÃẠĂẰẮẲẴẶÂẤẦẨẪẬBCDĐEÉÈẺẼẸÊẾỀỂỄỆFGHIÍÌỈĨỊJKLMNOÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢPQRSTUÚÙỦŨỤƯỨỪỬỮỰVXYÝỲỶỸỴZW";
    public static String tahoma = "SFUFuturaBook";
    public static String tahomaBd = "barmeneb";
    public static mFont tahoma_7;
    public static mFont tahoma_7_blue;
    public static mFont tahoma_7_blue1;
    public static mFont tahoma_7_brown;
    public static mFont tahoma_7_brown_12;
    public static mFont tahoma_7_brown_small;
    public static mFont tahoma_7_custom;
    public static mFont tahoma_7_gray;
    public static mFont tahoma_7_green;
    public static mFont tahoma_7_green2;
    public static mFont tahoma_7_grey;
    public static mFont tahoma_7_greySmall;
    public static mFont tahoma_7_info_blue;
    public static mFont tahoma_7_info_cam;
    public static mFont tahoma_7_info_cam_dam;
    public static mFont tahoma_7_info_do;
    public static mFont tahoma_7_info_tim;
    public static mFont tahoma_7_info_vang;
    public static mFont tahoma_7_info_xam;
    public static mFont tahoma_7_info_xanh_la;
    public static mFont tahoma_7_info_xian;
    public static mFont tahoma_7_orange;
    public static mFont tahoma_7_orange_r;
    public static mFont tahoma_7_red;
    public static mFont tahoma_7_red_small;
    public static mFont tahoma_7_small;
    public static mFont tahoma_7_white;
    public static mFont tahoma_7_whiteSmall;
    public static mFont tahoma_7_white_11;
    public static mFont tahoma_7_white_12;
    public static mFont tahoma_7_white_13;
    public static mFont tahoma_7_white_border_black;
    public static mFont tahoma_7_white_border_black_clone;
    public static mFont tahoma_7_white_border_black_medium;
    public static mFont tahoma_7_white_border_black_medium_clone;
    public static mFont tahoma_7_white_border_black_small;
    public static mFont tahoma_7_white_small;
    public static mFont tahoma_7_yellow;
    public static mFont tahoma_7b_black;
    public static mFont tahoma_7b_blue;
    public static mFont tahoma_7b_blue_black;
    public static mFont tahoma_7b_blue_border_black;
    public static mFont tahoma_7b_brown;
    public static mFont tahoma_7b_brown_small;
    public static mFont tahoma_7b_custom;
    public static mFont tahoma_7b_dark;
    public static mFont tahoma_7b_focus;
    public static mFont tahoma_7b_green;
    public static mFont tahoma_7b_green2;
    public static mFont tahoma_7b_green2Small;
    public static mFont tahoma_7b_greenSmall;
    public static mFont tahoma_7b_green_border_black;
    public static mFont tahoma_7b_green_border_black_medium;
    public static mFont tahoma_7b_green_border_black_small;
    public static mFont tahoma_7b_green_party;
    public static mFont tahoma_7b_light_blue;
    public static mFont tahoma_7b_orange_border_black;
    public static mFont tahoma_7b_pink_border_black;
    public static mFont tahoma_7b_purple_border_black;
    public static mFont tahoma_7b_red;
    public static mFont tahoma_7b_red_border_black;
    public static mFont tahoma_7b_red_small;
    public static mFont tahoma_7b_red_spec;
    public static mFont tahoma_7b_white;
    public static mFont tahoma_7b_white13;
    public static mFont tahoma_7b_white_black;
    public static mFont tahoma_7b_white_border_black;
    public static mFont tahoma_7b_white_border_black_medium;
    public static mFont tahoma_7b_white_border_black_small;
    public static mFont tahoma_7b_white_green;
    public static mFont tahoma_7b_white_orange;
    public static mFont tahoma_7b_white_party;
    public static mFont tahoma_7b_yellow;
    public static mFont tahoma_7b_yellowSmall;
    public static mFont tahoma_7b_yellowSmall2;
    public static mFont tahoma_7b_yellow_black;
    public static mFont tahoma_7b_yellow_border_black;
    public static mFont tahoma_7b_yellow_border_black_small;
    public static mFont tahoma_7b_yellow_border_orange;
    public static mFont tahoma_7b_yellow_party;
    public static mFont tahoma_8b;
    public static mFont tahoma_blue_name;
    public static mFont tahoma_brown_dv;
    public static mFont tahoma_brown_focus_dv;
    public static mFont tahoma_brown_focus_dv_12;
    public static mFont tahoma_green_name;
    public static mFont tahoma_name_char_inventory;
    public static mFont tahoma_orange_name;
    public static mFont tahoma_pink_name;
    public static mFont tahoma_place_holder;
    public static mFont tahoma_purple_name;
    public static mFont tahoma_red_name;
    public static mFont tahoma_toc_he_detail;
    public static mFont tahoma_toc_he_detail_larger;
    public static mFont tahoma_toc_he_title;
    public static mFont bigNumber_While = tahoma_7b_white;
    public static mFont bigNumber_black;
    public static mFont bigNumber_blue = tahoma_7_blue1;
    public static mFont bigNumber_green = number_green;
    public static mFont bigNumber_orange = number_orange;
    public static mFont bigNumber_red = number_red;
    public static mFont bigNumber_yellow = number_yellow;
    public int color;
    public SystemFont fN;
    public String fontNameFile = "";
    private int height;
    public short idFontServer;
    public int size;

    public mFont(String fontNameFile2, int size2, int color2, boolean useBorder, int colorBorder, int sizeBorder, boolean useShadow, int colorShadow) {
        this.fontNameFile = fontNameFile2;
        this.size = size2;
        this.color = color2;
        this.fN = new SystemFont(fontNameFile2, size2, color2, useBorder, colorBorder, sizeBorder, useShadow, colorShadow);
    }

    public mFont(String fontNameFile2, int size2, int color2) {
        this.fontNameFile = fontNameFile2;
        this.size = size2;
        this.color = color2;
        this.fN = new SystemFont(fontNameFile2, size2, color2, false, 1, 0, false, 1);
    }

    public mFont() {
    }

    public mFont cloneFontColor(int color2) {
        mFont fontClone = new mFont();
        fontClone.fontNameFile = this.fontNameFile;
        fontClone.size = this.size;
        fontClone.color = color2;
        fontClone.fN = new SystemFont(this.fontNameFile, this.size, color2, false, 1, 0, false, 1);
        return fontClone;
    }

    public static int getFontSizeMedium() {
        return (int) (((float) 14) * mGraphics.zoomLevel * mGraphics.zoomGdx);
    }

    public static int getFontSizeMedium2() {
        return (int) (((float) 12) * mGraphics.zoomLevel * mGraphics.zoomGdx);
    }

    public static int getFontSizeSmall() {
        return (int) (((float) 10) * mGraphics.zoomLevel * mGraphics.zoomGdx);
    }

    public static int getFontSize11() {
        return (int) (((float) 11) * mGraphics.zoomLevel * mGraphics.zoomGdx);
    }

    private static int getFontSize12() {
        return (int) (((float) 12) * mGraphics.zoomLevel * mGraphics.zoomGdx);
    }

    private static int getFontSize8() {
        return (int) (((float) 8) * mGraphics.zoomLevel * mGraphics.zoomGdx);
    }

    private static int getFontSize12_5() {
        return (int) (mGraphics.zoomLevel * 12.5f * mGraphics.zoomGdx);
    }

    private static int getFontSize13() {
        return (int) (((float) 13) * mGraphics.zoomLevel * mGraphics.zoomGdx);
    }

    public static int getFontSizeName() {
        return (int) (((float) 16) * mGraphics.zoomLevel * mGraphics.zoomGdx);
    }

    private static int getFontSizeMapName() {
        return (int) (((float) 8) * mGraphics.zoomLevel * mGraphics.zoomGdx);
    }

    private static int getFontSizeLevel() {
        return (int) (((float) 10) * mGraphics.zoomLevel * mGraphics.zoomGdx);
    }

    private static int getFontSizeNameBig() {
        return (int) (((float) 20) * mGraphics.zoomLevel * mGraphics.zoomGdx);
    }

    private static int getFontSizeNameInventory() {
        return (int) (((float) 17) * mGraphics.zoomLevel * mGraphics.zoomGdx);
    }

    public static void init() {
        if (!isLoadFontDone) {
            isLoadFontDone = false;
            int fontSizeMedium = getFontSizeMedium();
            int fontSizeMedium2 = getFontSizeMedium2();
            if (Config.isOfflineMode) {
                initHardCodeFontServer();
            }
            fontPaintTab = new mFont(tahomaBd, getFontSizeMedium(), 2135627775);
            fontPaintMyName = new mFont(tahomaBd, getFontSizeSmall(), -143974657);
            fontPaintTagName = new mFont("tahomabd", getFontSizeSmall(), mColorGdxCustom.BROWN_DV);
            fontPaintTagNameFocus = new mFont("tahomabd", getFontSizeSmall(), mColorGdxCustom.BROWN_DV_FOCUS);
            fontTextTrang = new mFont("barmeneb", getFontSizeSmall(), SystemFont.WHITE_COLOR);
            fontTextCam = new mFont("barmeneb", getFontSize12(), SystemFont.ORANGE_COLOR);
            fontTextNau = new mFont("barmeneb", getFontSize12(), SystemFont.BROWN_COLOR);
            fontTextXam = new mFont("barmeneb", getFontSize12(), SystemFont.GRAY_COLOR);
            fontTextDo = new mFont("barmeneb", getFontSize12(), SystemFont.RED_COLOR);
            fontTextXanh = new mFont("barmeneb", getFontSize12(), SystemFont.GREEN_COLOR);
            fontTextAn = new mFont("barmeneb", getFontSize12(), -1267972865);
            fontPaintSkillName = new mFont("barmeneb", getFontSize11(), SystemFont.BROWN_DV);
            fontPaintMoneyInven = new mFont(tahomaBd, getFontSizeSmall(), -1267972865);
            fontPaintTextUpgrade = new mFont(tahomaBd, getFontSizeMedium(), -1267972865);
            fontPaintTextUpgradeUp = new mFont(tahomaBd, getFontSizeMedium(), SystemFont.GREEN_COLOR);
            fontPaintTextUpgradeSuccess = new mFont(tahomaBd, getFontSizeMedium(), SystemFont.GREEN_COLOR);
            fontPaintLevelInfoMe = new mFont(tahomaBd, getFontSizeLevel(), SystemFont.WHITE_COLOR, true, SystemFont.BLACK_COLOR, 1, false, 0);
            fontPaintMapNameWhite = new mFont(tahomaBd, getFontSizeMapName(), SystemFont.WHITE_COLOR, true, SystemFont.BLACK_COLOR, 1, false, 0);
            fontPaintMapNameYellow = new mFont(tahomaBd, getFontSizeLevel(), SystemFont.YELLOW_COLOR, true, SystemFont.BLACK_COLOR, 1, false, 0);
            fontPaintNumberInventory = new mFont(tahomaBd, fontSizeMedium, -783935745);
            fontPaintKhu = new mFont(tahomaBd, getFontSizeMedium(), SystemFont.RED_COLOR, true, SystemFont.BLACK_COLOR, 1, false, 0);
            tahoma_blue_name = new mFont(tahomaBd, getFontSizeName(), SystemFont.BLUE_COLOR, true, SystemFont.BLACK_COLOR, 1, false, 0);
            tahoma_red_name = new mFont(tahomaBd, getFontSizeName(), SystemFont.RED_COLOR, true, SystemFont.BLACK_COLOR, 1, false, 0);
            tahoma_green_name = new mFont(tahomaBd, getFontSizeName(), SystemFont.GREEN_COLOR, true, SystemFont.BLACK_COLOR, 1, false, 0);
            tahoma_orange_name = new mFont(tahomaBd, getFontSizeName(), SystemFont.ORANGE_COLOR, true, SystemFont.BLACK_COLOR, 1, false, 0);
            tahoma_purple_name = new mFont(tahomaBd, getFontSizeName(), SystemFont.PURPLE_COLOR, true, SystemFont.BLACK_COLOR, 1, false, 0);
            tahoma_pink_name = new mFont(tahomaBd, getFontSizeName(), SystemFont.PINK_TEAM_COLOR, true, SystemFont.BLACK_COLOR, 1, false, 0);
            tahoma_7_white_border_black_medium = new mFont(tahoma, getFontSizeMedium(), SystemFont.WHITE_COLOR, true, SystemFont.BLACK_COLOR, 1, false, 0);
            tahoma_7_white_border_black = new mFont(tahoma, getFontSizeName(), SystemFont.WHITE_COLOR, true, SystemFont.BLACK_COLOR, 1, false, 0);
            tahoma_7_white_border_black_small = new mFont(tahoma, getFontSizeSmall(), SystemFont.WHITE_COLOR, true, SystemFont.BLACK_COLOR, 1, false, 0);
            tahoma_7_white_border_black_medium_clone = new mFont(tahoma, getFontSize12(), SystemFont.WHITE_COLOR, true, SystemFont.BLACK_COLOR, 1, false, 0);
            tahoma_7_white_border_black_clone = new mFont(tahoma, getFontSize12(), SystemFont.WHITE_COLOR, true, SystemFont.BLACK_COLOR, 1, false, 0);
            tahoma_7b_yellow_border_orange = new mFont(tahomaBd, getFontSizeName(), SystemFont.YELLOW_COLOR, true, SystemFont.ORANGE_COLOR, 1, false, 0);
            tahoma_7b_yellow_border_black = new mFont(tahomaBd, getFontSizeName(), SystemFont.YELLOW_COLOR, true, SystemFont.BLACK_COLOR, 1, false, 0);
            tahoma_7b_white_border_black = new mFont(tahomaBd, getFontSizeName(), SystemFont.WHITE_COLOR, true, SystemFont.BLACK_COLOR, 1, false, 0);
            font_NPC_name = new mFont(tahomaBd, getFontSizeName(), 63487, true, SystemFont.BLACK_COLOR, 1, false, 0);
            tahoma_7b_orange_border_black = new mFont(tahomaBd, getFontSizeName(), SystemFont.ORANGE_COLOR, true, SystemFont.BLACK_COLOR, 1, false, 0);
            tahoma_7b_red_border_black = new mFont(tahomaBd, getFontSizeName(), SystemFont.RED_COLOR, true, SystemFont.BLACK_COLOR, 1, false, 0);
            tahoma_7b_blue_border_black = new mFont(tahomaBd, getFontSizeName(), SystemFont.BLUE_COLOR, true, SystemFont.BLACK_COLOR, 1, false, 0);
            tahoma_7b_purple_border_black = new mFont(tahomaBd, getFontSizeName(), SystemFont.PURPLE_COLOR, true, SystemFont.BLACK_COLOR, 1, false, 0);
            tahoma_7b_pink_border_black = new mFont(tahomaBd, getFontSizeName(), SystemFont.PINK_TEAM_COLOR, true, SystemFont.BLACK_COLOR, 1, false, 0);
            tahoma_7b_green_border_black = new mFont(tahomaBd, getFontSizeName(), 1526661375, true, SystemFont.BLACK_COLOR, 1, false, 0);
            tahoma_7b_white_black = new mFont(tahomaBd, fontSizeMedium, SystemFont.WHITE_COLOR, true, SystemFont.BLACK_COLOR, 1, false, 0);
            tahoma_7b_white = new mFont(tahomaBd, fontSizeMedium, SystemFont.WHITE_COLOR);
            tahoma_7b_green = new mFont(tahomaBd, fontSizeMedium, SystemFont.GREEN_COLOR);
            tahoma_7b_white13 = new mFont(tahomaBd, fontSizeMedium2, SystemFont.WHITE_COLOR);
            tahoma_7b_custom = new mFont(tahomaBd, fontSizeMedium, -458393601);
            tahoma_7_custom = new mFont(tahoma, fontSizeMedium, -458393601);
            int i = fontSizeMedium;
            tahoma_7b_white_border_black_medium = new mFont(tahomaBd, i, SystemFont.WHITE_COLOR, true, SystemFont.BLACK_COLOR, 1, false, 0);
            tahoma_7b_green_border_black_medium = new mFont(tahomaBd, i, 1572343039, true, SystemFont.BLACK_COLOR, 1, false, 0);
            tahoma_7b_yellow_border_black_small = new mFont(tahomaBd, getFontSizeSmall(), SystemFont.YELLOW_COLOR, true, SystemFont.BLACK_COLOR, 1, false, 0);
            tahoma_7b_white_border_black_small = new mFont(tahomaBd, getFontSizeSmall(), SystemFont.WHITE_COLOR, true, SystemFont.BLACK_COLOR, 1, false, 0);
            tahoma_7b_green_border_black_small = new mFont(tahomaBd, getFontSizeSmall(), SystemFont.GREEN_COLOR, true, SystemFont.BLACK_COLOR, 1, false, 0);
            tahoma_7b_yellow_party = new mFont(tahomaBd, getFontSizeSmall(), -8060161);
            tahoma_7b_white_party = new mFont(tahomaBd, getFontSizeSmall(), SystemFont.WHITE_COLOR);
            tahoma_7b_green_party = new mFont(tahomaBd, getFontSizeSmall(), 1524704767);
            tahoma_8b = new mFont(tahoma, fontSizeMedium, SystemFont.BLACK_COLOR, false, SystemFont.WHITE_COLOR, 1, false, 0);
            tahoma_7_small = new mFont(tahomaBd, getFontSizeSmall(), -303174145, true, SystemFont.BLACK_COLOR, 1, false, 0);
            fontPaintNumPartyFull = new mFont(tahoma, getFontSizeMedium2(), SystemFont.RED_COLOR);
            fontPaintNumParty = new mFont(tahoma, getFontSizeMedium2(), SystemFont.GREEN_COLOR);
            fontPaintNameParty = new mFont(tahoma, getFontSizeMedium2(), -2042223617);
            fontPaintNamePartyFocus = new mFont(tahoma, getFontSizeMedium2(), -8060161);
            fontPaintAutoAccept = new mFont(tahoma, getFontSizeMedium2(), SystemFont.BROWN_COLOR);
            fontPaintSelectOption = new mFont(tahoma, getFontSizeMedium(), SystemFont.BROWN_COLOR);
            tahoma_7b_red_small = new mFont(tahomaBd, getFontSize8(), SystemFont.RED_COLOR, true, SystemFont.BLACK_COLOR, 1, false, 0);
            tahoma_place_holder = new mFont(tahomaBd, fontSizeMedium, SystemFont.PLACE_HOLDER_COLOR);
            tahoma_7_gray = new mFont(tahomaBd, fontSizeMedium, SystemFont.GRAY_COLOR);
            tahoma_7_green = new mFont(tahomaBd, fontSizeMedium, SystemFont.GREEN_COLOR);
            tahoma_7_orange = new mFont(tahomaBd, fontSizeMedium, SystemFont.ORANGE_COLOR);
            tahoma_7_orange_r = new mFont(tahoma, fontSizeMedium, -7919617);
            tahoma_7b_brown = new mFont(tahomaBd, fontSizeMedium, SystemFont.BROWN_COLOR);
            tahoma_7_brown = new mFont(tahoma, fontSizeMedium, SystemFont.BROWN_COLOR);
            tahoma_7_brown_12 = new mFont(tahoma, fontSizeMedium2, SystemFont.BROWN_COLOR);
            fontChatBoxUserName = new mFont(tahomaBd, getFontSizeSmall(), SystemFont.BROWN_COLOR);
            tahoma_7_brown_small = new mFont(tahoma, getFontSizeSmall(), SystemFont.BROWN_COLOR);
            tahoma_7b_brown_small = new mFont(tahomaBd, getFontSizeSmall(), SystemFont.BROWN_COLOR);
            tahoma_7 = new mFont(tahoma, fontSizeMedium, SystemFont.BLACK_COLOR);
            tahoma_7_white = new mFont(tahoma, fontSizeMedium, SystemFont.WHITE_COLOR);
            tahoma_7_yellow = new mFont(tahoma, fontSizeMedium, SystemFont.YELLOW_COLOR);
            tahoma_7_white_11 = new mFont(tahoma, getFontSize11(), SystemFont.WHITE_COLOR);
            tahoma_7_white_12 = new mFont(tahoma, getFontSize12(), SystemFont.WHITE_COLOR);
            tahoma_7_white_13 = new mFont(tahoma, getFontSize13(), SystemFont.WHITE_COLOR);
            tahoma_7_white_small = new mFont(tahoma, getFontSizeSmall(), SystemFont.WHITE_COLOR);
            tahoma_7_red_small = new mFont(tahoma, getFontSizeSmall(), SystemFont.RED_COLOR);
            tahoma_7b_white_orange = new mFont(tahomaBd, getFontSizeName(), SystemFont.WHITE_COLOR, true, SystemFont.ORANGE_COLOR, 2, false, 0);
            tahoma_7_red = new mFont(tahomaBd, fontSizeMedium, SystemFont.RED_SPEC_COLOR);
            tahoma_7_info_do = new mFont(tahoma, fontSizeMedium, -16776961);
            tahoma_7_info_blue = new mFont(tahoma, fontSizeMedium, 1706819071);
            tahoma_7_info_xian = new mFont(tahoma, fontSizeMedium, 167182335);
            tahoma_7_info_cam = new mFont(tahoma, fontSizeMedium, -6553345);
            tahoma_7_info_vang = new mFont(tahoma, fontSizeMedium, -135397121);
            tahoma_7_info_cam_dam = new mFont(tahoma, fontSizeMedium, -73793281);
            tahoma_7_info_xanh_la = new mFont(tahoma, fontSizeMedium, 1308297727);
            tahoma_7_info_xam = new mFont(tahoma, fontSizeMedium, -1313886977);
            tahoma_7_info_tim = new mFont(tahoma, fontSizeMedium, -642061313);
            tahoma_7b_white_green = new mFont(tahomaBd, fontSizeMedium, SystemFont.WHITE_COLOR, true, SystemFont.GREEN_BTN_BORDER, 2, false, 0);
            tahoma_brown_dv = new mFont(tahomaBd, fontSizeMedium, SystemFont.BROWN_DV);
            fontTmp = new mFont(tahomaBd, fontSizeMedium, SystemFont.TOC_HE_DETAIL);
            fontTmp2 = new mFont(tahomaBd, getFontSizeNameBig(), SystemFont.TOC_HE_DETAIL);
            fontPaintCharInfo = new mFont(tahomaBd, getFontSizeSmall(), SystemFont.WHITE_COLOR);
            fontPaintCharInfo2 = new mFont(tahomaBd, fontSizeMedium, -762218497);
            fontPaintCharInfoFocus = new mFont(tahomaBd, fontSizeMedium, 263129343);
            fontPaintDisableButotn = new mFont(tahomaBd, fontSizeMedium, -2038003969);
            fontPaintLvFocus = new mFont(tahomaBd, fontSizeMedium, -1673389825);
            fontPaintLv = new mFont(tahomaBd, fontSizeMedium, 2018918399);
            tahoma_brown_focus_dv = new mFont(tahomaBd, fontSizeMedium, SystemFont.BROWN_DV_FOCUS);
            tahoma_brown_focus_dv_12 = new mFont(tahomaBd, fontSizeMedium2, SystemFont.BROWN_DV_FOCUS);
            tahoma_7b_blue = new mFont(tahomaBd, fontSizeMedium, SystemFont.BLUE_COLOR);
            tahoma_7b_blue_black = new mFont(tahomaBd, fontSizeMedium, SystemFont.BLUE_COLOR, true, SystemFont.BLACK_COLOR, 2, false, 0);
            tahoma_7b_black = new mFont(tahomaBd, fontSizeMedium, SystemFont.BLACK_COLOR);
            tahoma_7b_yellow = new mFont(tahomaBd, fontSizeMedium, SystemFont.YELLOW_COLOR);
            tahoma_7b_yellow_black = new mFont(tahomaBd, fontSizeMedium, SystemFont.YELLOW_COLOR, true, SystemFont.BLACK_COLOR, 2, false, 0);
            tahoma_7b_light_blue = new mFont(tahomaBd, fontSizeMedium, SystemFont.LIGHT_BLUE_COLOR);
            tahoma_7b_red = new mFont(tahomaBd, fontSizeMedium, SystemFont.RED_COLOR);
            tahoma_7b_red_spec = new mFont(tahomaBd, fontSizeMedium, SystemFont.RED_SPEC_COLOR);
            tahoma_toc_he_detail = new mFont("tahoma", getFontSize12(), SystemFont.TOC_HE_DETAIL);
            tahoma_toc_he_detail_larger = new mFont("tahoma", getFontSize12_5(), SystemFont.TOC_HE_DETAIL);
            tahoma_toc_he_title = new mFont(tahomaBd, fontSizeMedium, SystemFont.TOC_HE_TITLE);
            tahoma_name_char_inventory = new mFont(tahomaBd, getFontSizeNameInventory(), SystemFont.TOC_HE_TITLE);
            tahoma_7b_dark = new mFont(tahomaBd, fontSizeMedium, SystemFont.DARK_COLLOR);
            bigNumber_red = new mFont(staccato, getFontSize12(), SystemFont.RED_COLOR);
            bigNumber_While = new mFont(staccato, getFontSize12(), SystemFont.WHITE_COLOR);
            bigNumber_yellow = new mFont(staccato, getFontSize12(), SystemFont.YELLOW_COLOR);
            bigNumber_green = new mFont(staccato, getFontSize12(), SystemFont.GREEN_COLOR);
            bigNumber_orange = new mFont(staccato, getFontSize12(), SystemFont.ORANGE_COLOR);
            bigNumber_blue = new mFont(staccato, getFontSize12(), SystemFont.BLUE_COLOR);
            bigNumber_black = new mFont(staccato, getFontSize12(), SystemFont.BLACK_COLOR);
            nameFontRed = tahoma_7_red;
            nameFontYellow = tahoma_7_yellow;
            nameFontGreen = tahoma_7_green;
            tahoma_7b_yellowSmall = new mFont(tahomaBd, getFontSizeSmall(), SystemFont.YELLOW_COLOR);
            tahoma_7_greySmall = tahoma_7_grey;
            tahoma_7b_yellowSmall2 = tahoma_7_yellow;
            tahoma_7b_green2Small = tahoma_7b_green2;
            mFont mfont = tahoma_7_white;
            tahoma_7_whiteSmall = mfont;
            tahoma_7b_greenSmall = tahoma_7b_green;
            MyButton.FONT_DEFAULT = mfont;
            PointChangeMap.fontPaint = tahoma_7b_white;
            mNPC.fontPaintName = tahoma_7b_white_border_black;
            mNPC.fontPaintNameFocus = tahoma_7b_red_border_black;
            MissionPaint.fontPaintMission = tahoma_7_small;
            MissionPaint.fontPaintTile = tahoma_7b_white_orange;
            TabScr.fontPaintTile = tahoma_7b_white;
            TabInventory.fontPaintMoney = fontPaintMoneyInven;
            TabInventory.fontPaintNameChar = tahoma_name_char_inventory;
            TabInventory.fontPaintTagName = tahoma_brown_dv;
            TabInventory.fontPaintCharInfo = fontPaintCharInfo;
            TabInventory.fontPaintCharInfo2 = fontPaintCharInfo2;
            TabInventory.fontPaintCharInfoFocus = fontPaintCharInfoFocus;
            TabInventory.fontPaintTagNameFocus = tahoma_brown_focus_dv;
            TabLearnSkill.fontPaintMoney = tahoma_7_white_small;
            TabLearnSkill.fontPaintLockedLv = tahoma_7_red_small;
            TabLearnSkill.fontPaintNameChar = tahoma_name_char_inventory;
            TabLearnSkill.fontPaintTagName = tahoma_brown_dv;
            TabLearnSkill.fontPaintSkillName = fontPaintSkillName;
            TabLearnSkill.fontPaintTagNameFocus = tahoma_brown_focus_dv;
            TabListQuest.fontPaint = tahoma_brown_dv;
            TabListQuest.fontPaintFocus = tahoma_brown_focus_dv;
            TabListQuest.fontPaintText = tahoma_7_brown;
            TabListQuest.fontPaintTextFocus = tahoma_7_white;
            TabListQuest.fontPlaintItemNum = tahoma_7_small;
            TabListQuest.fontPaintPhanThuong = tahoma_7b_brown;
            TabListQuest.fontPaintDetail = tahoma_7_brown;
            PopupItemInfo.fontPaintNameItem = tahoma_7_white;
            TabShop.fontPaintMoney = tahoma_7_brown_small;
            TabShop.fontPaintNameChar = tahoma_name_char_inventory;
            TabShop.fontPaintTagName = tahoma_brown_dv;
            TabShop.fontPaintTagNameFocus = tahoma_brown_focus_dv;
            MessageDialog.fontPaint = tahoma_7;
            SubTabScr.fontPaintTile = tahoma_7b_white;
            NPCTalkQuestScreen.fontPaintName = tahoma_brown_dv;
            NPCTalkQuestScreen.fontPaint = tahoma_7;
            SubTabKeyWareHouse.fontPaintPlaceHolder = tahoma_place_holder;
            SubTabKeyWareHouse.fontPaintText = tahoma_7b_brown;
            SubTabKeyWareHouse.fontPaintTitle = tahoma_7b_brown;
            isLoadFontDone = true;
        }
    }

    public void setHeight(int height2) {
        this.height = height2;
    }

    public int getWidth(String st) {
        SystemFont systemFont = this.fN;
        if (systemFont != null) {
            return systemFont.getWidth(st);
        }
        return 0;
    }

    public float getWidthF(String st) {
        SystemFont systemFont = this.fN;
        if (systemFont != null) {
            return systemFont.getWidthF(st) + 0.2f;
        }
        return 0.0f;
    }

    public int getHeight() {
        SystemFont systemFont = this.fN;
        if (systemFont != null) {
            return systemFont.getHeight();
        }
        return 0;
    }

    public int getHeightMore() {
        SystemFont systemFont = this.fN;
        if (systemFont != null) {
            return systemFont.getHeight() + 5;
        }
        return 0;
    }

    public void drawString(mGraphics g, String st, int x, int y, int align) {
        drawString(g, st, x, y, align, false, 0, 100);
    }

    public void drawString(mGraphics g, String st, int x, int y, int align, boolean useClip, int angle, int percentOpacity) {
        SystemFont systemFont = this.fN;
        if (systemFont != null) {
            systemFont.drawString(g, st, (float) x, y, align, useClip, angle, percentOpacity);
            return;
        }
        int i = x;
    }

    public void drawString(mGraphics g, String st, float x, float y, int align) {
        drawString(g, st, (int) x, (int) y, align, false, 0, 100);
    }

    public void drawString(mGraphics g, String st, int x, float y, int align) {
        drawString(g, st, x, (int) y, align, false, 0, 100);
    }

    public void drawString(mGraphics g, String st, float x, int y, int align) {
        drawString(g, st, (int) x, y, align, false, 0, 100);
    }

    public void drawString(mGraphics g, String st, int x, int y) {
        drawString(g, st, x, y, 0, false, 0, 100);
    }

    public void drawString(mGraphics g, String st, int x, int y, int align, boolean uClip) {
        drawString(g, st, x, y, align, uClip, 0, 100);
    }

    public void drawStringBd(mGraphics g, String st, int x, int y, int align, mFont font) {
        mFont mfont = font;
        if (mfont != null) {
            mGraphics mgraphics = g;
            String str2 = st;
            int i = align;
            mfont.fN.drawString(mgraphics, str2, x - 1, y - 1, i);
            mGraphics mgraphics2 = g;
            String str3 = st;
            int i2 = align;
            mfont.fN.drawString(mgraphics2, str3, x - 1, y + 1, i2);
            mfont.fN.drawString(mgraphics, str2, x + 1, y - 1, i);
            mfont.fN.drawString(mgraphics2, str3, x + 1, y + 1, i2);
            int i3 = x;
            mfont.fN.drawString(mgraphics, str2, i3, y - 1, i);
            mfont.fN.drawString(mgraphics, str2, i3, y + 1, i);
            int i4 = y;
            mfont.fN.drawString(mgraphics, str2, x + 1, i4, i);
            mfont.fN.drawString(mgraphics, str2, x - 1, i4, i);
        }
        this.fN.drawString(g, st, x, y, align);
    }

    public VectorCustom splitFontVector(String src, int lineWidth) {
        VectorCustom result = new VectorCustom();
        SystemFont systemFont = this.fN;
        if (systemFont != null) {
            result = systemFont.splitFontVector(src, lineWidth);
        }
        if (result != null && result.size() > 0) {
            for (int i = 0; i < result.size(); i++) {
                String text = result.elementAt(i).toString();
                if (text.startsWith(" ")) {
                    result.set(i, text.substring(1));
                }
            }
        }
        return result;
    }

    public VectorCustom splitFontVectorOrigin2(String src, int lineWidth) {
        VectorCustom result = new VectorCustom();
        SystemFont systemFont = this.fN;
        if (systemFont != null) {
            return systemFont.splitFontVector2(src, lineWidth);
        }
        return result;
    }

    public String[] splitFontArray(String src, int lineWidth) {
        VectorCustom lines = splitFontVector(src, lineWidth);
        String[] arr = new String[lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            String text = lines.elementAt(i).toString();
            if (text.startsWith(" ")) {
                text = text.substring(1);
            }
            arr[i] = text;
        }
        return arr;
    }

    public String[] splitFontArrayOrigin2(String src, int lineWidth) {
        VectorCustom lines = splitFontVectorOrigin2(src, lineWidth);
        String[] arr = new String[lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            arr[i] = lines.elementAt(i).toString();
        }
        return arr;
    }

    public void drawStringBorder(mGraphics g, String st, int x, int y, int align, mFont font2) {
        drawStringBd(g, st, x, y, align, font2);
    }

    public static void initFontServer(FontTemplate[] fontTemplates) {
        if (fontTemplates != null) {
            fontColorServer = new mFont[fontTemplates.length];
            for (int i = 0; i < fontTemplates.length; i++) {
                fontColorServer[i] = new mFont(fontTemplates[i].fontNameFile, fontTemplates[i].fontSize, fontTemplates[i].color, fontTemplates[i].colorBorder != 0, fontTemplates[i].colorBorder, fontTemplates[i].sizeBorder, fontTemplates[i].colorShadow != 0, fontTemplates[i].colorShadow);
                fontColorServer[i].idFontServer = fontTemplates[i].id;
            }
        }
    }

    public static mFont getFontServerFromServer(short id) {
        if (fontColorServer == null) {
            return tahoma_7;
        }
        int i = 0;
        while (true) {
            mFont[] mfontArr = fontColorServer;
            if (i >= mfontArr.length) {
                return tahoma_7;
            }
            mFont mfont = mfontArr[i];
            if (mfont.idFontServer == id) {
                return mfont;
            }
            i++;
        }
    }

    public static void initHardCodeFontServer() {
        FontTemplate[] fontTemplates = new FontTemplate[COLOR_FONT.length];
        for (int i = 0; i < fontTemplates.length; i++) {
            fontTemplates[i] = new FontTemplate((short) i, COLOR_FONT[i], (byte) getFontSizeMedium(), tahomaBd);
        }
        initFontServer(fontTemplates);
    }

    public int getHeightH(String st) {
        SystemFont systemFont = this.fN;
        if (systemFont != null) {
            return systemFont.getHeightH(st);
        }
        return 0;
    }

    public static String addColorByIndex(String text, int start, int end, String hex) {
        return text.substring(0, start) + "[" + hex + "]" + text.substring(start, end) + "[]" + text.substring(end);
    }

    public static String addColorByKey(String text, String key, String hex) {
        int start = text.indexOf(key);
        if (start > -1) {
            return addColorByIndex(text, start, key.length() + start, hex);
        }
        return "";
    }
}
