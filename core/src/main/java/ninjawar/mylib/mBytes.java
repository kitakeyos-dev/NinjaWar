package ninjawar.mylib;

import ninjawar.myscr.Res;

public class mBytes {
    public static byte[] createData(short idData, short type) {
        String path = FileData.getPathFromIdAndType(idData, type);
        byte[] result = Res.loadDataFromFile(path, (byte) 1);
        if (result == null) {
            return createBytesFromRms(path);
        }
        return result;
    }

    public static byte[] createBytesFromRms(String url) {
        try {
            return Rms.loadRMSDataFromServer(url);
        } catch (Exception e) {
            Res.outz("ảnh từ RMS null:" + url);
            return null;
        }
    }
}
