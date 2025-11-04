// Class Version: 8
package ninjawar.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import ninjawar.message.Config;
import ninjawar.message.SendMessage;
import ninjawar.model.MyCommand;
import ninjawar.mylib.FrameImage;
import ninjawar.mylib.mFont;
import ninjawar.mylib.mGraphics;
import ninjawar.mylib.mSystem;
import ninjawar.mymain.CanvasNinja;
import ninjawar.mymain.NinjaMidlet;
import ninjawar.myscr.MapScr;
import ninjawar.myscr.Player;
import ninjawar.myscr.Res;
import ninjawar.supporter.LoadDataManager;
import ninjawar.template.SkillTemplate;

import java.io.PrintStream;
import java.util.Vector;

public class TField {
    public static int[][] BBKEY;
    public static int CARET_COLOR;
    public static int CARET_HEIGHT;
    public static final int[] MAX_TIME_TO_CONFIRM_KEY;
    public static CanvasNinja c;
    public static int changeDau;
    public static int changeModeKey;
    public static mFont fontTFieldSmall;
    public static boolean isOpenTextBox;
    public static boolean isQwerty;
    private static int lastKey;
    public static NinjaMidlet m;
    public static int mode;
    public static final String[] modeNotify;
    public static String[] print;
    private static String[] printA;
    private static String[] printBB;
    private static String printDau;
    public static String[] printPc;
    public static int timeChangeMode;
    public static int typeXpeed;
    public static int typingModeAreaWidth;
    public int caretPos;
    public MyCommand cmdClear;
    public int counter;
    public int endSelected;
    public mFont fontPaint;
    public mFont fontTField;
    public int height;
    private int indexCong;
    private int indexDau;
    private int indexOfActiveChar;
    private int indexTemplate;
    public int inputType;
    public boolean isChangeFocus;
    public boolean isChat;
    private boolean isFocus;
    public boolean isKeyTyped;
    boolean isOffInputText;
    public boolean isSendChat;
    public boolean isShowInputTextPC;
    public boolean isUseComboKey;
    public int keyCodeInputKey;
    public int keyInActiveState;
    public boolean lockArrow;
    private int maxTextLenght;
    public int[] minMaxNumber;
    String name;
    public int offsetX;
    boolean openVirtual;
    public boolean paintFocus;
    public String paintedText;
    private String passwordText;
    public String placeHolder;
    public String sDefaust;
    public int showCaretCounter;
    public int startSelected;
    public String text;
    private long timeDau;
    int timeMoveCaret;
    long timeSetPassword;
    public int width;
    public int x;
    public int y;

    static {
        TField.typeXpeed = 1;
        MAX_TIME_TO_CONFIRM_KEY = new int[]{18, 14, 11, 9, 6, 4, 2};
        TField.CARET_HEIGHT = 0;
        TField.CARET_COLOR = 16777215;
        TField.print = new String[]{" 0", ".,@?!_1\"/$-():*+<=>;%&~#%^&*{}[];'/1", "aâa", "def3đê", "ghi4", "jkl5", "mno6ôơ", "pqrs7", "tuv8ư", "wxyz9", "*", "#"};
        TField.printPc = new String[]{"âđêô", "aáàảãạ", "âầấẩẫậ", "ằắẳẵặ"};
        TField.printA = new String[]{"0", "1", "abc2", "def3", "ghi4", "jkl5", "mno6", "pqrs7", "tuv8", "wxyz9", "0", "0"};
        TField.printBB = new String[]{" 0", "er1", "ty2", "ui3", "df4", "gh5", "jk6", "cv7", "bn8", "m9", "0", "0", "qw!", "as?", "zx", "op.", "l,"};
        TField.lastKey = -1984;
        TField.mode = 0;
        modeNotify = new String[]{"abc", "Abc", "ABC", "123"};
        TField.changeModeKey = 11;
        TField.fontTFieldSmall = mFont.tahoma_7_white_small;
        TField.isOpenTextBox = false;
        TField.printDau = "aáàảãạâấầẩẫậăắằẳẵặeéèẻẽẹêếềểễệiíìỉĩịoóòỏõọôốồổỗộơớờởỡợuúùủũụưứừửữựyýỳỷỹỵ";
        TField.BBKEY = new int[][]{{32, 48}, {49, 69}, {50, 84}, {51, 85}, {52, 68}, {53, 71}, {54, 74}, {55, 67}, {56, 66}, {57, 77}, {42, 128}, {35, 137}, {33, 113}, {63, 97}, {64, 121, 122}, {46, 111}, {44, 108}};
    }

