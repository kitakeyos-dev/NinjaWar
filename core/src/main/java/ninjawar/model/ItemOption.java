package ninjawar.model;

import java.util.Iterator;
import java.util.Vector;
import ninjawar.mylib.mGraphics;
import ninjawar.myscr.Res;
import ninjawar.template.ItemOptionTemplates;

public class ItemOption {
    public byte active;
    public byte[] colorParam;
    public int h;
    public String optionStringTemp = "";
    public ItemOptionTemplate optionTemplate = new ItemOptionTemplate();
    public int[] param;
    public byte typeCanUpgrade;
    public Vector<DetailText> vecDetails = new Vector<>();

    public ItemOption(int optionTemplateId, byte typeCanUpgrade2, byte color, int[] param2, byte[] colorParam2) {
        this.typeCanUpgrade = typeCanUpgrade2;
        this.active = color;
        this.param = param2;
        this.colorParam = colorParam2;
        this.optionTemplate = ItemOptionTemplates.get(optionTemplateId) != null ? ItemOptionTemplates.get(optionTemplateId).clones() : null;
        this.optionStringTemp = getOptionString();
    }

    public void getDetailtext(int w) {
        int size;
        String str;
        String optionnName;
        byte colorOptionOrigin;
        byte colorOptionOrigin2 = 0;
        ItemOptionTemplate itemOptionTemplate = this.optionTemplate;
        String str2 = "";
        String optionnName2 = itemOptionTemplate != null ? itemOptionTemplate.name : str2;
        String optionString = getOptionString();
        Vector<DetailText> vecDetailColor = new Vector<>();
        int indexOptionString = 0;
        int indexParam = 0;
        int size2 = optionnName2.length();
        int i = 0;
        while (i < size2) {
            String c = String.valueOf(optionnName2.charAt(i));
            if (c.equals("#")) {
                if (i > 0) {
                    String textCur = vecDetailColor.get(vecDetailColor.size() - 1).text;
                    boolean isHaveText1 = false;
                    if (vecDetailColor.get(i - 1).text.equals(" ") || textCur.equals(" ") || textCur.equals(str2)) {
                        colorOptionOrigin = colorOptionOrigin2;
                        optionnName = optionnName2;
                    } else {
                        colorOptionOrigin = colorOptionOrigin2;
                        optionnName = optionnName2;
                        vecDetailColor.get(i - 1).setFontByteType(this.colorParam[indexParam]);
                        isHaveText1 = true;
                    }
                    String text2 = vecDetailColor.get(i - 2).text;
                    if (!text2.equals(" ") && isHaveText1) {
                        vecDetailColor.get(i - 2).setFontByteType(this.colorParam[indexParam]);
                    }
                    String textParam = String.valueOf(this.param[indexParam]);
                    int numPlus = textParam.length();
                    String str3 = text2;
                    int p = 0;
                    while (true) {
                        str = str2;
                        if (p >= textParam.length()) {
                            break;
                        }
                        vecDetailColor.add(new DetailText(String.valueOf(textParam.charAt(p)), this.colorParam[indexParam]));
                        p++;
                        str2 = str;
                        size2 = size2;
                        textParam = textParam;
                    }
                    size = size2;
                    indexOptionString += numPlus;
                } else {
                    colorOptionOrigin = colorOptionOrigin2;
                    optionnName = optionnName2;
                    str = str2;
                    size = size2;
                    String textParam2 = String.valueOf(this.param[indexParam]);
                    int numPlus2 = textParam2.length();
                    for (int p2 = 0; p2 < textParam2.length(); p2++) {
                        vecDetailColor.add(new DetailText(String.valueOf(textParam2.charAt(p2)), this.colorParam[indexParam]));
                    }
                    indexOptionString += numPlus2;
                }
                indexParam++;
            } else if (c.equals(String.valueOf(optionString.charAt(indexOptionString)))) {
                vecDetailColor.add(new DetailText(c, colorOptionOrigin2));
                indexOptionString++;
                colorOptionOrigin = colorOptionOrigin2;
                optionnName = optionnName2;
                str = str2;
                size = size2;
            } else {
                colorOptionOrigin = colorOptionOrigin2;
                optionnName = optionnName2;
                str = str2;
                size = size2;
            }
            i++;
            colorOptionOrigin2 = colorOptionOrigin;
            optionnName2 = optionnName;
            str2 = str;
            size2 = size;
        }
        String str4 = optionnName2;
        int i2 = size2;
        String[] arrTextTemp = FontPaintDetails.fontPaintdefault.splitFontArrayOrigin2(optionString, w);
        int size22 = 0;
        int index = 0;
        for (int i3 = 0; i3 < arrTextTemp.length; i3++) {
            size22 += arrTextTemp[i3].length();
            for (int k = 0; k < arrTextTemp[i3].length(); k++) {
                index++;
            }
        }
        int indexTemp = 0;
        for (int i4 = 0; i4 < arrTextTemp.length; i4++) {
            for (int k2 = 0; k2 < arrTextTemp[i4].length(); k2++) {
                vecDetailColor.get(indexTemp).line = i4;
                indexTemp++;
            }
        }
        this.vecDetails.removeAllElements();
        int h2 = 0;
        DetailText dtFirst = vecDetailColor.get(0);
        dtFirst.indexTemp = 0;
        DetailText result = getVecByLineAndColor(dtFirst, 1, vecDetailColor);
        this.vecDetails.add(result);
        while (true) {
            DetailText dtFirst2 = result.indexTemp + 1 < vecDetailColor.size() ? vecDetailColor.get(result.indexTemp + 1) : null;
            if (dtFirst2 == null) {
                break;
            }
            dtFirst2.indexTemp = result.indexTemp + 1;
            result = getVecByLineAndColor(dtFirst2, result.indexTemp + 2, vecDetailColor);
            this.vecDetails.add(result);
            h2 = h2;
        }
        int maxLine = 0;
        Iterator<DetailText> it = this.vecDetails.iterator();
        while (it.hasNext()) {
            DetailText dt = it.next();
            if (dt.line > maxLine) {
                maxLine = dt.line;
            }
        }
        this.h = (maxLine + 1) * (this.vecDetails.get(0).fontText.getHeight() + 7);
    }

