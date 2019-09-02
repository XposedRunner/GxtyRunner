package com.example.gita.gxty.weiget.sortme;

import java.util.Comparator;

/* compiled from: PinyinComparator */
public class c implements Comparator<b> {
    public /* synthetic */ int compare(Object obj, Object obj2) {
        return a((b) obj, (b) obj2);
    }

    public int a(b bVar, b bVar2) {
        if (bVar.getSortLetters().equals("@") || bVar2.getSortLetters().equals("#")) {
            return -1;
        }
        if (bVar.getSortLetters().equals("#") || bVar2.getSortLetters().equals("@")) {
            return 1;
        }
        return bVar.getSortLetters().compareTo(bVar2.getSortLetters());
    }
}