    public TField() {
        this.minMaxNumber = new int[2];
        this.isUseComboKey = true;
        this.isChat = false;
        this.isKeyTyped = false;
        this.lockArrow = false;
        this.paintFocus = true;
        this.isChangeFocus = true;
        this.isShowInputTextPC = false;
        this.placeHolder = "";
        this.fontPaint = mFont.tahoma_7_blue;
        this.text = "";
        this.passwordText = "";
        this.paintedText = "";
        this.caretPos = 0;
        this.counter = 0;
        this.maxTextLenght = 500;
        this.offsetX = 0;
        this.keyInActiveState = 0;
        this.indexOfActiveChar = 0;
        this.showCaretCounter = 10;
        this.inputType = 0;
        this.sDefaust = "";
        this.fontTField = mFont.tahoma_7_white;
        this.timeMoveCaret = 100;
        this.startSelected = -1;
        this.endSelected = -1;
        this.keyCodeInputKey = -1999;
        this.indexDau = -1;
        this.indexTemplate = 0;
        this.indexCong = 0;
        this.timeDau = 0L;
        this.openVirtual = false;
        this.isOffInputText = true;
        this.name = "";
        this.text = "";
        this.init();
        this.setFocus(false);
        final FrameImage frameImage = LoadDataManager.myButtons[0];
        int height;
        if (frameImage == null) {
            height = 30;
        } else {
            height = (int) frameImage.frameHeight;
        }
        this.height = height;
    }

    private int getCaretPosByClick(int length) {
        final float n = (length - (this.offsetX + 4 + this.x)) / (float) this.fontTField.getWidth(this.paintedText);
        if (n <= 0.0f) {
            length = 0;
        } else if (n >= 1.0f) {
            length = this.paintedText.length();
        } else {
            length = (int) (this.paintedText.length() * n);
        }
        return length;
    }

    private void init() {
        TField.CARET_HEIGHT = this.fontTField.getHeight() + 2;
        this.cmdClear = new MyCommand("Xoá", new ActionInterfaceNone() {
        });
        if (TField.c == null) {
            TField.c = CanvasNinja.instance;
            TField.m = NinjaMidlet.instance;
        }
    }

    private void keyPressedAny(int indexOfActiveChar) {
        final boolean isBB = CanvasNinja.isBB;
        String[] array;
        if (isBB) {
            array = TField.printBB;
        } else {
            final int inputType = this.inputType;
            if (inputType != 2 && inputType != 3) {
                array = TField.print;
            } else {
                array = TField.printA;
            }
        }
        int lastKey = indexOfActiveChar;
        if (isBB) {
            indexOfActiveChar = this.getBBKeyCode(indexOfActiveChar);
            if ((lastKey = indexOfActiveChar) == -1) {
                return;
            }
        }
        if (lastKey == TField.lastKey) {
            indexOfActiveChar = (this.indexOfActiveChar + 1) % array[lastKey - 48].length();
            this.indexOfActiveChar = indexOfActiveChar;
            final char char1 = array[lastKey - 48].charAt(indexOfActiveChar);
            indexOfActiveChar = TField.mode;
            char c;
            if (indexOfActiveChar == 0) {
                indexOfActiveChar = (c = Character.toLowerCase(char1));
            } else if (indexOfActiveChar == 1) {
                indexOfActiveChar = (c = Character.toUpperCase(char1));
            } else if (indexOfActiveChar == 2) {
                indexOfActiveChar = (c = Character.toUpperCase(char1));
            } else {
                indexOfActiveChar = (c = array[lastKey - 48].charAt(array[lastKey - 48].length() - 1));
            }
            final StringBuilder sb = new StringBuilder();
            sb.append(this.text.substring(0, this.caretPos - 1));
            sb.append(c);
            String text = sb.toString();
            if (this.caretPos < this.text.length()) {
                final StringBuilder sb2 = new StringBuilder();
                sb2.append(text);
                final String text2 = this.text;
                sb2.append(text2.substring(this.caretPos, text2.length()));
                text = sb2.toString();
            }
            this.text = text;
            this.keyInActiveState = TField.MAX_TIME_TO_CONFIRM_KEY[TField.typeXpeed];
            this.setPasswordTest();
        } else if (this.text.length() < this.maxTextLenght) {
            if (TField.mode == 1 && TField.lastKey != -1984) {
                TField.mode = 0;
            }
            this.indexOfActiveChar = 0;
            final char char2 = array[lastKey - 48].charAt(0);
            indexOfActiveChar = TField.mode;
            char c2;
            if (indexOfActiveChar == 0) {
                indexOfActiveChar = (c2 = Character.toLowerCase(char2));
            } else if (indexOfActiveChar == 1) {
                indexOfActiveChar = (c2 = Character.toUpperCase(char2));
            } else if (indexOfActiveChar == 2) {
                indexOfActiveChar = (c2 = Character.toUpperCase(char2));
            } else {
                indexOfActiveChar = (c2 = array[lastKey - 48].charAt(array[lastKey - 48].length() - 1));
            }
            final StringBuilder sb3 = new StringBuilder();
            sb3.append(this.text.substring(0, this.caretPos));
            sb3.append(c2);
            String text3 = sb3.toString();
            if (this.caretPos < this.text.length()) {
                final StringBuilder sb4 = new StringBuilder();
                sb4.append(text3);
                final String text4 = this.text;
                sb4.append(text4.substring(this.caretPos, text4.length()));
                text3 = sb4.toString();
            }
            this.text = text3;
            this.keyInActiveState = TField.MAX_TIME_TO_CONFIRM_KEY[TField.typeXpeed];
            ++this.caretPos;
            this.setPasswordTest();
            this.setOffset(0);
        }
        TField.lastKey = lastKey;
    }

