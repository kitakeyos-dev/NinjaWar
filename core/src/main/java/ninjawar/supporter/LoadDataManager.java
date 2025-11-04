package ninjawar.supporter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import javax.imageio.ImageIO;
import ninjawar.mylib.Atlas;
import ninjawar.mylib.FileData;
import ninjawar.mylib.FrameImage;
import ninjawar.mylib.Image;
import ninjawar.mylib.Rms;
import ninjawar.myscr.Res;
import ninjawar.template.MobTemplates;
import ninjawar.template.NPCHashTables;
import ninjawar.template.QuaiTemplate;
import ninjawar.template.TemplateNPC;

public class LoadDataManager {
    public static FrameImage[] buffEffect;
    public static int color_circle = 0;
    public static int[] color_frame = {16380640, 16711417, 14209473, 16446691, 16777215, 15849382, 16645624, 16315885, 16112305, 0, 16179905, 15919062};
    public static FrameImage conCho;
    public static Image coolDownBuffEffect;
    public static Image coolDownSkill;
    public static Image coolDownSkillBig;
    public static DataImage[] dataEffects = new DataImage[500];
    public static DataImage[] dataParts = new DataImage[500];
    public static Image[] effectLost;
    public static FrameImage frame3;
    public static FrameImage frameAmThanh;
    public static FrameImage frameAuto;
    public static FrameImage frameBGPercentSucess;
    public static FrameImage[][] frameBGs;
    public static FrameImage frameBgCam;
    public static FrameImage frameBgFocusObj;
    public static FrameImage frameBgFunction;
    public static FrameImage frameBgGem;
    public static FrameImage frameBgGift;
    public static FrameImage frameBgInfo;
    public static FrameImage frameBgMiniMap;
    public static FrameImage frameBgMission;
    public static FrameImage frameBgMission2;
    public static FrameImage frameBgOAuth2;
    public static FrameImage frameBgOrange0;
    public static FrameImage frameBgOrange2_0;
    public static FrameImage frameBgParty;
    public static FrameImage frameBgTag;
    public static FrameImage frameBgTextAppear;
    public static FrameImage frameBgWhite1;
    public static FrameImage frameBgWhite4;
    public static FrameImage[] frameBoxHanhTrang;
    public static FrameImage frameBoxHanhTrangAdd;
    public static FrameImage frameBoxHanhTrangDefault;
    public static FrameImage frameBoxHanhTrangLocked;
    public static FrameImage frameBoxUpgrade;
    public static FrameImage frameBoxUsed;
    public static FrameImage[] frameCencored;
    public static FrameImage frameChange;
    public static FrameImage frameCharDead;
    public static FrameImage frameCharRevive;
    public static FrameImage frameChat4;
    public static FrameImage frameChatBox;
    public static FrameImage frameChatBoxInput;
    public static FrameImage frameChatMsg;
    public static FrameImage frameChatRieng;
    public static FrameImage frameCheckBox;
    public static FrameImage frameChooseChar;
    public static FrameImage frameChooseTag;
    public static FrameImage frameCongTN;
    public static FrameImage frameCucSat;
    public static FrameImage[] frameEffBg;
    public static FrameImage frameEffBungNo;
    public static FrameImage frameEffLvUp;
    public static FrameImage frameEffNo;
    public static FrameImage frameEffectChooseChar;
    public static FrameImage frameEffectUpgradeSuccess;
    public static FrameImage frameEffectUpgradeSuccess2;
    public static FrameImage frameFlagLeaderMap;
    public static FrameImage frameFocusPk;
    public static FrameImage frameFriendList;
    public static FrameImage frameGacha;
    public static FrameImage frameGiaTien;
    public static FrameImage frameGoal;
    public static FrameImage[] frameHatMua;
    public static FrameImage frameHide;
    public static FrameImage frameIconApple;
    public static FrameImage frameIconAuto;
    public static FrameImage frameIconGoogle;
    public static FrameImage frameIconPk;
    public static FrameImage frameIconPvP;
    public static FrameImage frameIconSend;
    public static FrameImage[] frameIconTabFriend;
    public static FrameImage[] frameIconTabGacha;
    public static FrameImage[] frameIconTabHanhTrangs;
    public static FrameImage[] frameIconTabLearnSkill;
    public static FrameImage[] frameIconTabPartys;
    public static FrameImage[] frameIconTabThoRen;
    public static FrameImage[] frameIconTabUpgrades;
    public static FrameImage[] frameIconTagNameInventory;
    public static FrameImage frameInfo;
    public static FrameImage frameInfoTitle;
    public static FrameImage frameInput;
    public static FrameImage frameInput1;
    public static FrameImage frameInputChat1;
    public static FrameImage frameInputChat2;
    public static FrameImage frameInputTf;
    public static FrameImage frameInventory;
    public static FrameImage frameJump;
    public static FrameImage frameLaRoi;
    public static FrameImage frameLearnSkill;
    public static FrameImage frameLineNgang;
    public static FrameImage frameLvUp;
    public static FrameImage frameMenuPvp;
    public static FrameImage frameMessage;
    public static FrameImage frameMinus;
    public static FrameImage frameMission;
    public static FrameImage frameMobHurt;
    public static FrameImage frameNameNhanVat;
    public static FrameImage frameNenHe;
    public static FrameImage[] frameNenHes;
    public static FrameImage[] frameNextPreviousKhoDo;
    public static FrameImage frameNutBam;
    public static FrameImage framePage;
    public static FrameImage frameParty;
    public static FrameImage framePartySearch;
    public static FrameImage framePlus;
    public static FrameImage framePointer;
    public static FrameImage[] framePointerFindMap = new FrameImage[8];
    public static FrameImage framePrevious;
    public static FrameImage framePurchase;
    public static FrameImage frameQuest;
    public static FrameImage frameRadio;
    public static FrameImage frameSelected;
    public static FrameImage frameSendChat;
    public static FrameImage frameSetting;
    public static FrameImage frameShield;
    public static FrameImage frameShow;
    public static FrameImage frameShurikenEff;
    public static FrameImage frameSkill;
    public static FrameImage[][] frameSubBGs;
    public static FrameImage frameSubEffectUpgradeSuccess;
    public static FrameImage frameTabChatBox;
    public static FrameImage frameTabIconHanhTrang;
    public static FrameImage frameTabNameNews;
    public static FrameImage frameTagNameNPC;
    public static FrameImage frameTagText;
    public static FrameImage frameTagTitleHanhTrang;
    public static FrameImage[] frameTelePort;
    public static FrameImage frameThanh2;
    public static FrameImage frameThanhCuonTruc;
    public static FrameImage frameTitle;
    public static FrameImage frameTitle2;
    public static FrameImage frameTraiTim;
    public static FrameImage frameTron;
    public static FrameImage frameTruTN;
    public static FrameImage[] frameTypeChat;
    public static FrameImage[] frameTypeNPCs = new FrameImage[6];
    public static FrameImage frameUnread;
    public static FrameImage frameUpgrade;
    public static FrameImage frameUserNameTitle;
    public static FrameImage frameVachDoc;
    public static FrameImage frameVienBi;
    public static FrameImage frameVuong;
    public static FrameImage frameWaiting;
    public static FrameImage frameWorldMap;
    public static FrameImage frameXam2;
    public static FrameImage frameXam3;
    public static FrameImage[] frameZoneStatus;
    public static FrameImage[] frame_mob_template = new FrameImage[50];
    public static FrameImage[] frame_npc_template = new FrameImage[50];
    public static HashMap<String, Atlas> hashMapAtlasAll = new HashMap<>();
    public static FrameImage iconNam;
    public static FrameImage iconNu;
    public static Image img18Plus;
    public static Image imgAccept;
    public static Image imgAdd;
    public static Image imgAmLuong;
    public static Image imgApplied;
    public static Image imgBGMinisize;
    public static Image imgBack;
    public static Image imgBackgroundInfoMe;
    public static Image imgBgChar;
    public static Image imgBgIconSkill;
    public static Image imgBgIconSkillBd;
    public static Image[] imgBgMoney;
    public static Image imgBgPK;
    public static Image imgBgTagUpgrade;
    public static Image[][] imgBgs;
    public static Image imgBoxNeed;
    public static Image imgBoxNeedLost;
    public static Image imgCanhVat;
    public static Image imgCauHinh;
    public static Image imgCensored;
    public static Image imgChamHoi;
    public static Image imgChangeBigUpgrade;
    public static Image imgChangeUpgrade;
    public static Image imgChatDown;
    public static Image imgChatUp;
    public static Image imgChatUp2;
    public static Image imgCircleTarget;
    public static Image imgClose;
    public static FrameImage imgCloseChatBox;
    public static Image imgCloseMiniMap;
    public static Image imgCloseNenParty;
    public static Image imgCloseOption;
    public static Image imgCloseOptionBg;
    public static Image imgCloseParty;
    public static Image imgCoGai;
    public static Image imgCucDat;
    public static Image imgCucSat;
    public static FrameImage imgDapDat;
    public static Image imgDatCreateChar;
    public static Image imgDecline;
    public static Image imgDong;
    public static Image imgDongSE;
    public static Image imgDotInfo;
    public static Image[] imgEffectMap;
    public static Image imgFemale;
    public static Image imgFlagLeader;
    public static Image imgFlagLeaderMap;
    public static Image imgFlare;
    public static Image imgFlare2;
    public static Image imgFocus;
    public static Image imgFocus2;
    public static Image imgFocusMe;
    public static Image imgGachChan;
    public static Image[] imgGem;
    public static Image imgGrFull;
    public static Image imgHP_tm_do;
    public static Image imgHP_tm_trang;
    public static Image imgHP_tm_trang_big;
    public static Image imgHP_tm_vang;
    public static Image imgHP_tm_xam;
    public static Image imgHP_tm_xanh;
    public static Image imgHP_tm_xanh_big;
    public static Image imgHP_tm_xanh_big_nen;
    public static Image[] imgHe;
    public static Image[] imgHeIcon;
    public static Image[] imgHeIconPlayer;
    public static Image[] imgHeLost;
    public static Image[] imgHpMpSpInfoMe;
    public static Image[] imgIconHide;
    public static Image imgIconHideDetailChat;
    public static Image imgIconSearch;
    public static Image imgIconSend;
    public static Image imgIconShowDetailChat;
    public static Image imgImojiIcon;
    public static Image imgInvenIsMe;
    public static Image imgInvited;
    public static Image imgItemDefault;
    public static Image imgJoin;
    public static Image imgKB;
    public static Image imgLineBgParty;
    public static Image imgLineLogin;
    public static Image imgLineParty;
    public static Image imgLineUpgrade;
    public static Image imgLogoGame;
    public static Image[] imgLostInfoMe;
    public static Image imgMale;
    public static Image imgMapBlock;
    public static Image imgMapCanJoin;
    public static Image imgMapTaoSong;
    public static Image imgMay;
    public static Image imgMay1;
    public static Image imgMay2;
    public static Image imgMessage;
    public static Image imgMiniMapBlue;
    public static Image imgMiniMapGreen;
    public static Image imgMiniMapRed;
    public static Image[] imgMoney;
    public static Image imgMuiTen;
    public static Image imgMuitenphai;
    public static Image imgMuitentrai;
    public static Image[] imgNamNu;
    public static Image imgNenChar;
    public static Image imgNenCharNone;
    public static Image imgNenCharPlus;
    public static Image imgNenCloseOpen;
    public static Image imgNenTroi;
    public static Image imgNewMsg;
    public static Image[] imgNextPreviousNPCTalk = new Image[3];
    public static Image imgNhac;
    public static Image imgOKhoa;
    public static FrameImage imgOpenChatBox;
    public static Image imgOpenNenParty;
    public static Image imgOpenOption;
    public static Image imgOpenOptionBg;
    public static Image imgPlus;
    public static Image imgPlusParty;
    public static Image imgPointer;
    public static Image[] imgPointerFindMap = new Image[8];
    public static Image imgPopup;
    public static Image imgQuestion;
    public static Image imgRemove;
    public static Image imgSKillDefault;
    public static Image imgSao;
    public static Image imgSaoDen;
    public static Image imgSelect;
    public static Image imgShuriken;
    public static Image imgStarUpgrade;
    public static Image[][] imgSubBgs;
    public static Image imgTest;
    public static Image imgTest2;
    public static Image imgThaThu;
    public static Image imgTitleMission;
    public static Image imgTruotAmThanh;
    public static Image imgUpUpgrade;
    public static Image imgXoa;
    public static Image img_ld_xanh_big;
    public static Image img_ld_xanh_big_nen;
    public static FrameImage khungSkill;
    public static FrameImage khungTenSkill;
    public static FrameImage lvInfo;
    public static FrameImage lvSkillUnlock;
    public static FrameImage[] myButtonSmalls;
    public static FrameImage[] myButtons;
    public static FrameImage[] mySkillButton;
    public static FrameImage[] mySkillButtonOld;
    public static Image ngoiSaoL;
    public static Image ngoiSaoR;
    public static int numFrameHpMpSp = 1;
    public static FrameImage skillIcon;
    public static Image tagNameInteract;
    public static Vector<FileData> vAssetsWaitings = new Vector<>();
    public static Vector<FileData> vSaveRMSDelay = new Vector<>();

