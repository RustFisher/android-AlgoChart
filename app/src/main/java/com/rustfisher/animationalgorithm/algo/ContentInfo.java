package com.rustfisher.animationalgorithm.algo;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 存储字符串或者字符串的资源id
 */
public class ContentInfo implements Serializable {
    private static int infoNum = 0;
    public int cNum; // 这一条记录的序号
    public boolean hasId = false;
    public boolean hasString = false;
    public int textId;
    public String contentString;

    public ContentInfo(String text) {
        hasString = true;
        contentString = text;
        cNum = infoNum++;
    }

    public ContentInfo(int id) {
        hasId = true;
        textId = id;
        cNum = infoNum++;
    }

    private static Comparator<ContentInfo> mNumberComparator = new Comparator<ContentInfo>() {
        @Override
        public int compare(ContentInfo o1, ContentInfo o2) {
            return o1.cNum - o2.cNum;
        }
    };

    public static void sortByNumber(List<ContentInfo> kInfoList) {
        Collections.sort(kInfoList, mNumberComparator);
    }

}