    private void keyPressedAscii(final int n) {
        final int inputType = this.inputType;
        if ((inputType == 1 || inputType == 4) && (n < 48 || n > 57)) {
            return;
        }
        if ((inputType == 2 || inputType == 3) && (n < 48 || n > 57) && (n < 65 || n > 90) && (n < 97 || n > 122)) {
            return;
        }
        if (this.text.length() < this.maxTextLenght) {
            final StringBuilder sb = new StringBuilder();
            sb.append(this.text.substring(0, this.caretPos));
            sb.append((char) n);
            String text;
            final String s = text = sb.toString();
            if (this.caretPos < this.text.length()) {
                final StringBuilder sb2 = new StringBuilder();
                sb2.append(s);
                final String text2 = this.text;
                sb2.append(text2.substring(this.caretPos, text2.length()));
                text = sb2.toString();
            }
            this.text = text;
            if (this.inputType == 4 && (text.length() == 2 || this.text.length() == 5)) {
                final StringBuilder sb3 = new StringBuilder();
                sb3.append(this.text);
                sb3.append("/");
                this.text = sb3.toString();
                ++this.caretPos;
            }
            ++this.caretPos;
            this.setPasswordTest();
            this.setOffset(0);
        }
    }

    public static void resetTField() {
        TField.CARET_COLOR = 16777215;
    }

    private void setDau() {
        this.timeDau = System.currentTimeMillis() / 100L;
        final int indexDau = this.indexDau;
        if (indexDau == -1) {
            for (int i = this.caretPos; i > 0; --i) {
                final char char1 = this.text.charAt(i - 1);
                for (int j = 0; j < TField.printDau.length(); ++j) {
                    if (char1 == TField.printDau.charAt(j)) {
                        this.indexTemplate = j;
                        this.indexCong = 0;
                        this.indexDau = i - 1;
                        return;
                    }
                }
            }
            this.indexDau = -1;
        } else {
            if (++this.indexCong >= 6) {
                this.indexCong = 0;
            }
            final String substring = this.text.substring(0, indexDau);
            final String substring2 = this.text.substring(this.indexDau + 1);
            final String printDau = TField.printDau;
            final int indexTemplate = this.indexTemplate;
            final int indexCong = this.indexCong;
            final String substring3 = printDau.substring(indexTemplate + indexCong, indexTemplate + indexCong + 1);
            final StringBuilder sb = new StringBuilder();
            sb.append(substring);
            sb.append(substring3);
            sb.append(substring2);
            this.text = sb.toString();
        }
    }

    public static void setMode() {
        if (++TField.mode > 3) {
            TField.mode = 0;
        }
        TField.lastKey = TField.changeModeKey;
        TField.timeChangeMode = CanvasNinja.getSecond();
    }

    private void setPasswordTest() {
        if (this.inputType == 2) {
            this.passwordText = "";
            for (int i = 0; i < this.text.length(); ++i) {
                final StringBuilder sb = new StringBuilder();
                sb.append(this.passwordText);
                sb.append("*");
                this.passwordText = sb.toString();
            }
            if (this.caretPos > 0) {
                final StringBuilder sb2 = new StringBuilder();
                sb2.append(this.passwordText, 0, this.caretPos - 1);
                sb2.append(this.text.charAt(this.caretPos - 1));
                final String passwordText = this.passwordText;
                sb2.append(passwordText.substring(this.caretPos));
                this.passwordText = sb2.toString();
            }
            this.timeSetPassword = mSystem.currentTimeMillis();
        }
    }

    private Input.OnscreenKeyboardType typeInput() {
        Input.OnscreenKeyboardType input$OnscreenKeyboardType = Input.OnscreenKeyboardType.Default;
        switch (this.inputType) {
            case 5: {
                input$OnscreenKeyboardType = Input.OnscreenKeyboardType.Email;
                break;
            }
            case 2: {
                input$OnscreenKeyboardType = Input.OnscreenKeyboardType.Password;
                break;
            }
            case 1: {
                input$OnscreenKeyboardType = Input.OnscreenKeyboardType.NumberPad;
                break;
            }
        }
        return input$OnscreenKeyboardType;
    }

    public static void unFocusAll(final Vector<TField> vector) {
        for (TField tField : vector) {
            tField.isFocus = false;
        }
    }

    public static void updateFocus(TField tField, final TField tField2) {
        final Vector<TField> vector = new Vector<TField>();
        vector.add(tField);
        vector.add(tField2);
        for (TField field : vector) {
            tField = field;
            if (CanvasNinja.isPointerRelease && CanvasNinja.isPointerClick && CanvasNinja.isPoint(tField.x, tField.y, tField.width, tField.height)) {
                unFocusAll(vector);
                tField.isFocus = true;
                CanvasNinja.isPointerRelease = false;
                break;
            }
        }
    }