    static {
        Class<Image> cls = Image.class;
        imgBgs = (Image[][]) Array.newInstance(cls, new int[]{10, 10});
        Class<FrameImage> cls2 = FrameImage.class;
        frameBGs = (FrameImage[][]) Array.newInstance(cls2, new int[]{10, 10});
        imgSubBgs = (Image[][]) Array.newInstance(cls, new int[]{10, 10});
        frameSubBGs = (FrameImage[][]) Array.newInstance(cls2, new int[]{10, 10});
    }

    public static FileData findBigImageWaiting(short idIcon, short type) {
        Iterator<FileData> it = vAssetsWaitings.iterator();
        while (it.hasNext()) {
            FileData fd = it.next();
            if (fd.idIcon == idIcon && fd.type == type) {
                return fd;
            }
        }
        return null;
    }

    public static void initData() {
        for (int i = 0; i < dataParts.length; i++) {
            dataEffects[i] = new DataImage();
            dataParts[i] = new DataImage();
        }
    }

    public static void initFrame() {
        frameTelePort = new FrameImage[2];
        int i = 0;
        while (true) {
            int i2 = 4;
            if (i < 2) {
                FrameImage[] frameImageArr = frameTelePort;
                Image createImage = Image.createImage("/tile/wayPoint" + i + ".png");
                if (i == 0) {
                    i2 = 8;
                }
                frameImageArr[i] = new FrameImage(createImage, i2);
                i++;
            } else {
                frameWaiting = new FrameImage(Image.createImage("/normal/frame/waiting.png"), 4);
                frameShurikenEff = new FrameImage(Image.createImage("/normal/frame/shuriken.png"), 4);
                return;
            }
        }
    }

    public static void initSkillButton() {
        mySkillButton = new FrameImage[8];
        for (int i = 0; i < mySkillButton.length; i++) {
            Image img = Image.createImage("/normal/skill/" + i + ".png");
            if (img != null) {
                mySkillButton[i] = new FrameImage(img, 2);
            }
        }
        mySkillButtonOld = new FrameImage[8];
        for (int i2 = 0; i2 < mySkillButtonOld.length; i2++) {
            Image img2 = Image.createImage("/normal/skill/old/" + i2 + ".png");
            if (img2 != null) {
                mySkillButtonOld[i2] = new FrameImage(img2, 2);
            }
        }
        coolDownSkill = Image.createImage("/normal/skill/cooldown.png");
        coolDownSkillBig = Image.createImage("/normal/skill/cooldown_big.png");
    }