    private DetailText getVecByLineAndColor(DetailText dt, int start, Vector<DetailText> vecDetailColor) {
        String temp = dt.getText();
        int index = -1;
        for (int i = start; i < vecDetailColor.size(); i++) {
            DetailText dt1 = vecDetailColor.get(i);
            if (dt.line != dt1.line || dt.type != dt1.type) {
                break;
            }
            temp = temp + dt1.getText();
            index = i;
        }
        DetailText result = new DetailText(temp, dt.type, dt.line);
        if (index == -1) {
            index = dt.indexTemp;
        }
        result.indexTemp = index;
        return result;
    }

    public String getOptionString() {
        ItemOptionTemplate itemOptionTemplate = this.optionTemplate;
        return itemOptionTemplate != null ? Res.replace(itemOptionTemplate.name, "#", this.param) : "null";
    }

    public String getOptionName() {
        ItemOptionTemplate itemOptionTemplate = this.optionTemplate;
        if (itemOptionTemplate == null) {
            return "null";
        }
        if (!itemOptionTemplate.name.contains("#")) {
            return null;
        }
        String text = Res.replace(this.optionTemplate.name, "#", "");
        if (text != null) {
            return text.substring(0, text.lastIndexOf(" "));
        }
        return "null";
    }

    private int getWBefore(int indexCur) {
        int wBefore = 0;
        DetailText item = this.vecDetails.get(indexCur);
        if (indexCur > 0) {
            for (int i = 0; i < indexCur; i++) {
                DetailText temp = this.vecDetails.get(i);
                if (temp.line == item.line) {
                    wBefore += temp.fontText.getWidth(temp.text);
                }
            }
        }
        return wBefore;
    }

    public void paint(mGraphics g, int x, int y, boolean useClip) {
        for (int i = 0; i < this.vecDetails.size(); i++) {
            DetailText item = this.vecDetails.get(i);
            mGraphics mgraphics = g;
            item.fontText.drawString(mgraphics, item.text, x + getWBefore(i), y + (item.line * (item.fontText.getHeight() + 5)), 0, useClip);
        }
    }

    public void paint(mGraphics g, int x, int y) {
        paint(g, x, y, false);
    }
}