    public boolean checkKeyTypedByTypeInput(final String s) {
        if (this.text.length() >= this.maxTextLenght) {
            return false;
        }
        boolean b = true;
        int n = 1;
        switch (this.inputType) {
            default: {
                return b;
            }
            case 4: {
                if (!Res.checkNumber(s)) {
                    n = 0;
                }
                if (!s.equals("/")) {
                    b = (n != 0);
                    if (!s.equals("-")) {
                        return b;
                    }
                }
                final int index = this.paintedText.indexOf("/");
                int n2 = n;
                if (index > -1) {
                    n2 = n;
                    if (s.equals("/")) {
                        n2 = 1;
                    }
                }
                final int index2 = this.paintedText.indexOf("-");
                int n3 = n2;
                if (index2 > -1) {
                    n3 = n2;
                    if (s.equals("-")) {
                        n3 = 1;
                    }
                }
                b = (n3 != 0);
                if (index != -1) {
                    return b;
                }
                b = (n3 != 0);
                if (index2 == -1) {
                    b = true;
                    return b;
                }
                return b;
            }
            case 1: {
                if (!Res.checkNumber(s)) {
                    b = false;
                    return b;
                }
                return b;
            }
        }
    }

    public void clear() {
        if (this.caretPos > 0 && !this.text.isEmpty()) {
            final String text = this.text;
            String sb = this.text.substring(0, this.caretPos - 1) +
                text.substring(this.caretPos);
            this.text = sb;
            --this.caretPos;
            this.setOffset(0);
            this.setPasswordTest();
            if (this.inputType == 4 && this.text.length() > 2) {
                final String text2 = this.text;
                if (text2.substring(text2.length() - 1, this.text.length()).equals("/")) {
                    final StringBuilder sb2 = new StringBuilder();
                    sb2.append(this.text.substring(0, this.caretPos - 1));
                    final String text3 = this.text;
                    sb2.append(text3.substring(this.caretPos, text3.length()));
                    this.text = sb2.toString();
                    --this.caretPos;
                    this.setOffset(0);
                }
            }
        }
    }

    public void comboKeyPC() {
        if (!this.isUseComboKey) {
            return;
        }
        if (CanvasNinja.comboKeyPC[0]) {
            if (this.paintedText.isEmpty()) {
                return;
            }
            this.startSelected = 0;
            this.endSelected = this.paintedText.length();
        }
        if (this.inputType != 2) {
            if (CanvasNinja.comboKeyPC[1]) {
                final int startSelected = this.startSelected;
                if (startSelected != -1) {
                    final int endSelected = this.endSelected;
                    if (endSelected != -1) {
                        Res.setTextToClipBoard(this.text.substring(startSelected, endSelected));
                        final StringBuilder sb = new StringBuilder();
                        sb.append(this.text.substring(0, this.startSelected));
                        final String text = this.text;
                        sb.append(text.substring(this.endSelected, text.length()));
                        final String string = sb.toString();
                        this.text = string;
                        this.paintedText = string;
                        this.caretPos = this.startSelected;
                        this.setOffset(0);
                    }
                }
                this.resetSelected();
            }
            if (CanvasNinja.comboKeyPC[2]) {
                final int startSelected2 = this.startSelected;
                if (startSelected2 != -1) {
                    final int endSelected2 = this.endSelected;
                    if (endSelected2 != -1) {
                        Res.setTextToClipBoard(this.text.substring(startSelected2, endSelected2));
                    }
                }
            }
            if (CanvasNinja.comboKeyPC[3]) {
                final String textFromClipBoard = Res.getTextFromClipBoard();
                if (this.startSelected != -1 && this.endSelected != -1) {
                    final int length = this.text.length();
                    final int length2 = textFromClipBoard.length();
                    final int maxTextLenght = this.maxTextLenght;
                    String substring = textFromClipBoard;
                    if (length + length2 > maxTextLenght) {
                        final int n = maxTextLenght - this.text.length();
                        if (n <= 0) {
                            substring = "";
                        } else {
                            substring = textFromClipBoard.substring(0, n - 1);
                        }
                    }
                    final String text2 = this.text;
                    this.text = this.text.substring(0, this.startSelected) +
                        substring +
                        text2.substring(this.endSelected);
                    this.caretPos = this.startSelected + substring.length();
                    this.resetSelected();
                } else {
                    final int length3 = this.text.length();
                    final int length4 = textFromClipBoard.length();
                    final int maxTextLenght2 = this.maxTextLenght;
                    String substring2 = textFromClipBoard;
                    if (length3 + length4 > maxTextLenght2) {
                        final int n2 = maxTextLenght2 - this.text.length();
                        if (n2 <= 0) {
                            substring2 = "";
                        } else {
                            substring2 = textFromClipBoard.substring(0, n2 - 1);
                        }
                    }
                    final String text3 = this.text;
                    String sb3 = this.text.substring(0, this.caretPos) +
                        substring2 +
                        text3.substring(this.caretPos);
                    this.text = sb3;
                    this.caretPos += substring2.length();
                }
                this.paintedText = this.text;
                this.setOffset(0);
                this.resetSelected();
            }
        }
    }