    public static void initButton() {
        myButtons = new FrameImage[5];
        myButtonSmalls = new FrameImage[2];
        for (int i = 0; i < myButtons.length; i++) {
            myButtons[i] = new FrameImage(Image.createImage("/normal/button/bt" + i + ".png"), 2);
        }
        frameCheckBox = new FrameImage(Image.createImage("/normal/radio_checkbox/checkBox.png"), 2);
        frameRadio = new FrameImage(Image.createImage("/normal/radio_checkbox/radio.png"), 2);
        frameInputTf = new FrameImage(Image.createImage("/normal/radio_checkbox/inputTf.png"), 2);
        myButtonSmalls = new FrameImage[2];
        for (int i2 = 0; i2 < myButtonSmalls.length; i2++) {
            myButtonSmalls[i2] = new FrameImage(Image.createImage("/normal/button/inventory/bt" + i2 + ".png"), 2);
        }
    }

    public static void initLogin() {
        imgLineLogin = Image.createImage("/normal/login/line.png");
        frameBgOAuth2 = new FrameImage(Image.createImage("/normal/login/bg-oauth2.png"), 2);
        frameIconApple = new FrameImage(Image.createImage("/normal/login/icon-apple.png"), 2);
        frameIconGoogle = new FrameImage(Image.createImage("/normal/login/icon-gg.png"), 2);
        frameBgOrange0 = new FrameImage(Image.createImage("/normal/login/frame1.png"), 30.0f, 30.0f);
        frameBgCam = new FrameImage(Image.createImage("/normal/login/frame_cam.png"), 8);
        frameBgWhite1 = new FrameImage(Image.createImage("/normal/login/frame2.png"), 30.0f, 30.0f);
        frameInput = new FrameImage(Image.createImage("/normal/login/input.png"), 10.0f, 30.0f);
        frameTitle = new FrameImage(Image.createImage("/normal/login/title.png"), 30.0f, 27.0f);
        framePrevious = new FrameImage(Image.createImage("/normal/login/previous.png"), 1);
    }

    public static void initSetting() {
        imgAmLuong = Image.createImage("/normal/setting/amluong.png");
        imgCauHinh = Image.createImage("/normal/setting/cauhinh.png");
        imgClose = Image.createImage("/normal/setting/dong.png");
        imgDong = Image.createImage("/normal/setting/dong2.png");
        imgDongSE = Image.createImage("/normal/setting/dong2_se.png");
        imgNhac = Image.createImage("/normal/setting/nhac.png");
        imgTruotAmThanh = Image.createImage("/normal/setting/nut_chinh_amthanh.png");
        frameAmThanh = new FrameImage(Image.createImage("/normal/setting/amthanh.png"), 25.0f, 12.0f);
    }

    public static void initChooseChar() {
        imgCanhVat = Image.createImage("/normal/bg/login/1.png");
        imgNenTroi = Image.createImage("/normal/bg/login/2.png");
        imgMay1 = Image.createImage("/normal/bg/login/3.png");
        imgMay2 = Image.createImage("/normal/bg/login/4.png");
        frame3 = new FrameImage(Image.createImage("/normal/frame/frame3.png"), 20.0f, 20.0f);
        frameThanhCuonTruc = new FrameImage(Image.createImage("/normal/frame/thanhngang.png"), 15.0f, 11.0f);
        frameNameNhanVat = new FrameImage(Image.createImage("/normal/frame/nameNv.png"), 2);
        imgDatCreateChar = Image.createImage("/normal/createchar/datchonnhanvat.png");
        frameNenHe = new FrameImage(Image.createImage("/normal/createchar/nen.png"), 43.0f, 42.0f);
        imgBgChar = Image.createImage("/normal/createchar/bgChar.png");
        imgGachChan = Image.createImage("/normal/createchar/gachChan.png");
        imgMuiTen = Image.createImage("/normal/createchar/muiTen.png");
        iconNam = new FrameImage(Image.createImage("/normal/createchar/nam.png"), 2);
        iconNu = new FrameImage(Image.createImage("/normal/createchar/nu.png"), 2);
        frameNenHes = new FrameImage[5];
        int i = 0;
        while (true) {
            FrameImage[] frameImageArr = frameNenHes;
            if (i < frameImageArr.length) {
                frameImageArr[i] = new FrameImage(Image.createImage("/normal/createchar/" + (i + 1) + ".png"), 2);
                i++;
            } else {
                frameEffectChooseChar = new FrameImage(Image.createImage("/normal/frame/selected.png"), 13);
                imgJoin = Image.createImage("/normal/pointer/join.png");
                imgBack = Image.createImage("/normal/pointer/back.png");
                imgCucDat = Image.createImage("/normal/object/cucdat.png");
                FrameImage frameImage = new FrameImage(Image.createImage("/normal/frame/frame0.png"), 30.0f, 30.0f);
                frameBgOrange2_0 = frameImage;
                frameImage.yMoreFrameMore = 8;
                frameImage.xMoreFrameMore = 8;
                FrameImage frameImage2 = new FrameImage(Image.createImage("/normal/frame/frame1.png"), 10.0f, 10.0f);
                frameChatBox = frameImage2;
                frameImage2.yMoreFrameMore = 8;
                frameImage2.xMoreFrameMore = 8;
                frameChooseChar = new FrameImage(Image.createImage("/normal/login/nen.png"), 2);
                frameChooseTag = new FrameImage(Image.createImage("/normal/frame/choose1.png"), 14.0f, 24.0f);
                frameInput1 = new FrameImage(Image.createImage("/normal/frame/input1.png"), 30.0f, 30.0f);
                frameTagText = new FrameImage(Image.createImage("/normal/frame/tocquanao.png"), 2);
                frameVachDoc = new FrameImage(Image.createImage("/normal/frame/vach3.png"), 1.0f, 81.0f);
                return;
            }
        }
    }

    public static void initDataInfoMe() {
        imgIconHide = new Image[16];
        int i = 0;
        while (true) {
            Image[] imageArr = imgIconHide;
            if (i >= imageArr.length) {
                break;
            }
            imageArr[i] = Image.createImage("/normal/inventory/item_hide/" + i + ".png");
            i++;
        }
        frameIconTagNameInventory = new FrameImage[5];
        for (int i2 = 0; i2 < frameIconTagNameInventory.length; i2++) {
            Image img = Image.createImage("/normal/inventory/iconTag/" + i2 + ".png");
            if (img != null) {
                frameIconTagNameInventory[i2] = new FrameImage(img, 2);
            }
        }
        Image[] imageArr2 = new Image[2];
        imgNamNu = imageArr2;
        imageArr2[0] = Image.createImage("/normal/inventory/nam.png");
        imgNamNu[1] = Image.createImage("/normal/inventory/nu.png");
        imgSao = Image.createImage("/normal/inventory/sao.png");
        imgSaoDen = Image.createImage("/normal/inventory/sao_den.png");
        Image[] imageArr3 = new Image[3];
        imgBgMoney = imageArr3;
        imageArr3[0] = Image.createImage("/normal/inventory/money/bg-vang.png");
        imgBgMoney[1] = Image.createImage("/normal/inventory/money/bg-ngoc.png");
        imgBgMoney[2] = Image.createImage("/normal/inventory/money/bg-ngockhoa.png");
        Image[] imageArr4 = new Image[3];
        imgMoney = imageArr4;
        imageArr4[0] = Image.createImage("/normal/inventory/money/vang.png");
        imgMoney[1] = Image.createImage("/normal/inventory/money/ngoc.png");
        imgMoney[2] = Image.createImage("/normal/inventory/money/ngockhoa.png");
        imgItemDefault = Image.createImage("/item/-1.png");
        imgCucSat = Image.createImage("/item/cucsat.png");
        imgChamHoi = Image.createImage("/item/chamhoi.png");
        imgShuriken = Image.createImage("/item/shuriken.png");
        frameCucSat = new FrameImage(imgCucSat, 1);
        frameIconTabHanhTrangs = new FrameImage[3];
        for (int i3 = 0; i3 < frameIconTabHanhTrangs.length; i3++) {
            frameIconTabHanhTrangs[i3] = new FrameImage(Image.createImage("/normal/inventory/iconTab" + i3 + ".png"), 2);
        }
        frameIconTabFriend = new FrameImage[2];
        for (int i4 = 0; i4 < frameIconTabFriend.length; i4++) {
            frameIconTabFriend[i4] = new FrameImage(Image.createImage("/normal/friend/iconTab" + i4 + ".png"), 2);
        }
        FrameImage frameImage = new FrameImage(Image.createImage("/normal/frame/frame2.png"), 20.0f, 20.0f);
        frameXam2 = frameImage;
        frameImage.yMoreFrameMore = 8;
        frameImage.xMoreFrameMore = 8;
        FrameImage frameImage2 = new FrameImage(Image.createImage("/normal/frame/frame6.png"), 20.0f, 20.0f);
        frameXam3 = frameImage2;
        frameXam2.yMoreFrameMore = 8;
        frameImage2.xMoreFrameMore = 8;
        frameInfo = new FrameImage(Image.createImage("/normal/inventory/frameInfo.png"), 1);
        frameInfoTitle = new FrameImage(Image.createImage("/normal/inventory/charInfo/infoTitle.png"), 1);
        frameLineNgang = new FrameImage(Image.createImage("/normal/inventory/charInfo/lineNgang.png"), 1);
        ngoiSaoL = Image.createImage("/normal/inventory/charInfo/ngoiSaoL.png");
        ngoiSaoR = Image.createImage("/normal/inventory/charInfo/ngoiSaoR.png");
        frameCongTN = new FrameImage(Image.createImage("/normal/inventory/cong.png"), 2);
        frameTruTN = new FrameImage(Image.createImage("/normal/inventory/tru.png"), 2);
        Image imgTemp = Image.createImage("/normal/inventory/box.png");
        frameBoxUsed = new FrameImage(imgTemp, (float) imgTemp.getRWidth(), (float) (imgTemp.getRHeight() / 2));
        imgInvenIsMe = Image.createImage("/normal/inventory/is_me.png");
        frameBoxHanhTrang = new FrameImage[5];
        for (int i5 = 0; i5 < frameBoxHanhTrang.length; i5++) {
            Image imgTemp2 = Image.createImage("/normal/inventory/box/" + i5 + ".png");
            frameBoxHanhTrang[i5] = new FrameImage(imgTemp2, (float) imgTemp2.getRWidth(), (float) (imgTemp2.getRHeight() / 2));
        }
        frameBoxHanhTrangDefault = new FrameImage(Image.createImage("/normal/inventory/box/-1.png"), 2);
        frameBoxHanhTrangAdd = new FrameImage(Image.createImage("/normal/inventory/box/-2.png"), 2);
        frameBoxHanhTrangLocked = new FrameImage(Image.createImage("/normal/inventory/box/-3.png"), 2);
        Image imgTemp3 = Image.createImage("/normal/inventory/title.png");
        frameTagTitleHanhTrang = new FrameImage(imgTemp3, (float) imgTemp3.getRWidth(), (float) (imgTemp3.getRHeight() / 2));
        Image imgTemp4 = Image.createImage("/normal/inventory/frametab.png");
        frameTabIconHanhTrang = new FrameImage(imgTemp4, (float) imgTemp4.getRWidth(), (float) (imgTemp4.getRHeight() / 2));
        FrameImage frameImage3 = new FrameImage(Image.createImage("/normal/frame/frameInfo.png"), 8);
        frameBgInfo = frameImage3;
        frameImage3.yMoreFrameMore = 5;
        frameImage3.xMoreFrameMore = 5;
        imgDotInfo = Image.createImage("/normal/inventory/dot.png");
    }

    public static void initFriend() {
        imgMessage = Image.createImage("/normal/friend/gui_tn.png");
        imgRemove = Image.createImage("/normal/friend/xoa.png");
        imgAccept = Image.createImage("/normal/friend/v.png");
        imgDecline = Image.createImage("/normal/friend/x.png");
        imgKB = Image.createImage("/normal/friend/add.png");
    }

    public static void initInMapScr() {
        imgIconShowDetailChat = Image.createImage("/normal/pointer/showchat.png");
        imgIconHideDetailChat = Image.createImage("/normal/pointer/hidechat.png");
        imgIconSend = Image.createImage("/normal/pointer/iconsend.png");
        imgPopup = Image.createImage("/normal/pointer/popupNext.png");
        frameChat4 = new FrameImage(Image.createImage("/normal/frame/framechat4.png"), 10.0f, 10.0f);
        frameInputChat1 = new FrameImage(Image.createImage("/normal/frame/inputchat.png"), 12.0f, 20.0f);
        frameInputChat2 = new FrameImage(Image.createImage("/normal/frame/inputchat_2.png"), 20.0f, 13.0f);
    }

    public static void initTutorialScr() {
        frameTron = new FrameImage(Image.createImage("/normal/tutorial/frameTron.png"), 1);
        FrameImage frameImage = new FrameImage(Image.createImage("/normal/tutorial/frameVuong.png"), 30.0f, 30.0f);
        frameVuong = frameImage;
        frameImage.yMoreFrameMore = 8;
        frameImage.xMoreFrameMore = 8;
    }

    public static void initChatBox() {
        frameUserNameTitle = new FrameImage(Image.createImage("/normal/chatbox/title.png"), 1);
        frameSendChat = new FrameImage(Image.createImage("/normal/chatbox/framesendchat.png"), 2);
        frameIconSend = new FrameImage(Image.createImage("/normal/chatbox/sendchat.png"), 2);
        frameTabChatBox = new FrameImage(Image.createImage("/normal/chatbox/tabchatbox.png"), 2);
        frameChatBoxInput = new FrameImage(Image.createImage("/normal/chatbox/inputchat.png"), 2);
        imgCloseChatBox = new FrameImage(Image.createImage("/normal/chatbox/close.png"), 2);
        imgOpenChatBox = new FrameImage(Image.createImage("/normal/chatbox/open.png"), 2);
        frameUnread = new FrameImage(Image.createImage("/normal/chatbox/unread.png"), 2);
        frameChatMsg = new FrameImage(Image.createImage("/normal/chatbox/framechatmsg.png"), 6.0f, 6.0f);
        imgMuitentrai = Image.createImage("/normal/chatbox/mttrai.png");
        imgMuitenphai = Image.createImage("/normal/chatbox/mtphai.png");
        imgNewMsg = Image.createImage("/normal/chatbox/newMsg.png");
        frameChatRieng = new FrameImage(Image.createImage("/normal/chatbox/framechatrieng.png"), 2);
        imgAdd = Image.createImage("/normal/chatbox/kb.png");
        imgXoa = Image.createImage("/normal/chatbox/xoa.png");
        frameTypeChat = new FrameImage[6];
        for (int i = 0; i < 6; i++) {
            FrameImage[] frameImageArr = frameTypeChat;
            frameImageArr[i] = new FrameImage(Image.createImage("/normal/chatbox/" + i + ".png"), 1);
        }
    }