    public void doChangeToTextBox() {
        System.out.println("doChangeToTextBox");
        this.isOffInputText = false;
        final Input.TextInputListener listener = new Input.TextInputListener() {
            public void canceled() {
                isFocus = false;
                isOffInputText = true;
            }

            public void input(final String textFirst) {
                setTextFirst(textFirst);
                final TField this$0 = TField.this;
                if (this$0.isSendChat) {
                    this$0.sendChat();
                }
                isFocus = false;
                isOffInputText = true;
            }
        };
        final String placeHolder = this.placeHolder;
        final String text = this.text;
        String placeHolder2 = "";
        if (text.isEmpty()) {
            placeHolder2 = this.placeHolder;
        }
        Gdx.input.getTextInput(listener, placeHolder, text, placeHolder2, this.typeInput());
    }

    public int getBBKeyCode(final int n) {
        for (int i = 0; i < TField.BBKEY.length; ++i) {
            int n2 = 0;
            while (true) {
                final int[] array = TField.BBKEY[i];
                if (n2 >= array.length) {
                    break;
                }
                if (array[n2] == n) {
                    return i + 48;
                }
                ++n2;
            }
        }
        return -1;
    }

    public String getText() {
        return this.text;
    }

    public boolean isFocused() {
        return this.isFocus;
    }