    public static void initMission() {
        imgTitleMission = Image.createImage("/normal/mission/1.png");
        Image imgTemp = Image.createImage("/normal/mission/2.png");
        frameMission = new FrameImage(imgTemp, (float) imgTemp.getRWidth(), (float) (imgTemp.getRHeight() / 8));
        frameBgMission = new FrameImage(Image.createImage("/normal/mission/tagBg.png"), 2);
        frameBgMission2 = new FrameImage(Image.createImage("/normal/mission/tagBg2.png"), 2);
        Image imgTemp2 = Image.createImage("/normal/mission/bgGift.png");
        frameBgGift = new FrameImage(imgTemp2, (float) (imgTemp2.getRWidth() / 2), (float) imgTemp2.getRHeight());
    }

    public static void initPurchase() {
        frameBgGem = new FrameImage(Image.createImage("/normal/purchase/bgGem.png"), 1);
        frameCencored = new FrameImage[6];
        for (int i = 0; i < 6; i++) {
            FrameImage[] frameImageArr = frameCencored;
            frameImageArr[i] = new FrameImage(Image.createImage("/normal/purchase/" + i + ".png"), 1);
        }
        imgGem = new Image[6];
        for (int i2 = 0; i2 < 6; i2++) {
            imgGem[i2] = Image.createImage("/normal/purchase/gem" + i2 + ".png");
        }
        frameGiaTien = new FrameImage(Image.createImage("/normal/purchase/giaTien.png"), 2);
    }

    public static void initZoneStatus() {
        FrameImage[] frameImageArr = new FrameImage[4];
        frameZoneStatus = frameImageArr;
        frameImageArr[0] = new FrameImage(Image.createImage("/normal/zone/0.png"), 2);
        frameZoneStatus[1] = new FrameImage(Image.createImage("/normal/zone/1.png"), 2);
        frameZoneStatus[3] = new FrameImage(Image.createImage("/normal/zone/3.png"), 2);
    }

    public static void initLearnSkill() {
        imgBgIconSkill = Image.createImage("/normal/learnskill/bgIconSkill.png");
        imgBgIconSkillBd = Image.createImage("/normal/learnskill/bgIconSkill_bd.png");
        imgSKillDefault = Image.createImage("/normal/learnskill/skillDefault.png");
        frameIconTabLearnSkill = new FrameImage[5];
        for (int i = 0; i < frameIconTabLearnSkill.length; i++) {
            Image imgTemp = Image.createImage("/normal/learnskill/iconTab" + i + ".png");
            if (imgTemp != null) {
                frameIconTabLearnSkill[i] = new FrameImage(imgTemp, 2);
            }
        }
        skillIcon = new FrameImage(Image.createImage("/normal/learnskill/skill_icon.png"), 2);
        khungSkill = new FrameImage(Image.createImage("/normal/learnskill/skill_frame.png"), 2);
        lvSkillUnlock = new FrameImage(Image.createImage("/normal/learnskill/lv_frame.png"), 2);
        khungTenSkill = new FrameImage(Image.createImage("/normal/learnskill/skill_name_frame.png"), 1);
        lvInfo = new FrameImage(Image.createImage("/normal/learnskill/lv_info.png"), 1);
        imgOKhoa = Image.createImage("/normal/learnskill/lvLocked.png");
        imgCensored = Image.createImage("/normal/learnskill/censored.png");
    }

    public static void initBackGround() {
        for (int i = 0; i < imgBgs.length; i++) {
            for (int k = 0; k < imgBgs[i].length; k++) {
                Image imgBg = Image.createImage("/normal/bg/" + i + "/" + (k + 1) + ".png");
                if (imgBg != null) {
                    imgBgs[i][k] = imgBg;
                    frameBGs[i][k] = new FrameImage(imgBg, (float) imgBg.getRWidth(), (float) (imgBg.getRHeight() / 2));
                }
                Image imgBg2 = Image.createImage("/normal/bg/" + i + "/sub/" + (k + 1) + ".png");
                if (imgBg2 != null) {
                    imgSubBgs[i][k] = imgBg2;
                    frameSubBGs[i][k] = new FrameImage(imgBg2, (float) imgBg2.getRWidth(), (float) (imgBg2.getRHeight() / 2));
                }
            }
        }
        imgMay = Image.createImage("/normal/bg/5/0.png");
    }

    public static void initNPC() {
        int numTypeStatusFrame;
        for (int i = 0; i < frame_npc_template.length; i++) {
            Image imgTemp = Image.createImage("/npc/" + i + ".png");
            if (imgTemp != null) {
                frame_npc_template[i] = new FrameImage(imgTemp, 5);
            }
        }
        for (int i2 = 0; i2 < frameTypeNPCs.length; i2++) {
            Image imgTemp2 = Image.createImage("/npc/status/" + i2 + ".png");
            if (imgTemp2 != null) {
                if (i2 == 5) {
                    numTypeStatusFrame = 4;
                } else {
                    numTypeStatusFrame = 1;
                }
                frameTypeNPCs[i2] = new FrameImage(imgTemp2, numTypeStatusFrame);
            }
        }
    }

    public static void initNews() {
        frameTitle2 = new FrameImage(Image.createImage("/normal/login/title.png"), 30.0f, 27.0f);
        Image imgTemp = Image.createImage("/normal/inventory/frametab.png");
        frameTabNameNews = new FrameImage(imgTemp, (float) imgTemp.getRWidth(), (float) (imgTemp.getRHeight() / 2));
        Image imgTemp2 = Image.createImage("/normal/frame/framechat4.png");
        if (imgTemp2 != null) {
            frameBgWhite4 = new FrameImage(imgTemp2, (float) imgTemp2.getRWidth(), (float) (imgTemp2.getRHeight() / 8));
        }
    }

    public static void initOther() {
        imgDapDat = new FrameImage(Image.createImage("/effectChar/img/dapdat.png"), 5);
        conCho = new FrameImage(Image.createImage("/normal/mascot/pakkun.png"), 16);
        imgLogoGame = Image.createImage("/normal/other/logo-game.png");
        img18Plus = Image.createImage("/normal/18plus.png");
        frameLvUp = new FrameImage(Image.createImage("/normal/frame/lvUp.png"), 15);
        imgFlare = Image.createImage("/normal/other/flare.png");
        imgFlare2 = Image.createImage("/normal/other/flare2.png");
        tagNameInteract = Image.createImage("/normal/frame/tagNameInteract.png");
        buffEffect = new FrameImage[6];
        int i = 0;
        while (true) {
            FrameImage[] frameImageArr = buffEffect;
            if (i >= frameImageArr.length) {
                break;
            }
            frameImageArr[i] = new FrameImage(Image.createImage("/normal/buff/" + i + ".png"), 1);
            i++;
        }
        coolDownBuffEffect = Image.createImage("/normal/buff/cooldown.png");
        framePlus = new FrameImage(Image.createImage("/normal/pointer/fplus.png"), 2);
        Image imgTemp = Image.createImage("/normal/frame/bg_focus_obj.png");
        frameBgFocusObj = new FrameImage(imgTemp, (float) (imgTemp.getRWidth() / 2), (float) imgTemp.getRHeight());
        imgBgPK = Image.createImage("/normal/pointer/pk.png");
        frameIconPk = new FrameImage(Image.createImage("/normal/icon/pk.png"), 5);
        frameIconAuto = new FrameImage(Image.createImage("/normal/icon/onoff.png"), 2);
        Image imgTemp2 = Image.createImage("/normal/frame/focusPK.png");
        frameFocusPk = new FrameImage(imgTemp2, (float) (imgTemp2.getRWidth() / 2), (float) imgTemp2.getRHeight());
        imgPointerFindMap[0] = Image.createImage("/normal/pointer/find/down.png");
        imgPointerFindMap[1] = Image.createImage("/normal/pointer/find/up.png");
        imgPointerFindMap[2] = Image.createImage("/normal/pointer/find/left.png");
        imgPointerFindMap[3] = Image.createImage("/normal/pointer/find/right.png");
        imgPointerFindMap[4] = Image.createImage("/normal/pointer/find/left_up.png");
        imgPointerFindMap[5] = Image.createImage("/normal/pointer/find/left_down.png");
        imgPointerFindMap[6] = Image.createImage("/normal/pointer/find/right_up.png");
        imgPointerFindMap[7] = Image.createImage("/normal/pointer/find/right_down.png");
        framePointerFindMap[0] = new FrameImage(imgPointerFindMap[0], 11);
        framePointerFindMap[1] = new FrameImage(imgPointerFindMap[1], 11);
        framePointerFindMap[2] = new FrameImage(imgPointerFindMap[2], 11);
        framePointerFindMap[3] = new FrameImage(imgPointerFindMap[3], 11);
        framePointerFindMap[4] = new FrameImage(imgPointerFindMap[4], 11);
        framePointerFindMap[5] = new FrameImage(imgPointerFindMap[5], 11);
        framePointerFindMap[6] = new FrameImage(imgPointerFindMap[6], 11);
        framePointerFindMap[7] = new FrameImage(imgPointerFindMap[7], 11);
        Image createImage = Image.createImage("/normal/pointer/pointer.png");
        imgPointer = createImage;
        framePointer = new FrameImage(createImage, 6);
        imgCircleTarget = Image.createImage("/normal/pointer/circleFocus.png");
        Image imgTemp3 = Image.createImage("/normal/icon/icon_pvp.png");
        frameIconPvP = new FrameImage(imgTemp3, imgTemp3.getRHeight() / 24);
        frameMenuPvp = new FrameImage(Image.createImage("/normal/frame/menu_tron.png"), 2);
        frameMinus = new FrameImage(Image.createImage("/normal/pointer/fminus.png"), 2);
        imgPlus = Image.createImage("/normal/pointer/plus.png");
        imgHP_tm_xanh_big = Image.createImage("/normal/other/hp/tm-xanh-big.png");
        imgHP_tm_xanh_big_nen = Image.createImage("/normal/other/hp/tm-xanh-big-nen.png");
        imgHP_tm_trang_big = Image.createImage("/normal/other/hp/tm-trang-big.png");
        img_ld_xanh_big_nen = Image.createImage("/normal/other/loading/ld-xanh-big-nen.png");
        img_ld_xanh_big = Image.createImage("/normal/other/loading/ld-xanh-big.png");
        imgFocus = Image.createImage("/normal/pointer/focus.png");
        imgFocus2 = Image.createImage("/normal/pointer/focus_2.png");
        frameShow = new FrameImage(Image.createImage("/normal/icon/show.png"), 2);
        frameCharDead = new FrameImage(Image.createImage("/normal/frame/char_dead.png"), 17);
        frameCharRevive = new FrameImage(Image.createImage("/normal/frame/char_revive.png"), 6);
        imgEffectMap = new Image[1];
        int i2 = 0;
        while (true) {
            Image[] imageArr = imgEffectMap;
            if (i2 >= imageArr.length) {
                break;
            }
            imageArr[i2] = Image.createImage("/normal/bg/eff/suong-mu.png");
            i2++;
        }
        FrameImage[] frameImageArr2 = new FrameImage[5];
        frameEffBg = frameImageArr2;
        frameImageArr2[0] = new FrameImage(Image.createImage("/normal/bg/eff/mua.png"), 4);
        frameEffBg[1] = new FrameImage(Image.createImage("/normal/bg/eff/mua-roi.png"), 3);
        frameEffBg[2] = new FrameImage(Image.createImage("/normal/bg/eff/mua-roi-2.png"), 2);
        frameEffBg[3] = new FrameImage(Image.createImage("/normal/bg/eff/mua-roi-3.png"), 4);
        frameEffBg[4] = new FrameImage(Image.createImage("/normal/bg/eff/sam-chop.png"), 8);
        frameHatMua = new FrameImage[3];
        for (int i3 = 0; i3 < 3; i3++) {
            FrameImage[] frameImageArr3 = frameHatMua;
            frameImageArr3[i3] = new FrameImage(Image.createImage("/normal/bg/eff/mua-" + (i3 + 1) + ".png"), 4);
        }
        frameLaRoi = new FrameImage(Image.createImage("/normal/bg/eff/la-roi.png"), 2);
        frameSkill = new FrameImage(Image.createImage("/skill/frame/0.png"), 4);
        frameQuest = new FrameImage(Image.createImage("/normal/icon/quest.png"), 2);
        frameBgParty = new FrameImage(Image.createImage("/normal/frame/frame_party.png"), 8);
        frameParty = new FrameImage(Image.createImage("/normal/icon/party.png"), 2);
        frameFriendList = new FrameImage(Image.createImage("/normal/icon/friend.png"), 2);
        frameLearnSkill = new FrameImage(Image.createImage("/normal/icon/skill.png"), 2);
        frameUpgrade = new FrameImage(Image.createImage("/normal/icon/upgrade.png"), 2);
        frameGacha = new FrameImage(Image.createImage("/normal/icon/vongquay.png"), 2);
        framePurchase = new FrameImage(Image.createImage("/normal/icon/purchase.png"), 2);
        frameWorldMap = new FrameImage(Image.createImage("/normal/icon/map.png"), 2);
        frameMessage = new FrameImage(Image.createImage("/normal/icon/msg.png"), 2);
        frameSetting = new FrameImage(Image.createImage("/normal/icon/setting.png"), 2);
        frameHide = new FrameImage(Image.createImage("/normal/icon/hide.png"), 2);
        frameInventory = new FrameImage(Image.createImage("/normal/icon/inventory.png"), 2);
        frameBgFunction = new FrameImage(Image.createImage("/normal/icon/bg.png"), 8);
        frameJump = new FrameImage(Image.createImage("/normal/frame/jump.png"), 8);
        frameMobHurt = new FrameImage(Image.createImage("/normal/frame/mob_hurt.png"), 4);
        frameChange = new FrameImage(Image.createImage("/normal/icon/change.png"), 2);
        frameAuto = new FrameImage(Image.createImage("/normal/icon/auto.png"), 2);
        imgImojiIcon = Image.createImage("/normal/icon/emoji.png");
        imgTest = Image.createImage("/normal/icon/test.png");
        imgTest2 = Image.createImage("/normal/icon/test2.png");
        imgChatDown = Image.createImage("/normal/pointer/down.png");
        imgChatUp = Image.createImage("/normal/pointer/up.png");
        imgChatUp2 = Image.createImage("/normal/pointer/up_2.png");
        imgMapBlock = Image.createImage("/normal/zone/khuBlock.png");
        imgMapCanJoin = Image.createImage("/normal/zone/khuOK.png");
        imgMapTaoSong = Image.createImage("/normal/zone/khuTaoSong.png");
        imgSelect = Image.createImage("/normal/zone/select.png");
        imgNextPreviousNPCTalk[0] = Image.createImage("/normal/pointer/next_2.png");
        imgNextPreviousNPCTalk[1] = Image.createImage("/normal/pointer/back_2.png");
        imgNextPreviousNPCTalk[2] = Image.createImage("/normal/pointer/next_3.png");
        frameTagNameNPC = new FrameImage(Image.createImage("/normal/frame/tagNameNpc.png"), 15.0f, 20.0f);
        imgHP_tm_do = Image.createImage("/normal/other/hp/tm-do.png");
        imgHP_tm_vang = Image.createImage("/normal/other/hp/tm-vang.png");
        imgHP_tm_xam = Image.createImage("/normal/other/hp/tm-xam.png");
        imgHP_tm_xanh = Image.createImage("/normal/other/hp/tm-xanh.png");
        imgHP_tm_trang = Image.createImage("/normal/other/hp/tm-trang.png");
    }