    public boolean keyPressed(int caretPos) {
        CanvasNinja.clearAllPointer();
        if (this.inputType == 6) {
            final String nameKey = KEY.getNameKey(caretPos);
            this.paintedText = nameKey;
            this.text = nameKey;
            if (nameKey.length() > 0) {
                this.keyCodeInputKey = caretPos;
            } else {
                this.keyCodeInputKey = -1999;
            }
            return true;
        }
        if (this.isFocus) {
            this.isFocus = true;
            if (caretPos == KEY.KEY_ENTER) {
                if (NinjaMidlet.isAndroid && !NinjaMidlet.isUseIOSSpec) {
                    Gdx.input.setOnscreenKeyboardVisible(false);
                }
                if (this.isChat) {
                    this.isFocus = false;
                    this.sendChat();
                }
            }
        }
        this.comboKeyPC();
        if (this.isKeyTyped) {
            return true;
        }
        if (CanvasNinja.isBB) {
            if (caretPos == 8 || caretPos == 127) {
                this.clear();
            }
        } else if (caretPos == 8 || caretPos == -8 || caretPos == 204) {
            this.clear();
            return true;
        }
        if (NinjaMidlet.isPC || NinjaMidlet.isAndroid || NinjaMidlet.isIOS) {
            TField.isQwerty = true;
            TField.typingModeAreaWidth = 0;
        }
        final boolean isBB = CanvasNinja.isBB;
        if (!isBB && caretPos >= 65 && caretPos <= 122) {
            TField.typingModeAreaWidth = 0;
        }
        final boolean isQwerty = TField.isQwerty;
        if (isQwerty && !isBB) {
            if (caretPos == 45) {
                if (caretPos == TField.lastKey && this.keyInActiveState < TField.MAX_TIME_TO_CONFIRM_KEY[TField.typeXpeed]) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append(this.text.substring(0, this.caretPos - 1));
                    sb.append('_');
                    final String string = sb.toString();
                    this.text = string;
                    this.paintedText = string;
                    this.setPasswordTest();
                    this.setOffset(0);
                    TField.lastKey = -1984;
                    return false;
                }
                TField.lastKey = 45;
            }
            if (caretPos >= 32) {
                this.keyPressedAscii(caretPos);
                return false;
            }
        }
        if (!isQwerty && caretPos == TField.changeModeKey) {
            setMode();
            this.keyInActiveState = 1;
            TField.lastKey = caretPos;
            return false;
        }
        if (caretPos == TField.changeDau && this.inputType == 0) {
            this.setDau();
            return false;
        }
        int n;
        if ((n = caretPos) == 42) {
            n = 58;
        }
        if ((caretPos = n) == 35) {
            caretPos = 59;
        }
        if (isBB && caretPos >= 48) {
            if (isQwerty) {
                this.keyPressedAscii(caretPos);
                this.keyInActiveState = 1;
            } else {
                this.setTypeKey(caretPos);
            }
        } else if (caretPos >= 48 && caretPos <= 59) {
            this.setTypeKey(caretPos);
        } else {
            this.indexOfActiveChar = 0;
            TField.lastKey = -1984;
            if (caretPos == 14 && !this.lockArrow) {
                caretPos = this.caretPos;
                if (caretPos > 0) {
                    this.caretPos = caretPos - 1;
                    this.setOffset(0);
                    this.showCaretCounter = 10;
                    return false;
                }
            } else if (caretPos == 15 && !this.lockArrow) {
                if (this.caretPos < this.text.length()) {
                    ++this.caretPos;
                    this.setOffset(0);
                    this.showCaretCounter = 10;
                    return false;
                }
            } else {
                if (caretPos == 19) {
                    this.clear();
                    return false;
                }
                TField.lastKey = caretPos;
            }
        }
        return true;
    }

    public void keyTyped() {
        if (this.inputType == 6) {
            return;
        }
        if (this.isFocus && this.isKeyTyped) {
            if (CanvasNinja.keyPCTyped.equals("") && !CanvasNinja.isDelete) {
                return;
            }
            if (!this.checkKeyTypedByTypeInput(CanvasNinja.keyPCTyped) && !CanvasNinja.isDelete) {
                return;
            }
            final int startSelected = this.startSelected;
            if (startSelected != -1 && this.endSelected != -1) {
                this.caretPos = startSelected;
                final StringBuilder sb = new StringBuilder();
                sb.append(this.text.substring(0, this.startSelected));
                final String text = this.text;
                sb.append(text.substring(this.endSelected, text.length()));
                this.text = sb.toString();
                this.resetSelected();
            }
            if (this.text.length() > 0) {
                if (CanvasNinja.isDelete) {
                    CanvasNinja.isDelete = false;
                    if (this.caretPos > 0) {
                        final StringBuilder sb2 = new StringBuilder();
                        sb2.append(this.text.substring(0, this.caretPos - 1));
                        sb2.append(this.text.substring(this.caretPos));
                        final String string = sb2.toString();
                        this.text = string;
                        this.paintedText = string;
                    }
                    if (--this.caretPos < 0) {
                        this.caretPos = 0;
                    }
                }
                if (CanvasNinja.isSetDau && !CanvasNinja.isDelete) {
                    final String substring = this.text.substring(0, this.caretPos);
                    final int lastIndex = substring.lastIndexOf(" ");
                    final int lastIndex2 = substring.lastIndexOf(CanvasNinja.keyChange);
                    if (lastIndex > -1) {
                        final String substring2 = substring.substring(lastIndex, this.caretPos);
                        final int lastIndex3 = substring2.lastIndexOf(CanvasNinja.keyChange);
                        if (lastIndex3 > -1 && lastIndex3 != substring2.length() - 1) {
                            final PrintStream err = System.err;
                            final StringBuilder sb3 = new StringBuilder();
                            sb3.append("text:");
                            sb3.append(this.text);
                            err.println(sb3.toString());
                            final PrintStream err2 = System.err;
                            final StringBuilder sb4 = new StringBuilder();
                            sb4.append("indexTemp:");
                            sb4.append(lastIndex3);
                            err2.println(sb4.toString());
                            final StringBuilder sb5 = new StringBuilder();
                            sb5.append(this.text.substring(0, lastIndex));
                            sb5.append(substring2.substring(0, lastIndex3));
                            sb5.append(this.text.substring(this.caretPos));
                            this.text = sb5.toString();
                            final PrintStream err3 = System.err;
                            final StringBuilder sb6 = new StringBuilder();
                            sb6.append("text:");
                            sb6.append(this.text);
                            err3.println(sb6.toString());
                            this.caretPos -= substring2.length() - lastIndex3;
                        }
                    } else if (lastIndex2 > -1) {
                        this.text = this.text.substring(0, lastIndex2);
                        this.caretPos -= substring.length() - lastIndex2;
                    }
                }
                final StringBuilder sb7 = new StringBuilder();
                sb7.append(this.text.substring(0, this.caretPos));
                sb7.append(CanvasNinja.keyPCTyped);
                sb7.append(this.text.substring(this.caretPos));
                this.text = sb7.toString();
                if (!CanvasNinja.keyPCTyped.equals("")) {
                    ++this.caretPos;
                }
            } else {
                final StringBuilder sb8 = new StringBuilder();
                sb8.append(this.text);
                sb8.append(CanvasNinja.keyPCTyped);
                this.text = sb8.toString();
                if (!CanvasNinja.keyPCTyped.equals("")) {
                    ++this.caretPos;
                }
            }
            this.paintedText = this.text;
            this.setPasswordTest();
            this.setOffset(0);
            CanvasNinja.keyPCTyped = "";
        }
    }

    public void painTextInputNoneBackGround(final mGraphics mGraphics) {
        final boolean focused = this.isFocused();
        if (this.inputType == 2) {
            this.paintedText = this.passwordText;
        } else {
            this.paintedText = this.text;
        }
        mGraphics.setClip(this.x, 0, this.width + 2, CanvasNinja.h);
        mGraphics.setColor(7829367);
        CanvasNinja.paintz.painTextInputNoneBackGround(mGraphics, this.x, this.y, this.width, this.height, this, focused);
        CanvasNinja.resetTrans(mGraphics);
    }

    public void painTextInputNoneBg(final mGraphics mGraphics, final int n) {
        final boolean focused = this.isFocused();
        if (this.inputType == 2) {
            this.paintedText = this.passwordText;
        } else {
            this.paintedText = this.text;
        }
        mGraphics.setClip(this.x, 0, this.width + 2, CanvasNinja.h);
        mGraphics.setColor(7829367);
        CanvasNinja.paintz.painTextInputNoneBg(mGraphics, this.x, this.y, this.width, this.height, this, focused, n);
        CanvasNinja.resetTrans(mGraphics);
    }

    public void resetSelected() {
        this.startSelected = -1;
        this.endSelected = -1;
    }

    public void sendChat() {
        this.resetSelected();
        if (this.text.equals("")) {
            this.isFocus = false;
            this.isChat = false;
            return;
        }
        if (Config.isOfflineMode) {
            if (!this.text.startsWith("#")) {
                Player.myCharz().startPopup(this.text, 10000);
            }
        }
        if (this.text.startsWith("###lv")) {
            NinjaMidlet.isLevelUp ^= true;
        }
        if (this.text.startsWith("###vl")) {
            final String[] array = {"#+$1 100000000", "#+$2 1000000", "#+$3 1000000", "#+it 22 1", "#+it 23 1", "#+it 321 100", "#+it 322 100", "#+it 323 100", "#+it 324 100", "#+it 325 100", "#+it 326 100", "#+it 327 100", "#+it 328 100", "#+it 321 100", "#+* 2100000000", "#+skpt 2000", "#+it 22 1", "#+ppt 30000"};
            for (int i = 0; i < array.length; ++i) {
                SendMessage.gI().chatTo(Player.myCharz().charID, array[i]);
            }
            final SkillTemplate[] skillTemplates = MapScr.nClasss[Player.myCharz().classId].skillTemplates;
            for (int j = 0; j < skillTemplates.length; ++j) {
                SendMessage.gI().requestUpgradeSkill(skillTemplates[j].id);
            }
            SendMessage.gI().useItemInventory((byte) 3, 1);
        } else {
            SendMessage.gI().chatTo(Player.myCharz().charID, this.getText());
        }
        this.caretPos = 0;
        this.text = "";
        this.isFocus = false;
        this.setOffset(0);
    }

    public void setFocus(final boolean isFocus) {
        if (this.isFocus != isFocus) {
            TField.mode = 0;
        }
        TField.lastKey = -1984;
        TField.timeChangeMode = CanvasNinja.getSecond();
        if (!(this.isFocus = isFocus)) {
            this.resetSelected();
        }
    }

    public void setIputType(final int inputType) {
        this.inputType = inputType;
        if (inputType == 4) {
            this.maxTextLenght = 10;
        }
    }

    public void setMaxTextLenght(final int maxTextLenght) {
        this.maxTextLenght = maxTextLenght;
    }

    public void setOffset(int n) {
        if (this.inputType == 2) {
            this.paintedText = this.passwordText;
        } else {
            this.paintedText = this.text;
        }
        final int width = this.fontTField.getWidth(this.paintedText.substring(0, this.caretPos));
        if (n == -1) {
            if (this.offsetX + width < 15) {
                n = this.caretPos;
                if (n > 0 && n < this.paintedText.length()) {
                    this.offsetX += this.fontTField.getWidth(this.paintedText.substring(0, this.caretPos + 1));
                }
            }
        } else if (n == 1) {
            if (this.offsetX + width > this.width - 25 && this.caretPos < this.paintedText.length()) {
                n = this.caretPos;
                if (n > 0) {
                    this.offsetX -= this.fontTField.getWidth(this.paintedText.substring(n - 1, n));
                }
            }
        } else {
            this.offsetX = -(width - (this.width - 12));
        }
        n = this.offsetX;
        if (n > 0) {
            this.offsetX = 0;
        } else if (n < 0) {
            n = this.fontTField.getWidth(this.paintedText) - (this.width - 12);
            if (this.offsetX < -n) {
                this.offsetX = -n;
            }
        }
    }

    public void setTextFirst(final String s) {
        if (s == null) {
            return;
        }
        TField.lastKey = -1984;
        this.keyInActiveState = 0;
        this.indexOfActiveChar = 0;
        this.text = s;
        this.paintedText = s;
        this.caretPos = s.length();
        this.setPasswordTest();
        this.setOffset(0);
    }

    public void setTypeKey(final int n) {
        final int inputType = this.inputType;
        if (inputType != 0 && inputType != 2 && inputType != 3) {
            if (inputType == 1) {
                this.keyPressedAscii(n);
                this.keyInActiveState = 1;
            } else {
                this.keyPressedAny(n);
            }
        } else {
            this.keyPressedAny(n);
        }
    }

    public void update() {
        if (this.isFocus && this.isOffInputText && NinjaMidlet.isUseIOSSpec) {
            this.doChangeToTextBox();
            return;
        }
        this.updateClickCaretPos();
        ++this.counter;
        int keyInActiveState = this.keyInActiveState;
        if (keyInActiveState > 0) {
            --keyInActiveState;
            this.keyInActiveState = keyInActiveState;
            if (keyInActiveState == 0 || TField.mode > 2) {
                this.indexOfActiveChar = 0;
                if (this.isFocus && TField.mode == 1 && TField.lastKey != TField.changeModeKey) {
                    TField.mode = 0;
                }
                TField.lastKey = -1984;
                this.setPasswordTest();
            }
        }
        final int showCaretCounter = this.showCaretCounter;
        if (showCaretCounter > 0) {
            this.showCaretCounter = showCaretCounter - 1;
        }
        if (this.indexDau != -1 && System.currentTimeMillis() / 100L - this.timeDau > 5L) {
            this.indexDau = -1;
        }
        if (this.timeSetPassword != 0L && mSystem.currentTimeMillis() - this.timeSetPassword >= 500L) {
            this.timeSetPassword = 0L;
            this.passwordText = "";
            for (int i = 0; i < this.text.length(); ++i) {
                final StringBuilder sb = new StringBuilder();
                sb.append(this.passwordText);
                sb.append("*");
                this.passwordText = sb.toString();
            }
        }
        if (this.isFocus && CanvasNinja.currentDialog == null) {
            final boolean[] keyPressed = CanvasNinja.keyPressed;
            if (keyPressed[4]) {
                if (this.inputType != 2) {
                    if (--this.caretPos < 0) {
                        this.caretPos = 0;
                    }
                    this.setOffset(-1);
                }
                CanvasNinja.keyPressed[4] = false;
            } else if (keyPressed[6]) {
                if (this.inputType != 2) {
                    if (++this.caretPos > this.getText().length()) {
                        this.caretPos = this.getText().length();
                    }
                    this.setOffset(1);
                }
                CanvasNinja.keyPressed[6] = false;
            }
        }
    }

    public void updateClickCaretPos() {
        if (!this.isFocus) {
            return;
        }
        if (CanvasNinja.isPointerDraggedX && CanvasNinja.isPoint(this.x, this.y, this.width, this.height)) {
            final int caretPosByClick = this.getCaretPosByClick(CanvasNinja.pxFirst);
            final int caretPosByClick2 = this.getCaretPosByClick(CanvasNinja.px);
            if (caretPosByClick < (this.caretPos = caretPosByClick2)) {
                this.startSelected = caretPosByClick;
                this.endSelected = caretPosByClick2;
            } else {
                this.startSelected = caretPosByClick2;
                this.endSelected = caretPosByClick;
            }
            final int startSelected = this.startSelected;
            if (startSelected > -1) {
                this.caretPos = startSelected;
            }
            this.setOffset(0);
            CanvasNinja.isPointerDraggedX = false;
            CanvasNinja.isPointerDraggedXDone = true;
        }
        final boolean[] keyHold = CanvasNinja.keyHold;
        if ((keyHold[50] || keyHold[51]) && CanvasNinja.isPointerRelease && !CanvasNinja.isPointerDraggedXDone && CanvasNinja.isPoint(this.x, this.y, this.width, this.height)) {
            final int caretPosByClick3 = this.getCaretPosByClick(CanvasNinja.px);
            final int caretPos = this.caretPos;
            if (caretPosByClick3 < caretPos) {
                this.startSelected = caretPosByClick3;
                this.endSelected = caretPos;
            } else {
                this.startSelected = caretPos;
                this.endSelected = caretPosByClick3;
            }
            this.setOffset(0);
            CanvasNinja.clearAllPointer();
        }
        final boolean[] keyReleased = CanvasNinja.keyReleased;
        if (keyReleased[52]) {
            keyReleased[52] = false;
            this.setOffset(this.caretPos = 0);
            this.resetSelected();
        }
        final boolean[] keyReleased2 = CanvasNinja.keyReleased;
        if (keyReleased2[53]) {
            keyReleased2[53] = false;
            this.caretPos = this.paintedText.length();
            this.setOffset(0);
            this.resetSelected();
        }
        final boolean[] keyReleased3 = CanvasNinja.keyReleased;
        if (keyReleased3[50] || keyReleased3[51]) {
            keyReleased3[51] = (keyReleased3[50] = false);
            final int startSelected2 = this.startSelected;
            if (startSelected2 != -1) {
                this.caretPos = startSelected2;
                this.setOffset(0);
            }
        }
        if (CanvasNinja.isPointerRelease && !CanvasNinja.isPointerDraggedXDone && CanvasNinja.isPoint(this.x, this.y, this.width, this.height)) {
            this.caretPos = this.getCaretPosByClick(CanvasNinja.px);
            this.setOffset(0);
            this.resetSelected();
        }
    }

    public boolean updateFocus() {
        if (!CanvasNinja.isPointerClick && !CanvasNinja.isPointerFirstClick) {
            return false;
        }
        if (CanvasNinja.isPoint(this.x, this.y, this.width, this.height)) {
            this.isFocus = true;
            CanvasNinja.isPointerClick = false;
            CanvasNinja.isPointerFirstClick = false;
            return true;
        }
        return this.isFocus = false;
    }

    public void updateFocus2() {
        if (CanvasNinja.isPointerRelease()) {
            if (CanvasNinja.isPointer(this.x, this.y, this.width, this.height)) {
                this.isFocus = true;
                CanvasNinja.clearAllPointer();
            } else {
                this.isFocus = false;
            }
        }
    }

    public void updateMarginTField(final int n) {
        this.x += n;
        this.width -= n * 2;
        this.y += 2;
    }
}