    public static void initMiniGame() {
        frameThanh2 = new FrameImage(Image.createImage("/normal/minigame/thanh2.png"), 2);
        frameVienBi = new FrameImage(Image.createImage("/normal/minigame/tron.png"), 3);
        frameGoal = new FrameImage(Image.createImage("/normal/minigame/den.png"), 3);
        frameEffNo = new FrameImage(Image.createImage("/normal/minigame/no.png"), 2);
        frameEffBungNo = new FrameImage(Image.createImage("/normal/minigame/bungno.png"), 9);
        imgCoGai = Image.createImage("/normal/minigame/cogai.png");
        frameTraiTim = new FrameImage(Image.createImage("/normal/minigame/traitim.png"), 2);
        frameNutBam = new FrameImage(Image.createImage("/normal/minigame/bam.png"), 2);
    }

    public static void initQuest() {
        imgQuestion = Image.createImage("/normal/icon/question.png");
    }

    public static void initInfoMe() {
        frameEffLvUp = new FrameImage(Image.createImage("/normal/frame/eff_lvup.png"), 7);
        imgHe = new Image[6];
        int i = 0;
        while (true) {
            Image[] imageArr = imgHe;
            if (i >= imageArr.length) {
                break;
            }
            imageArr[i] = Image.createImage("/normal/infome/he/" + i + ".png");
            i++;
        }
        imgHeIcon = new Image[6];
        int i2 = 0;
        while (true) {
            Image[] imageArr2 = imgHeIcon;
            if (i2 >= imageArr2.length) {
                break;
            }
            imageArr2[i2] = Image.createImage("/normal/infome/he/icon/" + i2 + ".png");
            i2++;
        }
        imgHeIconPlayer = new Image[6];
        int i3 = 0;
        while (true) {
            Image[] imageArr3 = imgHeIconPlayer;
            if (i3 >= imageArr3.length) {
                break;
            }
            imageArr3[i3] = Image.createImage("/normal/infome/he/hePlayer/" + i3 + ".png");
            i3++;
        }
        imgMale = Image.createImage("/normal/pointer/male.png");
        imgFemale = Image.createImage("/normal/pointer/female.png");
        imgHeLost = new Image[6];
        int i4 = 0;
        while (true) {
            Image[] imageArr4 = imgHeLost;
            if (i4 < imageArr4.length) {
                imageArr4[i4] = Image.createImage("/normal/infome/he/heLost/" + i4 + ".png");
                i4++;
            } else {
                imgBackgroundInfoMe = Image.createImage("/normal/infome/bg.png");
                Image[] imageArr5 = new Image[3];
                imgHpMpSpInfoMe = imageArr5;
                imageArr5[0] = Image.createImage("/normal/infome/do.png");
                imgHpMpSpInfoMe[1] = Image.createImage("/normal/infome/xanh.png");
                imgHpMpSpInfoMe[2] = Image.createImage("/normal/infome/vang.png");
                Image[] imageArr6 = new Image[3];
                imgLostInfoMe = imageArr6;
                imageArr6[0] = Image.createImage("/normal/infome/trang_do.png");
                imgLostInfoMe[1] = Image.createImage("/normal/infome/trang_xanh.png");
                imgLostInfoMe[2] = Image.createImage("/normal/infome/trang_vang.png");
                Image[] imageArr7 = new Image[3];
                effectLost = imageArr7;
                imageArr7[0] = Image.createImage("/normal/infome/ef_do.png");
                effectLost[1] = Image.createImage("/normal/infome/ef_xanh.png");
                effectLost[2] = Image.createImage("/normal/infome/ef_vang.png");
                return;
            }
        }
    }

    public static void initWareHouse() {
        framePage = new FrameImage(Image.createImage("/normal/button/next_previous/page.png"), 12.0f, 21.0f);
        frameNextPreviousKhoDo = new FrameImage[2];
        for (int i = 0; i < frameNextPreviousKhoDo.length; i++) {
            Image imgTemp = Image.createImage("/normal/button/next_previous/" + i + ".png");
            if (imgTemp != null) {
                frameNextPreviousKhoDo[i] = new FrameImage(imgTemp, 2);
            }
        }
    }

    public static void initShield() {
        frameShield = new FrameImage(Image.createImage("/effect/frame/shield/0.png"), 4);
    }

    public static void initMiniMap() {
        imgCloseMiniMap = Image.createImage("/normal/minimap/close.png");
        Image imgTemp = Image.createImage("/normal/frame/bg_mini_map.png");
        if (imgTemp != null) {
            frameBgMiniMap = new FrameImage(imgTemp, 8);
        }
        imgMiniMapGreen = Image.createImage("/normal/minimap/green.png");
        imgMiniMapBlue = Image.createImage("/normal/minimap/blue.png");
        imgMiniMapRed = Image.createImage("/normal/minimap/red.png");
        imgFocusMe = Image.createImage("/normal/minimap/focus_me.png");
        imgBGMinisize = Image.createImage("/normal/minimap/tagBgClose.png");
        imgCloseOptionBg = Image.createImage("/normal/minimap/close_option_bg.png");
        imgOpenOptionBg = Image.createImage("/normal/minimap/open_option_bg.png");
        imgOpenOption = Image.createImage("/normal/minimap/open_option.png");
        imgCloseOption = Image.createImage("/normal/minimap/close_option.png");
    }

    public static void initUpgrade() {
        Image imgTemp = Image.createImage("/normal/frame/text_appear.png");
        if (imgTemp != null) {
            frameBgTextAppear = new FrameImage(imgTemp, (float) (imgTemp.getRWidth() / 2), (float) imgTemp.getRHeight());
        }
        imgChangeBigUpgrade = Image.createImage("/normal/upgrade/change_big.png");
        imgChangeUpgrade = Image.createImage("/normal/upgrade/change.png");
        imgUpUpgrade = Image.createImage("/normal/upgrade/up.png");
        imgThaThu = Image.createImage("/normal/upgrade/tha_thu.png");
        imgBoxNeed = Image.createImage("/normal/upgrade/box_need.png");
        imgBoxNeedLost = Image.createImage("/normal/upgrade/box_need_lost.png");
        imgStarUpgrade = Image.createImage("/normal/upgrade/star.png");
        imgBgTagUpgrade = Image.createImage("/normal/upgrade/bgTag.png");
        imgLineUpgrade = Image.createImage("/normal/upgrade/line.png");
        Image imgTemp2 = Image.createImage("/normal/upgrade/tag_percent_success.png");
        if (imgTemp2 != null) {
            frameBGPercentSucess = new FrameImage(imgTemp2, (float) (imgTemp2.getRWidth() / 2), (float) imgTemp2.getRHeight());
        }
        Image imgTemp3 = Image.createImage("/normal/upgrade/eff_success.png");
        if (imgTemp3 != null) {
            frameEffectUpgradeSuccess = new FrameImage(imgTemp3, 18);
        }
        Image imgTemp4 = Image.createImage("/normal/upgrade/eff_sub_success.png");
        if (imgTemp4 != null) {
            frameSubEffectUpgradeSuccess = new FrameImage(imgTemp4, 4);
        }
        Image imgTemp5 = Image.createImage("/normal/upgrade/eff_success2.png");
        if (imgTemp5 != null) {
            frameEffectUpgradeSuccess2 = new FrameImage(imgTemp5, 3);
        }
        Image imgTemp6 = Image.createImage("/normal/upgrade/btnPlus.png");
        if (imgTemp6 != null) {
            frameBoxUpgrade = new FrameImage(imgTemp6, 2);
        }
        frameIconTabUpgrades = new FrameImage[4];
        for (int i = 0; i < frameIconTabUpgrades.length; i++) {
            frameIconTabUpgrades[i] = new FrameImage(Image.createImage("/normal/upgrade/iconTab" + i + ".png"), 2);
        }
        frameIconTabThoRen = new FrameImage[3];
        for (int i2 = 0; i2 < frameIconTabThoRen.length; i2++) {
            frameIconTabThoRen[i2] = new FrameImage(Image.createImage("/normal/blacksmith/iconTab" + i2 + ".png"), 2);
        }
        frameIconTabGacha = new FrameImage[2];
        for (int i3 = 0; i3 < frameIconTabGacha.length; i3++) {
            frameIconTabGacha[i3] = new FrameImage(Image.createImage("/normal/gacha/iconTab" + i3 + ".png"), 2);
        }
    }

    public static void initParty() {
        imgPlusParty = Image.createImage("/normal/party/plus_party.png");
        imgLineBgParty = Image.createImage("/normal/party/line_bg.png");
        imgNenCloseOpen = Image.createImage("/normal/party/bg_close_open.png");
        imgCloseNenParty = Image.createImage("/normal/party/img_close_party.png");
        imgOpenNenParty = Image.createImage("/normal/party/img_open_party.png");
        imgLineParty = Image.createImage("/normal/party/line.png");
        imgLineParty = imgDong;
        imgCloseParty = Image.createImage("/normal/party/close.png");
        imgFlagLeader = Image.createImage("/normal/party/flag.png");
        imgFlagLeaderMap = Image.createImage("/normal/party/flag_map.png");
        imgNenCharNone = Image.createImage("/normal/party/bg_char_none.png");
        imgNenChar = Image.createImage("/normal/party/bg_char.png");
        imgNenCharPlus = Image.createImage("/normal/party/bg_char_plus.png");
        frameBgTag = new FrameImage(Image.createImage("/normal/party/bgTag.png"), 10.0f, 41.0f);
        Image imgTemp = Image.createImage("/normal/party/input.png");
        framePartySearch = new FrameImage(imgTemp, imgTemp.getWidth() / 2.0f, imgTemp.getHeight() / 2.0f);
        frameFlagLeaderMap = new FrameImage(Image.createImage("/normal/party/flag_frame.png"), 5);
        imgIconSearch = Image.createImage("/normal/party/icon_search.png");
        imgInvited = Image.createImage("/normal/party/invited.png");
        imgGrFull = Image.createImage("/normal/party/gr_full.png");
        imgApplied = Image.createImage("/normal/party/applied.png");
        Image imgTemp2 = Image.createImage("/normal/party/bg_selected.png");
        frameSelected = new FrameImage(imgTemp2, imgTemp2.getWidth() / 2.0f, imgTemp2.getHeight());
        frameIconTabPartys = new FrameImage[3];
        for (int i = 0; i < frameIconTabPartys.length; i++) {
            frameIconTabPartys[i] = new FrameImage(Image.createImage("/normal/party/iconTab" + i + ".png"), 2);
        }
    }

    public static void init() {
        initData();
        initButton();
        initLogin();
        initSetting();
        initSkillButton();
        initFrame();
        initChooseChar();
        initDataInfoMe();
        initFriend();
        initInMapScr();
        initChatBox();
        initMission();
        initTutorialScr();
        initLearnSkill();
        initZoneStatus();
        initPurchase();
        initBackGround();
        initNPC();
        initNews();
        initMiniGame();
        initOther();
        initQuest();
        initInfoMe();
        initWareHouse();
        initShield();
        initParty();
        initMiniMap();
        initUpgrade();
    }

    public static boolean saveRMSDelay() {
        while (vSaveRMSDelay.size() > 0) {
            for (int i = vSaveRMSDelay.size() - 1; i >= 0; i--) {
                FileData fd = vSaveRMSDelay.elementAt(i);
                Rms.saveRMSDataFromServer(fd.path, fd.data);
                vSaveRMSDelay.remove(i);
            }
        }
        if (vSaveRMSDelay.size() < 1) {
            return true;
        }
        return false;
    }

    public static void splitFrameImage() {
        File fCheck;
        File fCheck2;
        int numMob = 28;
        String pathSave = "F:\\workspaceNaruto\\NinjaWar-Client\\android\\assets\\input\\tool\\";
        try {
            File fCheck3 = new File(pathSave);
            if (!fCheck3.exists()) {
                fCheck3.mkdir();
            }
            String pathSave2 = pathSave + "mob\\";
            String pathImg = "F:\\workspaceNaruto\\NinjaWar-Client\\android\\assets\\input\\mob";
            File fCheck4 = new File(pathSave2);
            if (!fCheck4.exists()) {
                fCheck4.mkdir();
            }
            int i = 0;
            while (i < numMob) {
                int idIcon = i;
                BufferedImage source = ImageIO.read(new File(pathImg + (idIcon + ".png")));
                int idx = 0;
                int numMob2 = numMob;
                Image imgTemp = Image.createImage((short) idIcon, (short) 5);
                QuaiTemplate template = MobTemplates.get(idIcon);
                int step = imgTemp.getRHeight() / template.numFrame;
                int y = 0;
                while (true) {
                    fCheck2 = fCheck4;
                    if (y >= source.getHeight()) {
                        break;
                    }
                    ImageIO.write(source.getSubimage(0, y, source.getWidth(), step), "png", new File(pathSave2 + idIcon + "" + idx + ".png"));
                    y += step;
                    idx++;
                }
                i++;
                numMob = numMob2;
                fCheck4 = fCheck2;
            }
            Res.outz(1, "Ảnh quái được lưu tại:" + pathSave2);
        } catch (Exception e) {
            Res.outz(1, "Thất bại xem lại đường dẫn ở class LoadDataManager hàm testSomeThing");
            e.printStackTrace();
        }
        int num = 22;
        String pathImg2 = "F:\\workspaceNaruto\\NinjaWar-Client\\android\\assets\\input\\";
        try {
            File fCheck5 = new File("F:\\workspaceNaruto\\NinjaWar-Client\\android\\assets\\input\\tool\\");
            if (!fCheck5.exists()) {
                fCheck5.mkdir();
            }
            File fCheck6 = new File("F:\\workspaceNaruto\\NinjaWar-Client\\android\\assets\\input\\tool\\" + "npc");
            if (!fCheck6.exists()) {
                fCheck6.mkdir();
            }
            int i2 = 0;
            while (i2 < num) {
                int idIcon2 = i2;
                String path = idIcon2 + ".png";
                BufferedImage source2 = ImageIO.read(new File(pathImg2 + "npc" + "\\" + path));
                int idx2 = 0;
                int num2 = num;
                Image imgTemp2 = Image.createImage((short) idIcon2, (short) 8);
                TemplateNPC template2 = NPCHashTables.get(idIcon2);
                int step2 = imgTemp2.getRHeight() / template2.numFrame;
                String pathImg3 = pathImg2;
                int y2 = 0;
                while (true) {
                    fCheck = fCheck6;
                    if (y2 >= source2.getHeight()) {
                        break;
                    }
                    String folder = "F:\\workspaceNaruto\\NinjaWar-Client\\android\\assets\\input\\tool\\" + "npc";
                    String path2 = path;
                    File folderCheck = new File(folder);
                    if (!folderCheck.exists()) {
                        folderCheck.mkdir();
                    }
                    File file2 = folderCheck;
                    ImageIO.write(source2.getSubimage(0, y2, source2.getWidth(), step2), "png", new File(folder + "\\" + idIcon2 + "" + idx2 + ".png"));
                    y2 += step2;
                    fCheck6 = fCheck;
                    path = path2;
                    template2 = template2;
                    source2 = source2;
                    idx2++;
                }
                BufferedImage bufferedImage = source2;
                TemplateNPC templateNPC = template2;
                i2++;
                num = num2;
                pathImg2 = pathImg3;
                fCheck6 = fCheck;
            }
            String str3 = pathImg2;
            File file3 = fCheck6;
            Res.outz(1, "Ảnh npc được lưu tại:" + "F:\\workspaceNaruto\\NinjaWar-Client\\android\\assets\\input\\tool\\" + "npc");
        } catch (Exception e2) {
            Res.outz(1, "Thất bại xem lại đường dẫn ở class LoadDataManager hàm testSomeThing");
            e2.printStackTrace();
        }
    }
}
